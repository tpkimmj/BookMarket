<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/product.css">
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/product.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="customorder">
 <div class="head">
    <p style="text-align: center;
        font-size: 20px;">${ssKey.m_name}님 상세주문내역</p>
    <p style="padding: 5px; 
        margin-right: 10px;
        text-align: right;
        padding-right: 10px;
        font-size: 16px;">총 주문: ${orderTot} 건</p>
 </div>
   <table class="order">
			<tr id="bkName">
				<th>${product.p_name}
				<input  id="bkpno"type="hidden" value="${product.p_no}" name="p_no" >
				<input  id="bkp_stock"type="hidden" value="${product.stock}" name="stock">
				<input  id="bkp_name"type="hidden" value="${product.p_name}" name="p_name">
				<input  id="bkp_price"type="hidden" value="${product.price}" name="price">
				</th>
			</tr>
			<tr id="bkName1">
			</tr>
			<tbody>
				<tr id="bkcon">
					<td id="bkcon1"><img src="/upload/${product.image}"></td>
					<td id="bkcon2">
						<ul>
							<li>주문번호 : ${odto.o_no}</li><br>
							<li>상 품 명 :${odto.p_name}</li><br>
							<li class="price">단 가 : ${odto.price}	</li><br>
							<li>구매수량 : ${odto.quantity}</li><br>
							<li>결재금액 : ${odto.amount} ￦</div></li>
							<li>배송상태 : 
							<c:choose>
          						 <c:when test="${odto.state==1}">결재중</c:when>            
          						 <c:when test="${odto.state==2}">배송준비</c:when>            
           						<c:when test="${odto.state==3}">배송중</c:when>            
           						<c:when test="${odto.state==4}">배송완료</c:when>            
           						<c:when test="${odto.state==5}">구매확정</c:when>            
         						</c:choose></li>
						</ul>
					</td>
				</tr>
      </tr>
     <tr><th colspan="2" style="text-align: right;"
         class="tableBtn">
         <a href="/orderList"> <input type="button" value="구매목록으로"></a>
      </th> 
      </tr>
   </table>
 
 </div>
