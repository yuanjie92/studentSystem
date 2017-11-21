package com.shsxt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shsxt.model.StudentModel;
import com.shsxt.model.data.StudentData;
import com.shsxt.model.form.StudentForm;
import com.shsxt.page.Pagination;
import com.shsxt.page.SearchResult;
import com.shsxt.service.StudentService;

@Controller
public class StudentController {

	@Resource
	private StudentService studentService;

	@RequestMapping("/loadStudents")
	public String loadStudents(Model model) {
		List<StudentModel> list = studentService.findAll();
		model.addAttribute("stuDatas", list);
		return "students";
	}

	@RequestMapping("/loadStudentByName")
	public String loadStudents(Model model, @RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "clazz", required = false) String clazz,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {

		Pagination page = new Pagination();
		if (currentPage <= 0) {
			currentPage = 1;
		}
		page.setCurrentPage(currentPage);
		page.setPageSize(3);

		SearchResult<StudentModel> searchResult = studentService.findStudentsByName(name, clazz, page);
		model.addAttribute("searchResult", searchResult);
		return "students2";
	}

	@RequestMapping("/loadStudentsByFields")
	public String loadStudentsByFields(Model model, StudentForm studentForm,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {

		Pagination page = new Pagination();
		if (currentPage <= 0) {
			currentPage = 1;
		}
		page.setCurrentPage(currentPage);
		page.setPageSize(3);

		SearchResult<StudentData> searchResult = studentService.findStudent(studentForm, page);
		model.addAttribute("searchResult", searchResult);
		return "students2";
	}

	@RequestMapping("/addStudent")
	public String addStudent() {
		return "add";
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String addStudent(StudentForm studentForm) {
		studentService.add(studentForm);
		return "redirect:loadStudents";
	}

	@RequestMapping("/loadStudentById")
	public String loadStudentById(Integer id, Model model) {
		StudentData stuData = studentService.findById(id);
		model.addAttribute("stuData", stuData);
		return "edit";
	}

	@RequestMapping(value = "/updateStudentById", method = RequestMethod.POST)
	public String updateStudentById(StudentForm studentForm) {
		studentService.updateById(studentForm);
		return "redirect:loadStudents";
	}

	@RequestMapping("/deleteStudentById")
	public String deleteStudentById(StudentForm studentForm) {
		studentService.deleteById(studentForm);
		return "redirect:loadStudents";
	}

}
