package tech.taoq.oss.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DownloadInfoDto {

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件下载url")
    private String downloadUrl;
}
