package com.zg.vueback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.zg.vueback.mapper")
public class VueBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(VueBackApplication.class, args);
    }

}
