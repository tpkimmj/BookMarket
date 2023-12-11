<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<body>
<c:import url="./custom/Top.jsp"></c:import>
<div class="clear"></div>
<link type="text/css" rel="stylesheet" href="/css/payment.css">
<div id="order">
   <div id="orderDone">
      <div class="headDiv">
         BookMarket을 이용해 주셔서 감사합니다.
      </div>
      <div class="Completion">
         <span>
            고객님, <b>결제가 완료</b>되었습니다.
         </span>
      </div>
      <div class="orderNotice">
         주문내역 확인은 <br>
         주문목록"에서 하실 수 있습니다.
      </div>
      <div class="orderContact">
         기타 궁금하신 점은 문의하기를 통해<br>
         문의해주시길 바랍니다.
      </div>
   </div>
   <div id="return">
      <a href="/" class="mainHome">메인으로</a>
   </div>

<c:import url="./custom/Bottom.jsp"></c:import>
</body>
</html>