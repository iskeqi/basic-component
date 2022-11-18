package tech.taoq.sso.domain.db;

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
@TableName("sys_account_department")
public class AccountDepartmentDO extends BaseDO {

    @ApiModelProperty("用户id")
    private String accountId;

    @ApiModelProperty("部门id")
    private String departmentId;
}
