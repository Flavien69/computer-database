package com.flavien.dto.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.flavien.utils.Utils;

public class DateValidator implements ConstraintValidator<Date, String>{

	@Override
	public void initialize(Date constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.isEmpty())
			return true;
		
		return Utils.isLocalDateTime(value);
	}
}
