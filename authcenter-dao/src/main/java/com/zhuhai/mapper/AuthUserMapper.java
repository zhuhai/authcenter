package com.zhuhai.mapper;

import com.zhuhai.entity.AuthUser;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/18
 * Time: 18:25
 */
public interface AuthUserMapper {
    int insertAuthUser(AuthUser authUser);
    void updateAuthUser(AuthUser authUser);
    void deleteAuthUser(int[] ids);
    AuthUser selectAuthUserById(int id);
    AuthUser selectAuthUserByName(String userName);

    List<AuthUser> selectAll();
}
