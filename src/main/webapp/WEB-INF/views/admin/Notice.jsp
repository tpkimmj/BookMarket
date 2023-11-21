<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/Notice.css"> 
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="/script/Fix.js"></script>
<body>
<div id="form">
	<form action="/noticeProc" name="topForm1" method="post">
		<table>
			<tbody>
				<tr>
					<td class="notiTitle">
						<span>${cdto.subject}</span>
					</td>
				</tr>
				<tr>
					<td class="notiWriter">
						<span>${cdto.writer} &nbsp;&nbsp; ${cdto.regdate} </span>
					</td>
				</tr>
				<tr class="notiContent">
					<td>
						<textarea readonly="readonly" rows="35" cols="110">${cdto.content}</textarea>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
					<input type="hidden" name="cno" value="${cdto.cno}">
						<button type="button" id="noticeUpForm">공지사항 수정</button>
						<button type="button" class="noticeDel">공지사항 삭제</button>
						<button type="button" onclick="location.href='ClientCenter'">공지사항 목록</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>
</body>
</html>