<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<c:url var="login" value="/account/login" />
	<form action="${login }" method="post">
		<div>
			<label>이메일</label>
			<input id="id_email" type="text" name="email" required>
		</div>
		<div>
			<label>패스워드</label>
			<input id="id_password" type="password" name="password" required>
		</div>
		<div>
			<label style="color: red;">${error }</label>
		</div>
		<div>
			<button type="submit">로그인</button>
			<c:url var="main" value="/" />
			<button type="button" onclick="location.href='${main }'">취소</button>
		</div>
	</form>
</body>
</html>