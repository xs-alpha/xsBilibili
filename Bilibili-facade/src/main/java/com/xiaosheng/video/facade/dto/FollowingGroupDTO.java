package com.xiaosheng.video.facade.dto;

import com.xiaosheng.video.facade.bo.UserInfoBO;
import lombok.Data;

import java.util.List;

@Data
public class FollowingGroupDTO {
    private Long userid;

    private String name;

    private String type;

    private List<UserInfoDTO> followingUserInfoList;
}
