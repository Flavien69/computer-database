package com.flavien.dto.validators;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.flavien.utils.Utils;

/**
 * 
 * Implementation of the custom date annotation to validate a date.
 * 
 */

public class DateValidator implements ConstraintValidator<Date, String>{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public void initialize(Date constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.isEmpty())
			return true;
		
		return Utils.isLocalDateTime(value, getDateRegex());
	}
	
	/**
	 * This method returns the date regex that is associated with the current
	 * Locale.
	 * 
	 * @return The date regex of the current locale.
	 */
	private String getDateRegex() {
		Locale userLocale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("date.regex", null, userLocale);

	}
}
