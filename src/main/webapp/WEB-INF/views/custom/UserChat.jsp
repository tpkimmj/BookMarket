<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅문의</title>
<link rel='stylesheet' type='text/css' href='/css/chat.css'>
<script src="/jquery/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div id="userChat">
		<h2>Book Market</h2>
		<input id="endBtn" onclick="chatEnd()" type="button" value="문 의 종 료">
		<div id="messageTextArea"></div>
		<form>
			<!-- 텍스트 박스에 채팅의 내용을 작성한다. -->
			<input id="textMessage" name="insert" autofocus="autofocus" type="text" onkeydown="return enter()">
			<!-- 서버로 메시지를 전송하는 버튼 -->
			<input id="chatBtn" onclick="sendMessage()" value="전 송" type="button">
		</form>
		<br />
	</div>
	<!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
	<script src='/script/userChat.js'></script>
		<input type="hidden" name="ch_id" value="${ssKey.mem_id}">
		<input type="hidden" name="ch_name" value="${ssKey.m_name}">
</body>
</html>