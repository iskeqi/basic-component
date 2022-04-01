package tech.taoq.rbac.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import tech.taoq.mp.pojo.BaseDO;

/**
 * 菜单权限表
 *
 * @author keqi
 */
@TableName(value = "sys_menu")
public class MenuDO extends BaseDO {

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 请求URL地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * ICON图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 菜单类型[C:目录 M:菜单 B:按钮]
     */
    @TableField(value = "type")
    private String type;

    /**
     * 权限标识
     */
    @TableField(value = "permiss")
    private String permiss;

    /**
     * 父级ID
     */
    @TableField(value = "parent_id")
    private String parentId;

    /**
     * 排序字段
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 菜单类型[C:目录 M:菜单 B:按钮]
     */
    public enum Type {
        C, M, B
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPermiss() {
        return permiss;
    }

    public void setPermiss(String permiss) {
        this.permiss = permiss;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}