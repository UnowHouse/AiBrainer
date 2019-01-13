package com.ab.commons.advice;

import com.ab.commons.exception.AbException;
import com.ab.commons.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.commons.advice
 *  @文件名:   CommonExceptionHandler
 *  @创建者:   Unow
 *  @创建时间:  2019/1/3 9:13
 *  @描述：    TODO
 */

@ControllerAdvice

    public class CommonExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResult> handleException(AbException e){
        return ResponseEntity.status(e.getExceptionEnum().getStatus())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }

}
