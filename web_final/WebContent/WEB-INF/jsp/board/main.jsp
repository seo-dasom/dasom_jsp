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
			<c:forEach var="type" items="${requestScope.boardtypes }">
				<li><a href="?type=${type.id }">${type.name }</a></li>
			</c:forEach>
		</ul>
	</div>
	<div>
		<div>
			<c:url var="add" value="/board/add" />
			<button type="button" onclick="location.href='${add }'">글쓰기</button>
		</div>
		<table border="1">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td><a href="">제목</a></td>
					<td>user</td>
					<td>2021-01-01</td>
					<td>0</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>