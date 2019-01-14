package com.ab.label.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.pojo
 *  @文件名:   TaskWork
 *  @创建者:   Unow
 *  @创建时间:  2019/1/14 17:23
 *  @描述：    TODO
 */
@Table(name = "tb_task_work")
@Data
public class TaskWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long taskId;
    private Long workerId;
    private Integer adaptNum;
}
