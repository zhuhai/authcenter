package com.zhuhai.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/29
 * Time: 23:45
 */
public class AuthSessionListener implements SessionListener{

    private static final Logger logger = LoggerFactory.getLogger(AuthSessionListener.class);

    @Override
    public void onStart(Session session) {
        logger.debug("会话创建：sessionId={}", session.getId());
    }

    @Override
    public void onStop(Session session) {
        logger.debug("会话终止，sessionId={}", session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        logger.debug("会话过期，sessionId={}", session.getId());
    }
}
