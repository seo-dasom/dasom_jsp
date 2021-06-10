<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<c:url var="join" value="/account/join" />
<body>
	<form action="${join }" method="post">
		<div>
			<label for="id_username">* 이름</label>
			<input id="id_username" type="text" name="username" required>
		</div>
		<div>
			<label for="id_nickname">* 닉네임</label>
			<input id="id_nickname" type="text" name="nickname" required>
			<label id="nickname_check_res"></label>
		</div>
		<div>
			<label for="id_email">* 이메일</label>
			<input id="id_email" type="email" name="email" required>
			<button type="button" onclick="alert('미구현');">중복확인</button>
			<label id="email_check_res"></label>
		</div>
		<div>
			<label for="id_password">* 패스워드</label>
			<input id="id_password" type="password" name="password" required>
		</div>
		<div>
			<label>* 성별</label>
			<input id="id_gender_m" type="radio" name="gender" value="m" checked><label for="id_gender_m"> 남</label>
			<input id="id_gender_w" type="radio" name="gender" value="w"><label for="id_gender_w"> 여</label>
		</div>
		<div>
			<label for="id_age">* 나이</label>
			<input id="id_age" type="number" name="age" min="1" max="199" step="1" required>
		</div>
		<div>
			<button type="submit">가입</button>
			<button type="button" onclick="history.back();">취소</button>
		</div>
	</form>
</body>
</html>