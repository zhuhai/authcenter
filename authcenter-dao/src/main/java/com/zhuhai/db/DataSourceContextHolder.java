package com.zhuhai.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/19
 * Time: 23:25
 */
public class DataSourceContextHolder {

    private final static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);
    private final static ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDataSource(String dataSource) {
        logger.info("切换数据源为：{}", dataSource);
        contextHolder.set(dataSource);
    }

    public static String getDataSource() {
        String dataSource = contextHolder.get();
        if (dataSource == null) {
            setDataSource(DataSourceEnum.MASTER.getDefault());
        }
        return contextHolder.get();
    }

    public static void clearDataSource() {
        logger.info("清除数据源：{}", contextHolder.get());
        contextHolder.remove();
    }
}
