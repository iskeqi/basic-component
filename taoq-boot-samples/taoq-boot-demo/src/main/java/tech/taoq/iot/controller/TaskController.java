package tech.taoq.iot.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.iot.mapper.TestDataTypeMapper;
import tech.taoq.iot.test.TestDataType;
import tech.taoq.task.SpringTaskService;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * @author keqi
 */
@Slf4j
@RestController
@RequestMapping("/test/task")
public class TaskController {

    @Autowired
    private SpringTaskService springTaskService;
    @Autowired
    private TestDataTypeMapper testDataTypeMapper;

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
}
