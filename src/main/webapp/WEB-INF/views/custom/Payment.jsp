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
	<div id="payment">
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
				<div class="inimg"><img width="100%" height="100%" src="/upload/${pInfo.image}"></div>
				<div class="p_con">
					<span>${pInfo.state}</span>
					<span class="pname">${pInfo.p_name}</span>
					<span>${pInfo.writer}</span>
					<span>구매수량 : ${quantity}</span>
					<span class="price">${pInfo.price*quantity}원</span>
				</div>
			</div>
		</div>
		<div id="paybtn"><button>결 제 하 기</button></div>
	</div>
</body>
</html>