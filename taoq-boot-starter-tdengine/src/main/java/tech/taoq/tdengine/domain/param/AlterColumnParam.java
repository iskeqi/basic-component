package tech.taoq.tdengine.domain.param;

/**
 * AlterColumnParam
 *
 * @author keqi
 */
public class AlterColumnParam {

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

    /**
     * 长度（如果数据列的类型是可变长格式（BINARY 或 NCHAR），那么可以使用此指令修改其宽度（只能改大，不能改小））
     */
    private Integer length;

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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
