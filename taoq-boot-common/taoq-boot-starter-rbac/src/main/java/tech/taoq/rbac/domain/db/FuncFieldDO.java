package tech.taoq.rbac.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.BaseDO;

import java.util.Map;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_func_field")
public class FuncFieldDO extends BaseDO {

    @ApiModelProperty("字段名称")
    private String name;

    @ApiModelProperty("字段编码")
    private String code;

    @ApiModelProperty("回显字段编码")
    private String echoCode;

    @ApiModelProperty("菜单id")
    private String menuId;

    @ApiModelProperty("是否可新增[false:不可以 true:可以]")
    private Boolean create;

    @ApiModelProperty("是否可编辑[false:不可以 true:可以]")
    private Boolean update;

    @ApiModelProperty("是否可展示[false:不可以 true:可以]")
    private Boolean show;

    @ApiModelProperty("是否多选[false:不可以 true:可以]")
    private Boolean multiple;

    @ApiModelProperty("查询类型[使用英文逗号分隔,为null时不支持查询]")
    private String queryType;

    @ApiModelProperty("字段类型[INPUT:文本 NUMBER:数值 DATE:日期 DATETIME:日期时间 TIME:时间 DICT:字典 HTTP:接口]")
    private String type;

    @ApiModelProperty("字典类型编码")
    private String dictTypeCode;

    @ApiModelProperty("请求参数json字符串")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> requestParam;

    @ApiModelProperty("排序字段")
    private Integer orderNum;

    @ApiModelProperty("是否删除[false:未删除 true:已删除]")
    private Boolean deleted;

    public enum Type {
        INPUT, NUMBER, DATE, DATETIME, TIME, DICT, HTTP
    }

    /**
     * 字段支持的查询类型[bean-searcher 中内置的查询类型]
     * <p>
     * https://searcher.ejlchina.com/guide/latest/params.html
     */
    public enum SelectType {
        EQ, NE, GT, GE, LT, LE, BT, NB, CT, SW, EW, IL, NI, NL, NN, EY, NY,
    }
}