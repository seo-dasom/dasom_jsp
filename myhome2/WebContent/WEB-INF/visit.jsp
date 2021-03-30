<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,
				 visit.VisitVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<%@ include file="/WEB-INF/module/css_js.jsp" %>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/module/top_nav.jsp" %>
	</header>
	<h1>방명록</h1>
	<form action="./visit" method="post">
		<input type="text" name="author" placeholder="작성자" required>
		<textarea name="context" required></textarea>
		<button type="submit">작성</button>
	</form>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th></th>
		</tr>
		<% ArrayList<VisitVO> datas = (ArrayList<VisitVO>)request.getAttribute("datas");
		   for(VisitVO data: datas) { %>
			   <tr>
			       <td><%=data.getId() %></td>
			       <td><a href="./visit/update?id=<%=data.getId() %>"><%=data.getContext() %></a></td>
			       <td><%=data.getAuthor() %></td>
			       <td><%=data.getCreateDate() %></td>
			       <td><form action="./visit/delete" method="post">
			           <input type="hidden" name="id" value="<%=data.getId() %>" readonly>
			           <button type="submit">삭제</button>
			       </form></td>
			   </tr>
		<% } %>
	</table>
</body>
</html>