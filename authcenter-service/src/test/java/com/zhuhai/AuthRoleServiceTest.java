package com.zhuhai;

import com.github.pagehelper.PageInfo;
import com.zhuhai.api.AuthRoleService;
import com.zhuhai.entity.AuthRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/24
 * Time: 14:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AuthRoleServiceTest {

    @Resource
    private AuthRoleService authRoleService;

    @Test
    public void saveAuthRole() {
        AuthRole authRole = new AuthRole();
        authRole.setCode("admin");
        authRole.setName("管理员");
        authRole.setDescription("管理员角色");
        //authRoleService.saveAuthRole(authRole);
    }

    @Test
    public void listAuthRole() {
        PageInfo<AuthRole> pageInfo = authRoleService.listAuthRole(1,10);
        Assert.assertNotNull(pageInfo);
        Assert.assertTrue(CollectionUtils.isEmpty(pageInfo.getList()));
    }
}
