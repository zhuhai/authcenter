package com.zhuhai.mapper;

import com.zhuhai.entity.AuthRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/24
 * Time: 14:00
 */
public interface AuthRoleMapper {

    int insertAuthRole(AuthRole authRole);
    void updateAuthRole(AuthRole authRole);
    void deleteAuthRole(int[] ids);
    AuthRole selectAuthRoleById(int id);
    List<AuthRole> selectAuthRoleList();
    List<AuthRole> selectAuthRoleListByUserId(int userId);
}
