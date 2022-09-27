package com.xiaosheng.video.facade.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String phone;

    private String email;

    private String password;

    private String salt;
}
