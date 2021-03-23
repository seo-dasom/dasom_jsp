<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>피자주문</title>
</head>
<style type="text/css">
p {
	font-weight: bold;
}
.piz {
	color: red;
}
.topping {
	color: green;
}
.side {
	color: blue;
}
</style>
<body>
	<h2>주문 내역(JSP 사용)</h2>
	<p>피자는 <span class="piz"><%=request.getAttribute("pizza")%></span>,
	   토핑은 <span class="topping"><%=String.join(", ", (String[])request.getAttribute("topping")) %></span>,
	   사이드는 <span class="side"><%=String.join(", ", (String[])request.getAttribute("side")) %></span> 주문하셨습니다.</p>
    <p>총합 : <%=request.getAttribute("total") %> 원</p>
</body>
</html>