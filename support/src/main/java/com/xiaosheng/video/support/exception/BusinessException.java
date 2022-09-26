package com.xiaosheng.video.support.exception;

import com.xiaosheng.video.support.result.ResultCode;
import com.xiaosheng.video.support.result.ResultCodeEnum;

public class BusinessException extends RuntimeException {
    protected Integer code;
    protected String msg;
    protected ResultCode resultCode;

    public BusinessException(String message) {
        super(message);
        this.code = ResultCodeEnum.ERROR.getCode();
        this.msg = message;
    }

    public BusinessException(ResultCode resultCode) {
        this(resultCode.getMsg());
        this.resultCode = resultCode;
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public BusinessException(ResultCode resultCode, String msg) {
        this(msg);
        this.resultCode = resultCode;
        this.code = resultCode.getCode();
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public Integer getCode() {
        return this.code;
    }
}

