package com.zhuhai.mapper;

import com.zhuhai.entity.AuthLog;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/26
 * Time: 11:29
 */
public interface AuthLogMapper {
    int insertAuthLog(AuthLog authLog);
    AuthLog selectAuthLogById(Integer id);
    List<AuthLog> selectAuthLogList();
}
