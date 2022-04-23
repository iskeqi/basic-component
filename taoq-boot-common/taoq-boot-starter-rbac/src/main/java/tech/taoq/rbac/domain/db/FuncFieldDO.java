package tech.taoq.rbac.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.BaseDO;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_func_field")
public class FuncFieldDO extends BaseDO {

    @ApiModelProperty("字段名称")
    private String name;

    @ApiModelProperty("字段编码")
    private String code;

    @ApiModelProperty("菜单id")
    private String menuId;

    @ApiModelProperty("字段类型[INPUT:文本 NUMBER:数值 BOOL:开关 DATE:日期 " +
            "DATETIME:日期时间 TIME:时间 SDICT:单字典 MDICT:多字典]")
    private String type;

    @ApiModelProperty("字典类型id")
    private String dictTypeId;

    @ApiModelProperty("排序字段")
    private Integer orderNum;

    @ApiModelProperty("是否禁用[false:未禁用 true:已禁用]")
    @TableField(value = "is_disable")
    private Boolean disable;

    public enum Type {
        INPUT, NUMBER, BOOL, DATE, DATETIME, TIME, SDICT, MDICT
    }
}