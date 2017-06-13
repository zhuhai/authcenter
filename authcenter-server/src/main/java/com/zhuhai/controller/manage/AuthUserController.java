package com.zhuhai.controller.manage;

import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthUserService;
import com.zhuhai.dto.JqGridView;
import com.zhuhai.entity.AuthUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/19
 * Time: 15:11
 */
@Controller
@RequestMapping("/user")
public class AuthUserController {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserController.class);

    @Resource
    private AuthUserService authUserService;

    @RequiresPermissions("auth:user:add")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUser() {
        return "manage/user/userSave";
    }

    @RequiresPermissions("auth:user:add")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(AuthUser user, Integer[] organizationIds) {

        authUserService.saveUserAndOrganization(user, organizationIds);
        return "manage/index";
    }

    @RequiresPermissions("auth:user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {

        return "manage/user/userList";
    }

    @RequiresPermissions("auth:user:view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JqGridView<AuthUser> userList(@RequestParam(value = "sidx", required = false, defaultValue = "create_time") String sidx,
                                         @RequestParam(value = "sord", required = false, defaultValue = "desc") String sord,
                                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        JqGridView<AuthUser> jqGridView = new JqGridView<AuthUser>();
        try {
            PageInfo<AuthUser> pageInfo = authUserService.listAuthUser(sidx, sord, pageNum, pageSize);
            if (pageInfo != null) {
                List<AuthUser> authUserList = pageInfo.getList();
                jqGridView.setTotalPage(pageInfo.getPages());
                jqGridView.setCurrentPage(pageInfo.getPageNum());
                jqGridView.setRecords(pageInfo.getTotal());
                jqGridView.setRows(authUserList);
            }
        } catch (Exception e) {
            logger.error("get user list error", e);
        }
        return jqGridView;
    }

}
