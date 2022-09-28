package com.xiaosheng.video.api.response;

import com.xiaosheng.video.dao.po.UserInfoPO;

import java.util.List;

public class FollowingGroupResp {
    private Long userid;

    private String name;

    private String type;

    private List<UserInfoResp> followingUserInfoList;
}
