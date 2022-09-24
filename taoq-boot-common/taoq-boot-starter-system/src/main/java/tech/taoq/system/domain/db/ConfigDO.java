package tech.taoq.system.domain.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.BaseDO;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_config")
public class ConfigDO extends BaseDO {

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("配置key")
    private String configKey;

    @ApiModelProperty("配置名称")
    private String displayName;

    @ApiModelProperty("配置描述")
    private String displayDescription;

    @ApiModelProperty("配置值类型[INPUT:文本 NUMBER:数值 BOOL:开关 DATE:日期 DATETIME:日期时间 TIME:时间 SDICT:单字典 MDICT:多字典]")
    private String valueType;

    @ApiModelProperty("配置值")
    private String configValue;

    @ApiModelProperty("配置默认值")
    private String defaultValue;

    @ApiModelProperty("配置值单位")
    private String valueUnit;

    @ApiModelProperty("配置值取值范围")
    private String valueRange;

    @ApiModelProperty("是否删除[false:否 true:是]")
    private Boolean deleted;

    public enum ValueType {
        INPUT, NUMBER, BOOL, DATE, DATETIME, TIME, SDICT, MDICT
    }
}
