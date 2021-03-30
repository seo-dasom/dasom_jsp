<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<%@ include file="/WEB-INF/module/css_js.jsp" %>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/module/top_nav.jsp" %>
	</header>
	<h1>환영합니다.</h1>
	<ul>
        <li><a href="./gugudan">구구단</a></li>
        <li><a href="./random">랜덤</a></li>
        <li><a href="./join">회원가입</a></li>
        <li><a href="./member?name=admin">멤버 DB 연결</a></li>
        <li><a href="./visit">방명록</a></li>
        <li><a href="./board">게시판</a></li>
	</ul>
</body>
</html>