package com.flavien.dto.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flavien.utils.Utils;

/**
 * 
 * Validate the computerDTO object.
 * 
 */
@Component
public class DtoValidator {
	@Autowired
	private Utils utils;

	public <T> List<String> validate(T objectDTO){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(objectDTO);
		List<String> errors = new ArrayList<>();

		if (!violations.isEmpty()) {			
			for (ConstraintViolation<T> constraintViolation : violations) {			
				errors.add(utils.getErrorFromViolation(constraintViolation));			
			}
		}	
		return errors;
	}
}
