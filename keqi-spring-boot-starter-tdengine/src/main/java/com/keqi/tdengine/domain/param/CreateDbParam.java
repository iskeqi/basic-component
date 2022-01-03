package com.keqi.tdengine.domain.param;

/**
 * CreateDbParam
 *
 * @author keqi
 */
public class CreateDbParam {

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 数据保存时长(单位：天)
     */
    private Integer keep = 3650;

    /**
     * 每多少天一个数据文件
     */
    private Integer days = 1;

    /**
     * 数据库支持更新相同时间戳数据
     * 0 : 表示不允许更新数据，后发送的相同时间戳的数据会被直接丢弃
     * 1 : 表示更新全部数据列，如果更新一个数据，其中某些列没有提供取值，那么这些列会被设为 NULL
     * 2 : 表示支持更新部分列数据，如果更新一个数据，其中某些列没有提供取值，那么这些列会保持原有数据行中的对应值
     */
    private Integer update = 0;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Integer getKeep() {
        return keep;
    }

    public void setKeep(Integer keep) {
        this.keep = keep;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getUpdate() {
        return update;
    }

    public void setUpdate(Integer update) {
        this.update = update;
    }
}
