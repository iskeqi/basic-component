package tech.taoq.oms.domain.db;

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
@TableName("sys_package_record")
public class PackageRecordDO extends BaseDO {

    @ApiModelProperty("安装包文件名称")
    private String name;

    @ApiModelProperty("应用类型")
    private String type;

    @ApiModelProperty("文件大小[单位:字节]")
    private Integer size;

    @ApiModelProperty("安装包文件表id")
    private String fileId;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("当前使用版本[false:否 true:是]")
    private Boolean tag;
}
