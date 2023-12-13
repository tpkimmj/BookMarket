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
<script type="text/javascript">
$().ready(function(){
	$("#Canclepay").on("click",function(){
		var transactionId = $("input[name=transactionId]").val(); 
		var o_no = $("input[name=o_no]").val(); 
		$.ajax({
			   async:false,
			   type:'post',
			   data:{
				   	"transactionId" : transactionId,
				   	"o_no" : o_no
			   },
			   url: "/Canclepay",
			   dataType:"json",
			   success : function(data) {
				   console.log(data);
				   if("0000"==data.orderResult.responseCode){
					   alert("결제취소 완료");
					   location.href="/orderList";
				   }else{
					   alert('실패');
				   }
			     }
		   });
	});

});
</script>
</head>
<body>
 	<div id="payCancle">
		<h2>결 제 취 소</h2>
		<div id="p_address">
			<div class="p_title">결제취소 내용</div>
			<div class="p_inner">
				<span>${mInfo.m_name}<br></span>
				<span>${mInfo.m_phone}<br></span>
				<span>${mInfo.address}${mInfo.address2}</span>
			</div>
		</div>
		<div id="p_info">
			<div class="p_title">취소상품</div>
			<div class="p_inner">
			<c:set var="sum" value="0" />
			<c:set var="pro" value="0"/>
				<c:choose>
					<c:when test="${fn:length(pInfo)>0}">
						<c:forEach var="product" items="${pInfo}">
							<c:set var="sum" value="${sum + product.PRICE*product.QUANTITY}" />
							<c:set var="pro" value="${pro + 1}"/>
							<div class="p_con">
								<span class="pname">${product.P_NAME}</span>
								<span>취소수량 : ${product.QUANTITY}</span>
								<span class="price">${product.PRICE*product.QUANTITY}원</span>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</div>
		<div id="payTot">
			<div id="t_inner">
				<h3>총 취소금액&nbsp; :&nbsp; <span class="price">${sum}</span></h3>
			</div>
		</div>
		<div id="paybtn"><button id="Canclepay">취 소 하 기</button></div>
			<form name="kcp_order_info" id="kcp_order_info" action="/Canclepay" method="post" accept-charset="euc-kr">
				<input type="hidden" name="transactionId" value="${cInfo.TRANSACTIONID}">
				<input type="hidden" name="o_no" value="${cInfo.O_NO}">
			</form>
	</div>
</body>
</html>