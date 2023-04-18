package com.yupi.yupo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yupi.yupo.mapper")
//
public class yupoApplication {

    public static void main(String[] args) {
        SpringApplication.run(yupoApplication.class, args);
    }

}
