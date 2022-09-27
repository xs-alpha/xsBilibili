package com.xiaosheng.video.dao.mapper;

import com.xiaosheng.video.dao.po.FollowingGroupPO;

public interface FollowingGroupPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FollowingGroupPO record);

    int insertSelective(FollowingGroupPO record);

    FollowingGroupPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FollowingGroupPO record);

    int updateByPrimaryKey(FollowingGroupPO record);
}