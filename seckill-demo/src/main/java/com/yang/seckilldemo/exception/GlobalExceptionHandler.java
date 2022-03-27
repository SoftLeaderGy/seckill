package com.yang.seckilldemo.exception;

import com.yang.seckilldemo.vo.RespBean;
import com.yang.seckilldemo.vo.RespBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Description: 全局异常类
 * @Author: Guo.Yang
 * @Date: 2022/03/25/19:57
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 需要处理的异常类
    public RespBean exceptionHandler(Exception e){
        if(e instanceof GlobalException){
            GlobalException e1 = (GlobalException) e;
            return RespBean.error(e1.getRespBeanEnum());
        } else if(e instanceof BindException) {
            BindException e1 = (BindException) e;
            RespBean error = RespBean.error(RespBeanEnum.BIND_ERROR);
            error.setMessage(e1.getBindingResult().getAllErrors().get(0).getDefaultMessage()); // 获取正常
            return error;
        }
        e.printStackTrace();
        return RespBean.error(RespBeanEnum.ERROR);
    }
}
