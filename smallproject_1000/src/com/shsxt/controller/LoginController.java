package com.shsxt.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shsxt.model.UserModel;
import com.shsxt.model.form.UserForm;
import com.shsxt.service.UserService;
import com.sun.istack.internal.logging.Logger;


@Controller
public class LoginController
{

	private Logger logger = Logger.getLogger(getClass());

	@Resource
	private UserService userService;

	@Resource(name = "userValidator")
	private Validator userValidator;

	@RequestMapping("/login")
	public String login(Model model)
	{
		model.addAttribute("userForm", new UserForm());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserForm userForm, BindingResult bindingResult, Model model, HttpSession session,
			HttpServletResponse response)
	{
		logger.info(String.format("login [%s]...", ReflectionToStringBuilder.toString(userForm)));

		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors())
		{

			logger.info(String.format("user error log: %s", ReflectionToStringBuilder.toString(bindingResult)));
			model.addAttribute("userForm", userForm);
			return "login";
		}

		UserModel userModel = userService.queryUser(userForm);

		if (null == userModel)
		{
			return "login";
		}
		if (userForm.isRememberMe())
		{
			String token = userService.createToken(userForm.getName(), userModel.getPassword());
			logger.info(String.format("remember-Me token is %s", token));
			Cookie cookie = new Cookie("rememberMe", token);
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
		}

		session.setAttribute("userModel", userModel);
		return "redirect:loadStudentsByFields";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session, Model model)
	{
		model.addAttribute("userForm", new UserForm());
		session.invalidate();
		return "login";
	}
}
