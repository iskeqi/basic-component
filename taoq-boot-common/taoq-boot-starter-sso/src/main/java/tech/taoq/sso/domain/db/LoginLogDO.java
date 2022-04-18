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
@TableName(value = "sys_login_log")
public class LoginLogDO extends BaseDO {

    @ApiModelProperty("用户名称")
    private String account_name;

    @ApiModelProperty("用户id")
    private String accountId;

    @ApiModelProperty("登录ip")
    private String loginIp;

    @ApiModelProperty("日志类型[IN:登录 OUT:退出]")
    private String type;

    @ApiModelProperty("userAgent")
    private String userAgent;

    @ApiModelProperty("是否登录失败[false:登录成功 true:登录失败]")
    @TableField(value = "is_login_error")
    private Boolean loginError;

    public enum Type {
        IN, OUT
    }
}