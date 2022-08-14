package tech.taoq.oms.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LogFileListDto {

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件大小[单位/字节]")
    private String size;

    @ApiModelProperty("文件最后更改时间")
    private LocalDateTime lastUpdateTime;
}
