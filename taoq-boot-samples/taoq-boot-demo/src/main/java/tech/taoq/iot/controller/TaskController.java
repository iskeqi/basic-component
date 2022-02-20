package tech.taoq.iot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/test1")
    public void test1() {
        ScheduledFuture schedule_ones = springTaskService.schedule(() -> log.info("schedule ones"),
                new Date(System.currentTimeMillis() + 10000));


        springTaskService.scheduleAtFixedRate(() -> log.info("scheduleAtFixedRate"), 3000);

        springTaskService.scheduleWithFixedDelay(() -> log.info("scheduleWithFixedDelay"), 5000);

        springTaskService.schedule(() -> log.info("schedule cron"), "*/10 * * * * ?");

        schedule_ones.cancel(true);
    }
}
