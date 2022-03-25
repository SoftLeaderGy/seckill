package com.yang.seckilldemo.vo;

import lombok.*;

/**
 * @Description: 返回结果集的枚举
 * @Author: Guo.Yang
 * @Date: 2022/03/23/22:03
 */
@Getter
@AllArgsConstructor
@ToString
public enum RespBeanEnum {

    // 通用
    SUCCESS(200,"Success"),
    ERROR(500,"服务端异常"),

    // 登录模块
    LOGIN_ERROR(500210,"用户名或密码错误！"),
    MOBILE_ERROR(500211,"手机号格式不正确！")
    ;
    private final Integer code;
    private final String message;
}
