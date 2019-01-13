package com.ab.label.pojo;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.pojo
 *  @文件名:   Datas
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:35
 *  @描述：    TODO
 */

import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "tb_datas")
@Data
public class Datas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY：主键由数据库自动生成（主要是自动增长型）
    private Long id;
    private Long taskId;
    private Long userId;
    private String dataPath;

}
