package tech.taoq.tdengine.domain.param;

/**
 * AddColumn
 *
 * @author keqi
 */
public class AddColumnParam {

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

    /**
     * 字段类型
     */
    private String fieldType;

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

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
