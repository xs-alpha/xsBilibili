package com.xiaosheng.video.api.controller;

import com.xiaosheng.video.api.request.UserReq;
import com.xiaosheng.video.facade.dto.UserDto;
import com.xiaosheng.video.services.service.UserService;
import com.xiaosheng.video.services.util.RSAUtil;
import com.xiaosheng.video.support.result.Result;
import com.xiaosheng.video.support.utils.BeanConvertorUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;


    @GetMapping("/rsa-pks")
    public Result getRsaPublicKey() {
        return Result.buildSuccessResult(RSAUtil.getPublicKeyStr());
    }

    @PostMapping("/users")
    public Result addUser(@RequestBody UserReq param) {
        UserDto userDto = new UserDto();
        BeanConvertorUtils.copy(param, userDto);
        return userService.addUser(userDto);
    }

}
