package com.xjp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: maopanpan
 * @Description: SpringBoot启动项
 * @Date: 2017/10/12.
 **/
@SpringBootApplication
public class StartXjpAppService {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StartXjpAppService.class);
        app.run(args);
    }
}
