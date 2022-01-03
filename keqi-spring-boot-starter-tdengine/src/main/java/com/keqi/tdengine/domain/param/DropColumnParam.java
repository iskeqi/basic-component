package com.keqi.tdengine.domain.param;

/**
 * DropColumnParam
 *
 * @author keqi
 */
public class DropColumnParam {

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 超级表名称
     */
    private String stableName;

    /**
     * 字段名称（动态字段）
     */
    private String fieldName;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getStableName() {
        return stableName;
    }

    public void setStableName(String stableName) {
        this.stableName = stableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
