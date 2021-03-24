<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
</head>
<body>
	<h1>회원가입 완료</h1>
	<p>
		가입 아이디 : <span><%=request.getParameter("userid") %></span><br>
		가입 이메일 : <span><%=request.getParameter("email") %></span>
	</p>
</body>
</html>