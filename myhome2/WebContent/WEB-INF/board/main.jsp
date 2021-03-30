<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.BoardVO, java.util.ArrayList" %>
<%@ page info="board" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<%@ include file="/WEB-INF/module/css_js.jsp" %>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/module/top_nav.jsp" %>
	</header>
	<section class="container" style="margin-top: 1rem;">
		<div class="form-group text-right">
			<a class="btn btn-sm btn-outline-dark" href="./board/write">글쓰기</a>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>번호</th>
					<th>구분</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
			<%
				ArrayList<BoardVO> datas = (ArrayList<BoardVO>)request.getAttribute("datas");
				for(BoardVO data: datas) {
			%>
				<tr>
					<td><%=data.getId() %></td>
					<td><%=data.getBtype() %></td>
					<td><%=data.getTitle() %></td>
					<td><%=data.getAuthor() %></td>
					<td><%=data.getViewCnt() %></td>
					<td><%=data.getCreateDate() %></td>
				</tr>
			<%
				}
			%>
			</tbody>
		</table>
	</section>
</body>
</html>