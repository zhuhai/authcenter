package com.zhuhai.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/5/26
 * Time: 11:27
 */
public class AuthUserOrganization implements Serializable {
    private static final long serialVersionUID = 5366609516729169145L;

    private Integer userId;
    private Integer organizationId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
}
