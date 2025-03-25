package com.ysjz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ysjz.mapper")
public class YsjzApplication {
    public static void main(String[] args) {
        SpringApplication.run(YsjzApplication.class, args);
    }
}
