package com.xiaosheng.video.dao.auth.mapper;

import com.xiaosheng.video.dao.auth.po.AuthRoleElementOperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface AuthRoleElementOperationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthRoleElementOperation record);

    int insertSelective(AuthRoleElementOperation record);

    AuthRoleElementOperation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthRoleElementOperation record);

    int updateByPrimaryKey(AuthRoleElementOperation record);

    List<AuthRoleElementOperation> getRoleElementOperationsByRoleIds(@Param("roleIdSet") Set<Long> roleIdSet);
}