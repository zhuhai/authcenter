package com.zhuhai.shiro.filter;

import com.zhuhai.common.constant.AuthConstant;
import com.zhuhai.common.constant.AuthResult;
import com.zhuhai.common.util.JsonMapper;
import com.zhuhai.common.util.PropertiesUtil;
import com.zhuhai.common.util.RedisUtil;
import com.zhuhai.shiro.session.AuthSessionDao;
import com.zhuhai.util.RequestParameterUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/30
 * Time: 19:52
 */
public class MyAuthenticationFilter extends AuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationFilter.class);

    @Resource
    private AuthSessionDao authSessionDao;

    /**
     * 是否允许访问
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String authType = PropertiesUtil.getInstance("auth-client").getString("auth.type");
        session.setAttribute(AuthConstant.AUTH_TYPE, authType);
        if ("client".equals(authType)) {
            return validateClient(request, response);
        }
        if ("server".equals(authType)) {
            return subject.isAuthenticated();
        }

        return false;
    }

    /**
     * 当访问拒绝时是否已经处理了；
     * 如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        StringBuffer sso_server_url = new StringBuffer(PropertiesUtil.getInstance("auth-client").getString("sso.server.url"));
        String authType = PropertiesUtil.getInstance("auth-client").getString("auth.type");
        if ("server".equals(authType)) {
            WebUtils.toHttp(response).sendRedirect(sso_server_url.append("/sso/index").toString());
            return false;
        }
        sso_server_url.append("/sso/index").append("?").append("appid").append("=").append(PropertiesUtil.getInstance("auth-client").getString("appId"));
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        //回调地址
        StringBuffer backUrl = httpServletRequest.getRequestURL();
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            backUrl.append("?").append(queryString);
        }
        sso_server_url.append("&").append("backurl").append("=").append(URLEncoder.encode(backUrl.toString(), "UTF-8"));
        WebUtils.toHttp(response).sendRedirect(sso_server_url.toString());
        return false;

    }

    /**
     * 认证中心登录成功带回code
     *
     * @param request
     * @param response
     * @return
     */
    private boolean validateClient(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeout = (int) session.getTimeout() / 1000;
        //判断局部会话是否登录
        String cacheCode = RedisUtil.get(AuthConstant.AUTHCENTER_CLIENT_SESSION_ID + "_" + sessionId);
        if (StringUtils.isNotBlank(cacheCode)) {
            //更新code有效期
            RedisUtil.set(AuthConstant.AUTHCENTER_CLIENT_SESSION_ID + "_" + sessionId, cacheCode, timeout);
            RedisUtil.expire(AuthConstant.AUTHCENTER_CLIENT_SESSION_IDS + "_" + cacheCode, timeout);
            //移除url中的code参数
            if (request.getParameter("auth_code") != null) {
                try {
                    String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                    WebUtils.toHttp(response).sendRedirect(backUrl);
                } catch (IOException e) {
                    logger.error("局部会话已登录，移除参数跳转出错", e);
                }
            } else {
                return true;
            }

        }
        String code = request.getParameter("auth_code");
        //判断是否拿到认证中心code
        if (StringUtils.isNotBlank(code)) {
            //检验code是否合法
            try {
                StringBuffer sso_server_url = new StringBuffer(PropertiesUtil.getInstance("auth-client").getString("sso.server.url"));
                HttpClient httpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(sso_server_url.append("/sso/code").toString());
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("code", code));
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    String result = EntityUtils.toString(httpEntity);
                    AuthResult authResult = JsonMapper.nonEmptyMapper().fromJson(result, AuthResult.class);
                    if (1 == authResult.getCode() && code.equals(authResult.getData())) {
                        //code校验成功，创建局部会话
                        RedisUtil.set(AuthConstant.AUTHCENTER_CLIENT_SESSION_ID + "_" + sessionId, code, timeout);
                        RedisUtil.sadd(AuthConstant.AUTHCENTER_CLIENT_SESSION_IDS + "_" + code, sessionId, timeout);
                        //去掉url中的auth_code和auth_username参数
                        String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                        //client无密认证
                        try {
                            String userName = request.getParameter("auth_username");
                            subject.login(new UsernamePasswordToken(userName,""));
                            WebUtils.toHttp(response).sendRedirect(backUrl);
                        } catch (IOException e) {
                            logger.error("已拿到code，移除参数跳转失败");
                        }
                        return true;
                    }

                }
            } catch (Exception e) {
                logger.error("验证code失败", e);
            }


        }
        return false;

    }
}
