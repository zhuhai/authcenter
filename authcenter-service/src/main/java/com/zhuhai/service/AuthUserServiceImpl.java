package com.zhuhai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthUserService;
import com.zhuhai.entity.AuthUser;
import com.zhuhai.mapper.AuthUserMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/18
 * Time: 22:37
 */
@Service
@Transactional
public class AuthUserServiceImpl implements AuthUserService{

    @Resource
    private AuthUserMapper authUserMapper;

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
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }
        authUserMapper.deleteAuthUser(ids);
    }

    @Override
    public AuthUser getAuthUserById(Integer id) {
        if (id == null) {
            return null;
        }
        return authUserMapper.selectAuthUserById(id);
    }

    @Override
    public AuthUser getAuthUserByName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return null;
        }
        return authUserMapper.selectAuthUserByName(userName);
    }

    @Override
    public PageInfo<AuthUser> listAuthUser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthUser> authUserList = authUserMapper.selectAuthUserList();
        return new PageInfo<AuthUser>(authUserList);
    }

}
