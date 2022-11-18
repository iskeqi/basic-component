package tech.taoq.sso.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.sso.domain.db.AccountDO;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountParam extends AccountDO {

	@ApiModelProperty("岗位id列表")
	private List<String> jobIdList;

	@ApiModelProperty("部门id列表")
	private List<String> departmentIdList;
}
