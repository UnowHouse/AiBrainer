package com.ab.label.web;

import com.ab.label.pojo.Classes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.service
 *  @文件名:   DatasController
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:56
 *  @描述：    TODO
 */
@RestController
public class DatasController {
    @PostMapping("test1")
    public Classes putTest(@RequestBody Classes classes){
        System.out.println(classes.getLabel());
        return classes;
    }
}
