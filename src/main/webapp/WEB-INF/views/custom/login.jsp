<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/login.css">
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/member.js"></script>
</head>
<body>

<div class="wrapper">
	<form action="loginProc" method="post" name="topForm">
	<div class="wrap">
		<div class="logo_wrap">
			<span>Book Mall</span>
		</div>
		<div class="login_wrap"> 
			<div class="id_wrap">
				<input type="text" autofocus="autofocus" name="mem_id" class="chkt" title="아이디" id="id">
			</div>
			<div class="pw_wrap">
				<input type="password" name="m_passwd" class="chkt" title="패스워드" id="pw" onkeydown="enter()">
			</div>
			<div class="login_button_wrap">
				<div id="submitTop"><div>로그인</div></div>
				<a href="/memSearch"><div>아이디찾기/비밀번호변경</div></a>
			</div>			
		</div>
	</div>
	</form>
</div>
