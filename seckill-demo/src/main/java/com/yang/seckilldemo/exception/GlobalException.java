package com.yang.seckilldemo.exception;

import com.yang.seckilldemo.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 定义全局异常类
 * @Author: Guo.Yang
 * @Date: 2022/03/25/16:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private RespBeanEnum respBeanEnum;
}