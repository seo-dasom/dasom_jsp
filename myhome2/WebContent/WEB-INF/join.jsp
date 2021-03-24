<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="./join" method="post">
		<div>
			<label for="id_userid">아이디</label>
			<input type="text" name="userid" id="id_userid" required>
		</div>
		<div>
			<label for="id_pass1">패스워드</label>
			<input type="password" name="pass1" id="id_pass1" required>
		</div>
		<div>
			<label for="id_pass2">패스워드 확인</label>
			<input type="password" name="pass2" id="id_pass2" required>
		</div>
		<div>
			<label for="id_email">이메일</label>
			<input type="email" name="email" id="id_email" required>
		</div>
		<div>
			<button type="submit">가입</button>
		</div>
	</form>
</body>
</html>