package com.respondeaqui.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SexoImpl implements ConstraintValidator<Sexo, Character> {

    @Override
    public void initialize(Sexo constraintAnnotation) {}
    
    @Override
    public boolean isValid(Character sexo, ConstraintValidatorContext constraintValidatorContext) {
    	
        if ( sexo.charValue() == 'm' || sexo.charValue() == 'f' || sexo.charValue() == 'n' ) {
            return true;
        }
        	return false;
    }
}