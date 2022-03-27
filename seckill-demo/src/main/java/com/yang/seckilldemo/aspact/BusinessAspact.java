package com.yang.seckilldemo.aspact;

import com.yang.seckilldemo.exception.GlobalException;
import com.yang.seckilldemo.pojo.User;
import com.yang.seckilldemo.service.UserService;
import com.yang.seckilldemo.utils.CookieUtil;
import com.yang.seckilldemo.vo.RespBeanEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/27/17:56
 */
@Aspect
@Component
@Slf4j
public class BusinessAspact {

    @Autowired
    private UserService userService;

    @Pointcut(value = "execution(* com.yang.seckilldemo.controller.businessController.*Controller.*(..))")
    public void businessAspact(){

    }

    @Around("businessAspact()")
    @SneakyThrows
    @ResponseBody
    public Object doBefore(ProceedingJoinPoint joinPoint){
        String userTicket = "";
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        for (Object arg : joinPoint.getArgs()) {
            if(arg instanceof HttpServletRequest){
                request = (HttpServletRequest) arg;
                for (Cookie cookie : ((HttpServletRequest) arg).getCookies()) {
                    if (cookie.getName().equals("userTicket")) {
                        userTicket = cookie.getValue();
                        if(StringUtils.isEmpty(userTicket)){
//                            throw new GlobalException(RespBeanEnum.LOGIN_OVERDUE);
                            return "login";
                        }
                    }
                }
            }
            if(arg instanceof HttpServletResponse){
                response = (HttpServletResponse) arg;
            }
        }
        User user = userService.queryUserByCookie(request, response, userTicket);
        if (user == null) {
//            throw new GlobalException(RespBeanEnum.LOGIN_OVERDUE);
            return "login";
        }
        Object proceed = joinPoint.proceed();
        return "goodsList";
    }
}
