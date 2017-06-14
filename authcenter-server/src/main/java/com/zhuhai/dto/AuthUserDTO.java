package com.zhuhai.dto;

import com.zhuhai.entity.AuthOrganization;
import com.zhuhai.entity.AuthRole;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/6/14
 * Time: 14:34
 */
public class AuthUserDTO implements Serializable {
    private static final long serialVersionUID = 343879699461823791L;

    private Integer id;
    private String userName;
    private String phone;
    private String email;
    private Byte sex;
    private String avatar;
    private String realName;
    private Byte status;
    private String createTime;
    private List<AuthRole> roles;
    private AuthOrganization organization;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<AuthRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AuthRole> roles) {
        this.roles = roles;
    }

    public AuthOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(AuthOrganization organization) {
        this.organization = organization;
    }
}
