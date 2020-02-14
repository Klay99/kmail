package com.wl.kmail.config.exception;

/**
 * 自定义异常枚举类
 */
public enum HttpCode {

    SUCCESS(200, "访问成功"),
    ERROR(500, "访问失败，服务器出错了。。。")
    ;

    private int code;

    private String msg;

    HttpCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
