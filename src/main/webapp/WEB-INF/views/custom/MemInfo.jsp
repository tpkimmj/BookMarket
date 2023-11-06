<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" type="text/css" href="/css/member.css">
	<script src="/script/memberup.js"></script>
</head>
<div id="member">
	<table>
		<thead>
			<tr>
				<th colspan="3">회원정보</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="col1">아이디</td>
				<td class="col2">${mdto.mem_id}</td>
			</tr>
			<tr>
				<td class="col1">이름</td>
				<td class="col2">${mdto.m_name}</td>
			</tr>
			<tr>
				<td class="col1">이메일</td>
				<td class="col2">${mdto.m_email}</td>
			</tr>
			<tr>
				<td class="col1">전화번호</td>
				<td class="col2">${mdto.m_phone}</td>
			</tr>
			<tr>
				<td class="col1">우편번호</td>
				<td class="col2">${mdto.zipcode}</td>
			</tr>
			<tr>
				<td class="col1">주소</td>
				<td class="col2">${mdto.address}
					<br>${mdto.address2}
				</td>
			</tr>
			<tr>
				<td class="col11">직업</td>
				<td class="col22">${mdto.m_job}</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2" class="tabBtn" style="border: 1px solid white;">
					<input id="btn1" name="update" type="button" onclick="update('u')" value="정보수정">
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="btn2" name="delete" type="button" onclick="update('d')" value="회원탈퇴">
				</td>
			</tr>
		</tfoot>
	</table>
	<form name="upForm" id="upForm" action="" method="post">
		<input type="hidden" id="memID" name="mem_id" value="${mdto.mem_id}">
		<input type="hidden" id="pw" name="m_passwd" value="${mdto.m_passwd}">
	</form>
</div>
