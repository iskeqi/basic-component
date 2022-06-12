package com.sr.lsw.middle.controller;

import com.alibaba.fastjson.JSON;
import com.sr.lsw.middle.domain.db.CallbackParamDO;
import com.sr.lsw.middle.domain.dto.CommandDto;
import com.sr.lsw.middle.domain.param.CommandParam;
import com.sr.lsw.middle.service.CallbackService;
import com.sr.lsw.middle.util.CallbackParamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.taoq.web.mvc.result.NoAdvice;

import java.util.Arrays;
import java.util.function.BiFunction;

@Slf4j
@Api(tags = "自定义动作回调")
@RestController
@RequestMapping("/api/v2")
public class CallbackController {

    @NoAdvice
    @ApiOperation("回调接口")
    @PostMapping("/action/command")
    public CommandDto command(@RequestBody CommandParam param) {
        log.info("command request {}", JSON.toJSONString(param));

        // 解析回调参数 CallbackParam 对象
        CallbackParamDO callbackParamDO = CallbackParamUtil.decodeCallbackParam(param.getAction());
        if (callbackParamDO == null) {
            log.info("unable to find callbackParam based on action{}", Arrays.toString(param.getAction()));
            return CommandDto.received();
        }

        // 查找回调参数 CallbackParam 对应类型的处理方法
        BiFunction<CommandParam, CallbackParamDO, Boolean> biFunction = CallbackService.TYPE_MAP
                .get(CallbackService.CallbackParamType.valueOf(callbackParamDO.getType()));
        if (biFunction == null) {
            log.info("no callbackParam handler of type {} exists", callbackParamDO.getType());
            return CommandDto.received();
        }

        Boolean isSuccess = biFunction.apply(param, callbackParamDO);
        if (isSuccess) {
            // 若回调参数处理成功，则进行删除
            CallbackParamUtil.deleteCallbackParamById(callbackParamDO.getId());
            return CommandDto.success();
        }

        return CommandDto.received();
    }
}
