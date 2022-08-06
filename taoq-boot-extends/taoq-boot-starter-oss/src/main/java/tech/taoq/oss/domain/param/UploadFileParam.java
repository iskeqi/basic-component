package tech.taoq.oss.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UploadFileParam {

    @ApiModelProperty("文件名称")
    private String fileName;
}
