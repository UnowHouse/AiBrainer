package com.ab.label.service;

import com.ab.auth.entity.UserInfo;
import com.ab.commons.enums.ExceptionEnum;
import com.ab.commons.exception.AbException;
import com.ab.label.client.AuthClient;
import com.ab.label.mapper.TaskMapper;
import com.ab.label.mapper.TaskWorkMapper;
import com.ab.label.mapper.WorkMapper;
import com.ab.label.pojo.Task;
import com.ab.label.pojo.TaskWork;
import com.ab.label.pojo.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

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

    @Autowired
    private TaskWorkMapper taskWorkMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private AuthClient authClient;

    /**
     * @description 插入标注数据，分为3步，1.插入work数据，2.更新task记录，3.更新taskWork数据
     *                  1.判断参数是否为空
     *                  2.进行相关更新
     * @param labelList
     */
    @Transactional
    public void insertWorks(List<Work> labelList) {
        if(CollectionUtils.isEmpty(labelList)){
            throw new AbException(ExceptionEnum.INVALID_PARAM);
        }

        workMapper.insertList(labelList);

        int i = taskMapper.updateLabeledByDataId(labelList.size(),labelList.get(0).getDataId());
        if(i != 1){
            throw new AbException(ExceptionEnum.ERROR_INSERT_WORK_DATA);
        }
        TaskWork taskWork = new TaskWork();
        taskWork.setWorkerId(labelList.get(0).getWorkerId());
        taskWork.setAdaptNum(labelList.size());

        int i1 = taskWorkMapper.updateTaskWork(labelList.get(0).getDataId(), taskWork);
        if(i1 != 1){
            throw new AbException(ExceptionEnum.ERROR_INSERT_WORK_DATA);
        }


    }

    /**
     * @description 修改其他人标注的数据信息,分3步
     *              1.判断当前用户是否登陆
     *              2.查询要更新的数据所属的任务是否为本用户的任务
     *              3.更新
     * @param token
     * @param labelList
     */
    @Transactional
    public void updateLabeled(String token,Long taskId, List<Work> labelList) {

        UserInfo currrentUserInfo = authClient.getCurrrentUserInfo(token);
        if(ObjectUtils.isEmpty(currrentUserInfo))
            throw new AbException(ExceptionEnum.INVAILD_AUTHORITY_VISIT);

        Long userId = currrentUserInfo.getId();

        Task task = taskMapper.selectByUserIdAndTaskId(userId, taskId);

        if(ObjectUtils.isEmpty(task))
            throw new AbException(ExceptionEnum.INVAILD_AUTHORITY_VISIT);


        for (Work work : labelList) {
            workMapper.updateWork(work);
        }

    }
}
