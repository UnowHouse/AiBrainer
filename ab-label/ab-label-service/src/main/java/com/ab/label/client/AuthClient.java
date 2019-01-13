package com.ab.label.client;

import com.ab.auth.api.AuthApi;
import org.springframework.cloud.openfeign.FeignClient;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.client
 *  @文件名:   AuthClient
 *  @创建者:   Unow
 *  @创建时间:  2019/1/7 21:24
 *  @描述：    TODO
 */

@FeignClient("auth-service")
public interface AuthClient extends AuthApi {
}
