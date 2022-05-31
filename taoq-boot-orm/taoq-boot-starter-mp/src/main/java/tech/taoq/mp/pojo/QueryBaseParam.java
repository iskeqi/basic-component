package tech.taoq.mp.pojo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 基础查询实体类[仅用于统一公共字段命名，不要求必须继承，可以拷贝需要的属性至自己的Param实体类中]
 *
 * @author keqi
 */
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

    public QueryBaseParam() {

    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getOrderFiled() {
        return orderFiled;
    }

    public void setOrderFiled(String orderFiled) {
        this.orderFiled = orderFiled;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
