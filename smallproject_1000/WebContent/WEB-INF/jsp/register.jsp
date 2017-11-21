<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>register</h2>
	
	<form:form action="register" method="post" commandName="userForm">
		<font color="red"><form:errors path="name"/></font><br>
		用户名：<form:input path="name"/><br>
		mobile:<input type="text" name="mobile"><br>
		<c:if test="${not empty password}">
			<font color="red">${password }</font>
		</c:if>
		password:<input type="password" name="password"><br>
		confirmPassword:<input type="password" name="confirmPassword"><br>
		<input type="submit" value="register"/>
	
	</form:form>
	
</body>
</html>