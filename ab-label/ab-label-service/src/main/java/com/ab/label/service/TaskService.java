package com.ab.label.service;

import com.ab.commons.enums.ExceptionEnum;
import com.ab.commons.exception.AbException;
import com.ab.label.client.FileClient;
import com.ab.label.mapper.ClassesMapper;
import com.ab.label.mapper.DatasMapper;
import com.ab.label.mapper.TaskMapper;
import com.ab.label.mapper.TaskWorkMapper;
import com.ab.label.pojo.Classes;
import com.ab.label.pojo.Datas;
import com.ab.label.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.service
 *  @文件名:   TaskService
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:56
 *  @描述：    TODO
 */
@Service
public class TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private DatasMapper datasMapper;

    @Autowired
    private ClassesMapper classesMapper;

    @Autowired
    private TaskWorkMapper taskWorkMapper;


    @Autowired
    private FileClient fileClient;


    /**
     * @description: 添加任务一共分为3步，1.新增task数据，2.新增classes数据，3.新增datas数据
     *                  1.新增task数据：
     *                      将接收的Task参数，包装后直接插入tb_task数据库表
     *                  2.新增classes数据：
     *                      从接收的Task参数中获取classes列表，迭代构造classes对象，
     *                      获取新插入数据库的taskId和发布任务的userId，赋值给classes对象，插入。
     *                  3.新增datas数据（同时上传至fastDFS）
     *                      从接收的Task参数中获取files列表，迭代files，并通过fileClient.uploadImage接口，
     *                      将他们一一上传至fastDFS，并获取他们返回的存储路径，赋值给迭代构造的Datas对象，
     *                      插入数据库表操作与步骤2一样。
     * @param task
     */

    @Transactional
    public void addTask(Task task) {
        task.setCreated(new Date());
        task.setIsOver(false);
        System.out.println(task);
        // 新增task数据
        int i = taskMapper.insert(task);
        if (i != 1) {
            throw new AbException(ExceptionEnum.ERROR_ADD_TASK);
        }

        // 新增classes自定义标签
        List<String> classes = task.getClasses();
        Task currentTask = taskMapper.selectOne(task);
        Long taskId = currentTask.getId();
        Long userId = task.getId();

        if (CollectionUtils.isEmpty(classes) || taskId == null || userId == null) {
            throw new AbException(ExceptionEnum.ERROR_ADD_TASK);
        }
        for (String label : classes) {
            Classes tmp = new Classes();
            tmp.setLabel(label);
            tmp.setTaskId(taskId);
            tmp.setUserId(userId);
            classesMapper.insert(tmp);
        }
        // 新增data数据信息
        List<MultipartFile> files = task.getFiles();
        if (CollectionUtils.isEmpty(files)) {
            throw new AbException(ExceptionEnum.ERROR_ADD_TASK);
        }
        for (MultipartFile file : files) {
            // 上传数据到fastDFS
            String imagePath = fileClient.uploadImage(file);
            Datas data = new Datas();
            data.setDataPath(imagePath);
            data.setUserId(userId);
            data.setTaskId(taskId);
            datasMapper.insert(data);
        }

    }

    @Transactional
    public void joinTask(Long userId, Long taskId,Long workerId) {

        int i = taskWorkMapper.insertTaskWork(userId, taskId, workerId);
        if(i != 1){
            throw new AbException(ExceptionEnum.INVAILD_JOIN);
        }

        int i1 = taskMapper.addWorkerNumber(taskId);
        if(i1 != 1) {
            throw new AbException(ExceptionEnum.INVAILD_JOIN);
        }

    }
}
