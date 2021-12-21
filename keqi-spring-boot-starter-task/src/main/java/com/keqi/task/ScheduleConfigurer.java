package com.keqi.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class ScheduleConfigurer implements SchedulingConfigurer {

    private static final Logger log = LoggerFactory.getLogger(TaskAutoConfiguration.class);

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        // if there is a task in execution, wait until the task is executed and then close the thread pool
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        // if there are still unfinished tasks after x seconds, it is also forced to close
        scheduler.setAwaitTerminationSeconds(10);
        // after the task is canceled, the task will be deleted
        scheduler.setRemoveOnCancelPolicy(true);
        scheduler.setThreadNamePrefix("spirng-task-");
        scheduler.setErrorHandler(t -> log.error("an error occurred in the spirng task thread pool", t));
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(threadPoolTaskScheduler());
    }
}