package com.shsxt.dao;

import com.shsxt.model.UserModel;

public interface UserDao {

	UserModel queryUserByNameAndPassword(String name, String password);
}
