package com.keqi.tdengine.domain.param;

/**
 * AlterTagValueParam
 *
 * @author keqi
 */
public class AlterTagValueParam {

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 子表名称
     */
    private String tableName;

    /**
     * 标签名称（静态字段）
     */
    private String tagName;

    /**
     * 标签值
     */
    private String tagValue;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }
}
