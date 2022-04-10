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
 * 角色表
 *
 * @author keqi
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role")
public class RoleDO extends BaseDO {

    @ApiModelProperty("角色名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty("权限标识")
    @TableField(value = "permiss")
    private String permiss;

    @ApiModelProperty("角色类型[N:内置 Z:自定义]")
    @TableField(value = "type")
    private String type;

    public enum Type {
        N, Z
    }
}