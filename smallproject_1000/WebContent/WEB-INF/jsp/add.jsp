<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add student</title>
</head>
<body>
	<h1>add student</h1>
	<hr>
	<form action="addStudent" method="post">
		name:<input type="text" name="name" ><br>
		class:<input type="text" name="clazz" ><br>
		birthday:<input type="date" name="birthday" ><br>
		<input type="submit" value="添加" >
	</form>
</body>
</html>