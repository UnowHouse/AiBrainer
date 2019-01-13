package com.ab.commons.exception;

import com.ab.commons.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.commons.exception
 *  @文件名:   AbException
 *  @创建者:   Unow
 *  @创建时间:  2019/1/1 14:30
 *  @描述：    自定义异常
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
