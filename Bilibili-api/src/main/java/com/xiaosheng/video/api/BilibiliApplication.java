package com.xiaosheng.video.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages = {"com.xiaosheng"})
@MapperScan("com.xiaosheng.video.dao.mapper")
@SpringBootApplication
public class BilibiliApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BilibiliApplication.class, args);
    }
}
