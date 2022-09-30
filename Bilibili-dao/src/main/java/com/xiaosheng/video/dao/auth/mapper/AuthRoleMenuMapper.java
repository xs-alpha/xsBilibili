package com.xiaosheng.video.dao.auth.mapper;

import com.xiaosheng.video.dao.auth.po.AuthRoleMenu;

import java.util.List;
import java.util.Set;

public interface AuthRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthRoleMenu record);

    int insertSelective(AuthRoleMenu record);

    AuthRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthRoleMenu record);

    int updateByPrimaryKey(AuthRoleMenu record);

    List<AuthRoleMenu> getAuthRoleMenusByRoleIds(Set<Long> roleIdSet);
}