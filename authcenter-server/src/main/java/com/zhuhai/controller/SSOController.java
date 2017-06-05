package com.zhuhai.controller;

import com.zhuhai.api.AuthSystemService;
import com.zhuhai.common.constant.AuthConstant;
import com.zhuhai.common.constant.AuthResult;
import com.zhuhai.common.constant.AuthResultConstant;
import com.zhuhai.common.util.RedisUtil;
import com.zhuhai.entity.AuthSystem;
import com.zhuhai.shiro.session.AuthSession;
import com.zhuhai.shiro.session.AuthSessionDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/6/2
 * Time: 11:16
 */
@Controller
@RequestMapping("/sso")
public class SSOController {

    private static final Logger logger = LoggerFactory.getLogger(SSOController.class);

    @Resource
    private AuthSystemService authSystemService;

    @Resource
    private AuthSessionDao authSessionDao;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestParam("appid") String appid,
                        @RequestParam("backurl") String backurl) {

        try {
            AuthSystem authSystem = authSystemService.getAuthSystemByName(appid);
            backurl = URLEncoder.encode(backurl, "UTF-8");
            if (authSystem == null) {
                throw new RuntimeException("未注册的系统，appid=" + appid);
            }
        } catch (Exception e) {
            logger.error("系统异常", e);
        }

        return "redirect:/sso/login?backurl=" + backurl;

    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "backurl", required = false) String backurl, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            String sessionId = session.getId().toString();
            //判断是否已登录，如果已经登录，则会跳
            String code = RedisUtil.get(AuthConstant.AUTHCENTER_SERVER_SESSION_ID + "_" + sessionId);
            if (StringUtils.isNotBlank(code)) {
                String userName = (String) subject.getPrincipal();
                if (StringUtils.isBlank(backurl)) {
                    backurl = "/";
                } else {
                    if (backurl.contains("?")) {
                        backurl = backurl + "&auth_code=" + code + "&auth_username=" + userName;
                    } else {
                        backurl = backurl + "?auth_code=" + code + "&auth_username=" + userName;
                    }
                }
                logger.info("认证中心验证账号通过，回跳地址：{}", backurl);
                return "redirect:" + backurl;
            }

        } catch (Exception e) {
            logger.error("跳转登录页面失败", e);
        }
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam(value = "rememberMe", required = false) boolean rememberMe,
                        @RequestParam(value = "backurl", required = false) String backurl,
                        RedirectAttributes redirectAttributes) {

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        //判断是否已登录，如果已登录，则直接跳转，防止重复登录
        try {
            String hasCode = RedisUtil.get(AuthConstant.AUTHCENTER_SERVER_SESSION_ID + "_" + sessionId);
            if (StringUtils.isBlank(hasCode)) {
                UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
                subject.login(token);
                //更新session状态
                authSessionDao.updateStatus(sessionId, AuthSession.OnlineStatus.on_line);
                //全局会话sessionId列表
                RedisUtil.lpush(AuthConstant.AUTHCENTER_SERVER_SESSION_IDS, sessionId);
                String code = UUID.randomUUID().toString();
                //全局会话的code
                RedisUtil.set(AuthConstant.AUTHCENTER_SERVER_SESSION_ID + "_" + sessionId, code, (int) (session.getTimeout()/1000));
                //code校验值
                RedisUtil.set(AuthConstant.AUTHCENTER_SERVER_CODE + "_" + code, code, (int) (session.getTimeout()/1000));

            }
            if (StringUtils.isBlank(backurl)) {
                backurl = "/";
            }
            return "redirect:" + backurl;
        } catch (UnknownAccountException e) {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
        } catch (LockedAccountException e) {
            redirectAttributes.addFlashAttribute("message", "用户被锁定");
        } catch (IncorrectCredentialsException e) {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
        } catch (Exception e) {
            logger.error("login error", e);
            redirectAttributes.addFlashAttribute("message", "服务器忙，请稍后重试");
        }

        return "redirect:login";

    }

    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @ResponseBody
    public AuthResult code(@RequestParam("code") String code) {
        try {
            String cacheCode = RedisUtil.get(AuthConstant.AUTHCENTER_SERVER_CODE + "_" + code);
            if (StringUtils.isBlank(cacheCode) || !code.equals(cacheCode)) {
                return new AuthResult(AuthResultConstant.INVALID_CODE);
            }
            return new AuthResult(AuthResultConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new AuthResult(AuthResultConstant.FAIL);
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        String redirectUrl = request.getHeader("Referer");
        if (StringUtils.isBlank(redirectUrl)) {
            redirectUrl = "/";
        }
        return "redirect:" + redirectUrl;
    }

}
