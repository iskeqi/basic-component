package tech.taoq.rbac.domain.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@TableName("sys_role_menu")
public class RoleMenuDO {

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("菜单id")
    private String menuId;
}