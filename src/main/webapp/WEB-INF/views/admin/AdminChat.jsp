<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>관리자 채팅</title>
<link rel='stylesheet' type='text/css' href='/css/chat.css'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
  /* 여러 채팅창 간의 간격과 배열 위치*/
  .float-left{
    float:left;
    margin: 5px;
  }
</style>
</head>
<body>
	<div style="background-color: #237AF6; color: white; width: 200px; text-align: center; margin: 20px auto; font-size: 30px;">AdminChat</div>
  <!-- 유저가 접속할 때마다 이 템플릿으로 채팅창을 생성한다. -->
  <div class="template" style="display:none">
  	<div id="adminChat">
  	<h2>UserChat</h2>
  	<input id="endBtn" name="endBtn" onclick="chatEnd(this)" type="button" value="문 의 종 료">
    <!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
    <div id="messageTextArea"></div>
    <form>
      <!-- 메시지 텍스트 박스 -->
      <input type="text" class="message" onkeydown="if(event.keyCode === 13) return false;">
      <!-- 전송 버튼 -->
      <input value="Send" type="button" class="sendBtn">
    </form>
    <br />
  	</div>
  </div>
  <!-- 소스를 간단하게 하기 위하 Jquery를 사용했습니다. -->
  <script src='/script/adminChat.js'></script>
</body>
</html>
