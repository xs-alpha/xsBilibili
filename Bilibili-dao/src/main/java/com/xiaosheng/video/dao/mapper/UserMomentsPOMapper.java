package com.xiaosheng.video.dao.mapper;

import com.xiaosheng.video.dao.po.UserMomentsPO;

public interface UserMomentsPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMomentsPO record);

    int insertSelective(UserMomentsPO record);

    UserMomentsPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMomentsPO record);

    int updateByPrimaryKey(UserMomentsPO record);

}