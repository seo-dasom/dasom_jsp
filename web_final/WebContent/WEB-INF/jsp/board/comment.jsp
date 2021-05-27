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
	<div style="border-style: solid; padding: 6px 0px;">
		<span>작성자</span>
		<span>댓글 내용</span>
		<span>작성일</span>
		<span>추천</span>
		<span>비추천</span>
	</div>
</div>