package com.zhuhai.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/28
 * Time: 23:21
 */
public class PropertiesUtil {

    private Date loadTime;
    private ResourceBundle resourceBundle;
    //默认资源名称
    private static final String NAME = "config";
    //缓存时间
    private static final Integer TIME_OUT = 60*1000;
    private static Map<String,PropertiesUtil> configMap = new HashMap<String,PropertiesUtil>();

    private PropertiesUtil(String name) {
        this.loadTime = new Date();
        this.resourceBundle = ResourceBundle.getBundle(name);
    }

    public static synchronized PropertiesUtil getInstance() {
        return getInstance(NAME);
    }

    public static synchronized PropertiesUtil getInstance(String name) {
        PropertiesUtil config = configMap.get(name);
        if (config == null) {
            config = new PropertiesUtil(name);
            configMap.put(name, config);
        }
        //判断文件打开时间是否超过一分钟
        if (System.currentTimeMillis() - config.getLoadTime().getTime() > TIME_OUT) {
            config = new PropertiesUtil(name);
            configMap.put(name, config);
        }
        return config;
    }


    public String getString(String key) {
        try {
            String value = resourceBundle.getString(key);
            return value;
        } catch (Exception e) {
           return null;
        }
    }

    public Integer getInt(String key) {
        try {
            String value = resourceBundle.getString(key);
            return Integer.valueOf(value);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean getBoolean(String key) {
        try {
            String value = resourceBundle.getString(key);
            if ("true".equals(value)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Date getLoadTime() {
        return loadTime;
    }

}
