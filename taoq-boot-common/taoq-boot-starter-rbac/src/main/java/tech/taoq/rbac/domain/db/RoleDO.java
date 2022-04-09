package tech.taoq.rbac.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import tech.taoq.mp.pojo.BaseDO;

/**
 * 角色表
 *
 * @author keqi
 */
@TableName(value = "sys_role")
public class RoleDO extends BaseDO {

    /**
     * 角色名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 权限标识
     */
    @TableField(value = "permiss")
    private String permiss;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermiss() {
        return permiss;
    }

    public void setPermiss(String permiss) {
        this.permiss = permiss;
    }
}