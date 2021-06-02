<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/common.js"></script>
</head>
<c:url var="expire" value="/ajax/account/expire" />
<script type="text/javascript">
	function updateInfo() {
		var nickCheck = document.getElementById("nickname_check_res").innerText;
		var nickname = document.getElementById("id_nickname").value;
		
		if(nickname == "") {
			alert("닉네임을 입력하세요.");
			return;
		}

		if(nickname != "${account.getNickname() }" && nickCheck == "사용 가능!") {
			// 닉네임을 변경하는 경우
			document.update_account.submit();
		} else if(nickname == "${account.getNickname() }") {
			// 닉네임을 변경하지 않고 성별 및 나이만 변경하는 경우
			document.update_account.submit();
		} else {
			alert("닉네임 중복 체크 필수");
		}
	}
	
	function expire() {
		$.ajax({
			url: "${expire }",
			type: "post",
			datatype: "json",
			data: {
				id: document.getElementById("id").value
			},
			success: function(data) {
				if(data.res == "success") {
					alert("탈퇴 처리가 완료되었습니다.")
					location.href = "${pageContext.request.contextPath }/account/logout"
				} else {
					alert("탈퇴 처리에 실패하였습니다.")
				}
			}
		});
	}
</script>
<c:url var="nickname_check" value="/ajax/checker/nickname" />
<c:url var="update" value="/account/info" />
<body>
	<form name="update_account" action="${update }" method="post">
		<input type="hidden" id="id" name="id" value="${requestScope.account.getId() }">
		<div>
			<label for="id_nickname">닉네임</label>
			<input id="id_nickname" type="text" name="nickname" value="${requestScope.account.getNickname() }">
			<button type="button" onclick="nicknameCheck('${nickname_check }', document.getElementById('id_nickname').value);">중복확인</button>
			<label id="nickname_check_res"></label>
		</div>
		<div>
			<label for="id_username">이름</label>
			<input id="id_username" type="text" name="username" value="${requestScope.account.getUsername() }" disabled>
		</div>
		<div>
			<label for="id_email">이메일</label>
			<input id="id_email" type="text" name="email" value="${requestScope.account.getEmail() }" disabled>
		</div>
		<div>
			<label>성별</label>
			<input id="id_gender_m" type="radio" name="gender" value="m" ${requestScope.account.getGender() == "m" ? "checked" : "" }><label for="id_gender_m"> 남</label>
			<input id="id_gender_w" type="radio" name="gender" value="w" ${requestScope.account.getGender() == "w" ? "checked" : "" }><label for="id_gender_w"> 여</label>
		</div>
		<div>
			<label for="id_age">나이</label>
			<input id="id_age" type="number" name="age" min="1" max="199" value="${requestScope.account.getAge() }">
		</div>
		<div>
			<button type="button" onclick="updateInfo();">수정</button>
			<button type="button" onclick="expire();">탈퇴</button>
			<button type="button" onclick="history.back();">취소</button>
		</div>
	</form>
</body>
</html>