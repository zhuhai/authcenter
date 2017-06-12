package com.zhuhai.controller.manage;

import com.zhuhai.api.AuthUserService;
import com.zhuhai.entity.AuthUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/19
 * Time: 15:11
 */
@Controller
@RequestMapping("/user")
public class AuthUserController {

    @Resource
    private AuthUserService authUserService;

    @RequiresPermissions("auth:user:add")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUser() {
        return "manage/user/userSave";
    }

    @RequiresPermissions("auth:user:add")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(AuthUser user,Integer[] organizationIds) {

        authUserService.saveUserAndOrganization(user, organizationIds);
        return "manage/index";
    }

    @RequiresPermissions("auth:user:view")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model) {

        return "manage/user/userList";
    }

    public String userList() {
        return "";
    }

}
