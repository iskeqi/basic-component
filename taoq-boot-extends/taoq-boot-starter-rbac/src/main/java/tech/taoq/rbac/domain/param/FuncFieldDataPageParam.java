package tech.taoq.rbac.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class FuncFieldDataPageParam {

    @ApiModelProperty("第几页")
    private long current = 1;

    @ApiModelProperty("每页数量")
    private long size = 10;

    @ApiModelProperty(value = "偏移量", hidden = true)
    private long offset;

    @ApiModelProperty("菜单id")
    private String menuId;

    @ApiModelProperty(value = "权限标识", hidden = true)
    private String menuPermiss;

    @ApiModelProperty("搜索字段")
    private List<Param> paramList;

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class Param {

        @ApiModelProperty("参数编码")
        private String code;

        @ApiModelProperty("参数值")
        private String value;
    }

    public long calculateOffset() {
        if (current <= 1L) {
            return 0L;
        }
        return Math.max((current - 1) * size, 0L);
    }
}
