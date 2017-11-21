package com.shsxt.dao;

import java.util.Map;

import com.shsxt.model.StudentModel;
import com.shsxt.page.Pagination;
import com.shsxt.page.SearchResult;

public interface StudentDao {

	SearchResult<StudentModel> queryStudentByName(String name, Pagination page);

	SearchResult<StudentModel> queryStudentByName(String name, String clazz, Pagination page);

	SearchResult<StudentModel> queryStudent(Map<String, Object> params, Pagination page);

}
