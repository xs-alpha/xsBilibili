package com.xiaosheng.video.dao.auth.mapper;

import com.xiaosheng.video.dao.auth.po.AuthElementOperation;

public interface AuthElementOperationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthElementOperation record);

    int insertSelective(AuthElementOperation record);

    AuthElementOperation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthElementOperation record);

    int updateByPrimaryKey(AuthElementOperation record);
}