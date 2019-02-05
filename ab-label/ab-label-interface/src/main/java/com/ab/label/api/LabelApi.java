package com.ab.label.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/*
 *  @项目名：  aibrainer
 *  @包名：    com.ab.label.api
 *  @文件名:   LabelApi
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-02-03 22:08
 *  @描述：    TODO
 */
public interface LabelApi {
    @GetMapping("getPersonMsg")
    Map<String,Object> getPersonMsg(@RequestParam("token") String token);
}
