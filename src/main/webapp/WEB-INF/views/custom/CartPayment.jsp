<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/payment.css">
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/product.js"></script>
<meta charset="UTF-8">
</head>
<body>
	<div id="cartPayment">
		<h2>주문/결제</h2>
		<div id="p_address">
			<div class="p_title">배송지</div>
			<div class="p_inner">
				<span>${mInfo.m_name}<br></span>
				<span>${mInfo.m_phone}<br></span>
				<span>${mInfo.address}${mInfo.address2}</span>
			</div>
		</div>
		<div id="p_info">
			<div class="p_title">주문상품</div>
			<div class="p_inner">
			<c:set var="sum" value="0" />
				<c:choose>
					<c:when test="${fn:length(pInfo)>0}">
						<c:forEach var="product" items="${pInfo}">
							<c:set var="sum" value="${sum + product.price*product.quantity}" />
							<div class="p_con">
								<span class="pname">${product.p_name}</span>
								<span>구매수량 : ${product.quantity}</span>
								<span class="price">${product.price*product.quantity}원</span>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</div>
		<div id="payTot">
			<div id="t_inner">
				<h3>총 결제금액&nbsp; :&nbsp; <span class="price">${sum}</span></h3>
			</div>
		</div>
		<div id="paybtn"><button>결 제 하 기</button></div>
	</div>
</body>
</html>