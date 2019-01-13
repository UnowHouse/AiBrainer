package com.ab.label.web;

import com.ab.label.pojo.Work;
import com.ab.label.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.service
 *  @文件名:   LabelController
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:55
 *  @描述：    TODO
 */
@RestController
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 记录标注的数据
     * @param labelList
     * @return
     */
    @PostMapping("labeled")
    public ResponseEntity<Void> labeled (@RequestBody List<Work> labelList){

        labelService.insertWorks(labelList);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
