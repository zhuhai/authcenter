package com.zhuhai.api;

import com.github.pagehelper.PageInfo;
import com.zhuhai.entity.AuthLog;
import com.zhuhai.exception.ServiceException;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/26
 * Time: 13:32
 */
public interface AuthLogService {

    /**
     * 添加日志
     * @param authLog
     * @return
     */
    int saveAuthLog(AuthLog authLog) throws ServiceException;

    /**
     * 根据id查询日志
     * @param id
     * @return
     */
    AuthLog getAuthLog(Integer id) throws ServiceException;

    /**
     * 分页查询日志列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AuthLog> listAuthLog(Integer pageNum, Integer pageSize) throws ServiceException;
}
