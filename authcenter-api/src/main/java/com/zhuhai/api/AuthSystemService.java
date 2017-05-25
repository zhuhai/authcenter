package com.zhuhai.api;

import com.github.pagehelper.PageInfo;
import com.zhuhai.entity.AuthSystem;

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
    int saveAuthSystem(AuthSystem authSystem);

    /**
     * 修改系统
     * @param authSystem
     */
    void updateAuthSystem(AuthSystem authSystem);

    /**
     * 删除系统
     * @param ids
     */
    void deleteAuthSystem(int[] ids);

    /**
     * 根据系统id获取系统
     * @param id
     * @return
     */
    AuthSystem getAuthSystem(Integer id);

    /**
     * 分页获取系统列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AuthSystem> listAuthSystem(Integer pageNum, Integer pageSize);
}
