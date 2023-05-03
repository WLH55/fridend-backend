package com.yupi.yupo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.yupi.yupo.mapper")
//
@EnableScheduling//开启定时任务
public class yupoApplication {

    public static void main(String[] args) {
        SpringApplication.run(yupoApplication.class, args);
    }

}
