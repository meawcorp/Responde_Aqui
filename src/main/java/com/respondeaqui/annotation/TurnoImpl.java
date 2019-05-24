package com.respondeaqui.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class TurnoImpl implements ConstraintValidator<Turno, Character> {

    @Override
    public void initialize(Turno constraintAnnotation) {}
    
    @Override
    public boolean isValid(Character turno, ConstraintValidatorContext constraintValidatorContext) {
    	
        if ( turno.charValue() == 'x' ) {
            return false;
        }
        	return true;
    }

}