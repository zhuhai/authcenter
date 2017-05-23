package com.zhuhai.service;

import com.zhuhai.api.AuthUserService;
import com.zhuhai.entity.AuthUser;
import com.zhuhai.mapper.AuthUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/18
 * Time: 22:37
 */
@Service
public class AuthUserServiceImpl implements AuthUserService{

    @Resource
    AuthUserMapper authUserMapper;

    @Override
    public int saveAuthUser(AuthUser authUser) {
        return authUserMapper.insertAuthUser(authUser);
    }

    @Override
    public void updateAuthUser(AuthUser authUser) {
        authUserMapper.updateAuthUser(authUser);
    }

    @Override
    public void removeAuthUser(int[] ids) {
        authUserMapper.deleteAuthUser(ids);
    }

    @Override
    public List<AuthUser> selectAll() {
        return authUserMapper.selectAll();
    }
}
