package com.ab.upload.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.upload.sms.config
 *  @文件名:   UploadProperties
 *  @创建者:   Unow
 *  @创建时间:  2019/1/8 20:44
 *  @描述：    TODO
 */
@Data
@ConfigurationProperties(prefix = "ab.upload")
public class UploadProperties {
    private String baseUrl;
    private Long maxSize;
    private List<String> allowTypes;
}
