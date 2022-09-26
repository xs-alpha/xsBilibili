package com.xiaosheng.video.support.result;

public enum ResultCodeEnum implements ResultCode {
    SUCCESS(200, "成功"),
    ERROR(500, "服务器错误"),
    BAD_REQUEST(400, "请求失败"),
    NOT_LOGIN(401, "未授权"),
    REFRESH_TOKEN_EXPIRATION(403, "刷新token过期"),
    SIGN_ERROR(420, "接口签名错误"),
    SIGN_TIMEOUT_ERROR(421, "接口签名超时"),
    REPEAT_SUBMIT_EXPIRATION(440, "请勿重复提交");

    private int code;
    private String msg;

    private ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

