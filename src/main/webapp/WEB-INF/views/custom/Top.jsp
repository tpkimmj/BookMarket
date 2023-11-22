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
<script src="/script/top.js"></script>
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
					<th><a href="/cartList">장바구니</a></th>
					<th><a href="/orderList">주문내역</a></th>
					<th><a href="ClientCenter">공지사항</a></th>
			</tr>
		</table>
	</div>
	<div id="mid">
		<div id="mid_top">
			<a href="/"><img src="/images/logo.png" id="logo"></a>
			<div id="search">
				<form action="/search" method="get" name="searchForm">
					<input type="text" name="searchText" value="">
					<button type="submit" style="background: #fff; border: none;">
						<img id="searchIcon" src="/images/search_static.png" alt="" data-animated="/images/search.gif" data-static="/images/search_static.png" class="hov-anim">
					</button>
				</form>
			</div>
			<div id="displayList" ></div>
		</div>
	<div id="Menu">
		<ul class="main1">
			<li style="border: none;"><a href="/productList?state=all"><b style="font-size: 20px;"><span style="font-size: 20px;">≡</span> 전체보기</b></a>
				<ul class="main2">
					<li><a href="/productList?state=best">베스트셀러</a>
					<li><a href="/productList?state=fiction">소설</a> 
					<li><a href="/productList?state=cartoon">만화</a>
					<li><a href="/productList?state=divan">시집</a>
					<li><a href="/productList?state=referbook">참고서</a>
					<li><a href="/productList?state=selfdev">자기계발</a>
				</ul>
			</li>
			<li><a href="/productList?state=best"><b style="font-size: 20px;">베스트셀러</b></a>
			<li><a href="/productList?state=fiction"><b style="font-size: 20px;">소설</b></a>
			<li><a href="/productList?state=cartoon"><b style="font-size: 20px;">만화</b></a>
			<li><a href="/productList?state=divan"><b style="font-size: 20px;">시집</b></a>
			<li><a href="/productList?state=referbook"><b style="font-size: 20px;">참고서</b></a>
			<li><a href="/productList?state=selfdev"><b style="font-size: 20px;">자기계발</b></a>
		</ul>
	</div> 
	</div>
	</header>
		<div id="side">
			<a onClick="window.scrollTo(0,0);"><img src="/images/up.png"></a>
			<a onClick="window.location.reload()"><img src="/images/re.png"></a>
			<c:choose>
		            <c:when test="${ssKey!=null}">
						<a onClick="window.open('/mychatt', 'chat', 'width = 550, height = 690, toolbar = no, location = no, menubar = no, resizable = no, scrollbars = no')">
							<img width="64" height="64" src="https://img.icons8.com/nolan/64/speech-bubble.png" alt="speech-bubble"/>
						</a>
		            </c:when>
		            <c:when test="${ssKey==null}">
						<a href="/login" onClick="alert('로그인 먼저 필요합니다.')">
							<img width="64" height="64" src="https://img.icons8.com/nolan/64/speech-bubble.png" alt="speech-bubble"/>
						</a>
		            </c:when>
		    </c:choose>
			<a href="https://www.instagram.com/book__market__/" target="_blank" rel="noopener noreferrer"><img width="50" height="50" src="https://www.bookoa.co.kr/assets/images/icons/instagram_icon.jpg"></a>
		</div>
		<div id="sideBanner">
		    <nav id="sidebar">
		    	<a href="/bookDetail?p_no=35"><img id="bannerImg" src="https://img.ypbooks.co.kr/upload/banner/rightban_231031_03.jpg"></a>
		    </nav>
		  	 <button id="sidebarCollapse"><img width="20" height="20" src="https://img.icons8.com/stickers/100/x.png" alt="x"/></button> 
		</div>
</body>
</html>