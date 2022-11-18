package com.keqi.tbt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

@MapperScan("com.keqi.tbt.**.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        CachingOperationNameGenerator cachingOperationNameGenerator;
        SpringApplication.run(Application.class, args);
    }
}
