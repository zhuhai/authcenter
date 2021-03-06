package com.zhuhai.aspect;

import com.zhuhai.db.DataSourceContextHolder;
import com.zhuhai.db.DataSourceEnum;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/20
 * Time: 15:26
 */
//@Component
//@Aspect
public class DynamicDataSourceAspect {

    //@Pointcut("execution(* com.zhuhai.mapper.*.insert*(..)) || execution(* com.zhuhai.mapper.*.update*(..)) || execution(* com.zhuhai.mapper.*.delete*(..))")
    public void masterPointcut() {}

    //@Pointcut("execution(* com.zhuhai.mapper.*.select*(..)) || execution(* com.zhuhai.mapper..*.count*(..))")
    public void slavePointcut() {}

    //@Before("masterPointcut()")
    public void switchMasterDataSource() {
        DataSourceContextHolder.setDataSource(DataSourceEnum.MASTER.getName());
    }

    //@Before("slavePointcut()")
    public void switchSlaveDataSource() {
        DataSourceContextHolder.setDataSource(DataSourceEnum.SLAVE.getName());
    }

    //@AfterReturning("masterPointcut()")
    public void clearMasterDataSource() {
        DataSourceContextHolder.clearDataSource();
    }

    //@AfterReturning("slavePointcut()")
    public void clearSlaveDataSource() {
        DataSourceContextHolder.clearDataSource();
    }
}
