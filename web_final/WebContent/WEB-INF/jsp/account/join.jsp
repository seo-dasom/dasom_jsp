<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<c:url var="email_check" value="/ajax/checker/email" />
<c:url var="nickname_check" value="/ajax/checker/nickname" />
<script type="text/javascript">
	function emailCheck() {
		var email = document.getElementById("id_email").value;
		if(email == "" || email == undefined) {
			alert("이메일 주소를 입력하세요.");
			document.getElementById("id_email").focus();
			return;
		}
		$.ajax({
			url: "${email_check }",
			type: "get",
			datatype: "json",
			data: {
				email: document.getElementById("id_email").value
			},
			success: function(data) {
				if(data.result == false) {
					document.getElementById("email_check_res").innerText = "사용 가능!";
				} else {
					document.getElementById("email_check_res").innerText = "이미 사용중인 이메일 주소";
				}
			}
		});
	}
	
	function nicknameCheck(value) {
		$.ajax({
			url: "${nickname_check }",
			type: "get",
			datatype: "json",
			data: {
				nickname: value
			},
			success: function(data) {
				if(data.result == false) {
					document.getElementById("nickname_check_res").innerText = "사용 가능!";
				} else {
					document.getElementById("nickname_check_res").innerText = "이미 사용중인 닉네임";
				}
			}
		});
	}
	
	function send() {
		var email_check = document.getElementById("email_check_res").innerText;
		var nickname_check = document.getElementById("nickname_check_res").innerText;
		
		if(email_check == "" || email_check == undefined) {
			alert("이메일 중복확인을 먼저 진행해주세요.")
			document.getElementById("id_email").focus();
			return;
		} else if(email_check != "사용 가능!") {
			alert("해당 이메일 주소로는 가입을 할 수 없습니다.");
			document.getElementById("id_email").focus();
			return;
		}
		
		if(nickname_check == "" || nickname_check == undefined) {
			alert("닉네임을 입력하세요.")
			document.getElementById("id_nickname").focus();
			return;
		} else if(nickname_check != "사용 가능!") {
			alert("해당 닉네임으로는 가입을 할 수 없습니다.");
			document.getElementById("id_nickname").focus();
			return;
		}
		
		document.account_form.submit();
	}
</script>
</head>
<c:url var="join" value="/account/join" />
<body>
	<form name="account_form" action="${join }" method="post">
		<div>
			<label for="id_username">* 이름</label>
			<input id="id_username" type="text" name="username" required>
		</div>
		<div>
			<label for="id_nickname">* 닉네임</label>
			<input id="id_nickname" type="text" name="nickname" oninput="nicknameCheck(this.value);" required>
			<label id="nickname_check_res"></label>
		</div>
		<div>
			<label for="id_email">* 이메일</label>
			<input id="id_email" type="email" name="email" required>
			<button type="button" onclick="emailCheck();">중복확인</button>
			<label id="email_check_res"></label>
		</div>
		<div>
			<label for="id_password">* 패스워드</label>
			<input id="id_password" type="password" name="password" required>
		</div>
		<div>
			<label>* 성별</label>
			<input id="id_gender_m" type="radio" name="gender" checked><label for="id_gender_m"> 남</label>
			<input id="id_gender_w" type="radio" name="gender"><label for="id_gender_w"> 여</label>
		</div>
		<div>
			<label for="id_age">* 나이</label>
			<input id="id_age" type="number" name="age" min="1" max="199" step="1" required>
		</div>
		<div>
			<button type="button" onclick="send();">가입</button>
			<button type="button" onclick="history.back();">취소</button>
		</div>
	</form>
</body>
</html>