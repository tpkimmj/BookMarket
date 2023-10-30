<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/css/index.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/script/index.js"></script>
<title>BookMarket</title>
</head>
<body>
<c:import url="Top.jsp"></c:import>
<div class="clear"></div>
<div id="contents">
	 <div class="slideshow-container">
	      <div class="mySlides fade">
		      <div class="numbertext">1 / 6</div>
		      <img src="images/banner1.jpg" style="width:100%">
		      <div class="text">ACNE STUDIO</div>
	      </div>
	
	      <div class="mySlides fade">
		      <div class="numbertext">2 / 6</div>
		      <img src="images/banner2.jpg" style="width:100%">
		      <div class="text">ACNE STUDIO</div>
	      </div>
	
	      <a class="prev" onclick="moveSlides(-1)">&#10094;</a>
	      <a class="next" onclick="moveSlides(1)">&#10095;</a>
	 </div>
	    <br/>
	
	    <div style="text-align:center">
	   		<span class="dot" onclick="currentSlide(0)"></span>
	    	<span class="dot" onclick="currentSlide(1)"></span>
	    </div>
	    
	<div id="MainBest">
		<table>
			<tr>
				<th id="Best" colspan="4">BEST</th>
			</tr>
			<tr>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book1.png"></a></li>
						<li class="col2">너에게만 좋은 사람이 되고 싶어<li>
					</ul>
				</td>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book2.png"></a></li>
						<li class="col2">드래곤볼 슈퍼<li>
					</ul>
				</td>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book3.png"></a></li>
						<li class="col2">스튜디오 지브리 입체 건축<li>
					</ul>
				</td>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book4.png"></a></li>
						<li class="col2">스파이 패밀리<li>
					</ul>
				</td>
			</tr>
			<tr>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book5.png"></a></li>
						<li class="col2">원피스<li>
					</ul>
				</td>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book6.png"></a></li>
						<li class="col2">주술회전 0<li>
					</ul>
				</td>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book7.png"></a></li>
						<li class="col2">최애의 아이<li>
					</ul>
				</td>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book8.jpg"></a></li>
						<li class="col2">더 그레이트 비트코인<li>
					</ul>
				</td>
			</tr>
		</table>
	</div>
</div>
<c:import url="Bottom.jsp"></c:import>
</body>
</html>