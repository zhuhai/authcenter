package com.zhuhai.api;

import com.github.pagehelper.PageInfo;
import com.zhuhai.entity.AuthRole;
import com.zhuhai.entity.AuthRolePermission;
import com.zhuhai.exception.ServiceException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/24
 * Time: 14:16
 */
public interface AuthRoleService {

    /**
     * 添加角色
     * @param authRole
     * @return
     */
    int saveAuthRole(AuthRole authRole) throws ServiceException;

    /**
     * 修改角色
     * @param authRole
     */
    void updateAuthRole(AuthRole authRole) throws ServiceException;

    /**
     * 删除角色
     * @param ids
     */
    void removeAuthRole(int[] ids) throws ServiceException;

    /**
     * 根据角色id查找角色
     * @param id
     * @return
     */
    AuthRole getAuthRole(Integer id) throws ServiceException;

    /**
     * 根据用户id获取用户的角色列表
     * @param userId
     * @return
     */
    List<AuthRole> listAuthRoleByUserId(Integer userId) throws ServiceException;

    /**
     * 分页获取角色列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AuthRole> listAuthRole(Integer pageNum, Integer pageSize) throws ServiceException;

    /**
     * 添加角色权限
     * @param authRolePermissions
     * @return
     * @throws ServiceException
     */
    int saveRolePermissions(List<AuthRolePermission> authRolePermissions) throws ServiceException;

    /**
     * 根据角色id删除角色权限
     * @param roleId
     * @throws ServiceException
     */
    void removeRolePermissionsByRoleId(Integer roleId) throws ServiceException;
}
