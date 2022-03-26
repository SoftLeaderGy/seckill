package com.yang.seckilldemo.utils;

import com.yang.seckilldemo.annotation.IsMobile;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/25/14:27
 */
@Slf4j
public class ValidatorMobile implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;

    /**
     * 初识化方法
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    /**
     * 验证
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            // 必填
            return ValidatorUtil.isMobile(s);
        }else {
            // 非必填
            if(StringUtils.isEmpty(s)){
                return true;
            } else {
                return ValidatorUtil.isMobile(s);
            }
        }
    }
}
