package com.xiaosheng.video.dao.mapper;

import com.xiaosheng.video.dao.po.UserFollowingPO;

public interface UserFollowingPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFollowingPO record);

    int insertSelective(UserFollowingPO record);

    UserFollowingPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserFollowingPO record);

    int updateByPrimaryKey(UserFollowingPO record);
}