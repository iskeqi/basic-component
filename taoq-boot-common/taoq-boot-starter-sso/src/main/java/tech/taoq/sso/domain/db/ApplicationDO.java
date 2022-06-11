package tech.taoq.sso.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("sys_application")
public class ApplicationDO extends BaseDO {

    @ApiModelProperty("应用名称")
    private String name;

    @ApiModelProperty("应用编码")
    private String code;

    @ApiModelProperty("排序字段")
    private String orderNum;

    @ApiModelProperty("角色类型[N:内置 Z:自定义]")
    private String type;

    @ApiModelProperty("应用访问地址")
    private String url;

    @ApiModelProperty("是否禁用[false:未禁用 true:已禁用]")
    @TableField(value = "is_disable")
    private Boolean disable;

    public enum Type {
        N, Z
    }
}
