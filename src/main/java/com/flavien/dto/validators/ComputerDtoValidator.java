package com.flavien.dto.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.flavien.dto.ComputerDTO;
import com.flavien.utils.Utils;

public class ComputerDtoValidator {

	public static List<String> validate(ComputerDTO computerDTO){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ComputerDTO>> violations = validator.validate(computerDTO);
		List<String> errors = new ArrayList<>();

		if (!violations.isEmpty()) {			
			for (ConstraintViolation<ComputerDTO> constraintViolation : violations) {			
				errors.add(Utils.getErrorFromViolation(constraintViolation));			
			}
		}
		
		return errors;
	}
}
