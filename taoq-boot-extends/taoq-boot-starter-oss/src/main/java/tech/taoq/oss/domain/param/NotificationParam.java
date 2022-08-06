package tech.taoq.oss.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class NotificationParam{

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件存储路径[相对路径]")
    private String path;

    @ApiModelProperty("文件类型[Content-Type]")
    private String type;

    @ApiModelProperty("文件大小[单位:字节]")
    private Long size;
}