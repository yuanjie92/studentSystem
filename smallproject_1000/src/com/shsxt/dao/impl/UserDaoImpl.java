package com.shsxt.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.shsxt.common.dao.CommonDao;
import com.shsxt.dao.UserDao;
import com.shsxt.model.UserModel;

public class UserDaoImpl implements UserDao {

	private CommonDao commonDao;

	public UserModel queryUserByNameAndPassword(String name, String password) {
		JdbcTemplate jdbcTemplate = commonDao.getJdbcTemplate();
		String sql = "select id from users where name=? and password=?";
		jdbcTemplate.queryForList(sql, new Object[] { name, password }, Integer.class);

		return null;
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

}
