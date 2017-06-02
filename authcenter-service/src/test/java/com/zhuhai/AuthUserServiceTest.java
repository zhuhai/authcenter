package com.zhuhai;

import com.zhuhai.api.AuthUserService;
import com.zhuhai.entity.AuthUserOrganization;
import com.zhuhai.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/19
 * Time: 11:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class AuthUserServiceTest {

    @Resource
    private AuthUserService authUserService;

    @Test
    public void createUser() throws ServiceException {
        /*AuthUser user = new AuthUser();
        user.setUserName("张三");
        user.setPassword("123456");
        user.setSalt("abcdsjfioewjf");
        user.setSex((byte) 0);
        user.setStatus((byte) 0);
        int result = authUserService.saveAuthUser(user);
        System.out.println("插入数量："+result);*/

    }

    @Test
    public void updateUser(){
       /*AuthUser user = new AuthUser();
       user.setId(1);
       user.setSex((byte) 1);
       authUserService.updateAuthUser(user);*/
    }

    @Test
    public void deleteUser() {
        /*int[] ids = {12};
        authUserService.removeAuthUser(ids);*/
    }

    @Test
    public void selectAllUser() {
        /*PageInfo<AuthUser> pageInfo = authUserService.listAuthUser(1,10);
        System.out.println(pageInfo.getSize());
        Assert.assertFalse(CollectionUtils.isEmpty(pageInfo.getList()));*/
    }

    @Test
    public void getAuthUser() {
        /*AuthUser authUser = authUserService.getAuthUserById(1);
        Assert.assertNotNull(authUser);*/
    }

    @Test
    public void saveAuthUserRole() throws ServiceException {

        /*AuthUser user = new AuthUser();
        user.setUserName("jack");
        user.setPassword("123456");
        user.setSalt("abcdsjfioewjf");
        user.setSex((byte) 0);
        user.setStatus((byte) 0);
        int result = authUserService.saveAuthUser(user);
        System.out.println("插入数量："+result);
        List<AuthUserRole> authUserRoles = new ArrayList<>();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(1);
        authUserRole.setRoleId(1);
        AuthUserRole authUserRole2 = new AuthUserRole();
        authUserRole2.setUserId(1);
        authUserRole2.setRoleId(2);
        authUserRoles.add(authUserRole);
        authUserRoles.add(authUserRole2);
        int count = authUserService.saveAuthUserRoles(authUserRoles);
        System.out.println(count);*/

    }

    @Test
    public void saveAuthUserOrganization() {
        List<AuthUserOrganization> authUserOrganizations = new ArrayList<>();
        AuthUserOrganization authUserOrganization = new AuthUserOrganization();
        authUserOrganization.setUserId(1);
        authUserOrganization.setOrganizationId(2);
        authUserOrganizations.add(authUserOrganization);
        //authUserService.saveAuthUserOrganizations(authUserOrganizations);
    }

}
