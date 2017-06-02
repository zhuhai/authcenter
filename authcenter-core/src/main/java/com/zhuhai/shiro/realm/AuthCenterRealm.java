package com.zhuhai.shiro.realm;

import com.zhuhai.api.AuthPermissionService;
import com.zhuhai.api.AuthRoleService;
import com.zhuhai.api.AuthUserService;
import com.zhuhai.common.util.PropertiesUtil;
import com.zhuhai.entity.AuthRole;
import com.zhuhai.entity.AuthUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/28
 * Time: 18:06
 */
public class AuthCenterRealm extends AuthorizingRealm {

    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthRoleService authRoleService;
    @Resource
    private AuthPermissionService authPermissionService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AuthUser authUser = (AuthUser) principalCollection.getPrimaryPrincipal();
        if (authUser == null) {
            return null;
        }
        //用户的角色
        List<AuthRole> authRoleList = authRoleService.listAuthRoleByUserId(authUser.getId());
        Set<String> roleSet = new HashSet<String>();
        for (AuthRole authRole : authRoleList) {
            roleSet.add(authRole.getCode());
        }
        //用户权限
        List<String> authPermissionList = authPermissionService.listAuthPermissionByUserId(authUser.getId());
        if (CollectionUtils.isEmpty(authPermissionList)) {
            return null;
        }
        Set<String> permissionSet = new HashSet<>();
        permissionSet.addAll(authPermissionList);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(permissionSet);
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        String clientType = PropertiesUtil.getInstance("auth-client").getString("auth.type");
        //client无密认证
        if ("client".equals(clientType)) {
            return new SimpleAuthenticationInfo(userName, token.getPassword(), getName());
        }
        AuthUser authUser = authUserService.getAuthUserByName(userName);
        //用户不存在
        if (authUser == null) {
            throw new UnknownAccountException();
        }
        //用户被锁定
        if (authUser.getStatus() == 0) {
            throw new LockedAccountException();
        }
        //密码不正确
        if (!authUser.getPassword().equals(DigestUtils.sha1Hex(String.valueOf(token.getPassword()) + authUser.getSalt()))) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(authUser, token.getPassword(), getName());
    }
}
