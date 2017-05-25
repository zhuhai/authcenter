package com.zhuhai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthPermissionService;
import com.zhuhai.entity.AuthPermission;
import com.zhuhai.entity.AuthUser;
import com.zhuhai.mapper.AuthPermissionMapper;
import com.zhuhai.mapper.AuthUserMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/24
 * Time: 23:29
 */
@Service
@Transactional
public class AuthPermissionServiceImpl implements AuthPermissionService {

    @Resource
    private AuthPermissionMapper authPermissionMapper;
    @Resource
    private AuthUserMapper authUserMapper;

    @Override
    public int saveAuthPermission(AuthPermission authPermission) {
        if (authPermission == null) {
            return 0;
        }
        return authPermissionMapper.insertAuthPermission(authPermission);
    }

    @Override
    public void updateAuthPermission(AuthPermission authPermission) {
        authPermissionMapper.updateAuthPermission(authPermission);
    }

    @Override
    public void removeAuthPermission(int[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }
        authPermissionMapper.deleteAuthPermission(ids);
    }

    @Override
    public AuthPermission getAuthPermissionById(Integer id) {
        if (id == null) {
            return null;
        }
        return authPermissionMapper.selectAuthPermissionById(id);
    }

    @Override
    public PageInfo<AuthPermission> listAuthPermission(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthPermission> authPermissionList = authPermissionMapper.selectAuthPermissionList();
        return new PageInfo<AuthPermission>(authPermissionList);
    }

    @Override
    public List<String> listAuthPermissionByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        AuthUser authUser = authUserMapper.selectAuthUserById(userId);
        if (authUser == null || authUser.getStatus() == 0) {
            return null;
        }
        return authPermissionMapper.selectAuthPermissionByUserId(userId);
    }

    @Override
    public List<AuthPermission> listAuthPermissionByRoleId(Integer roleId) {
        if (roleId == null) {
            return null;
        }
        return authPermissionMapper.selectAuthPermissionByRoleId(roleId);
    }
}
