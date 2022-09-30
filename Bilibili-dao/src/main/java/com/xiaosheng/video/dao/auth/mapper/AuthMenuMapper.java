package com.xiaosheng.video.dao.auth.mapper;

import com.xiaosheng.video.dao.auth.po.AuthMenu;

public interface AuthMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthMenu record);

    int insertSelective(AuthMenu record);

    AuthMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthMenu record);

    int updateByPrimaryKey(AuthMenu record);
}