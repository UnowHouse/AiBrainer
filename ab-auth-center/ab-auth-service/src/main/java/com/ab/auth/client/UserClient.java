package com.ab.auth.client;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.auth.client
 *  @文件名:   UserClient
 *  @创建者:   Unow
 *  @创建时间:  2019/1/2 10:23
 *  @描述：    TODO
 */

import com.ab.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "user-service")
public interface UserClient extends UserApi {
}
