<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div id="bookDetail">
		<table>
			<tr id="bkName">
				<th>${product.p_name}</th>
			</tr>
			<tr id="bkName1">
				<td>저자 : ${product.writer}</td>
				<td>상품 등록일 : ${product.pr_date}</td>
			</tr>
			<tbody>
				<tr id="bkcon">
					<td id="bkcon1"><img src="/upload/${product.image}"></td>
					<td id="bkcon2">
						<ul>
							<li>카테고리 : ${product.state}</li><br>
							<li>상 품 명 : ${product.p_name}</li><br>
							<li>저 자 : ${product.writer}</li><br>
							<li class="price">가 격 : ${product.price}</li><br>
							<li>재 고 : ${product.stock}</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td id="bkDe">책소개</td>
				</tr>
				<tr id="bkDe1">
					<td colspan="2">${product.detail}</td>
				</tr>
			</tbody>
		</table>
	</div>
		<div id="bookBottom">
			<table>
				<tr>
					<td id="bookCol1">
						<table>
							<tr>
								<td id="bookNum1">수량</td>
								<td><a onclick='count("minus")'><img alt="" src="/images/mbtn.png"></a></td>
								<td id="bookNum2"><div id='result'>1</div></td>
								<td><a onclick='count("plus")'><img alt="" src="/images/pbtn.png"></a></td>
							</tr>
						</table>
						</td>
					<td id="bookCol2">결제예정금액&nbsp; <span class="price">${product.price}</span> 원</td>
					<td id="bookCol3">
						<input id="bookBtn1" type="button" value="장바구니">
						<input id="bookBtn2" type="button" value="바로구매">
					</td>
				</tr>
			</table>
		</div>
</body>
</html>