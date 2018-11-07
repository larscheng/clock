package com.easy.clock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.easy.clock")
@MapperScan("com.easy.clock.dao")
@SpringBootApplication
public class ClockApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClockApplication.class, args);
    }
}
