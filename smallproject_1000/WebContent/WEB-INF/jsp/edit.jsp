<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>edit student</title>
</head>
<body>
	<h1>edit student</h1>
	<hr>
	<form action="updateStudentById" method="post">
		<input type="hidden" name="id" value="${stuData.id }" >
		name:<input type="text" name="name" value="${stuData.name }" ><br>
		class:<input type="text" name="clazz" value="${stuData.clazz }" ><br>
		birthday:<input name="birthday" value="${stuData.birthday }" ><br>
		<input type="submit" value="修改" >
	</form>
</body>
</html>