<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<c:url var="update" value="/board/update" />
	<form action="${update }"	method="post">
		<input type="hidden" name="id" value="${item.getId() }" readonly>
		<div>
			<label for="id_title">제목</label>
			<input id="id_title" type="text" name="title" value="${item.getTitle() }" required>
		</div>
		<div>
			<label for="id_btype">게시판 구분</label>
			<select id="id_btype" name="btype">
				<c:forEach var="data" items="${requestScope.boardtypes }" >
					<option value="${data.id }" ${data.id == item.getBtype() ? "selected" : "" }>${data.name }</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for="id_contents">내용</label>
			<textarea id="id_contents" name="contents" cols="80" rows="20">${item.getContents() }</textarea>
		</div>
		<div>
			<label for="id_nodel">삭제금지</label>
			<input id="id_nodel" type="checkbox" name="nodel" ${item.getNodel() == "y" ? "checked" : "" }>
		</div>
		<div>
			<button type="submit">저장</button>
			<button type="button" onclick="history.back();">취소</button>
		</div>
	</form>
</body>
</html>