package com.flavien.dto.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.flavien.utils.Utils;

/**
 * 
 * Validate the computerDTO object.
 * 
 */

public class DtoValidator {

	public <T> List<String> validate(T objectDTO){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(objectDTO);
		List<String> errors = new ArrayList<>();

		if (!violations.isEmpty()) {			
			for (ConstraintViolation<T> constraintViolation : violations) {			
				errors.add(Utils.getErrorFromViolation(constraintViolation));			
			}
		}	
		return errors;
	}
}
