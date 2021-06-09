<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 요청으로부터 파라미터 정보 받기</title>
</head>
<body>
	<h1>다음의 파라미터 정보를 받았습니다.</h1>
	<h2>mv.addObject로 셋팅한 값 -> ${value }</h2>
	<h2>EL의 param 객체를 사용 -> ${param.name }</h2>
	<div>
		<button type="button" onclick="history.back();">뒤로가기</button>
	</div>
</body>
</html>