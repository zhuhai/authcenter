package com.zhuhai.mapper;

import com.zhuhai.entity.AuthPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/24
 * Time: 21:52
 */
public interface AuthPermissionMapper {

    int insertAuthPermission(AuthPermission authPermission);
    void updateAuthPermission(AuthPermission authPermission);
    void deleteAuthPermission(int[] ids);
    AuthPermission selectAuthPermissionById(Integer id);
    List<AuthPermission> selectAuthPermissionList();
    List<String> selectAuthPermissionByUserId(Integer userId);
    List<AuthPermission> selectAuthPermissionByRoleId(Integer roleId);
    List<AuthPermission> selectAuthPermissionsByUserId(@Param("userId") Integer userId, @Param("systemId") Integer systemId);
}
