package com.respondeaqui.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SelectImpl implements ConstraintValidator<Select, Integer> {

    @Override
    public void initialize(Select constraintAnnotation) {}
    
    @Override
    public boolean isValid(Integer select, ConstraintValidatorContext constraintValidatorContext) {
    	
        if ( select.intValue() == 0 ) {
            return false;
        }
        	return true;
    }

}