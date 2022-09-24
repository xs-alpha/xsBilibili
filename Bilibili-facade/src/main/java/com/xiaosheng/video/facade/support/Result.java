package com.xiaosheng.video.facade.support;

import lombok.Data;

@Data
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(T data) {
        this.data = data;
        msg = "成功";
        code = "0";
    }

    public static Result<String> success() {
        return new Result<>(null);
    }

    public static Result<String> success(String data) {
        return new Result<>(data);
    }

    public static Result<String> fail() {
        return new Result<>("1", "失败");
    }

    public static Result<String> fail(String code, String msg) {
        return new Result<>(code, msg);
    }
}
