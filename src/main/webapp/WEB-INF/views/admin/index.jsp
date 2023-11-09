<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		      <div class="numbertext">1 / 2</div>
		      <img src="/images/banner1.jpg" style="width:100%">
		      <div class="text">ACNE STUDIO</div>
	      </div>
	
	      <div class="mySlides fade">
		      <div class="numbertext">2 / 2</div>
		      <img src="/images/banner2.jpg" style="width:100%">
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
				<c:choose>
				<c:when test="${fn:length(productList)>0}">
					<c:forEach var="product" items="${productList}" begin="0" end="3">
					<td>
						<ul>
							<li class="col1"><a href="/bookDetail?p_no=${product.p_no}"><img src="/upload/${product.image}"></a></li>
							<li class="col2">${product.p_name}<li>
						</ul>
					</td>
					</c:forEach>
				</c:when>
			</c:choose>
			</tr>
			<tr>
			<c:choose>
				<c:when test="${fn:length(productList)>0}">
					<c:forEach var="product" items="${productList}" begin="4" end="7">
					<td>
						<ul>
							<li class="col1"><a href="/bookDetail?p_no=${product.p_no}"><img src="/upload/${product.image}"></a></li>
							<li class="col2">${product.p_name}<li>
						</ul>
					</td>
					</c:forEach>
				</c:when>
			</c:choose>
			</tr>
		</table>
	</div>
</div>
<c:import url="Bottom.jsp"></c:import>
</body>
</html>