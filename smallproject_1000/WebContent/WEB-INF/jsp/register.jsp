<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=path%>/js/jQuery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/register.js"></script>
</head>
<body>
	<h2>register</h2>
	
	<form:form action="register" enctype="multipart/form-data" method="post" commandName="userForm">
		<font color="red"><form:errors path="name"/></font><br>
		<spring:message code="user.photo" />:<input type="file" name="photo"/><br>
		<spring:message code="user.name" />:<form:input path="name" id="name" /><span id="tipMsg"></span><br>
		<spring:message code="user.mobile" />:<input type="text" name="mobile"><br>
		<c:if test="${not empty password}">
			<font color="red">${password }</font>
		</c:if>
		<spring:message code="user.password" />:<input type="password" name="password"><br>
		<spring:message code="user.psaaword.confirm" />:<input type="password" name="confirmPassword"><br>
		<input type="submit" value="register"/>
	
	</form:form>
	
</body>
</html>