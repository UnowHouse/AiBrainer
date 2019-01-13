package com.ab.auth.api;

import com.ab.auth.entity.UserInfo;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.auth.api
 *  @文件名:   AuthApi
 *  @创建者:   Unow
 *  @创建时间:  2019/1/7 21:17
 *  @描述：    TODO
 */
public interface AuthApi {
    @GetMapping("verify")
    UserInfo verifyUser(@CookieValue("AB_TOKEN") String token,
                                        HttpServletResponse response,
                                        HttpServletRequest request);
}
