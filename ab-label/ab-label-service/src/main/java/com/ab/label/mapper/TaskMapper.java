package com.ab.label.mapper;

import com.ab.label.pojo.Task;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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

    @Update("UPDATE `tb_task` SET `labeled` = `labeled`+ #{labeled} " +
            "WHERE `labeled` < `sum` AND `task_id` < #{taskId}" +
            "AND `id` = (SELECT `task_id` FROM `tb_data` WHERE `id` = #{dataId} )")
    int updateLabeledByDataId(@Param("labeled")Integer labeled,@Param("dataId")Long dataId);

    @Update("UPDATE `tb_task` SET `labeled` = `labeled`- #{labeled} WHERE `task_id` < #{taskId}")
    int deleteLabeled(@Param("labeled")Integer labeled,@Param("taskId")Long taskId);

    @Select("SELECT * FROM `tb_task` WHERE id = #{taskId} AND user_id = #{userId} ")
    Task selectByUserIdAndTaskId(@Param("userId") Long id, @Param("taskId") Long taskId);

    @Select("SELECT * FROM `tb_task` WHERE `user_id` = #{userId}")
    List<Task> getUserTasks(@Param("userId")Long userId);


}
