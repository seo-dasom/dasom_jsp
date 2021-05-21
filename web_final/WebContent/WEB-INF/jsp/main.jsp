<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트의 메인 화면</title>
</head>
<c:url var="board" value="/board" />
<c:url var="join" value="/account/join" />
<body>
	<h1>프로젝트의 메인 화면 입니다.</h1>
	<ul>
		<li><a href="${board }">게시판으로 이동</a></li>
		<li><a href="${join }">회원 가입</a></li>
	</ul>
</body>
</html>