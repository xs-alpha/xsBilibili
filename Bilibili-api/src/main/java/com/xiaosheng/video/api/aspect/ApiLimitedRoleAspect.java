package com.xiaosheng.video.api.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class ApiLimitedRoleAspect {

}
