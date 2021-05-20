<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL xml 사용법</title>
</head>
<body>
	<h1>XML 형식의 데이터 사용</h1>
	<c:set var="xmldata">
		<infos>
			<info>
				<name>홍길동</name>
				<age>30</age>
				<gender>남</gender>
			</info>
			<info>
				<name>김지한</name>
				<age>32</age>
				<gender>남</gender>
			</info>
			<info>
				<name>박연우</name>
				<age>31</age>
				<gender>여</gender>
			</info>
		</infos>
	</c:set>
	<x:parse xml="${xmldata }" var="xdata" />
	<h4><x:out select="$xdata/infos/info[1]/name" /></h4>
	<h4><x:out select="$xdata/infos/info[2]/name" /></h4>
	<h4><x:out select="$xdata/infos/info[3]/name" /></h4>
	<hr>
	<c:import url="xml/data.xml" var="xmlfile" charEncoding="UTF-8" />
	<x:parse xml="${xmlfile }" var="xfile" />
	<h4><x:out select="$xfile/infos/info[1]/age" /></h4>
	<h4><x:out select="$xfile/infos/info[2]/age" /></h4>
	<h4><x:out select="$xfile/infos/info[3]/age" /></h4>
	<hr>
	<h2>if</h2>
	<x:if select="$xfile/infos/info[1]/age>=30">
		<h4><x:out select="$xfile/infos/info[1]/name" />님은 30대 입니다.</h4>
	</x:if>
	<hr>
	<h2>choose</h2>
	<x:choose>
		<x:when select="$xfile/infos/info[2]/gender = 'm'">
			<h4><x:out select="$xfile/infos/info[2]/name" />님은 남자입니다.</h4>
		</x:when>
		<x:when select="$xfile/infos/info[2]/gender = 'w'">
			<h4><x:out select="$xfile/infos/info[2]/name" />님은 여자입니다.</h4>
		</x:when>
	</x:choose>
	<hr>
	<h2>forEach</h2>
	<ul>
		<x:forEach var="info" select="$xfile/infos/info">
			<li><x:out select="name" /> | <x:out select="age" /> | <x:out select="gender" /></li>
		</x:forEach>
	</ul>
	
</body>
</html>