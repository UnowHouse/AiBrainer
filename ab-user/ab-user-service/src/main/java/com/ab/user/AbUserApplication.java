package com.ab.user;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.user
 *  @文件名:   AbUserApplication
 *  @创建者:   Unow
 *  @创建时间:  2019/1/1 15:00
 *  @描述：    TODO
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.ab")
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.ab.user.mapper")
public class AbUserApplication {
    public static void main(String [] args){
        SpringApplication.run(AbUserApplication.class,args);
    }
}
