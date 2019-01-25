package com.ab.label.web;

/*
 *  @项目名：  aibrainer
 *  @包名：    com.ab.label.web
 *  @文件名:   TaskWorkController
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-01-24 22:28
 *  @描述：    TODO
 */

import com.ab.label.service.TaskWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaskWorkController {

    @Autowired
    private TaskWorkService taskWorkService;

    @DeleteMapping("removeWoker")
    public ResponseEntity<Void> removeWoker(@CookieValue(value = "AB_TOKEN")String token,
                                            @RequestParam(value = "workerId")Long workerId,
                                            @RequestParam(value = "taskId")Long taskId){
        taskWorkService.removeWoker(token,workerId,taskId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
