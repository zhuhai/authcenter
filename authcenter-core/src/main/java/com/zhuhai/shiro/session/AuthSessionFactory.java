package com.zhuhai.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/29
 * Time: 23:23
 */
public class AuthSessionFactory implements SessionFactory{
    @Override
    public Session createSession(SessionContext sessionContext) {
        AuthSession authSession = new AuthSession();
        if (sessionContext != null && sessionContext instanceof WebSessionContext) {
            WebSessionContext webSessionContext = (WebSessionContext) sessionContext;
            HttpServletRequest request = (HttpServletRequest) webSessionContext.getServletRequest();
            if (request != null) {
                authSession.setHost(request.getRemoteHost());
                authSession.setUserAgent(request.getHeader("User-Agent"));
            }
        }
        return authSession;
    }
}
