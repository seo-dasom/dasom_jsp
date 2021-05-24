<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트의 메인 화면</title>
</head>
<body>
	<h1>프로젝트의 메인 화면 입니다.</h1>
	<ul>
		<c:url var="board" value="/board" />
		<li><a href="${board }">게시판으로 이동</a></li>
		<c:choose>
			<c:when test="${sessionScope.logined }">
				<c:url var="logout" value="/account/logout" />
				<li><a href="${logout }">로그아웃</a>
				<c:url var="info" value="/account/info" />
				<li><a href="${info }">회원 정보</a></li>
			</c:when>
			<c:otherwise>
				<c:url var="login" value="/account/login" />
				<li><a href="${login }">로그인</a>
				<c:url var="join" value="/account/join" />
				<li><a href="${join }">회원 가입</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</body>
</html>