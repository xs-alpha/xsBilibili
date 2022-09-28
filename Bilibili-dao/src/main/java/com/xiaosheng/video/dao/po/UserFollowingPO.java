package com.xiaosheng.video.dao.po;

import lombok.Data;

import java.util.Date;

@Data
public class UserFollowingPO {
    private Long id;

    private Long userid;

    private Integer followingid;

    private Integer groupid;

    private Date createtime;

    private UserInfoPO userInfoPO;

}