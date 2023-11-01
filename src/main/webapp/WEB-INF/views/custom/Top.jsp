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
			            <th>${ssKey.m_name}님 반갑습니다</th>
			            <th><a href="/logoutProc">로그아웃</a></th>
		            </c:when>
		            <c:when test="${ssKey==null}">
			            <th><a href="/login">로그인</a></th>
						<th><a href="/join">회원가입</a></th>
		            </c:when>
	            </c:choose>
					<th><a href="/info">마이페이지</a></th>
					<th><a href="#">장바구니</a></th>
					<th><a href="ClientCenter">고객센터</a></th>
			</tr>
		</table>
	</div>
	<div id="mid">
		<a href="/"><img src="/images/logo.png" id="logo"></a>
		<div id="search">
			<form action="/search" method="post">
				<input type="text" name="">
				<button style="background: #fff; border: none;"><img src="/images/search.png" id="searchIcon"></button>
			</form>
		</div>
	<div id="Menu">
		<ul class="main1">
			<li style="border: none;"><a href="#"><b style="font-size: 20px;"><span style="font-size: 20px;">≡</span> 전체보기</b></a>
				<ul class="main2">
					<li><a href="#">베스트셀러</a>
					<li><a href="#">소설</a>
					<li><a href="#">만화</a>
					<li><a href="#">시집</a>
					<li><a href="#">참고서</a>
					<li><a href="#">자기계발</a>
				</ul>
			</li>
			<li><a href="#"><b style="font-size: 20px;">베스트셀러</b></a>
			<li><a href="#"><b style="font-size: 20px;">소설</b></a>
			<li><a href="#"><b style="font-size: 20px;">만화</b></a>
			<li><a href="#"><b style="font-size: 20px;">시집</b></a>
			<li><a href="#"><b style="font-size: 20px;">참고서</b></a>
			<li><a href="#"><b style="font-size: 20px;">자기계발</b></a>
		</ul>
	</div> 
	</header>
		<div id="side">
			<a href="javascript:window.scrollTo(0,0);"><img src="/images/up.png"></a>
			<a onClick="window.location.reload()"><img src="/images/re.png"></a>
		</div>
</body>
</html>