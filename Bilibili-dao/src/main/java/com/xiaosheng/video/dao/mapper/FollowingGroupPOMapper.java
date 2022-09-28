package com.xiaosheng.video.dao.mapper;

import com.xiaosheng.video.dao.po.FollowingGroupPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FollowingGroupPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FollowingGroupPO record);

    int insertSelective(FollowingGroupPO record);

    FollowingGroupPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FollowingGroupPO record);

    int updateByPrimaryKey(FollowingGroupPO record);

    FollowingGroupPO getByType(String type);

    List<FollowingGroupPO> getByUserId(Long userId);

    Integer addFollowingGroup(FollowingGroupPO followingGroupPO);

    List<FollowingGroupPO> getUserFollowingGroups(@Param("userId") Long userId);
}