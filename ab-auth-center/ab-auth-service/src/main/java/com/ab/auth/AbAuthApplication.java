package com.ab.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.auth
 *  @文件名:   AbAuthApplication
 *  @创建者:   Unow
 *  @创建时间:  2019/1/2 10:06
 *  @描述：    TODO
 */
@SpringBootApplication(scanBasePackages = "com.ab")
@EnableDiscoveryClient
@EnableFeignClients

public class AbAuthApplication {
    public static void main(String [] args){
        SpringApplication.run(AbAuthApplication.class,args);
    }
}
