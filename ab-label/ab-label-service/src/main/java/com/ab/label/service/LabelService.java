package com.ab.label.service;

import com.ab.commons.enums.ExceptionEnum;
import com.ab.commons.exception.AbException;
import com.ab.label.mapper.WorkMapper;
import com.ab.label.pojo.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.service
 *  @文件名:   LabelService
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:56
 *  @描述：    TODO
 */
@Service
public class LabelService {

    @Autowired
    private WorkMapper workMapper;

    /**
     * 插入标注数据
     * @param labelList
     */
    public void insertWorks(List<Work> labelList) {
        if(CollectionUtils.isEmpty(labelList)){
            throw new AbException(ExceptionEnum.INVALID_PARAM);
        }
        workMapper.insertList(labelList);

    }
}
