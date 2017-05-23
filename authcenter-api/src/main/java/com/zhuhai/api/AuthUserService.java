package com.zhuhai.api;

import com.zhuhai.entity.AuthUser;

import java.util.List;

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
    List<AuthUser> selectAll();
}
