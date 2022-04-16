package tech.taoq.mp.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * MyBatisPlus BaseDO 如果数据库表对应的实体类刚好有下面3个字段，可以继承此类
 *
 * @author keqi
 */
@Data
@NoArgsConstructor
public class BaseDO {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
