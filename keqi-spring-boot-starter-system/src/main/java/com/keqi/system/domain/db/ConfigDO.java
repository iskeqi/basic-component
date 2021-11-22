package com.keqi.system.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@TableName(value = "sys_config")
public class ConfigDO {

    @NotBlank
    @ApiModelProperty("配置项key")
    private String configKey;

    @NotBlank
    @ApiModelProperty("配置项value")
    private String configValue;

    @NotBlank
    @ApiModelProperty("配置项描述信息")
    private String note;

    @ApiModelProperty("扩展字段")
    private String extra;

    @ApiModelProperty("是否禁用[0 未禁用，1 已禁用]")
    @TableField(value = "is_disable")
    private String disable;
}
