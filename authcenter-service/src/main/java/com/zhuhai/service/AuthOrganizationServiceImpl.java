package com.zhuhai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthOrganizationService;
import com.zhuhai.entity.AuthOrganization;
import com.zhuhai.mapper.AuthOrganizationMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/26
 * Time: 10:51
 */
@Service
@Transactional
public class AuthOrganizationServiceImpl implements AuthOrganizationService {

    @Resource
    private AuthOrganizationMapper authOrganizationMapper;



    @Override
    public int saveAuthOrganization(AuthOrganization authOrganization) {
        if (authOrganization == null) {
            return 0;
        }
        return authOrganizationMapper.insertAuthOrganization(authOrganization);
    }

    @Override
    public void updateAuthOrganization(AuthOrganization authOrganization) {
        if (authOrganization == null) {
            return;
        }
        authOrganizationMapper.updateAuthOrganization(authOrganization);
    }

    @Override
    public void removeAuthOrganization(int[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }
        authOrganizationMapper.deleteAuthOrganization(ids);
    }

    @Override
    public AuthOrganization getAuthOrganization(Integer id) {
        if (id == null) {
            return null;
        }
        return authOrganizationMapper.selectAuthOrganization(id);
    }

    @Override
    public PageInfo<AuthOrganization> listAuthOrganization(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthOrganization> authOrganizationList = authOrganizationMapper.selectAuthOrganizationList();
        return new PageInfo<AuthOrganization>(authOrganizationList);
    }
}
