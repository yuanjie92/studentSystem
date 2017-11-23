package com.shsxt.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shsxt.model.UserModel;
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
	public String register(UserForm userForm, BindingResult bindingResult,
			@RequestParam("photo") MultipartFile photoFile, Model model) throws IOException {
		logger.info(String.format("register user [%s]...", ReflectionToStringBuilder.toString(userForm)));

		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			logger.info("user error log: %s" + ReflectionToStringBuilder.toString(bindingResult));
			model.addAttribute("userForm", userForm);
			return "register";
		}

		// upload photo
		String fileName = photoFile.getOriginalFilename();

		String newFileName = System.currentTimeMillis() + "_" + fileName;

		String encodingName = Base64.encodeBase64String(newFileName.getBytes());
		String result = encodingName.replaceAll("/", "_");
		byte[] bytes = photoFile.getBytes();
		String basePath = "E:\\tianwei\\imageTest\\upload";
		String path = basePath + File.separator + newFileName;
		File photo = new File(path);
		OutputStream output = new FileOutputStream(photo);
		output.write(bytes);
		output.flush();
		output.close();

		userForm.setPothoPath(result);

		userService.save(userForm);

		return "redirect:login";
	}

	@RequestMapping(value = "/unique", method = RequestMethod.POST)
	@ResponseBody
	public String unique(@RequestParam("name") String name) {
		List<UserModel> list = userService.uniqueByName(name);
		String data = "0";
		if (CollectionUtils.isNotEmpty(list)) {

			data = "1";
		}
		return data;
	}
}
