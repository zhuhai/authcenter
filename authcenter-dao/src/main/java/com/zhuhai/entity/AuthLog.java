package com.zhuhai.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/18
 * Time: 21:33
 */
public class AuthLog implements Serializable {

    private static final long serialVersionUID = -8055652940636590281L;

    private Integer id;
    private String userName;
    private Date startTime;
    private Integer sendTime;
    private String url;
    private String ip;
    private String method;
    private String parameter;
    private String userAgent;
    private String result;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getSendTime() {
        return sendTime;
    }

    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AuthLog{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", startTime=" + startTime +
                ", sendTime=" + sendTime +
                ", url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", method='" + method + '\'' +
                ", parameter='" + parameter + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
