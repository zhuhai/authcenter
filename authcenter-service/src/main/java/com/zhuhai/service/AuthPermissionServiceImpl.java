package com.zhuhai.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthPermissionService;
import com.zhuhai.entity.AuthPermission;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/24
 * Time: 23:29
 */
@Service
@Transactional
public class AuthPermissionServiceImpl implements AuthPermissionService {

    @Override
    public int saveAuthPermission(AuthPermission authPermission) {
        return 0;
    }

    @Override
    public void updateAuthPermission(AuthPermission authPermission) {

    }

    @Override
    public void deleteAuthPermission(int[] ids) {

    }

    @Override
    public AuthPermission getAuthPermissionById(Integer id) {
        return null;
    }

    @Override
    public PageInfo<AuthPermission> listAuthPermission(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return null;
    }
}
