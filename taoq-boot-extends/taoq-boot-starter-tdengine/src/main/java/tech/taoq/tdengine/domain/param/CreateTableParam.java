package tech.taoq.tdengine.domain.param;

import java.util.List;

/**
 * CreateTableParam
 *
 * @author keqi
 */
public class CreateTableParam {

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 超级表名称
     */
    private String stableName;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 标签列表
     */
    private List<Tag> tagList;

    public static class Tag {

        /**
         * 标签名称（静态字段）
         */
        private String tagName;

        /**
         * 标签类型
         */
        private String tagValue;

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

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
