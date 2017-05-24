package com.zhuhai.controller;

import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthUserService;
import com.zhuhai.entity.AuthUser;
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
@RequestMapping("/authUser")
public class AuthUserController {

    @Resource
    private AuthUserService authUserService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUser() {
        return "userCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(AuthUser user) {
        authUserService.saveAuthUser(user);
        return "index";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String getAuthUserList(Model model) {
        PageInfo<AuthUser> pageInfo = authUserService.listAuthUser(1,10);
        model.addAttribute("authUserList",pageInfo.getList());
        return "userList";
    }

}
