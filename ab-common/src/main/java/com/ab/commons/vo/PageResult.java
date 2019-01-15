package com.ab.commons.vo;

import lombok.Data;

import java.util.List;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.commons.vo
 *  @文件名:   PageResult
 *  @创建者:   Unow
 *  @创建时间:  2019/1/15 23:37
 *  @描述：    TODO
 */
@Data
public class PageResult<T> {
    private Long total;
    private Integer totalPage;
    private List<T> items;

    public PageResult(){

    }
    public PageResult(Long total,List<T>items){
        this.total=total;
        this.items=items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

}
