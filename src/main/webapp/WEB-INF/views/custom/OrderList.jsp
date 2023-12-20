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
<form action="" name="orderForm" method="post">
<table class="orderList">
	<tr class="border">
		<th>주문번호</th>
		<th>상품명</th>
		<th>주문수량</th>
		<th>단가</th>
		<th>결재금액</th>
		<th>결재일</th>
		<th>주문상태</th>
		<th>주문취소</th>
	</tr>
	<c:choose>
		<c:when test="${fn:length(orderList)>0}">
			<c:forEach var="order" items="${orderList}">
				<tr class="border">
					<td class="cl1">${order.o_no}</td>
					<td class="cl2">${order.p_name}
						<input type="hidden" name="p_no" value="${order.p_no}">
						<input type="hidden" name="o_no" value="${order.o_no}">
						<input type="hidden" name="mem_id" value="${order.mem_id}">
						<input type="hidden" name="quantity" value="${order.quantity}">
					</td>
					<td class="cl3">${order.quantity}</td>
					<td class="cl4 price">${order.price}</td>
					<td class="cl5 price">${order.amount}</td>
					<td class="cl6">${order.o_regdate}</td>
					<td class="cl7" >
					<c:choose>
						<c:when test="${order.state==1}">결재중</c:when>            
						<c:when test="${order.state==2}">배송준비</c:when>            
						<c:when test="${order.state==3}">배송중</c:when>            
						<c:when test="${order.state==4}">배송완료</c:when>            
						<c:when test="${order.state==5}">구매확정</c:when>            
					</c:choose>
				</td>
				<td class="cl8">
				<c:if test="${order.state == 1}">
			 	<input type="button" class="orderPay" value="결제하기"> / 
			 	<input type="button" class="orderCancle" value="주문취소">
					<input type="hidden" name="p_no" value="${order.p_no}">
					<input type="hidden" name="o_no" value="${order.o_no}">
					<input type="hidden" name="quantity" value="${order.quantity}">
					<input type="hidden" name="mem_id" value="${order.mem_id}">
					<input type="hidden" name="m_name" value="${order.m_name}">
					<input type="hidden" name="p_name" value="${order.p_name}">
				</c:if>
				<c:if test="${order.state ==2 }">
			 	<input type="button" class="payCancle" value="결제취소">
					<input type="hidden" name="p_no" value="${order.p_no}">
					<input type="hidden" name="o_no" value="${order.o_no}">
					<input type="hidden" name="mem_id" value="${order.mem_id}">
					<input type="hidden" name="p_name" value="${order.p_name}">
					<input type="hidden" name="quantity" value="${order.quantity}">
				</c:if>
				<c:if test="${order.state > 2}">
				<input type="button" class="cancleFail" value="취소불가">
				</c:if>
				</td>
				</tr>
			</c:forEach>
		</c:when>   
		<c:when test="${fn:length(orderList)==0}">
			<tr class=bord><th colspan="8" class="cl8"> 등록된 상품이 없습니다.</th></tr>
		</c:when>   
	</c:choose>
</table>
</form>
</div>
<form action="customOrDetail" method="post" name="orform">
	<input type="hidden" name="p_no" value="${order.p_no}">
	<input type="hidden" name="o_no" value="${order.o_no}">
	<input type="hidden" name="mem_id" value="${order.mem_id}">
</form>





