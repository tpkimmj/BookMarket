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

	$("#auth").on("click",function(){
		var orderNumber = $("input[name=orderNumber]").val(); 
		var amount = $("input[name=amount]").val(); 
		var itemName = $("input[name=itemName]").val(); 
		var userName = $("input[name=userName]").val(); 
		var mem_id = $("input[name=mem_id]").val(); 
		$.ajax({
			   async:false,
			   type:'post',
			   data:{"orderNumber":orderNumber,
				   	"amount" : amount,
				   	"mem_id" : mem_id,
				   	"itemName" : itemName,
				   	"userName" : userName
			   },
			   url: "/payOrder",
			   dataType:"json",
			   success : function(data) {
				   console.log(data);
				   if("0000"==data.orderResult.responseCode){
					   $("input[name=ordr_idxx]").val(data.orderResult.ordr_idxx);
					   $("input[name=good_name]").val(data.orderResult.good_name);
					   $("input[name=good_mny]").val(data.orderResult.good_mny);
					   $("input[name=buyr_name]").val(data.orderResult.buyr_name);
					   $("input[name=site_cd]").val(data.orderResult.site_cd);
					   
					   jsf__pay();
				   }else{
					   alert('실패');
				   }
				   
			     }
		   });
	});

});
/****************************************************************/
/* m_Completepayment 설명 */
/****************************************************************/
/* 인증완료시 재귀 함수 */
/* 해당 함수명은 절대 변경하면 안됩니다. */
/* 해당 함수의 위치는 payplus.js 보다먼저 선언되어여 합니다. */
/* Web 방식의 경우 리턴 값이 form 으로 넘어옴 */
/****************************************************************/
function m_Completepayment( FormOrJson, closeEvent ) {
	var frm = document.kcp_order_info; 
	/********************************************************************/
	/* FormOrJson은 가맹점 임의 활용 금지 */
	/* frm 값에 FormOrJson 값이 설정 됨 frm 값으로 활용 하셔야 됩니다. */
	/********************************************************************/
	GetField( frm, FormOrJson ); 
	console.log(frm);
 
	if( frm.res_cd.value == "0000" )
	{
		 /*
			 [가맹점 리턴값 처리 영역] 
			인증이 완료되면 frm에 인증값이 들어갑니다. 해당 데이터를 가지고
			승인요청을 진행 해주시면 됩니다.
		*/
		
		//폼 id = kcp_order_info
		// /authPay로 서브밋하기
		var form = document.getElementById("kcp_order_info");
    	form.submit();
		
		closeEvent();
 	} else

 	{
		/*
			(인증실패) 인증 실패 처리 진행
		*/
 		alert( "[" + frm.res_cd.value + "] " + frm.res_msg.value );
 
		closeEvent();
	}
}

/* 이 함수를 실행하여 표준웹 실행 */
function jsf__pay() {
	try {
		var form = document.kcp_order_info;
		KCP_Pay_Execute( form ); 
	} catch (e) {
		/* IE 에서 결제 정상종료시 throw로 스크립트 종료 */
	}
} 
</script>
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
			<c:set var="pro" value="0"/>
				<c:choose>
					<c:when test="${fn:length(pInfo)>0}">
						<c:forEach var="product" items="${pInfo}">
							<c:set var="sum" value="${sum + product.PRICE*product.QUANTITY}" />
							<c:set var="pro" value="${pro + 1}"/>
							<div class="p_con">
								<span class="pname">${product.P_NAME}</span>
								<span>구매수량 : ${product.QUANTITY}</span>
								<span class="price">${product.PRICE*product.QUANTITY}원</span>
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
		<div id="paybtn"><button id="auth">결 제 하 기</button></div>
	</div>
	<script type="text/javascript" src="https://testpay.kcp.co.kr/plugin/payplus_web.jsp"></script>
	<form name="kcp_order_info" id="kcp_order_info" action="/authPay" method="post" accept-charset="euc-kr">
		<input type="hidden" name="orderNumber" value="${pInfo[0].O_NO}">
		<input type="hidden" name="itemName" value="${pro}건">
		<input type="hidden" name="amount" value="${sum}">
		<input type="hidden" name="userName" value="${mInfo.m_name}">
		<input type="hidden" name="mem_id" value="${mInfo.mem_id}">
		
		<input type="hidden" name="ordr_idxx" value="">
		<input type="hidden" name="good_name" value="">
		<input type="hidden" name="good_mny" value="">
		<input type="hidden" name="buyr_name" value="">
		<input type="hidden" name="site_cd" value="">

		<!-- 고정값 -->
		<input type="hidden" name="req_tx" value="pay">
		<input type="hidden" name="pay_method" value="100000000000">
		<input type="hidden" name="site_name" value="payup" />

		<!--
			 ※ 필 수
			 필수 항목 : 표준웹에서 값을 설정하는 부분으로 반드시 포함되어야 합니다
			 값을 설정하지 마십시오
		-->
		<input type="hidden" name="res_cd" value="" />
		<input type="hidden" name="res_msg" value="" />
		<input type="hidden" name="enc_info" value="" />
		<input type="hidden" name="enc_data" value="" />
		<input type="hidden" name="ret_pay_method" value="" /> 
		<input type="hidden" name="tran_cd" value="" /> 
		<input type="hidden" name="use_pay_method" value="" /> 
		<input type="hidden" name="buyr_mail" value=""> 
		<input type="hidden" name="ordr_chk" value="" />
			
		<!-- 2012년 8월 18일 전자상거래법 개정 관련 설정 부분 -->
		<!-- 제공 기간 설정 0:일회성 1:기간설정(ex 1:2012010120120131) -->
		<input type="hidden" name="good_expr" value="0">
		
		<!-- 표준웹 설정 정보입니다(변경 불가) -->
		<input type="hidden" name="module_type" value="01" />
		
		<!-- 필수 항목 : 결제 금액/화폐단위 -->
		<input type="hidden" name="currency" value="WON" />
	</form>
</body>
</html>