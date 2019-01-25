package com.ab.label.mapper;

import com.ab.label.pojo.Task;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.mapper
 *  @文件名:   TaskMapper
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:52
 *  @描述：    TODO
 */
public interface TaskMapper extends Mapper<Task> {

    @Update("UPDATE `tb_task` SET `joined` = `joined`+1 WHERE `id` = #{taskId} AND `joined` < `need_workers`")
    int addWorkerNumber(@Param("taskId")Long taskId);

    @Update("UPDATE `tb_task` SET `joined` = `joined`-1 WHERE `id` = #{taskId} AND `joined` > 0")
    int deleteWorkerNumber(@Param("taskId")Long taskId);
}
