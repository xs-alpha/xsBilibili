package com.xiaosheng.video.services.service;

import com.xiaosheng.video.dao.mapper.FollowingGroupPOMapper;
import com.xiaosheng.video.dao.po.FollowingGroupPO;
import com.xiaosheng.video.facade.dto.FollowingGroupDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FollowingGroupService {

    @Resource
    private FollowingGroupPOMapper followingGroupPOMapper;

    public FollowingGroupPO getByType(String type) {
        return followingGroupPOMapper.getByType(type);
    }

    public FollowingGroupPO getById(Long id) {
        return followingGroupPOMapper.selectByPrimaryKey(id);
    }

    public List<FollowingGroupPO> getByUserId(Long userId) {
        return followingGroupPOMapper.getByUserId(userId);
    }

    public void addFollowingGroup(FollowingGroupPO followingGroupPO) {
        followingGroupPOMapper.addFollowingGroup(followingGroupPO);
    }

    public List<FollowingGroupPO> getUserFollowingGroups(Long currentUserId) {
        return followingGroupPOMapper.getUserFollowingGroups(currentUserId);
    }
}
