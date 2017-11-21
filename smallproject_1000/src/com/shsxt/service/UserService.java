package com.shsxt.service;

import com.shsxt.model.UserModel;
import com.shsxt.model.form.UserForm;

public interface UserService {

	void save(UserForm data);

	UserModel queryUser(UserForm userForm);

	UserModel queryUserByNameAndPassword(String name, String password);

}
