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

}
