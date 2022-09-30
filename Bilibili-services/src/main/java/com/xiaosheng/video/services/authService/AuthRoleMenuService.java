package com.xiaosheng.video.services.authService;

import com.xiaosheng.video.dao.auth.mapper.AuthRoleMenuMapper;
import com.xiaosheng.video.dao.auth.po.AuthRoleMenu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class AuthRoleMenuService {
    @Resource
    private AuthRoleMenuMapper authRoleMenuMapper;

    public List<AuthRoleMenu> getAuthRoleMenusByRoleIds(Set<Long> roleIdSet) {
        return authRoleMenuMapper.getAuthRoleMenusByRoleIds(roleIdSet);
    }
}
