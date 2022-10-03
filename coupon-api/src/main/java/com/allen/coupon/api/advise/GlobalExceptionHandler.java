package com.allen.coupon.api.advise;

import com.allen.coupon.common.result.BaseResult;
import com.allen.coupon.common.result.Result;
import com.allen.coupon.domain.handling.BusinessHandlerException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result runtimeExceptionHandler(Exception exception) {
        Result result=new Result();
        result.setCode(400);
        result.setMessage(exception.getLocalizedMessage());
        return result;
    }

    /**
     * 处理异常
     */
    @ExceptionHandler(value = BusinessHandlerException.class)
    @ResponseBody
    public Result runtimeExceptionHandler(BusinessHandlerException exception) {
        Result result=new Result();
        result.setCode(400);
        result.setMessage(exception.getLocalizedMessage());
        return result;
    }

    /**
     * 处理异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result runtimeExceptionHandler(MethodArgumentNotValidException exception) {
        Result result=new Result();
        result.setCode(BaseResult.CODE_SYSTEM_ERROR);
        result.setMessage(exception.getBindingResult().getFieldError().getDefaultMessage());
        return result;
    }
}