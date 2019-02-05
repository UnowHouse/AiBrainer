package com.ab.user.mapper;

import com.ab.label.pojo.Task;
import com.ab.label.pojo.TaskWork;
import com.ab.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.user.mapper
 *  @文件名:   UserMapper
 *  @创建者:   Unow
 *  @创建时间:  2019/1/1 15:44
 *  @描述：    TODO
 */
public interface UserMapper extends Mapper<User> {


    @Select("SELECT * FROM `tb_task` WHERE `user_id` = #{userId}")
    List<Task> getUserTasks(@Param("userId")Long userId);

    @Select("SELECT * FROM `tb_task_work` WHERE `user_id` = #{userId}")
    List<TaskWork> getUserWorks(@Param("userId")Long userId);

    @Update("UPDATE `tb_user` SET `sickname` = #{user.sickname},`identity` = #{user.identity},`face` = #{user.face} WHERE `id` = #{user.id}")
    int updatePersonal(@Param("user")User user);

}
