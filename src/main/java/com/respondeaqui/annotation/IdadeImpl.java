package com.respondeaqui.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class IdadeImpl implements ConstraintValidator<Idade, Date> {
    protected long minAge;
    @Override
    public void initialize(Idade ageValue) {
        this.minAge = ageValue.value();
    }
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
    	
        if ( date != null ) {
	    	LocalDate localdate = this.convertToLocalDateViaInstant(date);
	    	
	        LocalDate today = LocalDate.now();
	        return ChronoUnit.YEARS.between(localdate, today)>=minAge;
        }
        return true;
    }
    
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }
}