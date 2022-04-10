package tech.taoq.rbac.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.BaseDO;

/**
 * 菜单权限表
 *
 * @author keqi
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_menu")
public class MenuDO extends BaseDO {

    @ApiModelProperty("名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty("请求url地址")
    @TableField(value = "url")
    private String url;

    @ApiModelProperty("icon图标")
    @TableField(value = "icon")
    private String icon;

    @ApiModelProperty("菜单类型[C:目录 M:菜单 B:按钮]")
    @TableField(value = "type")
    private String type;

    @ApiModelProperty("权限标识")
    @TableField(value = "permiss")
    private String permiss;

    @ApiModelProperty("父级id")
    @TableField(value = "parent_id")
    private String parentId;

    @ApiModelProperty("排序字段")
    @TableField(value = "order_num")
    private Integer orderNum;

    public enum Type {
        C, M, B
    }
}