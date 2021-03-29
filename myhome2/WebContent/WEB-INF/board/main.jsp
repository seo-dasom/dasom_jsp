<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.BoardVO, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/static/bootstrap-4.6.0/css/bootstrap.min.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap-4.6.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="<%=request.getContextPath() %>">HOME</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
					aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/visit">방명록</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/board">게시판</a>
					</li>
				</ul>
			</div>
		</nav>
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