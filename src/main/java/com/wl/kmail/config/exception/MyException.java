package com.wl.kmail.config.exception;

import lombok.Data;

/**
 * @program: kmail
 * @description: 自定义异常类
 * @author: Koty
 * @create: 2020-02-14 13:35
 **/
@Data
public class MyException extends RuntimeException {

    private int code; // exception code

    private String msg; // exception message

    public MyException(HttpCode httpCode) {
        this.code = httpCode.getCode();
        this.msg = httpCode.getMsg();
    }

    public MyException msg(String msg) {
        this.msg = msg;
        return this;
    }
}
