package com.xiaosheng.video.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosheng.video.dao.po.UserInfoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserInfoPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfoPO record);

    int insertSelective(UserInfoPO record);

    UserInfoPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfoPO record);

    int updateByPrimaryKeyWithBLOBs(UserInfoPO record);

    int updateByPrimaryKey(UserInfoPO record);

    UserInfoPO getUserInfoById(Long userId);

    List<UserInfoPO> getUserInfoByUserIds(Set<Long> userIdList);

    IPage<UserInfoPO> getPageListUserInfos(Page<?> page, @Param("nick") String nick);
}