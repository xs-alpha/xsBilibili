package com.xiaosheng.video.api.controller;

import com.xiaosheng.video.api.request.FollowingGroupReq;
import com.xiaosheng.video.api.request.UserFollowingReq;
import com.xiaosheng.video.api.request.UserInfoPageReq;
import com.xiaosheng.video.api.response.FollowingGroupResp;
import com.xiaosheng.video.api.response.UserInfoResp;
import com.xiaosheng.video.api.support.UserSupport;
import com.xiaosheng.video.facade.bo.FollowingGroupBO;
import com.xiaosheng.video.facade.bo.UserFollowingBO;
import com.xiaosheng.video.facade.bo.UserInfoBO;
import com.xiaosheng.video.facade.dto.FollowingGroupDTO;
import com.xiaosheng.video.facade.dto.UserFollowingDTO;
import com.xiaosheng.video.facade.dto.UserInfoPageDTO;
import com.xiaosheng.video.services.service.UserFollowingService;
import com.xiaosheng.video.services.service.UserService;
import com.xiaosheng.video.support.result.PageResult;
import com.xiaosheng.video.support.result.Result;
import com.xiaosheng.video.support.utils.BeanConvertorUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserFollowingController {
    @Resource
    private UserFollowingService userFollowingService;

    @Resource
    private UserSupport userSupport;

    @Resource
    private UserService userService;

    /**
     * 添加用户关注
     *
     * @param userFollowingReq
     * @return
     */
    @PostMapping("/user-followings")
    public Result addUserFollowings(@RequestBody UserFollowingReq userFollowingReq) {
        Long currentUserId = userSupport.getCurrentUserId();
        UserFollowingDTO userFollowingDTO = new UserFollowingDTO();
        BeanConvertorUtils.copy(userFollowingReq, userFollowingDTO);
        userFollowingDTO.setUserid(currentUserId);
        userFollowingService.addUserFollowings(userFollowingDTO);
        return Result.buildSuccessResult();
    }

    /**
     * 获取用户关注列表
     *
     * @return
     */
    @GetMapping("/user-followings")
    public Result<List<FollowingGroupResp>> getUserFollowings() {
        Long currentUserId = userSupport.getCurrentUserId();
        List<FollowingGroupBO> list = userFollowingService.getUserFollowings(currentUserId);
        List<FollowingGroupResp> followingGroupResps = BeanConvertorUtils.copyList(list, FollowingGroupResp.class);
        return Result.buildSuccessResult(followingGroupResps);
    }

    /**
     * 获取用户粉丝
     *
     * @return
     */
    @GetMapping("/user-fans")
    public Result<List<FollowingGroupResp>> getUserFans() {
        Long currentUserId = userSupport.getCurrentUserId();
        List<UserFollowingBO> userFans = userFollowingService.getUserFans(currentUserId.intValue());
        List<FollowingGroupResp> followingGroupResps = BeanConvertorUtils.copyList(userFans, FollowingGroupResp.class);
        return Result.buildSuccessResult(followingGroupResps);
    }

    /**
     * 增加用户关注分组
     *
     * @param param
     * @return
     */
    @PostMapping("/user-following-groups")
    public Result addUserFollowingGroups(@RequestBody FollowingGroupReq param) {
        Long currentUserId = userSupport.getCurrentUserId();
        FollowingGroupDTO followingGroupDTO = new FollowingGroupDTO();
        BeanConvertorUtils.copy(param, followingGroupDTO);
        followingGroupDTO.setUserid(currentUserId);
        Long groupId = userFollowingService.addUserFollowingGroups(followingGroupDTO);
        HashMap<String, Long> map = new HashMap<>();
        map.put("groupId", groupId);
        return Result.buildSuccessResult(map);
    }

    /**
     * 获取用户关注列表
     *
     * @return
     */
    @GetMapping("/user-following-groups")
    public Result<List<FollowingGroupResp>> getFollowingGroups() {
        Long currentUserId = userSupport.getCurrentUserId();
        List<FollowingGroupBO> userFollowingGroups = userFollowingService.getUserFollowingGroups(currentUserId);
        return Result.buildSuccessResult(BeanConvertorUtils.copyList(userFollowingGroups, FollowingGroupResp.class));
    }

    /**
     * 获取用户信息分页接口
     *
     * @return
     */
    @GetMapping("/user-infos")
    public Result<PageResult<UserInfoResp>> pageListUserInfos(@ModelAttribute UserInfoPageReq param) {
        Long currentUserId = userSupport.getCurrentUserId();
        UserInfoPageDTO userInfoPageDTO = new UserInfoPageDTO();
        BeanConvertorUtils.copy(param, userInfoPageDTO);
        PageResult<UserInfoBO> userInfoBOPageResult = userService.pageListUserInfos(userInfoPageDTO);
        List<UserInfoBO> list = userInfoBOPageResult.getList();
        List<UserInfoBO> userInfoBOS = userFollowingService.checkFollowingStatus(list, currentUserId);
        List<UserInfoResp> userInfoResps = BeanConvertorUtils.copyList(userInfoBOS, UserInfoResp.class);
        return Result.buildSuccessResult(new PageResult<UserInfoResp>(userInfoResps, userInfoBOPageResult.getPageNo(), userInfoBOPageResult.getTotal(), userInfoBOPageResult.getPageSize()));
    }
}
