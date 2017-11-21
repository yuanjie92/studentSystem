package com.shsxt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.shsxt.common.service.CommonService;
import com.shsxt.convert.Convert;
import com.shsxt.dao.StudentDao;
import com.shsxt.model.StudentModel;
import com.shsxt.model.data.StudentData;
import com.shsxt.model.form.StudentForm;
import com.shsxt.page.Pagination;
import com.shsxt.page.SearchResult;
import com.shsxt.service.StudentService;
import com.shsxt.utils.DateUtil;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;
	private CommonService commonService;
	private Convert<StudentModel, StudentData> studentConvert;

	@Override
	public List<StudentModel> findAll() {
		List<StudentModel> studentModels = commonService.loadAllEntities(StudentModel.class);
		return studentModels;
	}

	@Override
	public void add(StudentForm studentForm) {
		StudentModel studentModel = new StudentModel();
		studentModel.setAvailable(true);
		studentModel.setBirthday(DateUtil.getDateByString(studentForm.getBirthday()));
		studentModel.setClazz(studentForm.getClazz());
		studentModel.setCreateTime(new Date());
		studentModel.setId(studentForm.getId());
		studentModel.setModifyTime(new Date());
		studentModel.setName(studentForm.getName());
		commonService.saveOrUpdateEntity(studentModel);
	}

	@Override
	public StudentData findById(Integer id) {
		StudentModel studentModel = (StudentModel) commonService.load(StudentModel.class, id);
		StudentData studentData = new StudentData();
		studentData.setAvailable(studentModel.isAvailable());
		studentData.setBirthday(studentModel.getBirthday());
		studentData.setClazz(studentModel.getClazz());
		studentData.setCreateTime(studentModel.getCreateTime());
		studentData.setId(studentModel.getId());
		studentData.setModifyTime(studentModel.getModifyTime());
		studentData.setName(studentModel.getName());
		return studentData;
	}

	@Override
	public void updateById(StudentForm studentForm) {
		StudentModel studentModel = (StudentModel) commonService.load(StudentModel.class, studentForm.getId());
		studentModel.setAvailable(true);
		studentModel.setBirthday(DateUtil.getDateByString(studentForm.getBirthday()));
		studentModel.setClazz(studentForm.getClazz());
		studentModel.setModifyTime(new Date());
		studentModel.setName(studentForm.getName());
		commonService.saveOrUpdateEntity(studentModel);
	}

	@Override
	public void deleteById(StudentForm studentForm) {
		StudentModel studentModel = (StudentModel) commonService.load(StudentModel.class, studentForm.getId());
		commonService.delete(studentModel);
	}

	@Override
	public SearchResult<StudentModel> findStudentsByName(String name, String clazz, Pagination page) {
		return studentDao.queryStudentByName(name, clazz, page);
	}

	@Override
	public SearchResult<StudentData> findStudent(StudentForm studentForm, Pagination page) {

		Map<String, Object> params = new HashMap<>();
		if (StringUtils.isNotBlank(studentForm.getName())) {
			params.put(StudentModel.NAME, studentForm.getName());
		}
		if (StringUtils.isNotBlank(studentForm.getClazz())) {
			params.put(StudentModel.CLASS, studentForm.getClazz());
		}

		SearchResult<StudentModel> searchResults = studentDao.queryStudent(params, page);
		// studentConvert

		SearchResult<StudentData> results = new SearchResult<StudentData>();
		List<StudentData> datas = new ArrayList<>();
		for (StudentModel model : searchResults.getResult()) {
			datas.add(studentConvert.convert(model));
		}
		results.setPagination(searchResults.getPagination());
		results.setResult(datas);
		return results;
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	@Override
	public SearchResult<StudentModel> findStudentsByName(String name, Pagination page) {
		// TODO Auto-generated method stub
		return null;
	}

	public Convert<StudentModel, StudentData> getStudentConvert() {
		return studentConvert;
	}

	public void setStudentConvert(Convert<StudentModel, StudentData> studentConvert) {
		this.studentConvert = studentConvert;
	}
}
