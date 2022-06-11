package tech.taoq.mail.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.taoq.mp.pojo.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * MailDO
 *
 * @author keqi
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_mail")
public class MailDO extends BaseDO {

    @ApiModelProperty("邮件服务商标识")
    private String identifier;

    @ApiModelProperty("host")
    private String host;

    @ApiModelProperty("port")
    private Integer port;

    @ApiModelProperty("username")
    private String username;

    @ApiModelProperty("password")
    private String password;

    @ApiModelProperty("额外属性[以json字符串方式填写]")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Object properties;

    @ApiModelProperty("优先级[数字越大,优先级越高]")
    private Integer priority;

    @ApiModelProperty("是否可连接[0:不可连接 1:可连接]")
    @TableField(value = "is_connect")
    private String connect;

    @ApiModelProperty("是否禁用[false:未禁用 true:已禁用]")
    @TableField(value = "is_disable")
    private Boolean disable;

    @Getter
    @AllArgsConstructor
    public enum Connect {

        NOT_CONNECT("0", "不可连接"),
        CONNECT("1", "可连接");

        private final String code;
        private final String codeName;
    }
}
