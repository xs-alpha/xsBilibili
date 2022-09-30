package com.xiaosheng.video.services.authService;

import com.xiaosheng.video.dao.auth.mapper.UserRoleMapper;
import com.xiaosheng.video.dao.auth.po.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    public List<UserRole> getUserRoleByUserId(Long currentUserId) {
        return userRoleMapper.getUserRoleByUserId(currentUserId);
    }
}
