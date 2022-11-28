package com.keqi.tbt;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.lang.management.ManagementFactory;

@Slf4j
@MapperScan("com.keqi.tbt.**.mapper")
@SpringBootApplication
public class Application {

    private static Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        Application.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        log.info("进程ID {} ,端口号 {} ,profile {}", ManagementFactory.getRuntimeMXBean().getName().split("@")[0],
                environment.getProperty("server.port"), environment.getActiveProfiles());
    }
}
