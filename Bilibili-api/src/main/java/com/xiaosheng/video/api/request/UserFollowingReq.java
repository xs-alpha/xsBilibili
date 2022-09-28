package com.xiaosheng.video.api.request;

import com.xiaosheng.video.dao.po.UserInfoPO;


public class UserFollowingReq {
    private Long userid;

    private Integer followingid;

    private Integer groupid;

    private UserInfoPO userInfoPO;
}
