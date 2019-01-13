package com.ab.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.gateway
 *  @文件名:   AbGateway
 *  @创建者:   Unow
 *  @创建时间:  2019/1/1 14:01
 *  @描述：    网关
 */
@SpringBootApplication(scanBasePackages = "com.ab")
@EnableDiscoveryClient
//@EnableHystrix
@EnableZuulProxy
public class AbGateway {
    public static void main(String [] args){
        SpringApplication.run(AbGateway.class,args);
    }
}
