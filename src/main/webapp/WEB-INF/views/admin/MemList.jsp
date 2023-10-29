<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/member.css">
<title>회원 전체 리스트</title>
</head>
<body>
	<div style="text-align: right; margin-right: 30px;">
	총 회원수: &nbsp;${memberTot}</div>
	<div id="member">
	<c:choose>
		<c:when test="${memberTot==0}">
			<table><tr><td>아직 회원이 없습니다.</td></tr></table>
		</c:when>
		<c:when test="${memberTot>0}">
			<table >
			<tr>
				<th>회원아이디</th>
				<th>회원명</th>
				<th>연락처</th>
				<th>주소</th>
			</tr>
			<c:forEach var="member" items="${members}">
				<tr>
					<td>${member.mem_id}</td>
					<td>${member.m_name}</td>
					<td>${member.m_phone}</td>
					<td>${member.address}</td>
				</tr>
			</c:forEach>
			</table>
		</c:when>
	</c:choose>
	</div>
</body>
</html>