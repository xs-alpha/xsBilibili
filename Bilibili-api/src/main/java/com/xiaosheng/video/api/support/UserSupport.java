package com.xiaosheng.video.api.support;

import com.xiaosheng.video.support.exception.BusinessException;
import com.xiaosheng.video.support.result.ResultCodeEnum;
import com.xiaosheng.video.support.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class UserSupport {
    public Long getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader("token");
        Long userId = TokenUtil.verifyToken(token);
        if (userId == null || StringUtils.isEmpty(String.valueOf(userId))) {
            throw new BusinessException(ResultCodeEnum.INVALID_TOKEN);
        }
        return userId;
    }
}
