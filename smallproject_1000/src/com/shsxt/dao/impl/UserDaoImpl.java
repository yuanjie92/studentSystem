package com.shsxt.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.shsxt.common.dao.CommonDao;
import com.shsxt.dao.UserDao;
import com.shsxt.model.UserModel;


public class UserDaoImpl implements UserDao
{

	private CommonDao commonDao;

	public UserModel queryUserByNameAndPassword(String name, String password)
	{
		JdbcTemplate jdbcTemplate = commonDao.getJdbcTemplate();
		String sql = "select * from users where name=? and password=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]
		{ name, password });

		if (list == null)
		{
			return null;
		}
		for (Map<String, Object> map : list)
		{
			UserModel u = new UserModel();
			Integer id = (Integer) map.get(UserModel.ID);
			String userName = (String) map.get(UserModel.NAME);
			String mobile = (String) map.get(UserModel.MOBILE);
			boolean available = (boolean) map.get(UserModel.AVAILABLE);
			Timestamp createDate = (Timestamp) map.get(UserModel.CREATEDATE);
			u.setId(id);
			u.setName(userName);
			u.setMobile(mobile);
			u.setAvailable(available);
			u.setCreateDate(createDate);
			return u;
		}
		return null;
	}

	public CommonDao getCommonDao()
	{
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao)
	{
		this.commonDao = commonDao;
	}

}
