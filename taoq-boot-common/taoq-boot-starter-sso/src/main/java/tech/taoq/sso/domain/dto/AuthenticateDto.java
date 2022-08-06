package tech.taoq.sso.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.sso.domain.db.AccountDO;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthenticateDto extends AccountDO {

    @ApiModelProperty("是否登录[true:已登录 false:未登录]")
    private Boolean login;
}
