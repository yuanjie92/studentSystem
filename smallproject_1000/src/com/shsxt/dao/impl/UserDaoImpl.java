package com.shsxt.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shsxt.common.dao.CommonDao;
import com.shsxt.dao.UserDao;
import com.shsxt.model.UserModel;

public class UserDaoImpl implements UserDao {

	private CommonDao commonDao;

	public UserModel queryUserByNameAndPassword(String name, String password) {
		JdbcTemplate jdbcTemplate = commonDao.getJdbcTemplate();
		String sql = "select * from users where name=? and password=?";
		// jdbcTemplate.query(sql, new Object[] { name, password }, new
		// UserModel());
		// List<UserModel> list = jdbcTemplate.queryForList(sql, new Object[] {
		// name, password },
		// new int[] { java.sql.Types.VARCHAR, java.sql.Types.VARCHAR },
		// UserModel.class);
		List<UserModel> list = jdbcTemplate.queryForList(sql, UserModel.class, name, password);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

}
