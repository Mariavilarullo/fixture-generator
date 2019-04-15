package ar.edu.uces.progweb2.springmvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.springmvc.model.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.user.name.empty");
	}

}
