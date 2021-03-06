package com.zhuhai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthUserService;
import com.zhuhai.entity.AuthUser;
import com.zhuhai.entity.AuthUserOrganization;
import com.zhuhai.entity.AuthUserRole;
import com.zhuhai.exception.ServiceException;
import com.zhuhai.mapper.AuthUserMapper;
import com.zhuhai.mapper.AuthUserOrganizationMapper;
import com.zhuhai.mapper.AuthUserRoleMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.orderbyhelper.OrderByHelper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/18
 * Time: 22:37
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Resource
    private AuthUserMapper authUserMapper;
    @Resource
    private AuthUserRoleMapper authUserRoleMapper;
    @Resource
    private AuthUserOrganizationMapper authUserOrganizationMapper;

    @Override
    public int saveAuthUser(AuthUser authUser) {
        if (authUser == null) {
            return 0;
        }
        return authUserMapper.insertAuthUser(authUser);
    }

    @Override
    public void updateAuthUser(AuthUser authUser) {
        authUserMapper.updateAuthUser(authUser);
    }

    @Override
    public void removeAuthUser(Integer[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }
        authUserMapper.deleteAuthUser(ids);
        authUserOrganizationMapper.deleteAuthUserOrganizationsByUserIds(ids);
        authUserRoleMapper.deleteAuthUserRolesByUserIds(ids);

    }

    @Override
    public AuthUser getAuthUserById(Integer id) {
        if (id == null) {
            return null;
        }
        return authUserMapper.selectAuthUserById(id);
    }

    @Override
    public AuthUser getAuthUserByName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return null;
        }
        return authUserMapper.selectAuthUserByName(userName);
    }

    @Override
    public PageInfo<AuthUser> listAuthUser(String sidx, String sord, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        OrderByHelper.orderBy(sidx + " " + sord);
        List<AuthUser> authUserList = authUserMapper.selectAuthUserList();
        return new PageInfo<AuthUser>(authUserList);
    }

    @Override
    public int saveAuthUserRoles(List<AuthUserRole> authUserRoles) {
        if (CollectionUtils.isEmpty(authUserRoles)) {
            return 0;
        }
        return authUserRoleMapper.insertAuthUserRoles(authUserRoles);
    }

    @Override
    public void removeAuthUserRolesByUserId(Integer userId) {
        if (userId == null) {
            return;
        }
        authUserRoleMapper.deleteAuthUserRolesByUserId(userId);
    }

    @Override
    public void removeAuthUserRolesByRoleId(Integer roleId) throws ServiceException {
        if (roleId == null) {
            return;
        }
        authUserRoleMapper.deleteAuthUserRolesByRoleId(roleId);
    }

    @Override
    public int saveAuthUserOrganizations(List<AuthUserOrganization> authUserOrganizations) throws ServiceException {
        if (CollectionUtils.isEmpty(authUserOrganizations)) {
            return 0;
        }

        return authUserOrganizationMapper.insertAuthUserOrganizations(authUserOrganizations);
    }

    @Override
    public void updateAuthUserOrganization(AuthUserOrganization authUserOrganization) throws ServiceException {
        if (authUserOrganization == null) {
            return;
        }
        authUserOrganizationMapper.updateAuthUserOrganization(authUserOrganization);
    }

    @Override
    public void removeAuthUserOrganizationByUserId(Integer userId) throws ServiceException {
        if (userId == null) {
            return;
        }
        authUserOrganizationMapper.deleteAuthUserOrganizationsByUserId(userId);
    }

    @Override
    public void removeAuthUserOrganizationByOrganizationId(Integer organizationId) throws ServiceException {
        if (organizationId == null) {
            return;
        }
        authUserOrganizationMapper.deleteAuthUserOrganizationsByOrganizationId(organizationId);
    }


    @Override
    public void saveUser(AuthUser authUser, Integer[] roleIds, Integer[] organizationIds) throws ServiceException {
        if (authUser == null) {
            return;
        }
        try {
            authUserMapper.insertAuthUser(authUser);
            if (!ArrayUtils.isEmpty(roleIds)) {
                List<AuthUserRole> userRoles = new ArrayList<>();
                for (Integer roleId : roleIds) {
                    AuthUserRole authUserRole = new AuthUserRole();
                    authUserRole.setUserId(authUser.getId());
                    authUserRole.setRoleId(roleId);
                    userRoles.add(authUserRole);
                }
                authUserRoleMapper.insertAuthUserRoles(userRoles);
            }
            if (!ArrayUtils.isEmpty(organizationIds)) {
                List<AuthUserOrganization> list = new ArrayList<>();
                for (Integer organizationId : organizationIds) {
                    AuthUserOrganization authUserOrganization = new AuthUserOrganization();
                    authUserOrganization.setUserId(authUser.getId());
                    authUserOrganization.setOrganizationId(organizationId);
                    list.add(authUserOrganization);
                }
                authUserOrganizationMapper.insertAuthUserOrganizations(list);
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    @Override
    public void updateAuthUser(AuthUser user, Integer[] roleIds, Integer[] organizationIds) throws ServiceException {
        if (user == null) {
            return;
        }
        try {
            authUserMapper.updateAuthUser(user);
            authUserRoleMapper.deleteAuthUserRolesByUserId(user.getId());
            authUserOrganizationMapper.deleteAuthUserOrganizationsByUserId(user.getId());
            if (!ArrayUtils.isEmpty(roleIds)) {
                List<AuthUserRole> authUserRoleList = new ArrayList<>();
                for (Integer roleId : roleIds) {
                    AuthUserRole authUserRole = new AuthUserRole();
                    authUserRole.setUserId(user.getId());
                    authUserRole.setRoleId(roleId);
                    authUserRoleList.add(authUserRole);
                }
                authUserRoleMapper.insertAuthUserRoles(authUserRoleList);
            }
            if (!ArrayUtils.isEmpty(organizationIds)) {
                List<AuthUserOrganization> list = new ArrayList<>();
                for (Integer organizationId : organizationIds) {
                    AuthUserOrganization authUserOrganization = new AuthUserOrganization();
                    authUserOrganization.setUserId(user.getId());
                    authUserOrganization.setOrganizationId(organizationId);
                    list.add(authUserOrganization);
                }
                authUserOrganizationMapper.insertAuthUserOrganizations(list);
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
