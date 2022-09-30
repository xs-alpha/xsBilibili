package com.xiaosheng.video.api.authController;

import com.xiaosheng.video.api.support.UserSupport;
import com.xiaosheng.video.dao.auth.po.UserAuthorities;
import com.xiaosheng.video.services.authService.UserAuthService;
import com.xiaosheng.video.support.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserAuthApi {

    @Resource
    private UserSupport userSupport;

    @Resource
    private UserAuthService userAuthService;

    @GetMapping("")
    public Result<UserAuthorities> getUserAuthorities() {
        Long currentUserId = userSupport.getCurrentUserId();
        userAuthService.getUserAuthorities(currentUserId);
        return null;
    }

}
