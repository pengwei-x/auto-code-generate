package com.yunzhitx.jysq.common;

/**
 * @Description: 业务异常类

 * @Createed Date: 2018/6/13-下午7:28

 **/
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -2359843215972162510L;
    private RestStatus restStatus;

    public BizException() {
    }

    public BizException(RestStatus status) {
        super(status.message());
        this.restStatus = status;
    }

    public BizException(String message){
        super(message);
        this.restStatus = StatusCode.INVALID_MODEL_FIELDS;
        this.restStatus.setMessage(message);
    }

    public RestStatus getRestStatus() {
        return this.restStatus;
    }
}