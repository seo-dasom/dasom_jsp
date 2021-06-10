<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 요청으로부터 파라미터 정보 받기</title>
</head>
<body>
	<h1>GET/POST 요청의 파라미터 정보 받기</h1>
	<ul>
		<li><a href="/som/param/get1?name=홍길동">
			name=홍길동 으로 보내고 받기
		</a></li>
		<li>
			<c:url var="url1" value="/param/post1" />
			<form action="${url1 }" method="post">
				<input type="text" name="name">
				<button type="submit">POST 전송</button>
			</form>
		</li>
		<li><a href="/som/param/get2?name=홍길동&number=10&age=30">
			name=홍길동&number=10 으로 보내고 받기
		</a></li>
		<li>
			<c:url var="url2" value="/param/post2" />
			<form action="${url2 }" method="post">
				<input type="text" name="name" placeholder="name">
				<input type="text" name="number" placeholder="number">
				<button type="submit">전송</button>
			</form>
		</li>
	</ul>
</body>
</html>