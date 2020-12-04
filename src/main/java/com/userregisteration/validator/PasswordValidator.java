package com.userregisteration.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * PasswordValidator
 */
@Constraint(validatedBy = { PasswordValidatorImpl.class })
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, 
    ElementType.CONSTRUCTOR, ElementType.PARAMETER , ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {

    String message() default "Password Did not matched";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}