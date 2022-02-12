package tech.taoq.tdengine.domain.param;

import java.util.List;

/**
 * CreateStableParam
 *
 * @author keqi
 */
public class CreateStableParam {

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 字段列表
     */
    private List<Field> fieldList;

    /**
     * 标签列表
     */
    private List<Tag> tagList;

    public static class Field {

        /**
         * 字段名称（动态字段）
         */
        private String fieldName;

        /**
         * 字段类型
         */
        private String fieldType;

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

    public static class Tag {

        /**
         * 标签字段名称（静态字段）
         */
        private String tagName;

        /**
         * 标签字段类型
         */
        private String tagType;

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
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
