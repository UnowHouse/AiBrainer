package com.ab.label.service;

import com.ab.commons.enums.ExceptionEnum;
import com.ab.commons.exception.AbException;
import com.ab.commons.vo.PageResult;
import com.ab.label.client.FileClient;
import com.ab.label.mapper.*;
import com.ab.label.pojo.Classes;
import com.ab.label.pojo.Datas;
import com.ab.label.pojo.Task;
import com.ab.label.pojo.Type;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

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
    private TypeMapper typeMapper;

    @Autowired
    private FileClient fileClient;

    @Autowired
    private AmqpTemplate amqpTemplate;


    /**
     * @description 添加任务，一共分为3步：1.新增task数据，2.新增classes数据，3.新增datas数据
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
        amqpTemplate.convertAndSend("task.insert",currentTask.getId());
    }

    /**
     * @description 加入在线私人任务，一共分2步：1.更新Task表joined人数，2.插入工作者记录（TaskWork）
     *                  1.更新Task表joined人数：
     *                      sql语句中判断 `need_workers`和`joined`是否合法，通过后joined++。
     *                  2.插入工作者记录（主要）：
     *                      由复杂的sql语句完成，sql语句通过判断 `要求的exp`, `任务是否未完成`,
     *                      `工作者是否有参加过其他未完成的任务`,`任务id是否存在`，全部通过后插
     *                      入工作者记录。
     *
     *
     * @param userId
     * @param taskId
     * @param workerId
     */
    @Transactional
    public void joinTask(Long userId, Long taskId,Long workerId) {

        int i1 = taskMapper.addWorkerNumber(taskId);
        if(i1 != 1) {
            throw new AbException(ExceptionEnum.INVAILD_JOIN);
        }

        int i = taskWorkMapper.insertTaskWork(userId, taskId, workerId);
        if(i != 1){
            throw new AbException(ExceptionEnum.INVAILD_JOIN);
        }

        amqpTemplate.convertAndSend("task.update",taskId);

    }

    /**
     * @description 查看平台上的任务，一共分为3步，1.分页设置，2，搜索条件设置，3.整合返回数据结果
     *                  1.使用分页插件，startPage记录要显示的页数和行数
     *                  2.过滤搜索条件key（如果有搜索条件key）：
     *                      a.模糊查询Type表中是否有任务类型名称匹配搜索条件key，默认取返回的数组的第一个元素
     *                      b.模糊查询Task表中是否有任务名称，任务描述匹配搜索条件key
     *                  3.搜索后通过整合到PageInfo，再赋值到自定义的PageResult类中，返回
     * @param page
     * @param rows
     * @param key
     * @return
     */
    public PageResult<Task> selectTasks(Integer page, Integer rows, String key) {

        PageHelper.startPage(page,rows);

        Example taskExample = new Example(Task.class);

        if(StringUtils.isNotBlank(key)){

            Example typeExample = new Example(Type.class);
            typeExample.createCriteria().orLike("name","%"+key+"%");

            List<Type> types = typeMapper.selectByExample(typeExample);

            if(!CollectionUtils.isEmpty(types)){
                taskExample.createCriteria().orLike("taskName","%"+key+"%")
                        .orLike("taskType","%"+types.get(0).getId()+"%")
                        .orLike("description","%"+key+"%")
                        .andEqualTo("isOver",false);
            }else{
                taskExample.createCriteria().orLike("taskName","%"+key+"%")
                        .orLike("description","%"+key+"%")
                        .andEqualTo("isOver",false);
            }
        }else{
            taskExample.createCriteria().andEqualTo("isOver",false);
        }
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        if(CollectionUtils.isEmpty(tasks)){
            throw new AbException(ExceptionEnum.TASK_NOT_FOUND);
        }
        PageInfo<Task> pageInfo = new PageInfo<>(tasks);

        return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }
}
