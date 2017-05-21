package com.zhuhai.db;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/19
 * Time: 23:32
 */
public enum DataSourceEnum {

    MASTER("masterDataSource", true),
    SLAVE("slaveDataSource", false);
    private String name;
    private boolean master;

    DataSourceEnum(String name, boolean master) {
        this.name = name;
        this.master = master;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public String getDefault() {
        String dataSource = "";
        for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
            if (!"".equals(dataSource)) {
                break;
            }
            if (dataSourceEnum.master) {
                dataSource = dataSourceEnum.getName();
            }
        }
        return dataSource;
    }
}
