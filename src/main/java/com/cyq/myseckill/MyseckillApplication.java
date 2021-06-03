package com.cyq.myseckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cyq.myseckill.mapper")
public class MyseckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyseckillApplication.class, args);
    }

}
