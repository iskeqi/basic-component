package tech.taoq.oss.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import tech.taoq.mp.pojo.BaseDO;

/**
 * 文件表
 */
@TableName(value = "sys_upload_file")
public class UploadFileDO extends BaseDO {

    /**
     * 文件名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 文件存储路径[相对路径]
     */
    @TableField(value = "path")
    private String path;

    /**
     * 文件类型[Content-Type]
     */
    @TableField(value = "type")
    private String type;

    /**
     * 文件大小[单位:字节]
     */
    @TableField(value = "size")
    private Long size;

    /**
     * 存储类型[1:本地文件系统 2:minio]
     */
    @TableField(value = "storage_type")
    private String storageType;

    /**
     * 是否删除[0:未删除 1:已删除]
     */
    @TableField(value = "is_deleted")
    private String deleted;

    public enum StorageType {

        /**
         * 存储在应用程序所在的本地文件系统中
         */
        LOCAL_FILE_SYSTEM("1", "localFileSystem"),

        /**
         * 存储在 MINIO 文件系统中
         */
        MINIO("2", "minio");

        private final String code;
        private final String codeName;

        StorageType(String code, String codeName) {
            this.code = code;
            this.codeName = codeName;
        }

        public String getCode() {
            return code;
        }

        public String getCodeName() {
            return codeName;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}