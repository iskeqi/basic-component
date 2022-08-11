package tech.taoq.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * SpringTaskService
 * <p>
 * 提供动态修改定时任务的能力
 *
 * @author keqi
 */
@Service
public class SpringTaskService {

    @Autowired
    @Qualifier("myThreadPoolTaskScheduler")
    private ThreadPoolTaskScheduler taskScheduler;

    /**
     * 增加一次性延时任务
     *
     * @param task      任务
     * @param startTime 早于当前时间会立即执行，否则到了指定时间后立即执行
     * @return r
     */
    public ScheduledFuture<?> schedule(Runnable task, Date startTime) {
        return taskScheduler.schedule(task, startTime);
    }

    /**
     * 增加固定频率任务
     *
     * @param task   task
     * @param period period 单位：毫秒
     * @return r
     */
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
        return taskScheduler.scheduleAtFixedRate(task, period);
    }

    /**
     * 增加固定间隔任务
     *
     * @param task  task
     * @param delay period 单位：毫秒
     * @return r
     */
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
        return taskScheduler.scheduleWithFixedDelay(task, delay);
    }

    /**
     * 增加 cron 表达式任务
     *
     * @param runnable   task
     * @param expression cron 表达式
     * @return r
     */
    public ScheduledFuture<?> schedule(Runnable runnable, String expression) {
        CronTask cronTask = new CronTask(runnable, expression);
        return taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
    }
}
