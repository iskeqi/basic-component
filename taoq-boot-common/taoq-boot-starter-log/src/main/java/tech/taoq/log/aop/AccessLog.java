package tech.taoq.log.aop;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.common.util.DateUtil;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class AccessLog {

    @ApiModelProperty("用户唯一标识符")
    private String userIdentifier;

    @ApiModelProperty("请求url")
    private String uri;

    @ApiModelProperty("请求类型")
    private String method;

    @ApiModelProperty("类名称")
    private String className;

    @ApiModelProperty("方法名称")
    private String methodName;

    @ApiModelProperty("请求参数")
    private Object reqBody;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = DateUtil.LOCAL_DATE_TIME_SSS)
    private LocalDateTime startTime;

    @ApiModelProperty("响应信息")
    private Object respBody;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = DateUtil.LOCAL_DATE_TIME_SSS)
    private LocalDateTime endTime;

    @ApiModelProperty("请求耗时")
    private String consumeTime;
}
