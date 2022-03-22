package com.liuyang.test.controller.config.exception;

/**
 * @Description: TODO

 * @Createed Date: 2018/6/13-下午8:05

 **/
public class LoginTimeOutException extends RuntimeException{
    public LoginTimeOutException() {
        super("登陆超时！");
    }

    public LoginTimeOutException(String message) {
        super(message);
    }

    public LoginTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
}