<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>students list</title>
</head>
<body>
	<h1>students list</h1>
	<hr>
	<a href="addStudent">add Student</a>
	<table border="1">
		<thead>
			<tr>
				<th>序号</th>
				<th>学号</th>
				<th><spring:message code="student.form.username"/></th>
				<th>班级</th>
				<th>生日</th>
				<th>入学时间</th>
				<th>修改时间</th>
				<th colspan="2">操作</th>
			</tr>		
		</thead>
		<tbody>
		<c:forEach items="${stuDatas }" var="stu" varStatus="status">
			<tr>
				<td>${status.index+1 }</td>
				<td>${stu.id }</td>
				<td>${stu.name }</td>
				<td>${stu.clazz }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${stu.birthday }"/></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${stu.createTime }"/></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${stu.modifyTime }"/></td>
				<td><a href="loadStudentById?id=${stu.id }">修改</a></td>
				<td><a href="deleteStudentById?id=${stu.id }">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>