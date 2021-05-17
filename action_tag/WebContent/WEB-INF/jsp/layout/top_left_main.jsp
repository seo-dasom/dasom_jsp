<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레이아웃 페이지</title>
</head>
<style type="text/css">
	* {
		margin: 0px; padding: 0px;
		box-sizing: border-box;
	}
	header {
		height: 64px;
		background-color: orange;
	}
	section.container {
		margin: 0px auto;
		width: 1280px; height: 720px;
	}
	aside.left {
		width: 400px;
		height: inherit;
		float: left;
		background-color: maroon;
	}
	section.right {
		margin-left: 10px;
		width: 870px;
		float: left;
		height: inherit;
		background-color: olive;
	}
</style>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/module/top_nav.jsp" flush="false">
			<jsp:param name="img" value="<%=request.getParameter(\"logo\") %>" />
		</jsp:include>
	</header>
	<section class="container">
		<aside class="left">
			<jsp:include page="/WEB-INF/jsp/module/left_menu.jsp" flush="false" />
		</aside>
		<section class="right">
			<jsp:include page="/WEB-INF/jsp/module/main.jsp" flush="false" />
		</section>
	</section>
</body>
</html>