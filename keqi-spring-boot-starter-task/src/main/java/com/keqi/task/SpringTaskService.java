package com.keqi.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Service;

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
    private TaskScheduler taskScheduler;

    public ScheduledFuture schedule(Runnable task, Date startTime) {
        return taskScheduler.schedule(task, startTime);
    }

    public ScheduledFuture scheduleAtFixedRate(Runnable task, long period) {
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
