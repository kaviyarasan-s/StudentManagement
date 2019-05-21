package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.model.StudentInfoModel;
import com.chainsys.util.ConnectionUtil;
import com.chainsys.util.SendEmail;

public class StudentInfoDAO {

	public boolean addStudentInfo(StudentInfoModel studentInfoModel) throws Exception {
		Connection connection;
		boolean success = false;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO trn_student_info(id,name,email_id,phone_number,user_name,password,created_by,created_date) VALUES(student_id_seq.nextval,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, studentInfoModel.getName());
			preparedStatement.setString(2, studentInfoModel.getEmail());
			preparedStatement.setString(3, studentInfoModel.getPhonenumber());
			preparedStatement.setString(4, studentInfoModel.getUserName());
			preparedStatement.setString(5, studentInfoModel.getPassword());
			preparedStatement.setInt(6, studentInfoModel.getCreatedBy());
			preparedStatement.setTimestamp(7, Timestamp.valueOf(studentInfoModel.getCreatedDate()));
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				SendEmail.sendMail(studentInfoModel);
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return success;
	}

	public StudentInfoModel getStudentInfoByName(StudentInfoModel studentInfoModel) throws Exception {
		Connection connection;
		StudentInfoModel studentInfo = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,name,email_id,phone_number,user_name,password from trn_student_info where name=? and status=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, studentInfoModel.getName());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					studentInfo = new StudentInfoModel();
					studentInfo.setId(resultSet.getInt("id"));
					studentInfo.setName(resultSet.getString("name"));
					studentInfo.setEmail(resultSet.getString("email_id"));
					studentInfo.setPhonenumber(resultSet.getString("phone_number"));
					studentInfo.setUserName(resultSet.getString("user_name"));
					studentInfo.setPassword(resultSet.getString("password"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return studentInfo;
	}

	public StudentInfoModel getStudentInfoById(StudentInfoModel studentInfoModel) throws Exception {
		Connection connection;
		StudentInfoModel studentInfo = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,name,email_id,phone_number,user_name,password from trn_student_info where id=? and status=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, studentInfoModel.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					studentInfo = new StudentInfoModel();
					studentInfo.setId(resultSet.getInt("id"));
					studentInfo.setName(resultSet.getString("name"));
					studentInfo.setEmail(resultSet.getString("email_id"));
					studentInfo.setPhonenumber(resultSet.getString("phone_number"));
					studentInfo.setUserName(resultSet.getString("user_name"));
					studentInfo.setPassword(resultSet.getString("password"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return studentInfo;
	}
	
	public List<StudentInfoModel> getAllStudentInfo() throws Exception {
		Connection connection;
		List<StudentInfoModel> studentInfoList = new ArrayList<StudentInfoModel>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,name,email_id,phone_number,user_name,password from trn_student_info where status=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					StudentInfoModel studentInfo = new StudentInfoModel();
					studentInfo.setId(resultSet.getInt("id"));
					studentInfo.setName(resultSet.getString("name"));
					studentInfo.setEmail(resultSet.getString("email_id"));
					studentInfo.setPhonenumber(resultSet.getString("phone_number"));
					studentInfo.setUserName(resultSet.getString("user_name"));
					studentInfo.setPassword(resultSet.getString("password"));
					studentInfoList.add(studentInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return studentInfoList;
	}

	public boolean updateStudentInfoById(StudentInfoModel studentInfoModel) throws Exception {
		Connection connection;
		boolean success = false;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "UPDATE trn_student_info SET name=?,email_id=?,phone_number=?,user_name=?,password=?,modified_by=?,modified_date=? WHERE id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, studentInfoModel.getName());
			preparedStatement.setString(2, studentInfoModel.getEmail());
			preparedStatement.setString(3, studentInfoModel.getPhonenumber());
			preparedStatement.setString(4, studentInfoModel.getUserName());
			preparedStatement.setString(5, studentInfoModel.getPassword());
			preparedStatement.setInt(6, studentInfoModel.getModifiedBy());
			preparedStatement.setTimestamp(7, Timestamp.valueOf(studentInfoModel.getModifiedDate()));
			preparedStatement.setInt(8, studentInfoModel.getId());
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return success;
	}
	
	public boolean deleteStudentInfoById(StudentInfoModel studentInfoModel) throws Exception {
		Connection connection;
		boolean success = false;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "UPDATE trn_student_info SET status=0 WHERE id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, studentInfoModel.getId());
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return success;
	}

}
