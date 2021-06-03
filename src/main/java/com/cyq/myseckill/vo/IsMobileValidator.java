package com.cyq.myseckill.vo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cyq.myseckill.utils.ValidatorUtil;
import com.cyq.myseckill.validator.IsMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机号码校验规则
 * @author chenyongquan
 * @version 1.0
 * @date 2021/5/18-9:28
 * @description com.cyq.myseckill.vo
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(required) {
            return ValidatorUtil.isMobile(value);
        }else {
            if(StringUtils.isBlank(value)) {
                return true;
            }else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
