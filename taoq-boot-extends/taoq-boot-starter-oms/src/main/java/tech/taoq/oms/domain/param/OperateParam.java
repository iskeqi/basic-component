package tech.taoq.oms.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OperateParam {

    @ApiModelProperty("应用Key")
    private String appKey;

    @ApiModelProperty("操作类型[START:启动 STOP:停止 RESTART:重启]")
    private String operate;

    public enum OPERATE {
        START, STOP, RESTART
    }
}
