package com.xiaosheng.video.api.controller;

import com.xiaosheng.video.api.request.UserMomentsReq;
import com.xiaosheng.video.api.response.UserMomentsResp;
import com.xiaosheng.video.api.support.UserSupport;
import com.xiaosheng.video.facade.bo.UserMomentsBO;
import com.xiaosheng.video.facade.dto.UserMomentsDTO;
import com.xiaosheng.video.services.service.UserMomentsService;
import com.xiaosheng.video.support.result.Result;
import com.xiaosheng.video.support.utils.BeanConvertorUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

public class UsermomentsController {
    @Resource
    private UserMomentsService userMomentsService;

    @Resource
    private UserSupport userSupport;

    /**
     * 新增用户动态
     *
     * @param param
     * @return
     */
    @PostMapping("/user-moments")
    public Result addUserMoments(@RequestBody UserMomentsReq param) {
        Long currentUserId = userSupport.getCurrentUserId();
        UserMomentsDTO userMomentsDTO = new UserMomentsDTO();
        BeanConvertorUtils.copy(param, userMomentsDTO);
        userMomentsDTO.setUserid(currentUserId);
        userMomentsService.addUserMoments(userMomentsDTO);
        return Result.buildSuccessResult();
    }

    @GetMapping("/user-subscribed-moments")
    public Result<List<UserMomentsResp>> getUserMoments() {
        Long currentUserId = userSupport.getCurrentUserId();
        List<UserMomentsBO> userMoments = userMomentsService.getUserMoments(currentUserId);
        List<UserMomentsResp> userMomentsResps = BeanConvertorUtils.copyList(userMoments, UserMomentsResp.class);
        return Result.buildSuccessResult(userMomentsResps);
    }
}
