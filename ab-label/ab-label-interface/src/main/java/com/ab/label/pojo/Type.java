package com.ab.label.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.pojo
 *  @文件名:   Type
 *  @创建者:   Unow
 *  @创建时间:  2019/1/16 0:06
 *  @描述：    TODO
 */
@Table(name = "tb_type")
@Data
public class Type {
    @Id
    private Integer id;
    private String name;

}
