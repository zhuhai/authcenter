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
    int saveAuthUser(AuthUser authUser);
    void updateAuthUser(AuthUser authUser);
    void removeAuthUser(int[] ids);
    AuthUser getAuthUserById(Integer id);
    AuthUser getAuthUserByName(String userName);
    PageInfo<AuthUser> listAuthUser(Integer pageNum, Integer pageSize);
}
