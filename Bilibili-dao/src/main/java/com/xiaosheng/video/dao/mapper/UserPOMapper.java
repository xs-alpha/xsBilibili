package com.xiaosheng.video.dao.mapper;

import com.xiaosheng.video.dao.po.UserPO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;

public interface UserPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserPO record);

    int insertSelective(UserPO record);

    UserPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPO record);

    int updateByPrimaryKey(UserPO record);

    UserPO getUserByPhone(@Param("phone") String phone);

    UserPO getUserById(@Param("id") Long id);
}