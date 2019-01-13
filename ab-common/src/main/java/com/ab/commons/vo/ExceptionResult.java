package com.ab.commons.vo;

import com.ab.commons.enums.ExceptionEnum;
import lombok.Data;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.commons.vo
 *  @文件名:   ExceptionResult
 *  @创建者:   Unow
 *  @创建时间:  2019/1/1 14:26
 *  @描述：    异常结果
 */
@Data
public class ExceptionResult {

    private int status;
    private String messge;
    private Long timestamp;
    public ExceptionResult(ExceptionEnum em){
        this.status = em.getStatus();
        this.messge = em.getMessage();
        this.timestamp = System.currentTimeMillis();
    }

}
