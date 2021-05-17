<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bean 사용예제</title>
</head>
<body>
	<jsp:useBean id="data" class="action.tag.SampleVO" />
	<jsp:setProperty name="data" property="*" />
	<form action="<%=request.getContextPath() %>/bean" method="post">
		<div>
			<label>이름 :</label>
			<input type="text" name="name" value=<jsp:getProperty property="name" name="data" />>
		</div>
		<div>
			<label>나이 :</label>
			<input type="number" name="age" value=<jsp:getProperty property="age" name="data" />>
		</div>
		<div>
			<label>성별 :</label>
			<input type="text" name="gender" value=<jsp:getProperty property="gender" name="data" />>
		</div>
		<div>
			<label>이메일 :</label>
			<input type="email" name="email" value=<jsp:getProperty property="email" name="data" />>
		</div>
		<div>
			<button type="submit">전송</button>
		</div>
	</form>
</body>
</html>