package com.ab.label.client;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.client
 *  @文件名:   FileClient
 *  @创建者:   Unow
 *  @创建时间:  2019/1/8 21:34
 *  @描述：    TODO
 */

import com.ab.upload.api.FileApi;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@FeignClient(name = "file-service" ,configuration = FileClient.MultipartSupportConfig.class)
public interface FileClient extends FileApi{
    @Configuration
    class MultipartSupportConfig {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }

}
