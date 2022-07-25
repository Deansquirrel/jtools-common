package com.github.deansquirrel.tools.common;

public class ResponseResult<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult<T> initCode(ResponseCode retCode) {
        this.code = retCode.code;
        return this;
    }

    public ResponseResult<T> initCode(int code) {
        this.code = code;
        return this;
    }

    public ResponseResult<T> initMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResponseResult<T> initData(T data) {
        this.data = data;
        return this;
    }
}
