package com.xiaosheng.video.support.result;

import java.io.Serializable;


public final class Result<T> implements Serializable {
    private static final long serialVersionUID = 8168900316765357276L;
    private Integer code;
    private String msg;
    private T data;

    private Result() {
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean isSuccess() {
        return this.code == ResultCodeEnum.SUCCESS.getCode();
    }

    public static <T> Result<T> buildSuccessResult() {
        return Result.ResultBuilder.newResult().buildCode(ResultCodeEnum.SUCCESS.getCode()).buildMsg(ResultCodeEnum.SUCCESS.getMsg()).build();
    }

    public static <T> Result<T> buildErrorResult() {
        return Result.ResultBuilder.newResult().buildCode(ResultCodeEnum.ERROR.getCode()).buildMsg(ResultCodeEnum.ERROR.getMsg()).build();
    }

    public static <T> Result<T> buildResult(ResultCode resultCode) {
        return Result.ResultBuilder.newResult().buildCode(resultCode.getCode()).buildMsg(resultCode.getMsg()).build();
    }

    public static <T> Result<T> buildResult(ResultCode resultCode, T data) {
        return Result.ResultBuilder.newResult().buildCode(resultCode.getCode()).buildMsg(resultCode.getMsg()).buildData(data).build();
    }

    public static <T> Result<T> buildSuccessResult(T data) {
        return Result.ResultBuilder.newResult().buildCode(ResultCodeEnum.SUCCESS.getCode()).buildMsg(ResultCodeEnum.SUCCESS.getMsg()).buildData(data).build();
    }

    public static <T> Result<T> buildErrorResult(T data) {
        return Result.ResultBuilder.newResult().buildCode(ResultCodeEnum.ERROR.getCode()).buildMsg(ResultCodeEnum.ERROR.getMsg()).buildData(data).build();
    }

    public static <T> Result<T> buildErrorResult(String msg, T data) {
        return Result.ResultBuilder.newResult().buildCode(ResultCodeEnum.ERROR.getCode()).buildMsg(msg).buildData(data).build();
    }

    public static <T> Result<T> buildErrorResult(String msg) {
        return Result.ResultBuilder.newResult().buildCode(ResultCodeEnum.ERROR.getCode()).buildMsg(msg).build();
    }

    private static final class ResultBuilder<T> {
        private Integer code;
        private String msg;
        private T data;

        private ResultBuilder() {
        }

        private static Result.ResultBuilder newResult() {
            return new Result.ResultBuilder();
        }

        private Result.ResultBuilder buildCode(Integer code) {
            this.code = code;
            return this;
        }

        private Result.ResultBuilder buildMsg(String msg) {
            this.msg = msg;
            return this;
        }

        private Result.ResultBuilder buildData(T data) {
            this.data = data;
            return this;
        }

        private Result build() {
            Result result = new Result();
            result.setCode(this.code);
            result.setMsg(this.msg);
            result.setData(this.data);
            return result;
        }
    }

    public static Result<String> success() {
        return Result.ResultBuilder.newResult().buildCode(0).buildMsg(ResultCodeEnum.SUCCESS.getMsg()).buildData("").build();
    }

    public static Result<String> fail() {
        return Result.ResultBuilder.newResult().buildCode(1).buildMsg("失败").build();
    }

    public static Result<String> fail(Integer code, String msg) {
        return Result.ResultBuilder.newResult().buildCode(code).buildMsg(msg).buildData(null).build();
    }
}
