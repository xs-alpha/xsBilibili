package com.xiaosheng.video.facade.dto;

import lombok.Data;

@Data
public class UserFollowingDTO {
    private Long userid;

    private Integer followingid;

    private Integer groupid;

    private UserInfoDTO userInfoDTO;
}
