package com.xiaosheng.video.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DemoMapper {
    Map<String, Object> queryallData(Integer id);
}
