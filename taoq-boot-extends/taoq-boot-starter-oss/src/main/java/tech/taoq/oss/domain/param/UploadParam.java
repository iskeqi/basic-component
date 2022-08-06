package tech.taoq.oss.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UploadParam {

    @ApiModelProperty("上传文件名称[包含路径]")
    private String fileName;

    @ApiModelProperty("文件")
    private MultipartFile uploadFile;
}
