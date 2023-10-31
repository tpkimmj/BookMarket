<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet" type="text/css" href="/css/product.css">
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/product.js"></script>
</head>
<div id="productMgt">
<table>
		<thead>
		<tr>
	   		<td class="title">상 품 목 록</td>
		</tr>
		<tr>
	   		<td id="productCnt" colspan="6">총 상품수 : ${pcnt} 가지</td>
		</tr>
	</thead>
	</table>
	<table id="productList">
		<tr class="border">
			<th>상품번호</th>
			<th>상품명</th>
			<th>상품가격</th>
			<th>상품분류</th>
			<th>등록일</th>
			<th>재 고</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(pList)>0}">
				<c:forEach var="product" items="${pList}">
					<tr class="border">
						<td class="pco1">${product.p_no}</td>
						<td class="pco2"><a href="/productDetail?p_no=${product.p_no}">${product.p_name}</a></td>
						<td class="pco3 price">${product.price}</td>
						<td class="pco4">${product.state}</td>
						<td class="pco5">${product.pr_date}</td>
						<td class="pco6">${product.stock}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${fn:length(pList)==0}">
				<tr><th colspan="6">등록된 상품이 없습니다.</th></tr>
			</c:when>
		</c:choose>
		<tr>
			<th class="proButton" colspan="6">
				<button onclick="location.href='/productInForm'">상품등록</button>
			</th>
		</tr>
	</table>
</div>
