package com.xiaosheng.video.facade.enums;

import com.xiaosheng.video.support.result.ResultCode;

public enum BizResultCodeEnum implements ResultCode {
    /**
     * 101 用户类
     **/
    CODE_ERR_PHONE(101001, "手机号错误"),

    CODE_PARAM_NULL_ERR(101002, "参数为空"),

    CODE_PASSWORD_PARSE_EXCEPTION(101003, "密码解析错误"),

    CODE_PHONE_ALREADY_REGISTERED(101004, "手机号已注册"),

    CODE_USER_NOT_EXIST(101005, "当前用户不存在"),

    CODE_INCORRECT_PASSWORD(101006, "用户名密码不匹配"),

    CODE_TOKEN_GENERATE_FAILED(101007, "token生成失败"),
    ;


    BizResultCodeEnum(int resultCode, String resultMsg) {
        this.code = resultCode;
        this.msg = resultMsg;
    }

    private int code;
    private String msg;

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String formatMsg(Object... args) {
        return String.format(msg, args);
    }

}
