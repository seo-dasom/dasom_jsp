<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 변수, 조건문, 반복문 등의 로직과 관련된 JSTL 문법 제공 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- 숫자, 날짜 형식과 관련된 포멧 제공 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 문자열과 관련된 처리 함수 제공 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Core 사용법</title>
</head>
<body>
	<h1>변수 설정</h1>
	<c:set var="path" value="${pageContext.request.contextPath }" />
	<h3>path -> ${path }</h3>
	
	<c:set var="number1" value="110" />
	<h3>number1 -> ${number1 }</h3>
	
	<c:set var="number2" value="100" />
	<h3>number2 + 10 -> ${number2+10 }</h3>
	
	<h1>변수 생성 할 때 스코프 지정</h1>
	<c:set var="p_var" value="page_scope" scope="page" />
	<h3>p_var -> ${p_var }</h3>
	
	<c:set var="p_req" value="request_scope" scope="request" />
	<h3>p_req -> ${p_req }</h3>
	<h3>p_req -> ${requestScope.p_req }</h3>
	
	<c:set var="p_ses" value="session_scope" scope="session" />
	<h3>p_ses -> ${p_ses }</h3>
	<h3>p_ses -> ${sessionScope.p_ses }</h3>
	
	<h1>배열 생성</h1>
	<c:set var="arr">
		홍길동, 김가람, 박은아, 황태현
	</c:set>
	<h3>arr -> ${arr }</h3>
	
	<h1>제어문 if</h1>
	<%-- <c:if test="조건식" var="변수명"> --%>
	<c:if test="${number1 >= 100 }">
		number1 은 100 보다 크거나 같습니다.
	</c:if>
	<c:if test="${number1 < 100 }">
		number1 은 100 보다 작습니다.
	</c:if>
	
	<h1>제어문 if ~ else</h1>
	<c:choose>
		<c:when test="${number1 >= 100 }">
			number1 은 100 보다 크거나 같습니다.
		</c:when>
		<c:otherwise>
			number1 은 100 보다 작습니다.
		</c:otherwise>
	</c:choose>
	
	<h1>제어문 if ~ else if ~ else</h1>
	<c:choose>
		<c:when test="${number1 lt 100 }">
			number1 은 100 보다 작습니다.
		</c:when>
		<c:when test="${number1 eq 100 }">
			number1 은 100 입니다.
		</c:when>
		<c:otherwise>
			number1 은 100 보다 큽니다.
		</c:otherwise>
	</c:choose>
	
	<h1>반복문 for</h1>
	<ul>
		<c:forEach var="i" begin="0" end="9">
			<li>${i }</li>
		</c:forEach>
	</ul>
	<hr>
	<ul>
		<c:forEach var="i" begin="0" end="9" step="2">
			<li>${i }</li>
		</c:forEach>
	</ul>
	<hr>
	<ul>
		<c:forEach var="i" items="${arr }">
			<li>${i }</li>
		</c:forEach>
	</ul>
	<hr>
	<ul>
		<c:forEach var="i" items="${arr }" varStatus="loop">
			<li>${loop.index } - ${loop.count } - ${i }</li>
		</c:forEach>
	</ul>
	<hr>
	<ul>
		<c:forEach var="i" items="${arr }" varStatus="loop">
			<li>${loop.first } - ${loop.last } - ${i }</li>
		</c:forEach>
	</ul>
	<hr>
	<table border="1" style="border-collapse: collapse;">
		<c:forEach var="i" begin="1" end="9">
			<tr>
				<c:forEach var="j" begin="1" end="9">
					<td>${i } x ${j } = ${i * j }</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<ul>
		<c:forTokens var="i" items="홍길동 김갑수 박정연 강기열" delims=" ">
			<li>${i }</li>
		</c:forTokens>
	</ul>
	<ul>
		<c:set var="phone" value="010-1234-5678" />
		<c:forTokens var="i" items="${phone }" delims="-">
			<li>${i }</li>
		</c:forTokens>
	</ul>
	
	<h1>URL 주소 생성</h1>
	<c:url var="url1" value="/jstl/core">
		<c:param name="x" value="10" />
		<c:param name="y" value="20" />
	</c:url>
	<h4><a href="${url1 }">10 + 20 = ${param.x + param.y }</a></h4>
	
</body>
</html>