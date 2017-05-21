package com.zhuhai.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/19
 * Time: 23:10
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private final static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DataSourceContextHolder.getDataSource();
        logger.info("当前使用的数据源为：{}",dataSource);
        return dataSource;
    }

}
