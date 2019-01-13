package com.ab.label;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.label
 *  @文件名:   AbLabelApplication
 *  @创建者:   Unow
 *  @创建时间:  2019/1/1 14:59
 *  @描述：    TODO
 */
@SpringBootApplication(scanBasePackages = "com.ab")
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.ab.label.mapper")
public class AbLabelApplication {
    public static void main(String [] args){
        SpringApplication.run(AbLabelApplication.class,args);
    }


}
