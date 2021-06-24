<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 화면</title>
</head>
<script type="text/javascript">
	function send(form_id) {
		var f = document.getElementById(form_id);
		f.submit();
	}
</script>
<body>
	<h1>결제 화면</h1>
	<div>
		상품명 : 테스트상품<br>
		수량 : 1 개<br>
		금액 : 10,000 원
	</div>
	<div>
		<c:url var="pay_img" value="/resources" />
		<c:url var="pay_url" value="/pay/payment" />
		<a onclick="send('pay_form')"><img src="${pay_img }/img/kakaopay/payment_icon_yellow_medium.png"></a>
		<form id="pay_form" action="${pay_url }" method="post">
			<input type="hidden" name="orderid" value="123456789">
			<input type="hidden" name="pname" value="테스트상품">
			<input type="hidden" name="qty" value="1">
			<input type="hidden" name="amount" value="10000">
		</form>
	</div>
</body>
</html>