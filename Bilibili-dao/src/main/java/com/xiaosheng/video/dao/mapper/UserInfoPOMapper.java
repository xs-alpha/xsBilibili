package com.xiaosheng.video.dao.mapper;

import com.xiaosheng.video.dao.po.UserInfoPO;

public interface UserInfoPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfoPO record);

    int insertSelective(UserInfoPO record);

    UserInfoPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfoPO record);

    int updateByPrimaryKeyWithBLOBs(UserInfoPO record);

    int updateByPrimaryKey(UserInfoPO record);

    UserInfoPO getUserInfoById(Long userId);
}