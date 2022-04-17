package tech.taoq.system.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.BaseDO;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_config")
public class ConfigDO extends BaseDO {

    @ApiModelProperty("配置项key")
    private String configKey;

    @ApiModelProperty("配置项value")
    private String configValue;

    @ApiModelProperty("配置项描述信息")
    private String note;

    @ApiModelProperty("扩展字段")
    private String extra;

    @ApiModelProperty("是否禁用[false:未禁用 true:已禁用]")
    @TableField(value = "is_disable")
    private Boolean disable;
}
