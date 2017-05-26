package com.zhuhai.mapper;

import com.zhuhai.entity.AuthRolePermission;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/26
 * Time: 21:08
 */
public interface AuthRolePermissionMapper {
    int insertAuthRolePermissions(List<AuthRolePermission> authRolePermissions);
    void deleteAuthRolePermissionsByRoleId(int roleId);
}
