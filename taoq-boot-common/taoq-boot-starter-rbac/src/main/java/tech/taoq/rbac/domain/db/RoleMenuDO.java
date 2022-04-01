package tech.taoq.rbac.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色-菜单关联表
 *
 * @author keqi
 */
@TableName(value = "sys_role_menu")
public class RoleMenuDO {

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private String roleId;

    /**
     * 菜单id
     */
    @TableField(value = "menu_id")
    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}