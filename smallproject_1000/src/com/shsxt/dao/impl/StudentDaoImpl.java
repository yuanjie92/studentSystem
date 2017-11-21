package com.shsxt.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.shsxt.common.dao.CommonDao;
import com.shsxt.dao.StudentDao;
import com.shsxt.model.StudentModel;
import com.shsxt.page.Pagination;
import com.shsxt.page.SearchResult;

public class StudentDaoImpl implements StudentDao {

	private CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public SearchResult<StudentModel> queryStudentByName(String name, String clazz, Pagination page) {
		// String sql = "FROM StudentModel where name=:name2";
		// String sql = "select * from Student where name=:name2";
		// Map<String, Object> param = new HashMap<>();
		// param.put("name2", name);
		// return commonDao.search(sql, page, param, StudentModel.class);
		// return commonDao.getEntitiesByFieldWithPagination(StudentModel.class,
		// StudentModel.NAME, name, null, page);
		HashMap<String, Object> fields = new HashMap<>();
		fields.put(StudentModel.NAME, name);
		fields.put(StudentModel.CLASS, clazz);
		return commonDao.getEntitiesByFields(StudentModel.class, fields, null, page);
	}

	@Override
	public SearchResult<StudentModel> queryStudentByName(String name, Pagination page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResult<StudentModel> queryStudent(Map<String, Object> params, Pagination page) {
		return commonDao.getEntitiesByFields(StudentModel.class, params, null, page);
	}

}
