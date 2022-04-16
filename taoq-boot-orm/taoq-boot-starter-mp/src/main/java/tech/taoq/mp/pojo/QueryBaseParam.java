package tech.taoq.mp.pojo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 基础查询实体类[仅用于统一公共字段命名，不要求必须继承，可以拷贝需要的属性至自己的Param实体类中]
 *
 * @author keqi
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QueryBaseParam<T> extends Page<T> {

    @ApiModelProperty("搜索字段名称")
    protected String searchName;

    @ApiModelProperty("搜索字段值")
    protected String searchValue;

    @ApiModelProperty("开始日期")
    protected LocalDate beginDate;

    @ApiModelProperty("结束日期")
    protected LocalDate endDate;

    @ApiModelProperty("开始时间")
    protected LocalDateTime beginTime;

    @ApiModelProperty("结束时间")
    protected LocalDateTime endTime;

    @ApiModelProperty("排序字段")
    protected String orderFiled;

    @ApiModelProperty("排序类型[升序:asc 降序:desc]")
    protected String orderType;
}
