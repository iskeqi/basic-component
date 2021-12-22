package com.keqi.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * ScheduleConfigurer
 *
 * @author keqi
 */
@Configuration
@EnableScheduling
public class ScheduleConfigurer implements SchedulingConfigurer {

    private static final Logger log = LoggerFactory.getLogger(ScheduleConfigurer.class);

    @Autowired
    private TaskProperties taskProperties;

    @Bean
    public ThreadPoolTaskScheduler myThreadPoolTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(taskProperties.getPoolSize());
        // if there is a task in execution, wait until the task is executed and then close the thread pool
        scheduler.setWaitForTasksToCompleteOnShutdown(taskProperties.isWaitForTasksToCompleteOnShutdown());
        // if there are still unfinished tasks after x seconds, it is also forced to close
        scheduler.setAwaitTerminationMillis(taskProperties.getAwaitTerminationMillis());
        // after the task is canceled, the task will be deleted
        scheduler.setRemoveOnCancelPolicy(taskProperties.isRemoveOnCancelPolicy());
        scheduler.setThreadNamePrefix(taskProperties.getThreadNamePrefix());
        scheduler.setErrorHandler(t -> log.error("an error occurred in the spirng task thread pool", t));
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(myThreadPoolTaskScheduler());
    }
}
