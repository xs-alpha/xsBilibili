package com.xiaosheng.video.dao.mapper;

import com.xiaosheng.video.dao.po.UserFollowingPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFollowingPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFollowingPO record);

    int insertSelective(UserFollowingPO record);

    UserFollowingPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserFollowingPO record);

    int updateByPrimaryKey(UserFollowingPO record);

    Integer deleteUserFollowing(@Param("userId") Long userid, @Param("followingId") Integer followingId);

    List<UserFollowingPO> getUserFollowings(Long userId);

    List<UserFollowingPO> getFans(@Param("followingId") Integer followingId);
}