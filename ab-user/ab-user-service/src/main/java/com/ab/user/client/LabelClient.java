package com.ab.user.client;

import com.ab.label.api.LabelApi;
import org.springframework.cloud.openfeign.FeignClient;

/*
 *  @项目名：  aibrainer
 *  @包名：    com.ab.user.client
 *  @文件名:   LabelClient
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-02-03 22:19
 *  @描述：    TODO
 */
@FeignClient("label-service")
public interface LabelClient extends LabelApi {
}
