package com.zhuhai.mapper;

import com.zhuhai.entity.AuthUserOrganization;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/27
 * Time: 10:18
 */
public interface AuthUserOrganizationMapper {

    int insertAuthUserOrganizations(List<AuthUserOrganization> authUserOrganizations);
    void deleteAuthUserOrganizationsByUserId(Integer userId);
    void updateAuthUserOrganization(AuthUserOrganization authUserOrganization);

    void deleteAuthUserOrganizationsByOrganizationId(Integer organizationId);
}
