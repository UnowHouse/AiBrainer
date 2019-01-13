package com.ab.gateway.config;

/*
 *  @项目名：  ai-brainer
 *  @包名：    com.leyou.gateway.sms
 *  @文件名:   JwtProperties
 *  @创建者:   Unow
 *  @创建时间:  2018/12/26 11:06
 *  @描述：    TODO
 */

import com.ab.auth.utils.RsaUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

@ConfigurationProperties(prefix = "ab.jwt")
@Data
public class JwtProperties {
    private String pubKeyPath;// 公钥

    private PublicKey publicKey; // 公钥

    private String cookieName;

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    @PostConstruct
    public void init(){
        try {
            // 获取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            logger.error("初始化公钥失败！", e);
            throw new RuntimeException();
        }
    }
}
