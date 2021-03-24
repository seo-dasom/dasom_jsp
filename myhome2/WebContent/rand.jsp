<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>랜덤 범위 설정</title>
</head>
<body>
	<h1>랜덤 범위 설정</h1>
	<form action="./random/res" method="post">
		<input type="number" name="min" value="0" placeholder="최소값" required> ~
		<input type="number" name="max" value="9" placeholder="최대값" required>
		<button type="submit">생성</button>
	</form>
</body>
</html>