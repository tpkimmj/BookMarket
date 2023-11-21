<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/member.css">
<script src="/script/memberup.js"></script>
<script src="/jquery/jquery-3.7.0.min.js"></script>
<title>비밀번호 확인</title>
</head>
<body>
<table id="pwcheck">
	<tr>
		<td >비밀번호 입력</td>
	</tr>
	<tr>
		<td id="pwinput"><input type="password" id="pwck" autofocus="autofocus" onkeydown="return enter()"></td>
	</tr>
	<tr id="pwbtn">
		<td colspan="2">
			<input type="button" value="확인" onclick="setParentText(this);" style="margin-right: 20px;">
			<input type="button" value="닫기" onclick="window.close()">
	</tr>
</table>
</body>
</html>