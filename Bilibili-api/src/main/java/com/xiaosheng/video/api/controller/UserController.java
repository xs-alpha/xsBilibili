package com.xiaosheng.video.api.controller;

import com.xiaosheng.video.api.request.UserInfoReq;
import com.xiaosheng.video.api.request.UserReq;
import com.xiaosheng.video.api.response.UserInfoResp;
import com.xiaosheng.video.api.support.UserSupport;
import com.xiaosheng.video.facade.bo.UserInfoBO;
import com.xiaosheng.video.facade.dto.UserDTO;
import com.xiaosheng.video.facade.dto.UserInfoDTO;
import com.xiaosheng.video.services.service.UserService;
import com.xiaosheng.video.support.result.Result;
import com.xiaosheng.video.support.utils.BeanConvertorUtils;
import com.xiaosheng.video.support.utils.RSAUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private UserSupport userSupport;

    @GetMapping("/rsa-pks")
    public Result getRsaPublicKey() {
        return Result.buildSuccessResult(RSAUtil.getPublicKeyStr());
    }

    /**
     * 注册 * @param param
     *
     * @return
     */
    @PostMapping("/users")
    public Result addUser(@RequestBody UserReq param) {
        UserDTO userDto = new UserDTO();
        BeanConvertorUtils.copy(param, userDto);
        return userService.addUser(userDto);
    }


    /**
     * 登录
     *
     * @param param
     * @return
     */
    @PostMapping("/user-tokens")
    public Result login(@RequestBody UserReq param) {
        UserDTO userDto = new UserDTO();
        BeanConvertorUtils.copy(param, userDto);
        return userService.login(userDto);
    }

    /**
     * 获取用户信息
     *
     * @param userId
     */
    @GetMapping("/users")
    public Result<UserInfoResp> getUserInfo(Long userId) {
        Long currentUserId = userSupport.getCurrentUserId();
        Result<UserInfoBO> userInfo = userService.getUserInfo(currentUserId);
        if (userInfo.isSuccess() && userInfo.getData() != null) {
            return Result.buildSuccessResult(BeanConvertorUtils.map(userInfo.getData(), UserInfoResp.class));
        }
        return BeanConvertorUtils.map(userInfo, Result.class);
    }

    /**
     * 更新用户信息
     *
     * @param param
     * @return
     */
    @PostMapping("/user-infos")
    public Result updateUserInfos(@RequestBody UserInfoReq param) {
        Long currentUserId = userSupport.getCurrentUserId();
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserid(currentUserId);
        BeanConvertorUtils.copy(param, userInfoDTO);
        return userService.updateUserInfo(userInfoDTO);
    }
}
