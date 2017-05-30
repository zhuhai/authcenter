package com.zhuhai.shiro.session;

import org.apache.shiro.session.mgt.SimpleSession;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/29
 * Time: 23:22
 */
public class AuthSession extends SimpleSession {

    public static enum OnlineStatus {
        on_line("在线"),
        off_line("离线"),
        force_logout("强制退出");
        private String info;
        private OnlineStatus(String info) {
            this.info = info;
        }
        public String getInfo() {
            return info;
        }
    }
    private String userAgent;
    private OnlineStatus onlineStatus = OnlineStatus.off_line;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(OnlineStatus onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
}
