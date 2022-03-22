package com.liuyang.test.controller.config.validate;


import com.yunzhitx.jysq.common.BizException;
import com.yunzhitx.jysq.common.InvokeResult;
import com.yunzhitx.jysq.common.StatusCode;
import com.yunzhitx.jysq.controller.config.exception.LoginTimeOutException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @Description: TODO

 * @Createed Date: 2018/6/13-下午8:00

 **/
@ControllerAdvice
public class RestErrorHandler {
    Logger logger = LoggerFactory.getLogger(RestErrorHandler.class);

    private MessageSource messageSource;

    @Autowired
    public RestErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 内部错误异常
     *
     * @param e
     * @param request
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exception(Exception e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        return InvokeResult.error();
    }

    /**
     * 业务异常判定
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BizException.class)
    public Object restStatusException(BizException e) {
        logger.error(e.getMessage());
        return InvokeResult.failure(e.getRestStatus().message());
    }

    /**
     * 登录超时异常
     *
     * @param e
     * @param request
     */
    @ResponseBody
    @ExceptionHandler(LoginTimeOutException.class)
    public Object exception(LoginTimeOutException e, HttpServletRequest request) {
        return InvokeResult.failure(401, "登录超时");
    }

    /**
     * 认证失败
     * @param ex
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public Object processValidationError(UnauthenticatedException ex) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("code", StatusCode.UNAUTHORIZED.code());
        params.put("msg", StatusCode.UNAUTHORIZED.message());
        return params;
    }

    /**
     * 无权访问
     * @param ex
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Object processValidationError(UnauthorizedException ex) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("code", StatusCode.INVALID_AUTH.code());
        params.put("msg", StatusCode.INVALID_AUTH.message());
        return params;
    }
    /**
     * 提交参数验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        Map<String, Object> params = new HashMap<>(2);
        params.put("code", StatusCode.INVALID_MODEL_FIELDS.code());
        params.put("msg", processFieldErrors(fieldErrors));
        return params;
    }

    private String processFieldErrors(List<FieldError> fieldErrors) {
        // ValidationErrorDTO dto = new ValidationErrorDTO();
        StringBuffer sb = new StringBuffer();
        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            sb.append(localizedErrorMessage);
            sb.append(";");
        }
        return sb.toString();
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        return localizedErrorMessage;
    }
}