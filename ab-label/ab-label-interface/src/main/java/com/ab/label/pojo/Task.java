package com.ab.label.pojo;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.pojo
 *  @文件名:   Task
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:35
 *  @描述：    TODO
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Integer taskType;
    private String taskName;
    private Boolean personal;
    private Long needExp;
    private String description;
    private Date created;
    private Integer sum;
    private Boolean isOver;
    private Integer needWorkers;
    private Integer joined;

    @Transient
    private List<String> classes;

    @Transient
    private List<MultipartFile> files;



}
