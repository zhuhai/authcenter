package com.zhuhai.shiro.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/6/2
 * Time: 10:30
 * 强制退出会话过滤器
 */
public class AuthSessionForceLogoutFilter extends AccessControlFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession(false);
        if (session == null) {
            return true;
        }
        boolean forceLogout = session.getAttribute("FORCE_LOGOUT") == null;
        return forceLogout;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        subject.logout();
        String loginUrl = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";
        WebUtils.issueRedirect(request, response, loginUrl);
        return false;
    }
}
