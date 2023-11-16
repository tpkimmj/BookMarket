<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>        
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="/css/product.css">
</head>
<script src="/script/product.js"></script>
<div class="clear"></div>
<div id="customorder">
 <div class="head">
    <p style="text-align: center;
        font-size: 30px;">${ssKey.m_name}님 주문내역</p>
    <p style="padding: 5px; 
        margin-right: 10px;
        text-align: right;
        padding-right: 10px;
        font-size: 16px;">총 주문: ${orderTot} 건</p>
 </div>
 <table class="orderList">
   <tr style="height: 50px; " class="headcolor">
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
          <tr>
            <td class="cl1"><a onclick="javascript:orderDetail(this)">${order.p_name}</a>
	            <input type="hidden" name="p_no" value="${order.p_no}">
	            <input type="hidden" name="o_no" value="${order.o_no}">
	            <input type="hidden" name="mem_id" value="${order.mem_id}">
            </td>
            <td class="cl2 price">${order.quantity}</td>
            <td class="price">${order.price}</td>
            <td class="cl3 price">${order.amount}</td>
            <td class="cl4">${order.o_regdate}</td>
            <td class="cl5" >
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
       <tr style="text-align:center;height: 30px;">
         <th colspan="6"> 등록된 상품이 없습니다.</th></tr>
     </c:when>   
   </c:choose>
   <tr style="text-align:right;height: 50px;">
   <th colspan="6"
     style="text-align:right;border: 1px solid #ffffff;">
     </th>
     </tr>
 </table>
</div>
 <form action="customOrDetail" method="post" name="orform">
    <input type="hidden" name="p_no" value="${order.p_no}">
    <input type="hidden" name="o_no" value="${order.o_no}">
	<input type="hidden" name="mem_id" value="${order.mem_id}">
 </form>





