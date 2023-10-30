<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/product.js"></script>
</head>
<table id="head">
		<thead>
		<tr style="text-align: center;">
	   		<th style="padding: 20px;">상품목록</th>
		</tr>
		<tr style="text-align: right;">
	   		<th style="padding: 5px;">총 상품수: ${pcnt} 가지</th>
		</tr>
	</thead>
	</table>
	<table id="productlist">
		<tr style="height: 50px;" class="headcolor">
			<th>상품번호</th>
			<th>상품명</th>
			<th>상품가격</th>
			<th>상품분류</th>
			<th>등록일</th>
			<th>재고</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(pList)>0}">
				<c:forEach var="product" items="${pList}">
					<tr>
						<td class="co1">${product.p_no}</td>
						<td class="co2"><a href="/productDetail?p_no=${product.p_no}">${product.p_name}</a></td>
						<td class="price">${product.price}</td>
						<td class="price">${product.state}</td>
						<td class="co4">${product.pr_date}</td>
						<td class="co5">${product.stock}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${fn:length(pList)==0}">
				<tr style="text-align: center; height: 30px;"><th colspan="5">등록된 상품이 없습니다.</th></tr>
			</c:when>
		</c:choose>
		<tr style="text-align: right;height: 50px;border: solid 1px #ffffff;">
			<th colspan="5" style="text-align: right;">
				<button onclick="location.href='/productInForm'">상품등록</button>
			</th>
		</tr>
	</table>
