/**
 * 
 */
function nicknameCheck(url, value) {
	$.ajax({
		url: url,
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