package com.ab.label.web;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.service
 *  @文件名:   TaskController
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:55
 *  @描述：    TODO
 */

import com.ab.label.pojo.Task;
import com.ab.label.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 发布任务
     * @param task
     * @return
     */
    @PostMapping("addTask")
    public ResponseEntity<Void> releaseTask(@ModelAttribute Task task){
        taskService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
