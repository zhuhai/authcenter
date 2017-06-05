package com.zhuhai.api;

import com.github.pagehelper.PageInfo;
import com.zhuhai.entity.AuthPermission;
import com.zhuhai.exception.ServiceException;

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
    int saveAuthPermission(AuthPermission authPermission) throws ServiceException;

    /**
     * 修改权限
     * @param authPermission
     */
    void updateAuthPermission(AuthPermission authPermission) throws ServiceException;

    /**
     * 删除权限
     * @param ids
     */
    void removeAuthPermission(int[] ids) throws ServiceException;

    /**
     * 根据权限id获取权限
     * @param id
     * @return
     */
    AuthPermission getAuthPermissionById(Integer id) throws ServiceException;

    /**
     * 分页获取权限列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AuthPermission> listAuthPermission(Integer pageNum, Integer pageSize) throws ServiceException;

    /**
     * 根据用户id获取用户的权限
     * @param userId
     * @return
     */
    List<String> listAuthPermissionByUserId(Integer userId) throws ServiceException;

    /**
     * 根据角色id获取该角色的权限
     * @param roleId
     * @return
     */
    List<AuthPermission> listAuthPermissionByRoleId(Integer roleId) throws ServiceException;

    /**
     * 根据用户id和系统id获取用户权限，systemId为null查出用户所有系统的权限
     * @param userId
     * @param systemId
     * @return
     * @throws ServiceException
     */
    List<AuthPermission> listAuthPermissionsByUserId(Integer userId, Integer systemId) throws ServiceException;

}
