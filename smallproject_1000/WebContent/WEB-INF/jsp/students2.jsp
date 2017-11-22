<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>students list2</title>
</head>

<body>
userInfo:
name:${sessionScope.userModel.name },<br>
<c:url var="photoUrl" value="/media/userPhoto/${sessionScope.userModel.photo}"/>
大脑袋:<img alt="" src="${photoUrl }">



	<h1>students list2</h1>
	<hr>
	<a href="addStudent">add Student</a>&nbsp;
	<a href="logout">logout</a>
	<table border="1">
		<thead>
			<tr>
				<th><spring:message code="student.form.index" /></th>
				<th><spring:message code="student.form.id" /></th>
				<th><spring:message code="student.form.username" /></th>
				<th><spring:message code="student.form.clazz" /></th>
				<th><spring:message code="student.form.birthday" /></th>
				<th><spring:message code="student.form.createTime" /></th>
				<th><spring:message code="student.form.modifyTime" /></th>
				<th colspan="2"><spring:message code="student.form.operation" /></th>
			</tr>
		</thead>
		
		<tbody>
			<c:set var="result" value="${searchResult.result }" />
			<c:set var="pagination" value="${searchResult.pagination }" />

			<c:forEach items="${result }" var="stu" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${stu.id }</td>
					<td>${stu.name }</td>
					<td>${stu.clazz }</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${stu.birthday }" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
							value="${stu.createTime }" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
							value="${stu.modifyTime }" /></td>
					<td><a href="loadStudentById?id=${stu.id }"><spring:message code="student.form.update" /></a></td>
					<td><a href="deleteStudentById?id=${stu.id }"><spring:message code="student.form.delete" /></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<hr>

	<c:set var="currentPage" value="${pagination.currentPage }"/>
	<c:if test="${currentPage > 1}">
		<a href="loadStudentsByFields?currentPage=${currentPage-1 }"><spring:message code="pre.page"/></a>&nbsp;
	</c:if>

	${currentPage} &nbsp;

	<c:if test="${currentPage < pagination.totalPage}">
		<a href="loadStudentsByFields?currentPage=${currentPage+1 }"><spring:message code="next.page"/></a>&nbsp;
	</c:if>

</body>
</html>