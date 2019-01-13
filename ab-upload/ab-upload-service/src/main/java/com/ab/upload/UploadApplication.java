package com.ab.upload;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.upload
 *  @文件名:   UploadApplication
 *  @创建者:   Unow
 *  @创建时间:  2019/1/2 21:58
 *  @描述：    TODO
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.ab")
@EnableDiscoveryClient
public class UploadApplication {

    public static void main(String [] args){
        SpringApplication.run(UploadApplication.class,args);
    }
}
