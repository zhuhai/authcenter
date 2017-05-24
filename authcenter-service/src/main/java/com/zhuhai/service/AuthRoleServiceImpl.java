package com.zhuhai.service;

import com.zhuhai.api.AuthRoleService;
import com.zhuhai.entity.AuthRole;
import com.zhuhai.mapper.AuthRoleMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/24
 * Time: 14:17
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Resource
    private AuthRoleMapper authRoleMapper;

    @Override
    public int saveAuthRole(AuthRole authRole) {
        if (authRole == null) {
            return 0;
        }
        return authRoleMapper.insertAuthRole(authRole);
    }

    public void updateAuthRole(AuthRole authRole) {
        authRoleMapper.updateAuthRole(authRole);
    }

    @Override
    public void removeAuthRole(int[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }
        authRoleMapper.deleteAuthRole(ids);
    }

    @Override
    public AuthRole getAuthRole(Integer id) {
        if (id == null) {
            return null;
        }
        return authRoleMapper.selectAuthRoleById(id);
    }

    @Override
    public List<AuthRole> listAuthRole() {
        return authRoleMapper.selectAuthRoleList();
    }

    @Override
    public List<AuthRole> listAuthRoleByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return authRoleMapper.selectAuthRoleListByUserId(userId);
    }
}
