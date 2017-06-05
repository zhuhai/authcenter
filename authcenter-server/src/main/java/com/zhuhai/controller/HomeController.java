package com.zhuhai.controller;

import com.zhuhai.api.AuthPermissionService;
import com.zhuhai.api.AuthSystemService;
import com.zhuhai.common.util.PropertiesUtil;
import com.zhuhai.entity.AuthPermission;
import com.zhuhai.entity.AuthSystem;
import com.zhuhai.entity.AuthUser;
import com.zhuhai.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/6/2
 * Time: 11:18
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private AuthSystemService authSystemService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        try {
            AuthUser authUser = (AuthUser) SecurityUtils.getSubject().getPrincipal();
            AuthSystem authSystem = authSystemService.getAuthSystemByName(PropertiesUtil.getInstance("auth-client").getString("appId"));
            if (authSystem != null) {
                Integer systemId = authSystem.getId();
                List<AuthPermission> menus = authPermissionService.listAuthPermissionsByUserId(authUser.getId(), systemId);
                model.addAttribute("menus",menus);
            }
            model.addAttribute("user", authUser);
        } catch (ServiceException e) {
            logger.error("跳转到首页失败", e);
        }
        return "manage/home";
    }


}
