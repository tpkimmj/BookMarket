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
<script type="text/javascript" src="/script/register.js"></script>
<body>
<div id="form">
	<form action="/noticeProc" name="topForm1" method="post">
		<table>
			<thead>
				<tr>
					<th colspan="2">공지사항등록</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="col1">제목</td>
					<td class="col2">
						<input type="text" name="subject" class="chk1" value="${cdto.subject}" title="제목">
					</td>
				</tr>
				<tr>
					<td class="col1">작성자</td>
					<td class="col2">
						<input type="text" name="writer" class="chk1" value="관리자" readonly="readonly">
						
					</td>
				</tr>
				<tr>
					<td class="col1">내용</td>
					<td class="col2">
						<textarea class="chk1" rows="15" cols="60" name="content" style="resize: none;">${cdto.content}</textarea>
					</td>
				</tr>
				
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
					
						<button type="button" id="submit12">공지사항 등록</button>
					</td>
				</tr>
			</tfoot>
		</table>
		<input type="hidden" name="flag" value="insert">
	</form>
</div>
</body>
</html>