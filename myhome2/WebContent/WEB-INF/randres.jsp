<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>랜덤값 확인</title>
</head>
<body>
	<h1>랜덤값 생성 확인</h1>
	<ul>
		<% 
			ArrayList<Integer> res = (ArrayList)request.getAttribute("rand");
			for(Integer i: res) {
		%>
			<li><%=i %></li>
		<% } %>
	</ul>
</body>
</html>