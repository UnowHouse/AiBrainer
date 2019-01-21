package com.ab.user.vo;

import com.ab.label.pojo.Task;
import com.ab.label.pojo.TaskWork;
import com.ab.user.pojo.User;
import lombok.Data;

import java.util.List;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.user.vo
 *  @文件名:   Personal
 *  @创建者:   Unow
 *  @创建时间:  2019/1/20 0:25
 *  @描述：    TODO
 */
@Data
public class Personal {
    private User user;
    private List<Task> taskList;
    private List<TaskWork> taskWorkList;
}
