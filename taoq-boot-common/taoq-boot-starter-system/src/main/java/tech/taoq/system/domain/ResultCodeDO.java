package tech.taoq.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.BaseDO;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_result_code")
public class ResultCodeDO extends BaseDO {

    @ApiModelProperty("状态码")
    private String code;

    @ApiModelProperty("状态码描述")
    private String codeName;
}
