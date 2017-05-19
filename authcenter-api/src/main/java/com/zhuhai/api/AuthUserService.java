package com.zhuhai.api;

import com.zhuhai.entity.AuthUser;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/18
 * Time: 18:29
 */
public interface AuthUserService {
    void createAuthUser(AuthUser authUser);
    void updateAuthUser(AuthUser authUser);
}
