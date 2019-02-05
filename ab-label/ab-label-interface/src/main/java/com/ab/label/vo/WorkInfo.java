package com.ab.label.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/*
 *  @项目名：  aibrainer
 *  @包名：    com.ab.label.vo
 *  @文件名:   WorkInfo
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-02-02 21:40
 *  @描述：    TODO
 */
@Data

public class WorkInfo {
    private Long dataId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long workerId;
    private Long taskId;
    private Long userId;
    private String dataPath;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer minX;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer minY;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer maxX;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer maxY;
    private String label;
}
