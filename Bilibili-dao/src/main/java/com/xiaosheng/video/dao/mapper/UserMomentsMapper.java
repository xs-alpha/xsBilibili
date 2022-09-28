package com.xiaosheng.video.dao.mapper;

import com.xiaosheng.video.dao.po.UserMoments;

public interface UserMomentsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMoments record);

    int insertSelective(UserMoments record);

    UserMoments selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMoments record);

    int updateByPrimaryKey(UserMoments record);
}