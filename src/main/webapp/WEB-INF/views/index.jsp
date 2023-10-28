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
<c:import url="./custom/Top.jsp"></c:import>
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
						<li class="col1"><a href="#"><img src="images/book1.png"></a></li>
						<li class="col2">너에게만 좋은 사람이 되고 싶어<li>
					</ul>
				</td>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book1.png"></a></li>
						<li class="col2">너에게만 좋은 사람이 되고 싶어<li>
					</ul>
				</td>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book1.png"></a></li>
						<li class="col2">너에게만 좋은 사람이 되고 싶어<li>
					</ul>
				</td>
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
						<li class="col1"><a href="#"><img src="images/book1.png"></a></li>
						<li class="col2">너에게만 좋은 사람이 되고 싶어<li>
					</ul>
				</td>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book1.png"></a></li>
						<li class="col2">너에게만 좋은 사람이 되고 싶어<li>
					</ul>
				</td>
				<td>
					<ul>
						<li class="col1"><a href="#"><img src="images/book1.png"></a></li>
						<li class="col2">너에게만 좋은 사람이 되고 싶어<li>
					</ul>
				</td>
			</tr>
		</table>
	</div>
</div>
<c:import url="./custom/Bottom.jsp"></c:import>
</body>
</html>