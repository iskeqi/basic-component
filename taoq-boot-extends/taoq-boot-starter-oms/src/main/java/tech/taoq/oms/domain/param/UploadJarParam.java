package tech.taoq.oms.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UploadJarParam {

    @ApiModelProperty("文件")
    private MultipartFile uploadFile;

    @ApiModelProperty("应用类型")
    private String type;

    @ApiModelProperty("备注")
    private String note;
}
