<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답 완료</title>
</head>
<body>
	<h1>응답 완료!</h1>
	<p><%=(String)request.getParameter("name") %></p>
	<form action="<%=request.getContextPath() %>/test/a" method="post">
		<input type="text" name="name">
		<button type="submit">Test A 로 전송</button>
	</form>
	<form action="<%=request.getContextPath() %>/test/b" method="post">
		<input type="text" name="name">
		<button type="submit">Test B 로 전송</button>
	</form>
</body>
</html>