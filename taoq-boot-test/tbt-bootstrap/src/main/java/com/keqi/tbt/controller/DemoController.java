package com.keqi.tbt.controller;

import com.alibaba.fastjson.JSON;
import com.keqi.tbt.domain.param.Test1Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @PostMapping("/test1")
    public void test1(@Validated @RequestBody Test1Param param) {
        log.info(JSON.toJSONString(param));
    }
}
