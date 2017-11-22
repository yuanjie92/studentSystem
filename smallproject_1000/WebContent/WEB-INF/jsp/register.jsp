<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	System.out.println("path:"+path);//path:/smallproject_1000
	System.out.println("basePath:"+basePath);//basePath:http://localhost:8080/smallproject_1000
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
	
	<form:form action="register" method="post" commandName="userForm">
		<font color="red"><form:errors path="name"/></font><br>
		用户名：<form:input path="name" id="name" /><br>
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