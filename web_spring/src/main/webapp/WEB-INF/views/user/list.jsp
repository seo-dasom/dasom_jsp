<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모든 회원정보</title>
</head>
<c:url var="list" value="/user/list" />
<body>
	<form action="${list }" method="get">
		<table>
			<tr>
				<th>ID</th>
				<th>NICKNAME</th>
				<th>EMAIL</th>
			</tr>
			<tr>
				<td>${id }</td>
				<td>${nickname }</td>
				<td>${email }</td>
			</tr>
		</table>
	</form>
</body>
</html>