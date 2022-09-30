package com.xiaosheng.video.services.authService;

import com.xiaosheng.video.dao.auth.po.AuthRoleElementOperation;
import com.xiaosheng.video.dao.auth.po.AuthRoleMenu;
import com.xiaosheng.video.dao.auth.po.UserAuthorities;
import com.xiaosheng.video.dao.auth.po.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAuthService {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AuthRoleService authRoleService;


    public UserAuthorities getUserAuthorities(Long currentUserId) {
        List<UserRole> userRoleList = userRoleService.getUserRoleByUserId(currentUserId);
        Set<Long> roleIdSet = userRoleList.stream().map(UserRole::getRoleid).collect(Collectors.toSet());
        List<AuthRoleElementOperation> roleElementOperationList = authRoleService.getRoleElementOperationsByRoleIdSet(roleIdSet);
        List<AuthRoleMenu> authRoleMenuList = authRoleService.getAuthRoleMenusByRoleIds(roleIdSet);
        UserAuthorities userAuthorities = new UserAuthorities();
        userAuthorities.setRoleElementOperationList(roleElementOperationList);
        userAuthorities.setRoleMenuList(authRoleMenuList);
        return userAuthorities;
    }
}
