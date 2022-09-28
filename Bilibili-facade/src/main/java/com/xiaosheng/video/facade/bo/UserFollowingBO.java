package com.xiaosheng.video.facade.bo;

import lombok.Data;


@Data
public class UserFollowingBO {
    private Long userid;

    private Integer followingid;

    private Integer groupid;

    private UserInfoBO userInfoBO;
}
