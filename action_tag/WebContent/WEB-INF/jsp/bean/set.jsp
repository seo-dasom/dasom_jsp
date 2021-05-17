<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bean 으로 객체 생성</title>
</head>
<body>
	<%--
		SampleVO data = new SampleVO();
		data.setName((String)request.getParameter("name");
		data.setAge(Integer.parseInt(request.getParameter("age")));
		data.setGender((String)request.getParameter("gender");
		data.setEmail((String)request.getParameter("email");
	--%>
	<jsp:useBean id="data" class="action.tag.SampleVO" />
	<jsp:setProperty name="data" property="*" />
	
	<h1>사용자 입력 정보를 잘 전달 받았습니다.</h1>
	<p><jsp:getProperty property="name" name="data" /></p>
	<p><jsp:getProperty property="age" name="data" /></p>
	<p><jsp:getProperty property="gender" name="data" /></p>
	<p><jsp:getProperty property="email" name="data" /></p>
</body>
</html>