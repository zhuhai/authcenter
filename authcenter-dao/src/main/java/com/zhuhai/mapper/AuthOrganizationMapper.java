package com.zhuhai.mapper;

import com.zhuhai.entity.AuthOrganization;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/26
 * Time: 9:43
 */
public interface AuthOrganizationMapper {

    int insertAuthOrganization(AuthOrganization authOrganization);
    void updateAuthOrganization(AuthOrganization authOrganization);
    void deleteAuthOrganization(int[] ids);
    AuthOrganization selectAuthOrganization(int id);
    List<AuthOrganization> selectAuthOrganizationList();

}
