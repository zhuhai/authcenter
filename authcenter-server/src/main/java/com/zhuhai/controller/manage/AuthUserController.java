package com.zhuhai.controller.manage;

import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthOrganizationService;
import com.zhuhai.api.AuthRoleService;
import com.zhuhai.api.AuthUserService;
import com.zhuhai.common.util.DateUtil;
import com.zhuhai.dto.AuthUserDTO;
import com.zhuhai.dto.JqGridView;
import com.zhuhai.entity.AuthOrganization;
import com.zhuhai.entity.AuthRole;
import com.zhuhai.entity.AuthUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private AuthOrganizationService authOrganizationService;
    @Resource
    private AuthRoleService authRoleService;


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
    public JqGridView<AuthUserDTO> userList(@RequestParam(value = "sidx", required = false, defaultValue = "create_time") String sidx,
                                            @RequestParam(value = "sord", required = false, defaultValue = "desc") String sord,
                                            @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize) {

        JqGridView<AuthUserDTO> jqGridView = new JqGridView<AuthUserDTO>();
        try {
            PageInfo<AuthUser> pageInfo = authUserService.listAuthUser(sidx, sord, pageNum, pageSize);
            if (pageInfo != null) {
                List<AuthUser> authUserList = pageInfo.getList();
                jqGridView.setTotalPage(pageInfo.getPages());
                jqGridView.setCurrentPage(pageInfo.getPageNum());
                jqGridView.setRecords(pageInfo.getTotal());
                if (!CollectionUtils.isEmpty(authUserList)) {
                    List<AuthUserDTO> authUserDTOList = new ArrayList<>();
                    for (AuthUser authUser : authUserList) {
                        AuthUserDTO authUserDTO = new AuthUserDTO();
                        authUserDTO.setId(authUser.getId());
                        authUserDTO.setUserName(authUser.getUserName());
                        authUserDTO.setPhone(authUser.getPhone());
                        authUserDTO.setEmail(authUser.getEmail());
                        authUserDTO.setRealName(authUser.getRealName());
                        authUserDTO.setAvatar(authUser.getAvatar());
                        authUserDTO.setSex(authUser.getSex());
                        authUserDTO.setStatus(authUser.getStatus());
                        authUserDTO.setCreateTime(DateUtil.Date2String(authUser.getCreateTime()));
                        AuthOrganization authOrganization = authOrganizationService.getAuthOrganizationByUserId(authUser.getId());
                        if (authOrganization != null) {
                            authUserDTO.setOrganization(authOrganization);
                        }
                        List<AuthRole> roles = authRoleService.listAuthRoleByUserId(authUser.getId());
                        if (!CollectionUtils.isEmpty(roles)) {
                            authUserDTO.setRoles(roles);
                        }
                        authUserDTOList.add(authUserDTO);

                    }

                    jqGridView.setRows(authUserDTOList);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("get user list error", e);
        }
        return jqGridView;
    }

}
