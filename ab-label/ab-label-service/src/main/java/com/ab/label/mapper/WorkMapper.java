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
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface WorkMapper extends Mapper<Work>,InsertListMapper<Work> {


}
