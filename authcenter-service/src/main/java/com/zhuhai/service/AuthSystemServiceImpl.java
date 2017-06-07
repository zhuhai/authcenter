package com.zhuhai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthSystemService;
import com.zhuhai.entity.AuthSystem;
import com.zhuhai.mapper.AuthSystemMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/25
 * Time: 13:57
 */
@Service
public class AuthSystemServiceImpl implements AuthSystemService {

    @Resource
    private AuthSystemMapper authSystemMapper;



    @Override
    public int saveAuthSystem(AuthSystem authSystem) {
        if (authSystem == null) {
            return 0;
        }
        return authSystemMapper.insertAuthSystem(authSystem);
    }

    @Override
    public void updateAuthSystem(AuthSystem authSystem) {
        authSystemMapper.updateAuthSystem(authSystem);
    }

    @Override
    public void deleteAuthSystem(int[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }
        authSystemMapper.deleteAuthSystem(ids);
    }

    @Override
    public AuthSystem getAuthSystem(Integer id) {
        if (id == null) {
            return null;
        }
        return authSystemMapper.selectAuthSystemById(id);
    }

    @Override
    public AuthSystem getAuthSystemByName(String name) {
        if (name == null) {
            return null;
        }
        return authSystemMapper.selectAuthSystemByName(name);
    }

    @Override
    public PageInfo<AuthSystem> listAuthSystem(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthSystem> authSystemList = authSystemMapper.selectAuthSystemList();
        return new PageInfo<AuthSystem>(authSystemList);
    }
}
