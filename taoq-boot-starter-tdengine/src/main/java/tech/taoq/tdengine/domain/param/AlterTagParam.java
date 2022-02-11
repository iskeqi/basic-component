package tech.taoq.tdengine.domain.param;

/**
 * AlterTagParam
 *
 * @author keqi
 */
public class AlterTagParam {

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 超级表名称
     */
    private String stableName;

    /**
     * 标签名称（静态字段）
     */
    private String tagName;

    /**
     * 标签类型
     */
    private String tagType;

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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
