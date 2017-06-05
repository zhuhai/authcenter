package com.zhuhai.mapper;

import com.zhuhai.entity.AuthSystem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/25
 * Time: 11:40
 */
public interface AuthSystemMapper {

    int insertAuthSystem(AuthSystem authSystem);
    void updateAuthSystem(AuthSystem authSystem);
    void deleteAuthSystem(int[] ids);
    AuthSystem selectAuthSystemById(Integer id);
    List<AuthSystem> selectAuthSystemList();
    AuthSystem selectAuthSystemByName(String name);
}
