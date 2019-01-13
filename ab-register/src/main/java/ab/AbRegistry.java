package ab;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab
 *  @文件名:   AbRegistry
 *  @创建者:   Unow
 *  @创建时间:  2019/1/1 13:59
 *  @描述：    服务注册中心
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AbRegistry {
    public static void main(String [] args){
        SpringApplication.run(AbRegistry.class,args);
    }
}
