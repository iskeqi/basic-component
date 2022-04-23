package tech.taoq.rbac.domain.db;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "sys_role")
public class RoleDO extends BaseDO {

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("权限标识")
    private String permiss;

    @ApiModelProperty("角色类型[N:内置 Z:自定义]")
    private String type;

    public enum Type {
        N, Z
    }
}