package com.shsxt.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.shsxt.model.form.UserForm;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm form = (UserForm) target;

		if (form.isNew()) {
			String name = form.getName();
			String mobile = form.getMobile();
			String password = form.getPassword();
			String confirmPassword = form.getConfirmPassword();

			if (StringUtils.isBlank(name)) {
				errors.rejectValue("name", "user.name.empty");
			}

			if (StringUtils.isBlank(mobile)) {
				errors.rejectValue("mobile", "user.mobile.empty");
			}

			if (!StringUtils.equals(password, confirmPassword)) {
				errors.rejectValue("password", "user.password.equal.error", "user.password.equal.error");
			}
		}

		String name = form.getName();
		String password = form.getPassword();
		if (StringUtils.isBlank(name)) {
			errors.rejectValue("name", "user.name.empty");
		}
		if (StringUtils.isBlank(password)) {
			errors.rejectValue("password", "user.password.empty");
		}

	}

}
