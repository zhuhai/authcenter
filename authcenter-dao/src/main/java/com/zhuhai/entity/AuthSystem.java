package com.zhuhai.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/18
 * Time: 21:22
 */
public class AuthSystem implements Serializable {

    private static final long serialVersionUID = -8219740074315575051L;

    private Integer id;
    /**系统名称**/
    private String name;
    /**系统标题**/
    private String title;
    /**系统描述**/
    private String description;
    /**系统状态（0：禁用 1：正常）**/
    private Byte status;
    private Date updateTime;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AuthSystem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
