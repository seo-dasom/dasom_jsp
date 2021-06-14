<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${requestScope.item.getTitle() }</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<c:url var="delete" value="/ajax/board/delete" />
<c:url var="comment_add" value="/ajax/comment/add" />
<script type="text/javascript">
	function boardDelete(id) {
		$.ajax({
			url: "${delete }",
			type: "post",
			datatype: "json",
			data: {
				id: id
			},
			success: function(data) {
				if(data.res == "success") {
					alert("삭제가 완료되었습니다.")
				} else if(data.res == "auth_fail") {
					alert("권한이 없습니다.");
				} else if(data.res == "no_login") {
					alert("로그인을 먼저 진행하세요.");
				} else if(data.res == "message"){
					alert(data.message);
				} else {
					alert("삭제 실패");
				}
				if(data.redirect) {
					location.href = data.redirect;
				}
			}
		});
	}
	
	function commentSend() {
		// Ajax 를 사용하여 댓글 저장
		var id = ${item.getId() };
		var comment = document.getElementById("id_comment").value;
		if(${(empty logined) ? false : logined }) {
			$.ajax({
				url: "${comment_add }",
				type: "post",
				datatype: "json",
				data: {
					bid: id,
					comment: comment
				},
				success: function(data) {
					if(data.res == "success") {
						appendComment("${(empty account) ? '' : account.getNickname() }", data.comment);
					} else {
						alert("댓글 등록에 실패 했습니다.");
					}
				}
			});
		} else {
			alert("먼저 로그인을 해주세요.");
		}
	}
	
	function appendComment(name, comment) {
		// 페이지 전환 없이 자신이 등록한 댓글을 화면에 만들어 준다.
		var item = document.createElement("div");
		var writer = document.createElement("span");
		var content = document.createElement("span");
		var date = document.createElement("span");
		var gcnt = document.createElement("span");
		var bcnt = document.createElement("span");
		
		item.style = "border-style: solid; padding: 6px 0px;"
		writer.innerText = name;
		content.innerText = comment.contents;
		date.innerText = comment.cdate;
		gcnt.innerText = comment.gcnt;
		bcnt.innerText = comment.bcnt;
		
		item.append(writer);
		item.append(content);
		item.append(date);
		item.append(gcnt);
		item.append(bcnt);
		
		document.getElementById("comment_list").prepend(item);
		
	}
	
	function txtCounting(obj) {
		// 입력 텍스트 수 카운팅
		document.getElementById("txt_len").innerText = obj.value.length;
	}
	
	function sendRecommend(code) {
		$.ajax({
			url: "/final/ajax/board/recommend",
			type: "post",
			datatype: "json",
			data: {
				id: "${item.getId() }",
				code: code
			},
			success: function(data) {
				if(data.res == "success") {
					if(data.code == "g") {
						document.getElementById("good").innerText = data.count
					} else if(data.code == "b") {
						document.getElementById("bad").innerText = data.count
					}
				} else if(data.res == "no_login") {
					location.href = data.redirect;
				} else if(data.res == "fail") {
					alert(data.message)
				}
			}
		});
	}
	
	function comRecommend(obj, code, cid) {
		$.ajax({
			url: "/final/ajax/comment/recommend",
			type: "post",
			datatype: "json",
			data: {
				id: cid,
				code: code
			},
			success: function(data) {
				if(data.res == "success") {
					if(data.code == "g") {
						obj.innerText = "추천 : " + data.count
					} else if(data.code == "b") {
						obj.innerText = "비추천 : " + data.count
					}
				} else if(data.res == "no_login") {
					location.href = data.redirect;
				} else if(data.res == "fail") {
					alert(data.message)
				}
			}
		});
	}
	
	function delComment(obj, cid) {
		$.ajax({
			url: "/final/ajax/comment/delete",
			type: "post",
			datatype: "json",
			data: {
				id: cid
			},
			success: function(data) {
				if(data.res == "success") {
					alert("삭제되었습니다.")
					obj.parentNode.innerHTML = "<span>삭제된 댓글</span>"
				} else if(data.res == "no_login") {
					location.href = data.redirect
				} else if(data.res == "fail") {
					alert(data.message)
				}
			}
		});
	}
</script>
</head>
<body>
	<div>
		<h3>${requestScope.item.getTitle() }</h3>
	</div>
	<div>
		<small>작성일 : ${requestScope.item.getCdate() }</small><br>
		<small>수정일 : ${requestScope.item.getUdate() }</small><br>
		<small>조회수 : ${requestScope.item.getVcnt() }</small><br>
		<a style="cursor: pointer;" onclick="sendRecommend('g');">
			<small>추천 : <span id="good">${requestScope.item.getGcnt() }</span></small></a><br>
		<a style="cursor: pointer;" onclick="sendRecommend('b');">
			<small>비추천 : <span id="bad">${requestScope.item.getBcnt() }</span></small></a><br>
	</div>
	<div>
		<p>${fn:replace(requestScope.item.getContents(), newline, "<br>") }</p>
	</div>
	<div>
		<c:url var="update" value="/board/update?id=${item.getId() }" />
		<c:url var="board" value="/board" />
		<button type="button" onclick="location.href='${update }'">수정</button>
		<button type="button" onclick="boardDelete(${item.getId() });">삭제</button>
		<button type="button" onclick="location.href='${board }'">목록</button>
	</div>
	<div>
		<jsp:include page="/WEB-INF/views/board/comment.jsp" flush="false" >
			<jsp:param name="comment_list" value="${comment_list }" />
		</jsp:include>
	</div>
</body>
</html>