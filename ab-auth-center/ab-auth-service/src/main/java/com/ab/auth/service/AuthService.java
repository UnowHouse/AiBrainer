package com.ab.auth.service;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.auth.service
 *  @文件名:   AuthService
 *  @创建者:   Unow
 *  @创建时间:  2019/1/2 10:31
 *  @描述：    TODO
 */

import com.ab.auth.client.UserClient;
import com.ab.auth.config.JwtProperties;
import com.ab.auth.entity.UserInfo;
import com.ab.auth.utils.JwtUtils;
import com.ab.commons.enums.ExceptionEnum;
import com.ab.commons.exception.AbException;
import com.ab.user.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthService {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserClient userClient;

    public String login(String username, String password) {

        try {
            User user = userClient.queryUser(username,password);
            if(user == null){
                throw new AbException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
            }
            UserInfo userInfo = new UserInfo(user.getId(), user.getUsername());
            String token = JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            return token;
        }catch (Exception e){
            log.error("【授权中心】用户名和密码错误，用户名：{}", username);
//            throw new AbException(ExceptionEnum.CREATE_TOKEN_ERROR);
            throw new AbException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
        }
    }
}
