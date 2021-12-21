package com.keqi.task;

import org.graalvm.compiler.nodes.memory.ReadNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.plaf.PanelUI;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * SpringTaskService
 * <p>
 * provide the ability to dynamically modify timing tasks
 *
 * @author keqi
 */
@Service
public class SpringTaskService {

    @Autowired
    private ScheduledTaskRegistrar taskRegistrar;
    private TaskScheduler taskScheduler;

    @PostConstruct
    public void init() {
        taskScheduler = taskRegistrar.getScheduler();
    }

    public ScheduledFuture schedule(Runnable task, Date startTime) {
        return taskScheduler.schedule(task, startTime);
    }

    public ScheduledFuture addFixedRateTask(Runnable task, long period) {
        return taskScheduler.scheduleAtFixedRate(task, period);
    }

    public ScheduledFuture scheduleWithFixedDelay(Runnable task, long delay) {
        return taskScheduler.scheduleWithFixedDelay(task, delay);
    }

    public ScheduledFuture schedule(Runnable runnable, String expression) {
        CronTask cronTask = new CronTask(runnable, expression);
        return taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
    }
}
