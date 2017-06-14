package com.zhuhai.api;

import com.github.pagehelper.PageInfo;
import com.zhuhai.entity.AuthOrganization;
import com.zhuhai.exception.ServiceException;

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
    int saveAuthOrganization(AuthOrganization authOrganization) throws ServiceException;

    /**
     * 修改组织
     * @param authOrganization
     */
    void updateAuthOrganization(AuthOrganization authOrganization) throws ServiceException;

    /**
     * 删除组织
     * @param ids
     */
    void removeAuthOrganization(int[] ids) throws ServiceException;

    /**
     * 根据组织id获取组织
     * @param id
     * @return
     */
    AuthOrganization getAuthOrganization(Integer id) throws ServiceException;

    /**
     * 分页获取组织列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AuthOrganization> listAuthOrganization(Integer pageNum, Integer pageSize) throws ServiceException;

    AuthOrganization getAuthOrganizationByUserId(Integer userId) throws ServiceException;

}
