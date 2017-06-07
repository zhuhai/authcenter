package com.zhuhai.db;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/6/7
 * Time: 15:12
 */
public class DynamicDataSourceTransationManager extends DataSourceTransactionManager {


    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        boolean readOnly = definition.isReadOnly();
        if (readOnly) {
            DataSourceContextHolder.setDataSource(DataSourceEnum.SLAVE.getName());
        } else {
            DataSourceContextHolder.setDataSource(DataSourceEnum.MASTER.getName());
        }

        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        DataSourceContextHolder.clearDataSource();
    }
}
