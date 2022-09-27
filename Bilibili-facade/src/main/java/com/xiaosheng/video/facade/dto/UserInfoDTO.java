package com.xiaosheng.video.facade.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoDTO {
    private Long id;

    private Long userid;

    private String nick;

    private String avatar;

    private String gender;

    private String birth;

    private Date createtime;

    private Date updatetime;

    private String sign;
}
