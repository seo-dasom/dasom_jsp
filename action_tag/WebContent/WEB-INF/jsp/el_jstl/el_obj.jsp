<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="action.tag.SampleVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 내장 객체 사용법</title>
</head>
<body>
	<% pageContext.setAttribute("name", "page value"); %>
	<h1>request 내장 객체</h1>
	<ul>
		<li><%=request.getAttribute("name") %></li>
		<li>${requestScope.name }</li>
	</ul>
	
	<h1>session 내장 객체</h1>
	<ul>
		<li><%=session.getAttribute("name") %></li>
		<li>${sessionScope.name }</li>
	</ul>
	
	<h1>param 내장 객체</h1>
	<ul>
		<li><%=request.getParameter("name") %></li>
		<li>${param.name }</li>
	</ul>
	
	<h1>객체명 생략</h1>
	<h2>pageScope -> requestScope -> sessionScope 순서로 탐색</h2>
	<ul>
		<li>${name }</li>
	</ul>
	
	<h1>ContextPath 가져오기</h1>
	<ul>
		<li><%=request.getContextPath() %></li>
		<li>${pageContext.request.contextPath }</li>
	</ul>
	
	<h1>ArrayList 사용</h1>
	<jsp:useBean id="data" class="java.util.ArrayList" />
	<%
		SampleVO s1 = new SampleVO("홍길동", 30, "남", "sample@gamil.com");
		SampleVO s2 = new SampleVO("김길동", 33, "남", "example@gamil.com");
		data.add(s1);
		data.add(s2);
	%>
	<ul>
		<li><%=((SampleVO)data.get(0)).getName() %></li>
		<li>${data[0].name }</li>
		<li><%=((SampleVO)data.get(1)).getEmail() %></li>
		<li>${data[1].email }</li>
	</ul>
</body>
</html>