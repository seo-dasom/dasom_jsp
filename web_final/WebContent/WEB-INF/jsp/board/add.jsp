<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 추가</title>
</head>
<body>
	<c:url var="add" value="/board/add" />
	<form action="${add }"	method="post">
		<div>
			<label for="id_title">제목</label>
			<input id="id_title" type="text" name="title" required>
		</div>
		<div>
			<label for="id_btype">게시판 구분</label>
			<select id="id_btype" name="btype">
				<c:forEach var="data" items="${requestScope.boardtypes }" >
					<option value="${data.id }">${data.name }</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for="id_contents">내용</label>
			<textarea id="id_contents" name="contents" cols="80" rows="20"></textarea>
		</div>
		<div>
			<label for="id_nodel">삭제금지</label>
			<input id="id_nodel" type="checkbox" name="nodel">
		</div>
		<div>
			<button type="submit">저장</button>
			<button type="button" onclick="history.back();">취소</button>
		</div>
	</form>
</body>
</html>