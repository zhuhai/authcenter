package com.zhuhai.mapper;

import com.zhuhai.entity.AuthUserRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/26
 * Time: 13:40
 */
public interface AuthUserRoleMapper {

    int insertAuthUserRoles(List<AuthUserRole> authUserRoles);
    void deleteAuthUserRolesByUserId(Integer userId);
    void deleteAuthUserRolesByRoleId(Integer roleId);
}
