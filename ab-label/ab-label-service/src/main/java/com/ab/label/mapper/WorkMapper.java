package com.ab.label.mapper;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.label.mapper
 *  @文件名:   WorkMapper
 *  @创建者:   Unow
 *  @创建时间:  2019/1/6 20:53
 *  @描述：    TODO
 */

import com.ab.label.pojo.Work;
import com.ab.label.vo.WorkInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface WorkMapper extends Mapper<Work>,InsertListMapper<Work> {

    @Select("SELECT t.id dataId,t.user_id userId,t.task_id taskId,t.data_path dataPath,t.worker_id workerId,t.min_x minX,t.min_y minY,t.max_x maxX, t.max_y maxY,t.label FROM\n" +
            "(\n" +
            "\tSELECT * ,ROUND(RAND()) newno\n" +
            "\tFROM tb_datas a LEFT JOIN tb_work b \n" +
            "\tON a.id = b.data_id \n" +
            "\tWHERE a.user_id = #{userId} \n" +
            "\tAND a.task_id = #{taskId} \n" +
            "\tAND b.worker_id = #{workerId}\n" +
            "\tGROUP BY a.id \n" +
            ") AS t\n" +
            "ORDER BY t.newno ASC LIMIT #{row}")
    List<WorkInfo> selectWorkRandom(@Param("userId")Long userId, @Param("taskId")Long taskId, @Param("workerId")Long workerId, @Param("row")Integer row);

    @Update("UPDATE `tb_work` SET `worker_id` = #{work.workerId}," +
            "`min_x` = #{work.minX},`min_y` = #{work.minY}," +
            "`max_x` = #{work.maxX},`max_y` = #{work.maxY},`label` = #{work.label}" +
            " WHERE `data_id` = #{work.dataId}")
    int updateWork(@Param("work") Work work);
}
