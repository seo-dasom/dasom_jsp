<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<%@ include file="/WEB-INF/module/css_js.jsp" %>
</head>
<body>
	<h1>로그인</h1>
	<form action="./login" method="post">
		<div>
			<label for="id_username">아이디</label>
			<input type="text" name="username" id="id_username" value="<%=(String)request.getAttribute("username") %>" required>
		</div>
		<div>
			<label for="id_password">패스워드</label>
			<input type="password" name="password" id="id_password" required>
		</div>
		<div>
			<%
				if(((String)request.getAttribute("username")).equals("")) {
			%>
					<input type="checkbox" name="remember" id="id_remember">
			<%
				} else {
			%>
					<input type="checkbox" name="remember" id="id_remember" checked>
			<%
				}
			%>
			<label for="id_remember">기억하기</label>
		</div>
		<div>
			<button type="submit">로그인</button>
		</div>
	</form>
</body>
</html>