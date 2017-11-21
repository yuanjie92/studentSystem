package com.shsxt.service;

import java.util.List;

import com.shsxt.model.StudentModel;
import com.shsxt.model.data.StudentData;
import com.shsxt.model.form.StudentForm;
import com.shsxt.page.Pagination;
import com.shsxt.page.SearchResult;

public interface StudentService {

	List<StudentModel> findAll();

	void add(StudentForm studentForm);

	StudentData findById(Integer id);

	void updateById(StudentForm studentForm);

	void deleteById(StudentForm studentForm);

	SearchResult<StudentModel> findStudentsByName(String name, Pagination page);

	SearchResult<StudentModel> findStudentsByName(String name, String clazz, Pagination page);

	SearchResult<StudentData> findStudent(StudentForm studentForm, Pagination page);

}
