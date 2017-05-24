package com.zhuhai.api;

import com.zhuhai.entity.AuthRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/24
 * Time: 14:16
 */
public interface AuthRoleService {

    int saveAuthRole(AuthRole authRole);
    void updateAuthRole(AuthRole authRole);
    void removeAuthRole(int[] ids);
    AuthRole getAuthRole(Integer id);
    List<AuthRole> listAuthRoleByUserId(Integer userId);
    List<AuthRole> listAuthRole();

}
