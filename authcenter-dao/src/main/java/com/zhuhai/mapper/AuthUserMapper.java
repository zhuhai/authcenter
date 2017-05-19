package com.zhuhai.mapper;

import com.zhuhai.entity.AuthUser;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/18
 * Time: 18:25
 */
public interface AuthUserMapper {
    void insertAuthUser(AuthUser authUser);
    void updateAuthUser(AuthUser authUser);
}
