package com.zhuhai.api;

import com.github.pagehelper.PageInfo;
import com.zhuhai.entity.AuthUser;
import com.zhuhai.entity.AuthUserRole;
import com.zhuhai.exception.ServiceException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/18
 * Time: 18:29
 */
public interface AuthUserService {
    /**
     * 添加用户
     * @param authUser
     * @return
     */
    int saveAuthUser(AuthUser authUser) throws ServiceException;

    /**
     * 修改用户
     * @param authUser
     */
    void updateAuthUser(AuthUser authUser) throws ServiceException;

    /**
     * 删除用户
     * @param ids
     */
    void removeAuthUser(int[] ids) throws ServiceException;

    /**
     * 根据用户id获取用户
     * @param id
     * @return
     */
    AuthUser getAuthUserById(Integer id) throws ServiceException;

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    AuthUser getAuthUserByName(String userName) throws ServiceException;

    /**
     * 分页获取用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AuthUser> listAuthUser(Integer pageNum, Integer pageSize) throws ServiceException;

    /**
     * 添加用户角色
     * @param authUserRoles
     * @return
     * @throws ServiceException
     */
    int saveAuthUserRoles(List<AuthUserRole> authUserRoles) throws ServiceException;

    /**
     * 根据用户id删除用户角色
     * @param userId
     * @throws ServiceException
     */
    void removeAuthUserRoles(Integer userId) throws ServiceException;
}
