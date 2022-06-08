package com.keqi.iot.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.keqi.iot.domain.CallbackParamDO;
import com.keqi.iot.mapper.CallbackParamMapper;
import com.keqi.iot.mapper.TestDataTypeMapper;
import com.keqi.iot.test.TestDataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.task.SpringTaskService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;

/**
 * @author keqi
 */
@Slf4j
@RestController
@RequestMapping("/test/task")
public class TaskController {

    @PostConstruct
    public void init() {
        log.info("init...");
    }

    @PreDestroy
    public void destroy() {
        log.info("destroy...");
    }

    @Autowired
    private SpringTaskService springTaskService;
    @Autowired
    private TestDataTypeMapper testDataTypeMapper;
    @Autowired
    private CallbackParamMapper callbackParamMapper;

    @GetMapping("/test1")
    public void test1() {
        ScheduledFuture schedule_ones = springTaskService.schedule(() -> log.info("schedule ones"),
                new Date(System.currentTimeMillis() + 10000));


        springTaskService.scheduleAtFixedRate(() -> log.info("scheduleAtFixedRate"), 3000);

        springTaskService.scheduleWithFixedDelay(() -> log.info("scheduleWithFixedDelay"), 5000);

        springTaskService.schedule(() -> log.info("schedule cron"), "*/10 * * * * ?");

        schedule_ones.cancel(true);
    }

    @PostMapping("/test2")
    public TestDataType test2(@RequestBody TestDataType param) {
        if (StpUtil.hasPermission("user-add")) {
            log.info("有 user-add 权限");
        }

        testDataTypeMapper.insert(param);

        TestDataType r = testDataTypeMapper.selectById(param.getId());
        return r;
    }

    @PostMapping("/test3")
    public void test3() {
        CallbackParamDO paramDO = new CallbackParamDO();
        paramDO.setType(UUID.randomUUID().toString());

        callbackParamMapper.insert(paramDO);
    }

    @PostMapping("/test4")
    public CallbackParamDO test4() {
        return callbackParamMapper.selectById("1531595820796977154");
    }
}
