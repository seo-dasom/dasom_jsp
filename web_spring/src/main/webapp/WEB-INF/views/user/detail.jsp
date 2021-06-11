<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세정보</title>
</head>
<c:url var="detail" value="/user/detail" />
<body>
	<form action="${detail }" method="get">
		<table>
			<tr>
				<th>ID</th>
				<th>USERNAME</th>
				<th>NICKNAME</th>
				<th>EMAIL</th>
				<th>PASSWORD</th>
				<th>GENDER</th>
				<th>AGE</th>
				<th>JOINDATE</th>
				<th>LOGINDATE</th>
				<th>EXPIREDATE</th>
			</tr>
			<tr>
				<td>${id }</td>
				<td>${username }</td>
				<td>${nickname }</td>
				<td>${email }</td>
				<td>${password }</td>
				<td>${gender }</td>
				<td>${age }</td>
				<td>${joindate }</td>
				<td>${logindate }</td>
				<td>${expiredate }</td>
			</tr>
		</table>
	</form>
</body>
</html>