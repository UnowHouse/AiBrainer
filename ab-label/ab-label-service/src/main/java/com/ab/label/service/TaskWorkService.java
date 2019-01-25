package com.ab.label.service;

import com.ab.auth.entity.UserInfo;
import com.ab.commons.enums.ExceptionEnum;
import com.ab.commons.exception.AbException;
import com.ab.label.client.AuthClient;
import com.ab.label.mapper.TaskMapper;
import com.ab.label.mapper.TaskWorkMapper;
import com.ab.label.mapper.WorkMapper;
import com.ab.label.pojo.TaskWork;
import com.ab.label.pojo.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 *  @项目名：  aibrainer
 *  @包名：    com.ab.label.service
 *  @文件名:   TaskWorkService
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-01-24 22:28
 *  @描述：    TODO
 */
@Service
public class TaskWorkService {

    @Autowired
    private TaskWorkMapper taskWorkMapper;

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private AuthClient authClient;

//    @Autowired
//    private AmqpTemplate amqpTemplate;

    /**
     * @description 移除工作者，一共分2步，1.判断cookie是否有效，2.删除相关信息
     *         1.cookie 有效则逻辑正常进行。
     *         2.删除相关信息
     *              删除taskwork记录
     *              删除工作者的标注信息
     *              更新task信息
     * @param token
     * @param workerId
     * @param taskId
     */
    @Transactional
    public void removeWoker(String token, Long workerId, Long taskId) {
        UserInfo currrentUserInfo = authClient.getCurrrentUserInfo(token);
        Long userId = currrentUserInfo.getId();
        TaskWork taskWork = new TaskWork();
        taskWork.setUserId(userId);
        taskWork.setTaskId(taskId);
        taskWork.setWorkerId(workerId);

        int delete = taskWorkMapper.delete(taskWork);
        if(delete != 1){
            throw new AbException(ExceptionEnum.USER_NOT_EXIST);
        }

        Work work = new Work();
        work.setWorkerId(workerId);
        workMapper.delete(work);

        int i = taskMapper.deleteWorkerNumber(taskId);
        if(i != 1){
            throw new AbException(ExceptionEnum.REMOVE_WOKER_FAILURE);
        }

//        amqpTemplate.convertAndSend("worker.delete",taskWork);
    }
}
