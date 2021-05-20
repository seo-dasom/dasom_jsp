<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 포멧 사용법</title>
</head>
<body>
	<h1>숫자 표현 포멧</h1>
	<c:set var="num1" value="1234.567" />
	<c:set var="num2" value="0.5" />
	<h4><fmt:formatNumber value="${num1 }" type="number" /></h4>
	<h4><fmt:formatNumber value="${num1 }" type="number" groupingUsed="false" /></h4>
	<hr>
	<h4><fmt:formatNumber value="${num1 }" type="currency" /></h4>
	<h4><fmt:formatNumber value="${num2 }" type="percent" /></h4>
	<hr>
	<h4><fmt:formatNumber value="${num1 }" type="currency" maxFractionDigits="2" /></h4>
	<h4><fmt:formatNumber value="${num1 }" type="currency" maxFractionDigits="4" /></h4>
	<h4><fmt:formatNumber value="${num1 }" type="currency" maxIntegerDigits="2" maxFractionDigits="2" /></h4>
	<h4><fmt:formatNumber value="${num1 }" type="currency" maxFractionDigits="4" pattern="0000.0000" /></h4>
	<hr>
	<fmt:setLocale value="en_US" />
	<h4><fmt:formatNumber value="${num1 }" type="currency" /></h4>
	<fmt:setLocale value="ja_JP" />
	<h4><fmt:formatNumber value="${num1 }" type="currency" /></h4>
	<fmt:setLocale value="ko_KR" />
	<h4><fmt:formatNumber value="${num1 }" type="currency" /></h4>
	
	<h1>날짜 관련 포멧</h1>
	<c:set var="date1" value="<%=new java.util.Date() %>" />
	<h4><fmt:formatDate type="date" value="${date1 }" /></h4>
	<h4><fmt:formatDate type="time" value="${date1 }" /></h4>
	<h4><fmt:formatDate type="both" value="${date1 }" /></h4>
	<hr>
	<h4><fmt:formatDate type="both" value="${date1 }" dateStyle="short" /></h4>
	<h4><fmt:formatDate type="both" value="${date1 }" timeStyle="short" /></h4>
	<h4><fmt:formatDate type="both" value="${date1 }" dateStyle="long" timeStyle="long" /></h4>
	<hr>
	<fmt:setLocale value="en_US" />
	<h4><fmt:formatDate type="both" value="${date1 }" /></h4>
	<fmt:setLocale value="ja_JP" />
	<h4><fmt:formatDate type="both" value="${date1 }" /></h4>
	<fmt:setLocale value="ko_KR" />
	<h4><fmt:formatDate type="both" value="${date1 }" /></h4>
	<hr>
	<h4><fmt:formatDate pattern="yyyy-MM-dd" value="${date1 }" /></h4>
	<h4><fmt:formatDate pattern="HH:mm:ss .SSS" value="${date1 }" /></h4>
	<h4><fmt:formatDate pattern="a hh:mm:ss" value="${date1 }" /></h4>
	<h4><fmt:formatDate pattern="E" value="${date1 }" />요일</h4>
	<hr>
	<c:set var="date2" value="2021-05-20" />
	<fmt:parseDate value="${date2 }" var="pdate2" pattern="yyyy-MM-dd" />
	<h4><fmt:formatDate type="both" value="${pdate2 }" /></h4>
</body>
</html>