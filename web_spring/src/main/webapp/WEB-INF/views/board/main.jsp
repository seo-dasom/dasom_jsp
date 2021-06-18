<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<div>
		<ul>
			<c:url var="all" value="/board" />
			<li><a href="${all }">전체</a></li>
			<c:forEach var="type" items="${requestScope.boardtypes }">
				<li><a href="?boardtype=${type.id }">${type.name }</a></li>
			</c:forEach>
		</ul>
	</div>
	<div>
		<div>
			<c:url var="add" value="/board/add" />
			<button type="button" onclick="location.href='${add }'">글쓰기</button>
		</div>
		<div>
			<c:url var="search" value="/board" />
			<form id="search_form" action="${search }" method="get">
				<c:if test="${not empty param.boardtype}">
					<input type="hidden" name="boardtype" value="${param.boardtype }">
				</c:if>
				<select name="searchtype">
					<option value="a">작성자</option>
					<option value="t">제목</option>
				</select>
				<input type="text" name="search">
				<button type="submit">검색</button>
			</form>
		</div>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>구분</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:url var="detail" value="/board/detail" />
				<c:forEach var="item" items="${requestScope.boardlist }" >
					<tr>
						<td>${item.getId() }</td>
						<td>${item.getBname() }</td>
						<td><a href="${detail }?id=${item.getId() }">${item.getTitle() }</a></td>
						<td>${item.getAname() }</td>
						<td>${item.getCdate() }</td>
						<td>${item.getVcnt() }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>