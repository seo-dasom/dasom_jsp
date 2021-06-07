<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form>
	<div>
		<textarea id="id_comment" rows="5" style="resize: none;" oninput="txtCounting(this);"></textarea>
		<span><small id="txt_len">0</small><small> / 1000</small></span>
		<button type="button" onclick="commentSend();">작성</button>
	</div>
</form>
<div id="comment_list">
	<c:forEach var="item" items="${comment_list }" >
		<div style="border-style: solid; padding: 6px 0px;">
			<span>${item.getAname() }</span>
			<span>${item.getContents() }</span>
			<span>${item.getCdate() }</span>
			<button type="button" onclick="comRecommend(this, 'g', ${item.getId() });">추천 : ${item.getGcnt() }</button>
			<button type="button" onclick="comRecommend(this, 'b', ${item.getId() });">비추천 : ${item.getBcnt() }</button>
			<c:if test="${item.getAid() == account.getId() }">
				<button type="button" onclick="delComment(this, ${item.getId() });">삭제</button>
			</c:if>
		</div>
	</c:forEach>
</div>