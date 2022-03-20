package tech.taoq.system.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.BaseDO;

import javax.validation.constraints.NotNull;

/**
 * 字典表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName(value = "sys_dict_item")
public class DictItemDO extends BaseDO {

    @NotNull
    @ApiModelProperty("字典类型编码")
    private String typeCode;

    @NotNull
    @ApiModelProperty("字典项编码")
    private String itemCode;

    @ApiModelProperty("字典类型名称")
    private String typeName;

    @NotNull
    @ApiModelProperty("字典项值")
    private String itemName;

    @NotNull
    @ApiModelProperty("排序字段")
    private Integer orderNum;

    @ApiModelProperty("是否禁用[0:未禁用 1:已禁用]")
    @TableField(value = "is_disable")
    private String disable;
}