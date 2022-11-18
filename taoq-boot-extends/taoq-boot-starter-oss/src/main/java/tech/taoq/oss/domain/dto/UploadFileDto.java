package tech.taoq.oss.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UploadFileDto {

    @ApiModelProperty("存储类型[LOCAL:本地文件系统 MINIO:minio]")
    private String storageType;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件上传url")
    private String uploadUrl;
}
