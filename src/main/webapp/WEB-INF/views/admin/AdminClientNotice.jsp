<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/notice.css">
<script src="/script/notice.js"></script>
<script src="/jquery/jquery-3.7.0.min.js"></script>
<body>
	<form action="noticeProc" name="topForm1" method="post">
		<table>
			<thead>
				<tr>
					<th colspan="2">공지사항등록</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="col1">공지사항번호</td>
					<td class="col2">
						<input type="text" name="noti_no" class="chk1" value="${notice.noti_no}">
					</td>
				</tr>
				<tr>
					<td class="col1">제목</td>
					<td class="col2">
						<input type="text" name="subject" class="chk1" value="${notice.subject}">
					</td>
				</tr>
				<tr>
					<td class="col1">작성자</td>
					<td class="col2">
						<input type="text" name="writer" class="chk1" value="${notice.writer}">
					</td>
				</tr>
				<tr>
					<td class="col1">내용</td>
					<td class="col2">
						<textarea readonly="readonly" class="chk1" rows="10" cols="60" name="content" style="resize: none;">${notice.content}</textarea>
					</td>
				</tr>
				<tr>
					<td class="col1">작성일자</td>
					<td class="col2">
						<input type="text" name="regdate" class="chk1" value="${notice.regdate}">
					</td>
				</tr>
				<tr>
					<td class="col1">공지종료</td>
					<td class="col2">
						<input type="date" name="v_date" class="chk1" value="${notice.v_date}">
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3">
						<button type="button" onclick="location.href='notice'">공지사항 수정</button>
						<button type="button" onclick="location.href='notice'">공지사항 삭제</button>
						<button type="button" onclick="location.href='notice'">공지사항 목록</button>
					</td>
				</tr>
			</tfoot>
		</table>
		<input type="hidden" name="flag" value="insert">
	</form>
</body>
</html>