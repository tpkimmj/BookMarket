<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="/script/memberup.js"></script>
<script src="/jquery/jquery-3.7.0.min.js"></script>
<title>비밀번호 확인</title>
</head>
<body>
<table>
	<tr>
		<td>비밀번호 입력: </td>
		<td><input type="password" id="pwck"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="확인" onclick="setParentText(this);" style="margin-right: 20px;">
			<input type="button" value="닫기" onclick="window.close()">
	</tr>
</table>
</body>
</html>