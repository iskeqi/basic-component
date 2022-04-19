package tech.taoq.sso.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.BaseDO;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_department")
public class DepartmentDO extends BaseDO {

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("部门编码")
    private String code;

    @ApiModelProperty("上级部门id")
    private String parentId;

    @ApiModelProperty("负责人id")
    private String headId;

    @ApiModelProperty("排序字段")
    private Integer orderNum;

    @ApiModelProperty("是否禁用[false:未禁用 true:已禁用]")
    @TableField(value = "is_disable")
    private Boolean disable;

    @ApiModelProperty("子级列表")
    @TableField(exist = false)
    private List<DepartmentDO> childList;
}
