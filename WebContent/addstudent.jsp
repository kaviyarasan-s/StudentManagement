<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add student</title>
</head>
<body>
	<form action="StudentInfo" method="post">
		<div>
			Name:<input type="text" name="name">
		</div>
		<div>
			Email:<input type="text" name="email">
		</div>
		<div>
			Phone number:<input type="text" name="phonenumber">
		</div>
		<div>
			User Name:<input type="text" name="username">
		</div>
		<div>
			Password:<input type="password" name="password">
		</div>
		<div>
			<input type="hidden" name="request" value="addstudent">
			<button type="submit">Add</button>
		</div>
	</form>
	<c:if test="${MESSAGE!=null }">
		<div>${MESSAGE }</div>
	</c:if>

</body>
</html>