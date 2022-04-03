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
    LOGIN_OVERDUE(500212,"账号过期，请重新登录！"),
    MOBILE_ERROR(500211,"手机号格式不正确！"),
    // 参数校验
    BIND_ERROR(500212,"参数校验异常"),

    // 商品
    GOODS_NULL(500300,"商品详情不存在！"),

    // 秒杀模块
    EMPTY_STOCK(500500,"该商品库存不足！"),
    REPEATE_ERROR(500501,"该商品每人限购一个！")
    ;
    private final Integer code;
    private final String message;
}
