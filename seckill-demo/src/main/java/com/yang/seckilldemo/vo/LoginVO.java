package com.yang.seckilldemo.vo;

import com.yang.seckilldemo.annotation.IsMobile;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: 登录入参
 * @Author: Guo.Yang
 * @Date: 2022/03/24/18:18
 */
@Data
public class LoginVO {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    private String password;
}
