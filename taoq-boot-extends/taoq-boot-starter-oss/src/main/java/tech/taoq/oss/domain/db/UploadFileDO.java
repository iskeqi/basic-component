package tech.taoq.oss.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.BaseDO;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_upload_file")
public class UploadFileDO extends BaseDO {

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件存储路径[相对路径]")
    @TableField(value = "path")
    private String path;

    @ApiModelProperty("文件类型[Content-Type]")
    private String type;

    @ApiModelProperty("文件大小[单位:字节]")
    private Long size;

    @ApiModelProperty("存储类型[1:本地文件系统 2:minio]")
    private String storageType;

    @ApiModelProperty("是否删除[false:未删除 true:已删除]")
    @TableField(value = "is_deleted")
    private Boolean deleted;

    @Getter
    @AllArgsConstructor
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
    }
}