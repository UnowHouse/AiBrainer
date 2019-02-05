package com.ab.label.pojo;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.pojo
 *  @文件名:   Work
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:35
 *  @描述：    TODO
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "tb_work")
public class Work {

    private Long dataId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long workerId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int minX;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int minY;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int maxX;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int maxY;
    private String label;

}
