package com.zhuhai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthRoleService;
import com.zhuhai.entity.AuthRole;
import com.zhuhai.mapper.AuthRoleMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/24
 * Time: 14:17
 */
@Service
@Transactional
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
    public PageInfo<AuthRole> listAuthRole(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthRole> authRoleList = authRoleMapper.selectAuthRoleList();
        return new PageInfo<AuthRole>(authRoleList);
    }

    @Override
    public List<AuthRole> listAuthRoleByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return authRoleMapper.selectAuthRoleListByUserId(userId);
    }
}
