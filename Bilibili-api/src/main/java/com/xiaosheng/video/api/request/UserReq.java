package com.xiaosheng.video.api.request;

import lombok.Data;

@Data
public class UserReq {
    private String phone;

    private String email;

    private String password;
}
