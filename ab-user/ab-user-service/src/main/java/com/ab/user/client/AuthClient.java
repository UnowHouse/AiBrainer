package com.ab.user.client;

import com.ab.auth.api.AuthApi;
import org.springframework.cloud.openfeign.FeignClient;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.user.client
 *  @文件名:   AuthClient
 *  @创建者:   Unow
 *  @创建时间:  2019/1/20 0:31
 *  @描述：    TODO
 */
@FeignClient(value = "auth-service")
public interface AuthClient extends AuthApi {
}
