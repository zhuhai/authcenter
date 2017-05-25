package com.zhuhai.api;

import com.github.pagehelper.PageInfo;
import com.zhuhai.entity.AuthPermission;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/24
 * Time: 23:25
 */
public interface AuthPermissionService {
    /**
     * 添加权限
     * @param authPermission
     * @return
     */
    int saveAuthPermission(AuthPermission authPermission);

    /**
     * 修改权限
     * @param authPermission
     */
    void updateAuthPermission(AuthPermission authPermission);

    /**
     * 删除权限
     * @param ids
     */
    void removeAuthPermission(int[] ids);

    /**
     * 根据权限id获取权限
     * @param id
     * @return
     */
    AuthPermission getAuthPermissionById(Integer id);

    /**
     * 分页获取权限列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AuthPermission> listAuthPermission(Integer pageNum, Integer pageSize);

    /**
     * 根据用户id获取用户的权限
     * @param userId
     * @return
     */
    List<String> listAuthPermissionByUserId(Integer userId);

    /**
     * 根据角色id获取该角色的权限
     * @param roleId
     * @return
     */
    List<AuthPermission> listAuthPermissionByRoleId(Integer roleId);
}
