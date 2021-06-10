<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터베이스 예제</title>
</head>
<body>
	<h1>데이터베이스 연결하기 1</h1>
	<c:url var="url1" value="/db/first" />
	<form action="${url1 }" method="post">
		<input type="text" name="id">
		<button type="submit">전송</button>
	</form>
	<hr>
	<h1>데이터베이스 연결하기 2</h1>
	<c:url var="url2" value="/db/second" />
	<form action="${url2 }" method="post">
		<input type="text" name="id">
		<button type="submit">전송</button>
	</form>
</body>
</html>