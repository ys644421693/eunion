package com.eunion.manage.common.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by yangshuo on 2016/11/27.
 */
public class ValidDataEntity {

    public static <T> String Check(T t){
        StringBuilder msg = new StringBuilder();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            msg.append(constraintViolation.getMessage());
        }
        return  msg.toString();
    }
}
