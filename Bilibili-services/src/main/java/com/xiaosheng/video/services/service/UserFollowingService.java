package com.xiaosheng.video.services.service;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import com.xiaosheng.video.dao.mapper.FollowingGroupPOMapper;
import com.xiaosheng.video.dao.mapper.UserFollowingPOMapper;
import com.xiaosheng.video.dao.po.FollowingGroupPO;
import com.xiaosheng.video.dao.po.UserFollowingPO;
import com.xiaosheng.video.dao.po.UserInfoPO;
import com.xiaosheng.video.dao.po.UserPO;
import com.xiaosheng.video.facade.bo.FollowingGroupBO;
import com.xiaosheng.video.facade.bo.UserFollowingBO;
import com.xiaosheng.video.facade.bo.UserInfoBO;
import com.xiaosheng.video.facade.dto.FollowingGroupDTO;
import com.xiaosheng.video.facade.dto.UserFollowingDTO;
import com.xiaosheng.video.facade.enums.FollowingTypeEnum;
import com.xiaosheng.video.support.utils.BeanConvertorUtils;
import jdk.nashorn.internal.ir.LiteralNode;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.lang.model.element.TypeElement;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserFollowingService {

    @Resource
    private UserFollowingPOMapper userFollowingPOMapper;

    @Resource
    private FollowingGroupService followingGroupService;

    @Resource
    private UserService userService;

    @Transactional
    public void addUserFollowings(UserFollowingDTO userFollowingDTO) {
        UserFollowingPO userFollowing = new UserFollowingPO();
        BeanConvertorUtils.copy(userFollowingDTO, userFollowing);
        Integer groupid = userFollowing.getGroupid();
        if (groupid == null) {
            FollowingGroupPO followingGroupPO = followingGroupService.getByType(FollowingTypeEnum.TYPE_DEFAULT_FOLLOWING.getCode());
            userFollowing.setGroupid(followingGroupPO.getId().intValue());
        } else {
            FollowingGroupPO byId = followingGroupService.getById(groupid.longValue());
            if (byId == null) {
                // 关注分组不存在
            }
        }
        Integer followingId = userFollowing.getFollowingid();
        UserPO user = userService.getUserById(followingId);
        if (user == null) {
            // 关注的分组不存在
        }

        userFollowingPOMapper.deleteUserFollowing(userFollowing.getUserid(), followingId);
        userFollowingPOMapper.insertSelective(userFollowing);
    }

    /**
     * 获取用户关注列表
     *
     * @param userId
     * @return
     */
    public List<FollowingGroupBO> getUserFollowings(Long userId) {
        List<UserFollowingPO> list = userFollowingPOMapper.getUserFollowings(userId);
        Set<Long> collect = list.stream().map((item) -> {
            return item.getFollowingid().longValue();
        }).collect(Collectors.toSet());
        List<UserInfoPO> userInfoList = new ArrayList<>();
        if (collect.size() > 0) {
            userInfoList = userService.getUserInfoByUserIds(collect);
        }

        for (UserFollowingPO userFollowingPO : list) {
            for (UserInfoPO userInfoPO : userInfoList) {
                if (userFollowingPO.getFollowingid().equals(userInfoPO.getUserid())) {
                    userFollowingPO.setUserInfoPO(userInfoPO);
                }
            }
        }

        List<FollowingGroupPO> groupList = followingGroupService.getByUserId(userId);
        FollowingGroupPO followingGroup = new FollowingGroupPO();
        followingGroup.setName(FollowingTypeEnum.TYPE_FOLLOW_ALL.getCode());
        followingGroup.setFollowingUserInfoList(userInfoList);

        ArrayList<FollowingGroupPO> result = new ArrayList<>();
        result.add(followingGroup);
        for (FollowingGroupPO group : groupList) {
            ArrayList<UserInfoPO> userInfoPOS = new ArrayList<>();
            for (UserFollowingPO userFollowingPO : list) {
                if (group.getId().equals(userFollowingPO.getGroupid())) {
                    userInfoPOS.add(userFollowingPO.getUserInfoPO());
                }
            }
            group.setFollowingUserInfoList(userInfoPOS);
            result.add(group);
        }
        List<FollowingGroupBO> followingGroupBOS = BeanConvertorUtils.copyList(result, FollowingGroupBO.class);
        return followingGroupBOS;
    }

    /**
     * 获取用户粉丝
     *
     * @return
     */
    public List<UserFollowingBO> getUserFans(Integer userId) {
        List<UserFollowingPO> fans = userFollowingPOMapper.getFans(userId);
        Set<Long> fanSet = fans.stream().map(UserFollowingPO::getUserid).collect(Collectors.toSet());
        List<UserInfoPO> userInfoPOS = new ArrayList<>();
        if (fanSet.size() > 0) {
            userInfoPOS = userService.getUserInfoByUserIds(fanSet);
        }

        List<UserInfoBO> userInfoList = BeanConvertorUtils.copyList(userInfoPOS, UserInfoBO.class);
        List<UserFollowingBO> fanList = BeanConvertorUtils.copyList(fans, UserFollowingBO.class);
        List<UserFollowingPO> followingPOS = userFollowingPOMapper.getUserFollowings(userId.longValue());
        List<UserFollowingBO> followingList = BeanConvertorUtils.copyList(followingPOS, UserFollowingBO.class);
        for (UserFollowingBO fan : fanList) {
            for (UserInfoBO userInfo : userInfoList) {
                if (fan.getUserid().equals(userInfo.getUserid())) {
                    userInfo.setFollowed(true);
                    fan.setUserInfoBO(userInfo);
                }
            }
            for (UserFollowingBO following : followingList) {
                if (following.getFollowingid().equals(fan.getUserid())) {
                    fan.getUserInfoBO().setFollowed(true);
                }
            }
        }
        return fanList;
    }

    /**
     * 添加关注分组
     *
     * @param followingGroupDTO
     * @return
     */
    public Long addUserFollowingGroups(FollowingGroupDTO followingGroupDTO) {
        followingGroupDTO.setType(FollowingTypeEnum.TYPE_USER_BUILD.getCode());
        FollowingGroupPO followingGroupPO = new FollowingGroupPO();
        BeanConvertorUtils.copy(followingGroupDTO, followingGroupPO);
        followingGroupService.addFollowingGroup(followingGroupPO);
        return followingGroupDTO.getUserid();
    }

    /**
     * 获取关注分组
     *
     * @param currentUserId
     * @return
     */
    public List<FollowingGroupBO> getUserFollowingGroups(Long currentUserId) {
        List<FollowingGroupPO> userFollowingGroups = followingGroupService.getUserFollowingGroups(currentUserId);
        return BeanConvertorUtils.copyList(userFollowingGroups, FollowingGroupBO.class);
    }

    /**
     * 检查关注状态
     *
     * @param userInfoBOS
     * @param userId
     * @return
     */
    public List<UserInfoBO> checkFollowingStatus(List<UserInfoBO> userInfoBOS, Long userId) {
        List<UserFollowingPO> userFollowingPOS = userFollowingPOMapper.getUserFollowings(userId);
        List<UserFollowingBO> userFollowingList = BeanConvertorUtils.copyList(userFollowingPOS, UserFollowingBO.class);
        for (UserInfoBO userInfo : userInfoBOS) {
            userInfo.setFollowed(false);
            for (UserFollowingBO userFollowing : userFollowingList) {
                if (userFollowing.getFollowingid().equals(userInfo.getUserid())) {
                    userInfo.setFollowed(true);
                }
            }
        }

        return userInfoBOS;
    }
}
