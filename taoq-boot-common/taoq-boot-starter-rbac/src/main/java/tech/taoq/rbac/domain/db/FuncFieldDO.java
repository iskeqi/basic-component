package tech.taoq.rbac.domain.db;

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
@TableName(value = "sys_func_field")
public class FuncFieldDO extends BaseDO {

	@ApiModelProperty("字段名称")
	private String name;

	@ApiModelProperty("字段编码")
	private String code;

	@ApiModelProperty("菜单id")
	private String menuId;

	@ApiModelProperty("字段类型[INPUT:文本 NUMBER:数值 BOOL:开关 DATE:日期 DATETIME:日期时间 TIME:时间 SDICT:单字典 MDICT:多字典]")
	private String type;

	@ApiModelProperty("是否支持查询[false:不支持 true:支持]")
	@TableField(value = "is_query")
	private Boolean query;

	@ApiModelProperty("字典类型id")
	private String dictTypeId;

	@ApiModelProperty("排序字段")
	private Integer orderNum;

	public enum Type {
		INPUT, NUMBER, BOOL, DATE, DATETIME, TIME, SDICT, MDICT
	}

	/**
	 * 字段支持的查询类型[bean-searcher 中内置的查询类型]
	 */
	public enum SelectType {
		EQUAL, NOT_EQUAL, GREATER_THAN, GREATER_EQUAL, LESS_THAN, LESS_EQUAL, BETWEEN, NOT_BETWEEN,
		CONTAIN, START_WITH, END_WITH, IN_LIST, NOT_IN, IS_NULL, NOT_NULL, EMPTY, NOT_EMPTY,
	}
}