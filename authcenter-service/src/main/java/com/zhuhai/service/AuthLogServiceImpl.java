package com.zhuhai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthLogService;
import com.zhuhai.entity.AuthLog;
import com.zhuhai.mapper.AuthLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/26
 * Time: 13:34
 */
@Service
public class AuthLogServiceImpl implements AuthLogService {

    @Resource
    private AuthLogMapper authLogMapper;


    @Override
    public int saveAuthLog(AuthLog authLog) {
        if (authLog == null) {
            return 0;
        }
        return authLogMapper.insertAuthLog(authLog);
    }

    @Override
    public AuthLog getAuthLog(Integer id) {
        if (id == null) {
            return null;
        }
        return authLogMapper.selectAuthLogById(id);
    }

    @Override
    public PageInfo<AuthLog> listAuthLog(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthLog> authLogList = authLogMapper.selectAuthLogList();
        return new PageInfo<AuthLog>(authLogList);
    }
}
