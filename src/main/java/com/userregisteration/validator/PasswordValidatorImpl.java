package com.userregisteration.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.userregisteration.dto.PasswordDto;
import com.userregisteration.exception.PasswordDidNotMatchException;

public class PasswordValidatorImpl implements ConstraintValidator<PasswordValidator, PasswordDto> {

    /**
     * @param value
     * @param context
     * @return boolean
     */
    @Override
    public boolean isValid(PasswordDto value, ConstraintValidatorContext context) {
        if (value.getNewPassword().equalsIgnoreCase(value.getOldPassword())) {
            throw new PasswordDidNotMatchException("Password should not be same as old password");
        }
        return true;
    }
}
