package com.xiaosheng.video.facade.bo;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoBO {
    private Long id;

    private Long userid;

    private String nick;

    private String avatar;

    private String gender;

    private String birth;

    private Date createtime;

    private Date updatetime;

    private String sign;

    private String phone;

    private String email;

    private String password;
}
