package com.xiaosheng.video.facade.dto;

import com.xiaosheng.video.support.result.PageRequest;
import lombok.Data;

@Data
public class UserInfoPageDTO extends PageRequest {
    private String nick;
}
