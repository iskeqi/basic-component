package tech.taoq.oss.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.BaseDO;

/**
 * 文件表
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
    private Boolean deleted;

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
}