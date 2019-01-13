package com.ab.sms.config;

/*
 *  @项目名：  leiyou 
 *  @包名：    com.leyou.config
 *  @文件名:   SmsProperties
 *  @创建者:   Unow
 *  @创建时间:  2018/12/25 11:05
 *  @描述：    TODO
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ab.sms")
@Data
public class SmsProperties {

    String accessKeyId;

    String accessKeySecret;

    String signName;

    String verifyCodeTemplate;
}
