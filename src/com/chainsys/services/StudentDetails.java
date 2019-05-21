package com.chainsys.services;

import java.util.List;

import com.chainsys.dao.StudentInfoDAO;
import com.chainsys.model.StudentInfoModel;

public class StudentDetails {

	public boolean addStudent(StudentInfoModel studentInfoModel) throws Exception {
		StudentInfoDAO studentInfoDAO = new StudentInfoDAO();
		return studentInfoDAO.addStudentInfo(studentInfoModel);
	}

	public boolean updateStudent(StudentInfoModel studentInfoModel) throws Exception {
		StudentInfoDAO studentInfoDAO = new StudentInfoDAO();
		return studentInfoDAO.updateStudentInfoById(studentInfoModel);
	}

	public StudentInfoModel getStudentInfoByName(StudentInfoModel studentInfoModel) throws Exception {
		StudentInfoDAO studentInfoDAO = new StudentInfoDAO();
		return studentInfoDAO.getStudentInfoByName(studentInfoModel);
	}
	
	public StudentInfoModel getStudentInfoById(StudentInfoModel studentInfoModel) throws Exception {
		StudentInfoDAO studentInfoDAO = new StudentInfoDAO();
		return studentInfoDAO.getStudentInfoById(studentInfoModel);
	}

	public List<StudentInfoModel> getAllStudentInfo() throws Exception {
		StudentInfoDAO studentInfoDAO = new StudentInfoDAO();
		return studentInfoDAO.getAllStudentInfo();
	}
	
	public boolean deleteStudent(StudentInfoModel studentInfoModel) throws Exception {
		StudentInfoDAO studentInfoDAO = new StudentInfoDAO();
		return studentInfoDAO.deleteStudentInfoById(studentInfoModel);
	}

}
