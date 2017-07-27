package com.learn.demo.unit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类说明:
 *
 * @author 杨磊
 * @since 2017-07-25 下午2:31
 */
@SpringBootApplication(scanBasePackages = "com.apricotforest")
public class UnitApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnitApplication.class, args);
    }
}
