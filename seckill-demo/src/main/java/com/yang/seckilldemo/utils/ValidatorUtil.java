package com.yang.seckilldemo.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/24/21:18
 */
public class ValidatorUtil {

    // 手机号正则表达式校验
    private static final Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");

    // 判断手机好是否是规则的手机号
    public static boolean isMobile(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobile);
        return  matcher.matches();
    }
}
