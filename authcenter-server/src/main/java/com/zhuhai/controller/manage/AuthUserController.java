package com.zhuhai.controller.manage;

import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthOrganizationService;
import com.zhuhai.api.AuthRoleService;
import com.zhuhai.api.AuthUserService;
import com.zhuhai.common.constant.AuthResult;
import com.zhuhai.common.constant.AuthResultConstant;
import com.zhuhai.common.util.DateUtil;
import com.zhuhai.dto.AuthUserDTO;
import com.zhuhai.dto.JqGridView;
import com.zhuhai.entity.AuthOrganization;
import com.zhuhai.entity.AuthRole;
import com.zhuhai.entity.AuthUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.UUID;

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


    @RequiresPermissions("auth:user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        PageInfo<AuthOrganization> pageInfo = authOrganizationService.listAuthOrganization(null, null);
        List<AuthOrganization> organizationList = pageInfo.getList();
        PageInfo<AuthRole> authRolePageInfo = authRoleService.listAuthRole(null, null);
        List<AuthRole> roleList = authRolePageInfo.getList();
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("roleList", roleList);
        return "manage/user/userList";
    }

    /**
     * 用户列表
     * @param sidx
     * @param sord
     * @param pageNum
     * @param pageSize
     * @return
     */
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


    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @param organizationIds
     * @return
     */
    @RequiresPermissions("auth:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AuthResult createUser(AuthUser user,
                                 @RequestParam(value = "roleIds", required = false) Integer[] roleIds,
                                 @RequestParam(value = "organizationIds", required = false) Integer[] organizationIds) {

        try {
            AuthUser authUser = authUserService.getAuthUserByName(user.getUserName());
            if (authUser == null) {
                String salt = UUID.randomUUID().toString().replace("-", "");
                user.setSalt(salt);
                user.setPassword(DigestUtils.sha1Hex(user.getPassword() + salt));
                authUserService.saveUser(user, roleIds, organizationIds);
                return new AuthResult(AuthResultConstant.SUCCESS);
            } else {
                return new AuthResult(AuthResultConstant.INVALID_USERNAME);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new AuthResult(AuthResultConstant.FAIL);
        }
    }

    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @param organizationIds
     * @return
     */
    @RequiresPermissions("auth:user:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AuthResult updateUser(AuthUser user,
                                 @RequestParam(value = "roleIds", required = false) Integer[] roleIds,
                                 @RequestParam(value = "organizationIds", required = false) Integer[] organizationIds) {

        try {
            if (user != null && StringUtils.isNotBlank(user.getPassword())) {
                String salt = UUID.randomUUID().toString().replace("-", "");
                user.setPassword(DigestUtils.sha1Hex(user.getPassword() + salt));
                user.setSalt(salt);
            }
            authUserService.updateAuthUser(user);

            System.out.println(roleIds[0]);
            System.out.println(organizationIds[0]);
            System.out.println(user.toString());
            return new AuthResult(AuthResultConstant.SUCCESS);


        } catch (Exception e) {
            e.printStackTrace();
            return new AuthResult(AuthResultConstant.FAIL);
        }
    }

}
