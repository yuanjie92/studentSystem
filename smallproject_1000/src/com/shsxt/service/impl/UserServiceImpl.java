package com.shsxt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.shsxt.common.service.CommonService;
import com.shsxt.dao.UserDao;
import com.shsxt.model.UserModel;
import com.shsxt.model.form.UserForm;
import com.shsxt.service.UserService;

public class UserServiceImpl implements UserService {

	private CommonService commonService;

	private Md5PasswordEncoder md5Encoder;

	private UserDao userDao;

	@Override
	public void save(UserForm data) {

		UserModel model = new UserModel();
		model.setName(data.getName());
		model.setMobile(data.getMobile());
		String password = md5Encoder.encodePassword(data.getPassword(), data.getName());
		model.setPassword(password);
		model.setCreateDate(new Date());
		model.setAvailable(true);
		model.setPhoto(data.getPothoPath());
		commonService.saveOrUpdateEntity(model);
	}

	// 根据对象查询
	@Override
	public UserModel queryUser(UserForm userForm) {
		Map<String, Object> fields = new HashMap<>();
		fields.put(UserModel.NAME, userForm.getName());
		String password = md5Encoder.encodePassword(userForm.getPassword(), userForm.getName());
		fields.put(UserModel.PASSWORD, password);
		List<UserModel> list = commonService.getEntitiesByFields(UserModel.class, fields);
		// if (list != null && list.size() > 0) {
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	// 根据普通属性查询
	@Override
	public UserModel queryUserByNameAndPassword(String name, String password) {
		String newPassword = md5Encoder.encodePassword(password, name);
		return userDao.queryUserByNameAndPassword(name, newPassword);
	}

	@Override
	public List<UserModel> uniqueByName(String name) {
		return commonService.getEntitiesByField(UserModel.class, UserModel.NAME, name);
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public Md5PasswordEncoder getMd5Encoder() {
		return md5Encoder;
	}

	public void setMd5Encoder(Md5PasswordEncoder md5Encoder) {
		this.md5Encoder = md5Encoder;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
