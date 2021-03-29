<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.BoardVO, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
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
		<form action="./write" method="post">
			<div class="form-group">
				<label for="id-title">제목</label>
				<input class="form-control" type="text" id="id_title" name="title" maxlength="200" required>
			</div>
			<div class="form-group">
				<label for="id_btype">구분</label>
				<select class="form-control" id="id_btype" name="btype">
					<option value="notice">공지사항</option>
					<option value="free" selected>자유게시판</option>
					<option value="humor">유머게시판</option>
				</select>
			</div>
			<div class="form-group">
				<label for="id_author">작성자</label>
				<input class="form-control" type="text" id="id_author" name="author" maxlength="20" required>
			</div>
			<div class="form-group">
				<label for="id_context">내용</label>
				<textarea class="form-control" id="id_context" name="context" rows="20" required></textarea>
			</div>
			<div class="form-group text-right">
				<button class="btn btn-sm btn-outline-primary" type="submit">글쓰기</button>
			</div>
		</form>
	</section>
</body>
</html>