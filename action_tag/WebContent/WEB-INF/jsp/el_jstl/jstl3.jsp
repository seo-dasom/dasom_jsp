<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL fn 사용법</title>
</head>
<body>
	<h1>문자열 처리와 관련된 기능</h1>
	<c:set var="str1" value="Hello JSTL functions" />
	<h4>${fn:contains(str1, 'JSTL') }</h4>
	<h4>${fn:contains(str1, 'jstl') }</h4>
	<c:if test="${fn:contains(str1, 'JSTL') }">
		<h4>"JSTL" 문자열을 발견했습니다.</h4>
	</c:if>
	<c:if test="${fn:containsIgnoreCase(str1, 'jstl') }" >
		<h4>"jstl" 문자열을 발견했습니다.(대소문자 구분 안함)</h4>
	</c:if>
	<hr>
	<c:set var="str2" value="<h2>Hello JSTL functions</h2>" />
	<div>${str2 }</div>
	<div>${fn:escapeXml(str2) }</div>
	<hr>
	<h4>${str1 }</h4>
	<h4>${fn:replace(str1, 'functions', '함수') }</h4>
	<hr>
	<h4>${fn:split(str1, ' ') }</h4>
	<c:forEach items="${fn:split(str1, ' ') }" var="s" >
		<h4>${s }</h4>
	</c:forEach>
	<hr>
	<c:set var="str3" value="${fn:split(str1, ' ') }" />
	<h4>${fn:join(str3, '_') }</h4>
	
</body>
</html>