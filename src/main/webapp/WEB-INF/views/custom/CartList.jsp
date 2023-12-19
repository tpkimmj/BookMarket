<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>        
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" type="text/css" href="/css/cart.css">
<script src="/script/product.js"></script>
</head>
<div class="clear"></div>
<form  id ="cartSave" action="cartProc?flag=add" name="productForm" method="post">
<div id="cart">
<table>
	<thead>
		<tr>
			<td class="title">장바구니 리스트</td>
		</tr>
		<tr><td id="blank" colspan="6"></td></tr>
	</thead>
</table>
<table class="cartList">
	<tr class="border">
		<th>상품명</th>
		<th>상품가격</th>
		<th>수량</th>
		<th>결제금액</th>
		<th>수정/삭제</th>
		<th>조회</th>
	</tr>
	<c:choose>
		<c:when test="${fn:length(cartList)>0}">
			<c:forEach var="cto" items="${cartList}" varStatus="status">
				<tbody>
					<tr class="border">
						<td class="co1">${cto.p_name}</td>
						<td class="co2 price">${cto.price}</td>
						<td class="co3" >${cto.quantity} <!-- 수량 -->
							<input name="quantity" type="hidden" style="text-align: right; padding-right: 5px;" value="${cto.quantity}" size="5">
							<input type="hidden" name="stock" value="${cto.stock}">
							<input type="hidden" name="c_no" value="${cto.c_no}">
							<input type="hidden" name="p_name" value="${cto.p_name}">
							<input type="hidden" name="price" value="${cto.price}">
						</td>
						<td class="co4 price">${cto.price * cto.quantity}</td>
						<td class="co5"><input type="button" value="수정" onclick="javascript:cartUpdate('U',this)">&nbsp;/&nbsp;
							<input type="button" value="삭제" onclick="javascript:cartUpdate('D',this)">
							<input type="hidden" name="stock" value="${cto.stock}">
							<input type="hidden" name="c_no" value="${cto.c_no}">
							<input type="hidden" name="p_name" value="${cto.p_name}">
							<input type="hidden" name="quantity" value="${cto.quantity}">
						</td>
						<td class="co6"> <!-- 상품 상세 조회 -->
							<input  id="bkpno" type="hidden" value="${product.p_no}"  name="p_no"><a href="/bookDetail?p_no=${cto.p_no}">상세보기</a>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</c:when>   
		<c:when test="${fn:length(cartList)==0}">
			<tr><th colspan="6"> 등록된 상품이 없습니다.</th></tr>
		</c:when>   
	</c:choose>
	<tr class="buttonArea">
		<th colspan="6" ><a class="cartButton" href="/orderProc">주문하기</a></th>
	</tr>
</table>
</div>
</form>

