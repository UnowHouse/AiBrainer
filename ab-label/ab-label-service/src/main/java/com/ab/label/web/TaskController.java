package com.ab.label.web;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.service
 *  @文件名:   TaskController
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:55
 *  @描述：    TODO
 */

import com.ab.commons.vo.PageResult;
import com.ab.label.pojo.Task;
import com.ab.label.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 查看平台任务页面
     * @param page
     * @param rows
     * @param key
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Task>> getTasks(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                    @RequestParam(value = "rows",defaultValue = "10")Integer rows,
                                                    @RequestParam(value = "key",required = false)String key){
        return ResponseEntity.ok(taskService.selectTasks(page,rows,key));
    }

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


    /**
     * 工作者加入工作
     * @param userId
     * @param taskId
     * @param workerId
     * @return
     */
    @PutMapping("joinTask")
    public ResponseEntity<Void> joinTask(@RequestParam(value = "userId")Long userId,
                                         @RequestParam(value = "taskId")Long taskId,
                                         @RequestParam(value = "workerId")Long workerId){
        taskService.joinTask(userId,taskId,workerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("deleteTask")
    public ResponseEntity<Void> deleteTask(@RequestParam(value = "taskId")Long taskId,
                                           @CookieValue("AB_TOKEN") String token,
                                           HttpServletResponse response,
                                           HttpServletRequest request){
//        taskService.deleteTask(taskId,token,request,response);
        return null;
    }

}
