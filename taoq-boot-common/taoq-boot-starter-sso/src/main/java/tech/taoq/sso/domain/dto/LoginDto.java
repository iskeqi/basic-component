package tech.taoq.sso.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class LoginDto {

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("用户id")
    private String accountId;

    @ApiModelProperty("用户昵称")
    private String name;
}
