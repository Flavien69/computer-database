package com.flavien.dto.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.flavien.utils.Utils;

/**
 * 
 * Implementation of the custom date annotation to validate a date.
 * 
 */

public class DateValidator implements ConstraintValidator<Date, String>{

	@Autowired
	private Utils utils;
	
	@Override
	public void initialize(Date constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.isEmpty())
			return true;
		
		return utils.isLocalDateTime(value);
	}
}
