package tech.taoq.oms.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AppStatus {

    @ApiModelProperty("应用状态[true:运行中 false:停止]")
    private Boolean status;
}
