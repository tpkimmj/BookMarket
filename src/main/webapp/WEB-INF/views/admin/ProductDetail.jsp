<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="UTF-8">
<title>상품상세페이지</title>
<link rel="stylesheet" type="text/css" href="/css/product.css">
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/product.js"></script>
</head>
<body>
<div class="clear"></div>
	<div id="productDetail">
		<table>
			<tr>
				<td class="title" colspan="2">상 품 정 보</td>
			</tr>
			<tr id="detailImg">
				<td>
					<a href="/upload/${product.image}">
						<img src="/upload/${product.image}" height="360px" width="250px">
					</a>
				</td>
			</tr>
			<tr>
				<td>
				<form action="" name="productForm" method="post">
					<table>
					<tbody class="border1">
						<tr>
							<th>상품번호</th>
							<td>${product.p_no}</td>
						</tr>
						<tr>
							<th>상품명</th>
							<td>${product.p_name}
								<input type="hidden" value="${product.p_name}" name="p_name">
								<input type="hidden" value="${product.price}" name="price">
							</td>
						</tr>
						<tr>
							<th>가 격</th>
							<td class="price">${product.price}</td>
						</tr>
						<tr>
							<th>상품분류</th>
							<td>${product.state}</td>
						</tr>
						<tr id="dcol1">
							<th>상품설명</th>
							<td>${product.detail}</td>
						</tr>
						<tr>
							<th>상품등록일</th>
							<td>${product.pr_date}</td>
						</tr>
						<tr>
							<th>재고</th>
							<td>${product.stock}</td>
						</tr>
					</tbody>
						<tr>
							<td colspan="2" class="proButton">
								<input type="button" class="productUp" value="상품수정">&nbsp;&nbsp;
								<input type="button" class="productDel" value="상품삭제">&nbsp;&nbsp;
								<input type="button" onclick="javascript:location.href='productMgt'" value="상품목록">
								<input type="hidden" name="p_no" value="${product.p_no}">
							</td>
						</tr>
					</table>
				</form>
				</td>
			</tr>
		</table>
	</div>
</body>
