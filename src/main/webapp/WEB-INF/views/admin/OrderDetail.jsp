<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<meta charset="UTF-8">
<title>주문상세</title>
<link rel="stylesheet" type="text/css" href="/css/product.css">
</head>
<script src="/script/product.js"></script>
<body>
<div class="clear"></div>
<div id="customorder"></div>
<div class="head">
		<p style="text-align: center; font-size: 30px;">${odto.p_name}(${odto.o_no}) 상세주문내역</p>
	</div>
	<form action="updateOrder" name="topForm1" id="" method="post">
		<table class="order">
			<tr>
				<th>주문번호</th>
					<td>${odto.o_no}
						<input type="hidden" name="o_no" value="${odto.o_no}">
						<input type="hidden" name="p_no" value="${odto.p_no}">
						<input type="hidden" name="mem_id" value="${odto.mem_id}">
					</td>
			</tr>
			<tr>
				<th>상품명</th>
					<td>${odto.p_name}</td>
			</tr>
			<tr>
				<th>단가</th>
					<td>${odto.price}</td>
			</tr>
			<tr>
				<th>구매수량</th>
					<td>${odto.quantity}</td>
			</tr>
			<tr>
				<th>결재금액</th>
					<td>${odto.amount}</td>
			</tr>
			<tr>
				<th>고객정보</th>
					<td>${odto.m_name}(${odto.mem_id})</td>
			</tr>
			<tr>
				<th>주문일</th>
					<td>${odto.o_regdate}</td>
			</tr>
			<tr>
				<th>배송상태</th>
				<td>
					<select name="state" id="state">
						<option value="1">결재중</option>
						<option value="2">배송준비</option>
						<option value="3">배송중</option>
						<option value="4">배송완료</option>
						<option value="5">구매확정</option>
					</select>
					<script type="text/javascript">
						//배송관련 상태
						$("#state").val('${odto.state}')
					</script>
				</td>
			</tr>
			<tr>
				<th colspan="2" style="text-align: right;" class="tableBtn">
					<input type="button" class="submit1" value="상태수정">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="/orderMgt"><input type="button" value="구매목록으로"></a>
				</th>
			</tr>
		</table>
	</form>
</div>
</body>