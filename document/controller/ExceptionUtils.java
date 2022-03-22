package com.yunzhi.evaluate.controller.rest;

import com.yunzhi.evaluate.common.BizException;
import com.yunzhi.evaluate.common.InvokeResult;
import com.yunzhi.evaluate.common.RestStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: TODO

 * @Createed Date: 2019/3/27-15:58

 **/
@ControllerAdvice
public class ExceptionUtils {
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public InvokeResult defaultErrorHandler(BizException e) {
        return InvokeResult.failure(e.getRestStatus());
    }
}
