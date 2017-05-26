package com.zhuhai.api;

import com.github.pagehelper.PageInfo;
import com.zhuhai.entity.AuthOrganization;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/26
 * Time: 10:46
 */
public interface AuthOrganizationService {

    /**
     * 添加组织
     * @param authOrganization
     * @return
     */
    int saveAuthOrganization(AuthOrganization authOrganization);

    /**
     * 修改组织
     * @param authOrganization
     */
    void updateAuthOrganization(AuthOrganization authOrganization);

    /**
     * 删除组织
     * @param ids
     */
    void removeAuthOrganization(int[] ids);

    /**
     * 根据组织id获取组织
     * @param id
     * @return
     */
    AuthOrganization getAuthOrganization(Integer id);

    /**
     * 分页获取组织列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AuthOrganization> listAuthOrganization(Integer pageNum, Integer pageSize);

}
