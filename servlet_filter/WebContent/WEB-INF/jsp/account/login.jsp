<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<% String err_msg = (String)request.getAttribute("login_error"); %>
	<h1>로그인</h1>
	<form action="<%=request.getContextPath() %>/login" method="post">
		<div>
			<input type="text" name="username" placeholder="사용자명">
		</div>
		<div>
			<input type="password" name="password" placeholder="패스워드">
		</div>
		<% if(err_msg != null) { %>
			<div>
				<span style="color: red;"><%=err_msg %></span>
			</div>
		<% } %>
		<div>
			<button type="submit">로그인</button>
		</div>
	</form>
</body>
</html>