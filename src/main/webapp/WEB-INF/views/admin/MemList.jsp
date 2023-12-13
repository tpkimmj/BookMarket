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
<div id="memlist">
	<div>
	<c:choose>
		<c:when test="${memberTot==0}">
			<table><tr><td>아직 회원이 없습니다.</td></tr></table>
		</c:when>
		<c:when test="${memberTot>0}">
			<table>
			<tr>
				<td id="tit" colspan="4">회 원 목 록</td>
			</tr>
			<tr>
				<td id="totlist" colspan="4">총 회원수: &nbsp;${memberTot}</td>
			</tr>
			<tr class="bor">
				<th>회원아이디</th>
				<th>회원명</th>
				<th>연락처</th>
				<th>주소</th>
			</tr>
			<c:forEach var="member" items="${members}">
				<tr class="bor">
					<td class="mco1">${member.mem_id}</td>
					<td class="mco2">${member.m_name}</td>
					<td class="mco3">${member.m_phone}</td>
					<td class="mco4">${member.address}</td>
				</tr>
			</c:forEach>
			</table>
		</c:when>
	</c:choose>
	</div>
</div>
</body>
</html>