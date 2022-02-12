package tech.taoq.mail.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "sys_mail")
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

    @ApiModelProperty("额外属性(以JSON字符串方式填写)")
    private String properties;

    @ApiModelProperty("优先级(数组越大，优先级越高)")
    private Integer priority;

    @ApiModelProperty("是否可连接[0 不可连接，1 连接]")
    @TableField(value = "is_connect")
    private Integer connect;

    @ApiModelProperty("是否禁用[0 启用，1 禁用]")
    @TableField(value = "is_disable")
    private String disable;
}
