<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${requestScope.item.getTitle() }</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<c:url var="comment_add" value="/ajax/comment/add" />
<script type="text/javascript">
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
</script>
</head>
<body>
	<div>
		<h4>${requestScope.item.getTitle() }</h4>
	</div>
	<div>
		<small>작성일 : ${requestScope.item.getCdate() }</small>
		<small>수정일 : ${requestScope.item.getUdate() }</small>
		<small>조회수 : ${requestScope.item.getVcnt() }</small>
		<small>추천 : ${requestScope.item.getGcnt() }</small>
		<small>비추천 : ${requestScope.item.getBcnt() }</small>
	</div>
	<div>
		<p>${requestScope.item.getContents() }</p>
	</div>
	<div>
		<jsp:include page="/WEB-INF/jsp/board/comment.jsp" flush="false" >
			<jsp:param name="comment_list" value="${comment_list }" />
		</jsp:include>
	</div>
</body>
</html>