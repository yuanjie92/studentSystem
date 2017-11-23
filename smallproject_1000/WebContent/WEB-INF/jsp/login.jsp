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
	<h2>login</h2>
	
	<form:form action="login" method="post" commandName="userForm">
		<font color="red"><form:errors path="name"/></font><br>
		<spring:message code="user.name" />：<form:input path="name"/><br>
		<c:if test="${not empty name}">
			<font color="red">${name }</font>
		</c:if>
		<spring:message code="user.password" />：<form:password path="password"/><br>
		<c:if test="${not empty password}">
			<font color="red">${password }</font>
		</c:if>
		记住密码：<form:checkbox path="rememberMe"/><br>
		<input type="submit" value="login"/>
	</form:form>
	
</body>
</html>