package com.ab.label.pojo;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.pojo
 *  @文件名:   Work
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:35
 *  @描述：    TODO
 */

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "tb_work")
public class Work {

    private Long dataId;
    private Long workerId;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private String label;

}
