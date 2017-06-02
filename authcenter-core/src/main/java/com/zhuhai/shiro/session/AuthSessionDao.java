package com.zhuhai.shiro.session;

import com.zhuhai.common.constant.AuthConstant;
import com.zhuhai.common.util.RedisUtil;
import com.zhuhai.util.SerializableUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        RedisUtil.set(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(session), (int) (session.getTimeout() / 1000));
        logger.debug("doCreate ====> sessionId={}", sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String session = RedisUtil.get(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + sessionId);
        logger.debug("doReadSession ====> sessionId={}", sessionId);
        return SerializableUtil.deserialize(session);
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
        RedisUtil.set(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) (session.getTimeout() / 1000));
        logger.debug("doUpdate ====> sessionId={}", session.getId());
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
            logger.debug("当前code={}，对应的注册系统个数：{}个", code, jedis.scard(AuthConstant.AUTHCENTER_CLIENT_SESSION_IDS + "_" + code));
            jedis.close();
            RedisUtil.lrem(AuthConstant.AUTHCENTER_SERVER_SESSION_IDS, 1, sessionId);

        }
        //删除session
        RedisUtil.remove(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + sessionId);
        logger.debug("doDelete ====> sessionId={}", sessionId);
    }


    /**
     * 获取会话列表
     *
     * @param offset
     * @param limit
     * @return
     */
    public Map getActiveSessions(int offset, int limit) {
        Map map = new HashMap();
        long total = RedisUtil.llen(AuthConstant.AUTHCENTER_SERVER_SESSION_IDS);
        List<Session> rows = new ArrayList<>();
        List<String> sessionIds = RedisUtil.lrange(AuthConstant.AUTHCENTER_SERVER_SESSION_IDS, offset, limit);
        for (String sessionId : sessionIds) {
            String session = RedisUtil.get(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + sessionId);
            //过滤掉过期的session
            if (session == null) {
                RedisUtil.lrem(AuthConstant.AUTHCENTER_SERVER_SESSION_IDS, 1, sessionId);
                total = total - 1;
                continue;
            }
            rows.add(SerializableUtil.deserialize(session));
        }
        map.put("total", total);
        map.put("rows", rows);
        return map;
    }

    /**
     * 强制退出
     * @param ids
     * @return
     */
    public int forceLogout(String ids) {
        String[] sessionIds = ids.split(",");
        //会话增加强制退出属性标识，当此会话访问系统时，判断有此属性，退出登录
        for (String sessionId : sessionIds) {
            String session = RedisUtil.get(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + sessionId);
            AuthSession authSession = (AuthSession) SerializableUtil.deserialize(session);
            authSession.setOnlineStatus(AuthSession.OnlineStatus.force_logout);
            authSession.setAttribute("FORCE_LOGOUT", "FORCE_LOGOUT");
            RedisUtil.set(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(authSession), (int) (authSession.getTimeout() / 1000));
        }
        return sessionIds.length;
    }


    /**
     * 更改在线状态
     * @param sessionId
     * @param onlineStatus
     */
    public void updateStatus(Serializable sessionId, AuthSession.OnlineStatus onlineStatus) {
        AuthSession authSession = (AuthSession) doReadSession(sessionId);
        if (authSession == null) {
            return;
        }
        authSession.setOnlineStatus(onlineStatus);
        RedisUtil.set(AuthConstant.AUTHCENTER_SHIRO_SESSION_ID + "_" + authSession.getId(), SerializableUtil.serialize(authSession), (int) (authSession.getTimeout() / 1000));
    }


}
