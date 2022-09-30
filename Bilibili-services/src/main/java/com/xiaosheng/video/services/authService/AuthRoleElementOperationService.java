package com.xiaosheng.video.services.authService;

import com.xiaosheng.video.dao.auth.mapper.AuthRoleElementOperationMapper;
import com.xiaosheng.video.dao.auth.po.AuthRoleElementOperation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class AuthRoleElementOperationService {
    @Resource
    private AuthRoleElementOperationMapper authRoleElementOperationMapper;

    public List<AuthRoleElementOperation> getRoleElementOperationsByRoleIds(Set<Long> roleIdSet) {
        return authRoleElementOperationMapper.getRoleElementOperationsByRoleIds(roleIdSet);
    }
}
