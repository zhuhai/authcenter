package com.zhuhai.api;

import com.github.pagehelper.PageInfo;
import com.zhuhai.entity.AuthSystem;
import com.zhuhai.exception.ServiceException;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/25
 * Time: 13:55
 */
public interface AuthSystemService {
    /**
     * 添加系统
     * @param authSystem
     * @return
     */
    int saveAuthSystem(AuthSystem authSystem) throws ServiceException;

    /**
     * 修改系统
     * @param authSystem
     */
    void updateAuthSystem(AuthSystem authSystem) throws ServiceException;

    /**
     * 删除系统
     * @param ids
     */
    void deleteAuthSystem(int[] ids) throws ServiceException;

    /**
     * 根据系统id获取系统
     * @param id
     * @return
     */
    AuthSystem getAuthSystem(Integer id) throws ServiceException;

    AuthSystem getAuthSystemByName(String name) throws ServiceException;

    /**
     * 分页获取系统列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AuthSystem> listAuthSystem(Integer pageNum, Integer pageSize) throws ServiceException;
}
