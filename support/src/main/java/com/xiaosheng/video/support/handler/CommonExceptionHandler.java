package com.xiaosheng.video.support.handler;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xiaosheng.video.support.exception.BusinessException;
import com.xiaosheng.video.support.result.Result;
import com.xiaosheng.video.support.result.ResultCodeEnum;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> commonExceptionHandler(HttpServletRequest request, Exception e) {
        String message = e.getMessage();
        if (e instanceof BusinessException) {
            Integer code = ((BusinessException) e).getCode();
            return Result.fail(code, message);
        } else if (e instanceof TokenExpiredException) {
            return Result.buildResult(ResultCodeEnum.REFRESH_TOKEN_EXPIRATION);
        } else {
            return Result.fail(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.ERROR.getMsg());
        }

    }


}
