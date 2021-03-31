<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String logined = "";
	if(session.getAttribute("login") != null) {
		logined = (String)session.getAttribute("login");
	}
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="<%=request.getContextPath() %>">HOME</a>
	<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
	data-bs-target="#navbarText" aria-controls="navbarText"
	aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath() %>/visit">방명록</a>
			</li>
			<li class="nav-item">
				<%
					if(getServletInfo().equals("board")) {
				%>
						<a class="nav-link active" href="<%=request.getContextPath() %>/board">게시판</a>
				<%		
					} else {
				%>
						<a class="nav-link" href="<%=request.getContextPath() %>/board">게시판</a>
				<%
					}
				%>
				
			</li>
		</ul>
		<%
			if(logined.equals("true")) {
		%>
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/info">회원정보</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/logout">로그아웃</a>
					</li>
				</ul>
		<% } else { %>
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/join">회원가입</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/login">로그인</a>
					</li>
				</ul>
		<% } %>
	</div>
</nav>