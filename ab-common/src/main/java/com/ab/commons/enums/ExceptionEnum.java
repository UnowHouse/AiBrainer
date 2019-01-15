package com.ab.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.commons.enums
 *  @文件名:   ExceptionEnum
 *  @创建者:   Unow
 *  @创建时间:  2019/1/1 14:27
 *  @描述：    异常种类枚举
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {
    USER_CHECK_FAILURE(401,"校验失败"),
    INVALID_PARAM(400, "参数错误"),
    USERNAME_OR_PASSWORD_ERROR(400, "账号或密码错误"),
    VERIFY_CODE_NOT_MATCHING(400, "验证码错误"),
    PASSWORD_NOT_MATCHING(400, "密码错误"),
    USER_NOT_EXIST(404, "用户不存在"),
    CREATE_TOKEN_ERROR(500, "生成校验码失败"),
    INVAILD_AUTHORITY_VISIT(403, "无访问权限"),
    ERROR_ADD_TASK(500, "新增任务失败"),
    INVALID_FILE_TYPE(400,"文件类型不匹配"),
    UPLOAD_FILE_ERROR(500,"文件上传失败"),
    FILE_EXCEEDING_MAXIMUM(500,"文件超过最大容量限制"),
    ERROR_INSERT_WORK_DATA(400,"标注数据插入失败"),
    INVAILD_JOIN(400,"不被允许的加入任务请求"),
    TASK_NOT_FOUND(404,"任务不存在"),
    ;

    private int status;
    private String message;
}
