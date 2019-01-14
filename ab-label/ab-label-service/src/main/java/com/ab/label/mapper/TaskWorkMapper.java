package com.ab.label.mapper;

import com.ab.label.pojo.TaskWork;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.mapper
 *  @文件名:   TaskWorkMapper
 *  @创建者:   Unow
 *  @创建时间:  2019/1/14 17:38
 *  @描述：    TODO
 */
public interface TaskWorkMapper extends Mapper<TaskWork> {

    @Insert("INSERT INTO `tb_task_work` (`user_id`, `task_id`, `worker_id`) \n" +
            "SELECT #{userId},#{taskId},#{workerId} FROM DUAL WHERE \n" +
            "EXISTS \n" +
            "(SELECT `id` FROM `tb_task` WHERE `id` = #{taskId} AND `is_over` = 0)\n" +
            "AND \n" +
            "NOT EXISTS\n" +
            "(SELECT `worker_id` FROM `tb_task_work` WHERE `worker_id` = #{workerId}) " +
            "AND\n" +
            "(SELECT `need_exp` FROM `tb_task` WHERE `id` = #{taskId} ) \n" +
            "<=\n" +
            "(SELECT `exp` FROM `tb_user` WHERE `id` = #{workerId})  ")
    int insertTaskWork(@Param("userId")Long userId,@Param("taskId")Long taskId,@Param("workerId")Long workerId);
}
