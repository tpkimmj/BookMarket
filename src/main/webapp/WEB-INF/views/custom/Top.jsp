<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/Top.css">
<link rel="stylesheet" type="text/css" href="/css/Main.css">
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/member.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
	<div id="TopMenuBox">
		<table>
			<tr>
				<c:choose>
		            <c:when test="${ssKey!=null}">
			            <th><a href="/logoutProc">로그아웃</a></th>
		            </c:when>
		            <c:when test="${ssKey==null}">
			            <th><a href="/login">로그인</a></th>
						<th><a href="/join">회원가입</a></th>
		            </c:when>
	            </c:choose>
					<th><a href="/info">마이페이지</a></th>
					<th><a href="#">장바구니</a></th>
					<th><a href="#">고객센터</a></th>
			</tr>
		</table>
	</div>
	<div id="mid">
		<a href="/"><img src="/images/logo.png" id="logo"></a>
		<div id="search">
			<input type="text" name="">
			<a href="#"><img src="/images/search.png" id="searchIcon"></a>
		</div>
	<div id="MainMenu">
		<table>
			<tr>
				<td><a href="#">베스트셀러</a></td>
				<td><a href="#">소설</a></td>
				<td><a href="#">만화</a></td>
				<td><a href="#">시집</a></td>
				<td><a href="#">참고서</a></td>
				<td><a href="#">자기계발</a></td>
			</tr>
		</table>
	</div>
	</div> 
	</header>
</body>
</html>