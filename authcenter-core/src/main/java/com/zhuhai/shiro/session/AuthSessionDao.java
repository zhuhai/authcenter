package com.zhuhai.shiro.session;

import com.zhuhai.common.constant.AuthConstant;
import com.zhuhai.common.util.ProtostuffUtil;
import com.zhuhai.common.util.RedisUtil;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/29
 * Time: 23:23
 */
public class AuthSessionDao extends CachingSessionDAO {

    private static final Logger logger = LoggerFactory.getLogger(AuthSessionDao.class);


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        RedisUtil.set(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + sessionId, Base64.encodeToString(ProtostuffUtil.serialize(session)), (int) (session.getTimeout() / 1000));
        logger.info("doCreate ====> sessionId={}", sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String session = RedisUtil.get(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + sessionId);
        logger.info("doReadSession ====> sessionId={}", sessionId);
        return ProtostuffUtil.derialize(Base64.decode(session), Session.class);
    }


    @Override
    protected void doUpdate(Session session) {
        //如何会话已经过期/停止，不需要更新
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }
        //更新session最后一次访问时间
        AuthSession authSession = (AuthSession) session;
        AuthSession cacheAuthSession = (AuthSession) doReadSession(session.getId());
        if (cacheAuthSession != null) {
            authSession.setOnlineStatus(cacheAuthSession.getOnlineStatus());
            authSession.setAttribute("FORCE_LOGOUT", cacheAuthSession.getAttribute("FORCE_LOGOUT"));
        }
        RedisUtil.set(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + session.getId(), Base64.encodeToString(ProtostuffUtil.serialize(session)), (int) (session.getTimeout() / 1000));
        logger.info("doUpdate ====> sessionId={}", session.getId());
    }

    @Override
    protected void doDelete(Session session) {
        String sessionId = session.getId().toString();
        String authType = Objects.toString(session.getAttribute(AuthConstant.AUTH_TYPE));
        if ("client".equals(authType)) {
            //删除局部会话和同一code注册的局部会话
            String code = RedisUtil.get(AuthConstant.AUTHCENTER_CLIENT_SESSION_ID + "_" + sessionId);
            Jedis jedis = RedisUtil.getJedis();
            RedisUtil.remove(AuthConstant.AUTHCENTER_CLIENT_SESSION_ID + "_" + sessionId);
            RedisUtil.srem(AuthConstant.AUTHCENTER_CLIENT_SESSION_IDS + "_" + code, sessionId);
            jedis.close();
        }
        if ("server".equals(authType)) {
            //全局会话code
            String code = RedisUtil.get(AuthConstant.AUTHCENTER_SERVER_SESSION_ID + "_" + sessionId);
            //删除全局会话
            RedisUtil.remove(AuthConstant.AUTHCENTER_SERVER_SESSION_ID + "_" + sessionId);
            RedisUtil.remove(AuthConstant.AUTHCENTER_SERVER_CODE + "_" + code);
            //删除所有局部会话
            Jedis jedis = RedisUtil.getJedis();
            Set<String> cacheSessionIds = jedis.smembers(AuthConstant.AUTHCENTER_CLIENT_SESSION_IDS + "_" + code);
            for (String cacheSessionId : cacheSessionIds) {
                jedis.del(AuthConstant.AUTHCENTER_CLIENT_SESSION_ID + "_" + cacheSessionId);
                jedis.srem(AuthConstant.AUTHCENTER_CLIENT_SESSION_IDS + "_" + code, cacheSessionId);
            }
            logger.info("当前code={}，对应的注册系统个数：{}个", code, jedis.scard(AuthConstant.AUTHCENTER_CLIENT_SESSION_IDS + "_" + code));
            jedis.close();
            RedisUtil.lrem(AuthConstant.AUTHCENTER_SERVER_SESSSION_IDS, 1, sessionId);

        }
        //删除session
        RedisUtil.remove(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + sessionId);
        logger.info("doDelete ====> sessionId={}", sessionId);
    }


}
