package com.zhuhai;

import com.zhuhai.api.AuthPermissionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/25
 * Time: 15:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AuthPermissionServiceTest {

    @Resource
    private AuthPermissionService authPermissionService;

    @Test
    public void listAuthPermission() {

        List<String> permissions = authPermissionService.listAuthPermissionByUserId(1);
        System.out.println(permissions.size());
        for (String permission : permissions) {
            System.out.println(permission);
        }
        Assert.assertFalse(CollectionUtils.isEmpty(permissions));


    }

    @Test
    public void saveAuthPermission() {
        /*AuthPermission authPermission = new AuthPermission();
        authPermission.setCode("authcenter:user:create");
        authPermission.setName("添加用户");
        authPermission.setOrders(3);
        authPermission.setPid(1);
        authPermission.setType((byte) 3);
        authPermission.setStatus((byte) 1);
        authPermission.setSystemId(1);
        System.out.println("userId:"+authPermission.getId());
        int count = authPermissionService.saveAuthPermission(authPermission);
        System.out.println("count:"+count);
        System.out.println("userId:"+authPermission.getId());*/
    }

}
