package com.yang.seckilldemo.annotation;

import com.yang.seckilldemo.utils.ValidatorMobile;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/25/12:20
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {ValidatorMobile.class}
)
public @interface IsMobile {
    // 必填验证，并且默认为true
    boolean required() default true;

    String message() default "手机号格式不正确！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
