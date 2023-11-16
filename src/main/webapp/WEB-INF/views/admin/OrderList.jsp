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
        font-size: 30px;">총 주문 내역</p>
    <p style="padding: 5px; 
        margin-right: 10px;
        text-align: right;
        padding-right: 50px;
        font-size: 16px;">총 주문: ${orderTot} 건</p>
 </div>
 <table class="orderList">
   <tr style="height: 50px; " class="headcolor">
    <th>선택</th>
    <th>상품번호</th>
    <th>상품명</th>
    <th>고객명(고객아이디)</th>
    <th>주문수량</th>
    <th>단가</th>
    <th>결재금액</th>
    <th>결재일</th>
    <th>주문상태</th>
   </tr>
   
   <c:choose>
     <c:when test="${fn:length(orderList)>0}">
        <c:forEach var="order" items="${orderList}"  varStatus="i">
          <tr class="orderList">
            <td class="acl0">
             <input type="checkbox" name="ck" value="${i.index}" 
             readonly="readonly"> 
            </td>
            <td class="acl1 price">
            ${order.p_no}</td>
            <td class="acl2"><a onclick="javascript:orderDetail(this)">${order.p_name}</a>
	            <input type="hidden" name="p_no" value="${order.p_no}">
	            <input type="hidden" name="o_no" value="${order.o_no}">
	            <input type="hidden" name="mem_id" value="${order.mem_id}">
            </td>
            <td class="acl3">${order.m_name}(${order.mem_id})</td>
            <td class="acl4 price">${order.quantity}</td>
            <td class="acl5 price">${order.price}</td>
            <td class="acl6 price">${order.amount}</td>
            <td class="acl7">${order.o_regdate}</td>
            <td class="acl8" >
              <select name="state" id="state${i.count}">
               <option value="1">결재중</option>
               <option value="2">배송준비</option>
               <option value="3">배송중</option>
               <option value="4">배송완료</option>
               <option value="5">구매확정</option>
              </select>  
              <script type="text/javascript">
               $(function(){
            	   //배송관련 상태
            	   $("#state"+'${i.count}').val('${order.state}')
               })
              </script>
            </td>
          </tr>
        </c:forEach>
     </c:when>   
     <c:when test="${fn:length(orderList)==0}">
       <tr style="text-align:center;height: 30px;">
         <th colspan="9"> 등록된 주문이 없습니다.</th></tr>
     </c:when>   
   </c:choose>
   <tr style="text-align:right;height: 50px;">
    <th colspan="9"
     style="text-align:right;border: 1px solid #ffffff;">
     <input name="s" type="button"  class="selectBtn"
      value="상태수정">
     </th>
     </tr>
 </table>
</div>
 <form action="orDetailMgt" method="post" name="orform">
    <input type="hidden" name="p_no" value="${order.p_no}">
    <input type="hidden" name="o_no" value="${order.o_no}">
	<input type="hidden" name="mem_id" value="${order.mem_id}">
 </form>
<form action="/orderMgt"></form>




