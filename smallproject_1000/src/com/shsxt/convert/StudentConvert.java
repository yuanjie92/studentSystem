package com.shsxt.convert;

import com.shsxt.model.StudentModel;
import com.shsxt.model.data.StudentData;

public class StudentConvert implements Convert<StudentModel, StudentData> {

	@Override
	public StudentData createTarget() {
		return new StudentData();
	}

	@Override
	public StudentData convert(StudentModel model) {
		StudentData data = createTarget();
		data.setId(model.getId());
		data.setName(model.getName());
		data.setAvailable(model.isAvailable());
		data.setBirthday(model.getBirthday());
		data.setClazz(model.getClazz());
		data.setCreateTime(model.getCreateTime());
		data.setModifyTime(model.getModifyTime());
		return data;
	}
}
