package com.github.deansquirrel.tools.common;

public enum ResponseCode {

    SUCCESS(0,"操作成功"),
    FAIL(-1,"操作失败");

    public int code;
    public String msg;

    ResponseCode(int code, String msg) {this.code = code; this.msg = msg;}

}
