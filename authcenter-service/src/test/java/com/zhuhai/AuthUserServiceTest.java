package com.zhuhai;

import com.zhuhai.api.AuthUserService;
import com.zhuhai.entity.AuthUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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
    public void createUser(){
        AuthUser user = new AuthUser();
        user.setUserName("jack");
        user.setPassword("1234");
        user.setSalt("abcdsjfioewjf");
        user.setSex((byte) 0);
        user.setStatus((byte) 0);
        //authUserService.createAuthUser(user);
    }

    @Test
    public void updateUser(){
       AuthUser user = new AuthUser();
       user.setId(1);
       user.setPassword("abcd");
       user.setUserName("张三");
       //authUserService.updateAuthUser(user);
    }

}
