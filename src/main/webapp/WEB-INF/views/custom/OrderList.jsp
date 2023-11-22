<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>        
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="/css/order.css">
<script src="/script/product.js"></script>
</head>
<div class="clear"></div>
<div id="customorder">
<table>
	<thead>
		<tr>
			<td class="title">${ssKey.m_name}님 주문내역</td>
		</tr>
		<tr>
			<td id="orderCnt" colspan="9">총 주문: ${orderTot} 건</td>
		</tr>
	</thead>
</table>
<table class="orderList">
	<tr class="border">
		<th>상품명</th>
		<th>주문수량</th>
		<th>단가</th>
		<th>결재금액</th>
		<th>결재일</th>
		<th>주문상태</th>
	</tr>
	<c:choose>
		<c:when test="${fn:length(orderList)>0}">
			<c:forEach var="order" items="${orderList}">
				<tr class="border">
					<td class="cl1"><a onclick="javascript:orderDetail(this)">${order.p_name}</a>
						<input type="hidden" name="p_no" value="${order.p_no}">
						<input type="hidden" name="o_no" value="${order.o_no}">
						<input type="hidden" name="mem_id" value="${order.mem_id}">
					</td>
					<td class="cl2">${order.quantity}</td>
					<td class="cl3 price">${order.price}</td>
					<td class="cl4 price">${order.amount}</td>
					<td class="cl5">${order.o_regdate}</td>
					<td class="cl6" >
					<c:choose>
						<c:when test="${order.state==1}">결재중</c:when>            
						<c:when test="${order.state==2}">배송준비</c:when>            
						<c:when test="${order.state==3}">배송중</c:when>            
						<c:when test="${order.state==4}">배송완료</c:when>            
						<c:when test="${order.state==5}">구매확정</c:when>            
					</c:choose>
				</td>
				</tr>
			</c:forEach>
		</c:when>   
		<c:when test="${fn:length(orderList)==0}">
			<tr><th colspan="6"> 등록된 상품이 없습니다.</th></tr>
		</c:when>   
	</c:choose>
</table>
</div>
<form action="customOrDetail" method="post" name="orform">
	<input type="hidden" name="p_no" value="${order.p_no}">
	<input type="hidden" name="o_no" value="${order.o_no}">
	<input type="hidden" name="mem_id" value="${order.mem_id}">
</form>





