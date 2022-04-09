package tech.taoq.rbac.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户-角色关联表
 *
 * @author keqi
 */
@TableName(value = "sys_account_role")
public class AccountRoleDO {

    /**
     * 用户唯一标识符
     */
    @TableField(value = "account_identifier")
    private String accountIdentifier;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private String roleId;

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}