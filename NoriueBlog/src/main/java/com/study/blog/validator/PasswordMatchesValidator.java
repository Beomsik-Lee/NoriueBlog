package com.study.blog.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.study.blog.model.UserDto;

/**
 * The PasswordMatchesValidator Custom Validator
 * @author Beom
 *
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		UserDto user = (UserDto) value;
        return user.getPassword().equals(user.getMatchingPassword());
	}
}
