<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/order.css">
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/product.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="customorder">
<form  id= "cartSave" action="cartProc?flag=add"  name="productForm"  method="post">
  <div id="orderDetail">
 <div id="orName">
    <tr id=>${ssKey.m_name}님 상세주문내역</tr> 
 </div>

     <table class="order">
			<tr>
				<th>
				<input  id="bkpno"type="hidden" value="${product.p_no}" name="p_no" >
				<input  id="bkp_stock"type="hidden" value="${product.stock}" name="stock">
				<input  id="bkp_name"type="hidden" value="${product.p_name}" name="p_name">
				<input  id="bkp_price"type="hidden" value="${product.price}" name="price">
				</th>
			</tr>
			</tr>
			<tbody>
				<tr id="orcon">
					<td id="orcon1"><img src="/upload/${product.image}"></td>
					<td id="orcon2">
						<ul>
							<li>주문번호 : ${odto.o_no}</li><br>
							<li>상 품 명 :${odto.p_name}</li><br>
							<li class="price">단 가 : ${odto.price}</li><br>
							<li>구매수량 : ${odto.quantity}</li><br>
							<li>결재금액 : ${odto.amount} ￦</div></li><br>
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
				<tr>
					<td id="orDe">책소개</td>
				</tr>
				<tr id="orDe1">
					<td colspan="2">${product.detail}</td>
				</tr>
      </tr>
      </tbody>
      </table>
      </div>
      <div id="orderBottom">
      <table>
      <tr>
      <td id="orderCol1">
      <table>
       <tr>
       </tr>
       </table>
       </td>
       <td id="orderCol2">결제예정금액&nbsp; <div id="bkprice" class="price">${product.price}</div>
		<div id="orprice2" style="display: none;">${product.price}</div></td>
       <td  class="tableBtn">
         <a href="/"> <input id="orderBtn2"type="button" value="주문하기"></a>
         <a href="/orderList"> <input id="orderBtn1"type="button" value="구매목록으로"></a>
        </td>
      </tr>
      </table>
      </div>
 </form>
 </div>
