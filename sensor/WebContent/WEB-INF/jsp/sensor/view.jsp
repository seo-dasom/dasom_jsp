<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, servlet.sensor.data.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>온/습도 측정 테이블</title>
</head>
<body>
<% ArrayList<TempHumVO> datas = (ArrayList<TempHumVO>)request.getAttribute("datas");
   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<div>
	<% Cookie[] c = request.getCookies();
	   int list_cnt = 10;
	   if(c != null) {
		   for(Cookie v: c) {
			   if(v.getName().equals("list_cnt")) {
				   list_cnt = Integer.parseInt(v.getValue());
			   }
		   }
	   }
	   if(request.getParameter("list_cnt") != null) {
		   list_cnt = Integer.parseInt(request.getParameter("list_cnt"));
	   }
	%>
	<form action="<%=request.getContextPath()%>/temphum/view" method="get">
		<select name="list_cnt" onchange="submit();">
			<option value="10" <%=list_cnt == 10 ? "selected" : "" %>>10개</option>
			<option value="15" <%=list_cnt == 15 ? "selected" : "" %>>15개</option>
			<option value="20" <%=list_cnt == 20 ? "selected" : "" %>>20개</option>
			<option value="25" <%=list_cnt == 25 ? "selected" : "" %>>25개</option>
			<option value="30" <%=list_cnt == 30 ? "selected" : "" %>>30개</option>
		</select>
	</form>
</div>
<table border="1">
	<tr>
		<th>번호</th>
		<th>온도</th>
		<th>습도</th>
		<th>시간</th>
	</tr>
	<% for(TempHumVO data: datas) { %>
		<tr>
			<td><%=data.getId() %></td>
			<td><%=data.getTemp() %></td>
			<td><%=data.getHum() %></td>
			<td><%=df.format(data.getSdate()) %></td>
		</tr>
	<% } %>
</table>

<div>
	<% int st_page = (int)request.getAttribute("st_page_num");
	   int ed_page = (int)request.getAttribute("ed_page_num");
	   int page_num = (int)request.getAttribute("page");
	%>
	<% for(int p = st_page; p <= ed_page; p++) { %>
		<% if(p == page_num) { %>
			<a style="color: red;">[<%=p %>]</a>
		<% } else { %>
			<a href="<%=request.getContextPath() %>/temphum/view?page=<%=p %>">[<%=p %>]</a>
		<% } %>
	<% } %>
</div>

</body>
</html>