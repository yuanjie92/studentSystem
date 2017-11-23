package com.shsxt.service;

import java.util.List;

import com.shsxt.model.UserModel;
import com.shsxt.model.form.UserForm;


public interface UserService
{

	void save(UserForm data);

	UserModel queryUser(UserForm userForm);

	UserModel queryUserByNameAndPassword(String name, String password);

	List<UserModel> uniqueByName(String name);

	String createToken(String name, String password);

	UserModel queryUserByName(String name);

}
