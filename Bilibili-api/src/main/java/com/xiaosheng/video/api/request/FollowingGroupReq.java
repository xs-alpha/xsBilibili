package com.xiaosheng.video.api.request;

import com.xiaosheng.video.dao.po.UserInfoPO;

import java.util.Date;
import java.util.List;

public class FollowingGroupReq {
    private Long userid;

    private String name;

    private String type;

    private Date createtime;

    private List<UserInfoReq> followingUserInfoList;
}
