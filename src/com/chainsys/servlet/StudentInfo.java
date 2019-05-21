package com.chainsys.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.model.StudentInfoModel;
import com.chainsys.services.StudentDetails;

/**
 * Servlet implementation class StudentInfo
 */
@WebServlet("/StudentInfo")
public class StudentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("request");
		StudentDetails studentDetails = new StudentDetails();
		RequestDispatcher requestDispatcher = null;
		List<StudentInfoModel> studentDetailsList = null;
		String message = null;
		int studentId = 0;
		switch (action) {
		case "addstudent":
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phonenumber = request.getParameter("phonenumber");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			StudentInfoModel studentInfoModel = new StudentInfoModel();
			if (!name.isEmpty() && name != null) {
				studentInfoModel.setName(name);
			}
			if (!email.isEmpty() && email != null) {
				studentInfoModel.setEmail(email);
			}
			if (!phonenumber.isEmpty() && phonenumber != null) {
				studentInfoModel.setPhonenumber(phonenumber);
			}
			if (!username.isEmpty() && username != null) {
				studentInfoModel.setUserName(username);
			}
			if (!password.isEmpty() && password != null) {
				studentInfoModel.setPassword(password);
			}
			studentInfoModel.setCreatedBy(1);
			studentInfoModel.setCreatedDate(LocalDateTime.now());
			try {
				boolean success = studentDetails.addStudent(studentInfoModel);
				if (success) {
					message = "Student info added successfully.";
				}
			} catch (Exception e) {
				message = e.getMessage();
			}
			request.setAttribute("MESSAGE", message);
			requestDispatcher = request.getRequestDispatcher("addstudent.jsp");
			requestDispatcher.forward(request, response);
			break;
		case "viewbyname":
			name = request.getParameter("name");
			StudentInfoModel infoModel = new StudentInfoModel();
			StudentInfoModel studentInfo = null;
			if (!name.isEmpty() && name != null) {
				infoModel.setName(name);
			}
			try {
				studentInfo = studentDetails.getStudentInfoByName(infoModel);
				if (studentInfo == null) {
					message = "No results found";
				}
			} catch (Exception e) {
				message = "No results found";
			}
			request.setAttribute("message", message);
			request.setAttribute("STUDENTDETAILS", studentInfo);
			requestDispatcher = request.getRequestDispatcher("viewstudent.jsp");
			requestDispatcher.forward(request, response);

			break;
		case "viewall":
			try {
				studentDetailsList = studentDetails.getAllStudentInfo();
				if (studentDetailsList.isEmpty() || studentDetailsList == null) {
					message = "no results found";
				}
			} catch (Exception e) {
				message = "no results found";
			}
			request.setAttribute("message", message);
			request.setAttribute("STUDENTlIST", studentDetailsList);
			requestDispatcher = request.getRequestDispatcher("viewallstudent.jsp");
			requestDispatcher.forward(request, response);

			break;
		case "editstudentprofile":
			studentId = Integer.valueOf(request.getParameter("id"));
			StudentInfoModel editStudent = new StudentInfoModel();
			StudentInfoModel editStudentProfile = null;
			editStudent.setId(studentId);
			try {
				editStudentProfile = studentDetails.getStudentInfoById(editStudent);
				if (editStudentProfile == null) {
					message = "No results found";
				}
			} catch (Exception e1) {
				message = "No results found";
			}
			request.setAttribute("message", message);
			request.setAttribute("STUDENTDETAILS", editStudentProfile);
			requestDispatcher = request.getRequestDispatcher("editprofile.jsp");
			requestDispatcher.forward(request, response);

			break;
		case "deletestudent":
			studentId = Integer.valueOf(request.getParameter("id"));
			String deleteMessage = "";
			StudentInfoModel deleteStudent = new StudentInfoModel();
			deleteStudent.setId(studentId);
			try {
				boolean success = studentDetails.deleteStudent(deleteStudent);
				if (success) {
					deleteMessage = "delete successfully";
					studentDetailsList = studentDetails.getAllStudentInfo();
					if (studentDetailsList.isEmpty()) {
						message = "no results found";
					}
				} else {
					deleteMessage = "delete successfully";
				}
			} catch (Exception e) {
				deleteMessage = e.getMessage();
			}
			request.setAttribute("message", message);
			request.setAttribute("deleteMessage", deleteMessage);
			request.setAttribute("STUDENTlIST", studentDetailsList);
			requestDispatcher = request.getRequestDispatcher("viewallstudent.jsp");
			requestDispatcher.forward(request, response);
			break;
		case "updateprofile":
			studentId = Integer.valueOf(request.getParameter("id"));
			name = request.getParameter("name");
			email = request.getParameter("email");
			phonenumber = request.getParameter("phonenumber");
			username = request.getParameter("username");
			password = request.getParameter("password");
			String updateMessage=null;
			StudentInfoModel updateInfo= new StudentInfoModel();
			if (!name.isEmpty() && name != null) {
				updateInfo.setName(name);
			}
			if (!email.isEmpty() && email != null) {
				updateInfo.setEmail(email);
			}
			if (!phonenumber.isEmpty() && phonenumber != null) {
				updateInfo.setPhonenumber(phonenumber);
			}
			if (!username.isEmpty() && username != null) {
				updateInfo.setUserName(username);
			}
			if (!password.isEmpty() && password != null) {
				updateInfo.setPassword(password);
			}
			updateInfo.setId(studentId);
			updateInfo.setModifiedBy(1);
			updateInfo.setModifiedDate(LocalDateTime.now());
			try {
				boolean success = studentDetails.updateStudent(updateInfo);
				if (success) {
					updateMessage = "Student info updated successfully.";
					studentDetailsList = studentDetails.getAllStudentInfo();
					if (studentDetailsList.isEmpty()||studentDetailsList==null) {
						message = "no results found";
					}
				}
			} catch (Exception e) {
				updateMessage = e.getMessage();
			}
			System.out.println(studentDetailsList);
			request.setAttribute("updateMessage", updateMessage);
			request.setAttribute("MESSAGE", message);
			request.setAttribute("STUDENTlIST", studentDetailsList);
			requestDispatcher = request.getRequestDispatcher("viewallstudent.jsp");
			requestDispatcher.forward(request, response);
			break;
		default:
			System.out.println("invalid input");
			break;
		}

	}

}
