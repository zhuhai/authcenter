package com.zhuhai.api;

import com.github.pagehelper.PageInfo;
import com.zhuhai.entity.AuthUser;

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
    int saveAuthUser(AuthUser authUser);

    /**
     * 修改用户
     * @param authUser
     */
    void updateAuthUser(AuthUser authUser);

    /**
     * 删除用户
     * @param ids
     */
    void removeAuthUser(int[] ids);

    /**
     * 根据用户id获取用户
     * @param id
     * @return
     */
    AuthUser getAuthUserById(Integer id);

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    AuthUser getAuthUserByName(String userName);

    /**
     * 分页获取用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AuthUser> listAuthUser(Integer pageNum, Integer pageSize);
}
