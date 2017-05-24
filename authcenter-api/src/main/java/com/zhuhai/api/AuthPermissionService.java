package com.zhuhai.api;

import com.github.pagehelper.PageInfo;
import com.zhuhai.entity.AuthPermission;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/24
 * Time: 23:25
 */
public interface AuthPermissionService {
    int saveAuthPermission(AuthPermission authPermission);
    void updateAuthPermission(AuthPermission authPermission);
    void deleteAuthPermission(int[] ids);
    AuthPermission getAuthPermissionById(Integer id);
    PageInfo<AuthPermission> listAuthPermission(Integer pageNum, Integer pageSize);
}
