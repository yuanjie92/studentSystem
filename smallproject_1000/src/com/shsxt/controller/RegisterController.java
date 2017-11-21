package com.shsxt.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shsxt.model.form.UserForm;
import com.shsxt.service.UserService;
import com.sun.istack.internal.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private Logger logger = Logger.getLogger(getClass());

	@Resource
	private UserService userService;

	@Resource(name = "userValidator")
	private Validator userValidator;

	@RequestMapping
	public String register(Model model) {
		model.addAttribute("userForm", new UserForm());
		logger.info("register page ...");
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(UserForm userForm, BindingResult bindingResult, Model model) {
		logger.info(String.format("register user [%s]...", ReflectionToStringBuilder.toString(userForm)));

		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			logger.info("user error log: %s" + ReflectionToStringBuilder.toString(bindingResult));
			model.addAttribute("userForm", userForm);
			return "register";
		}

		userService.save(userForm);
		return "redirect:loadStudentsByFields";
	}
}
