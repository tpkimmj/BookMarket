<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/product.css">
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/member.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="clear"></div>
	<div id="productMgt">
		<form action="/productMgtProc?flag=update" method="post" name="topForm" enctype="multipart/form-data">
			<table class="product">
				<tr>
					<td class="title" colspan="2">상 품 수 정</td>
				</tr>
				<tbody class="border">
				<tr>
					<th class="col1">상품번호</th>
					<td class="col2">
						<input type="text" name="p_no" class="chkt disable" title="상품명" readonly="readonly" value="${pdto.p_no}">
					</td>
				</tr>
				<tr>
					<th class="col1">상품명</th>
					<td class="col2">
						<input type="text" name="p_name" class="chkt" title="상품명" value="${pdto.p_name}">
					</td>
				</tr>
				<tr>
					<th class="col1">재고수량</th>
					<td class="col2">
						<input type="text" name="stock" class="chkt" title="재고수량" value="${pdto.stock}">
					</td>
				</tr>
				<tr>
					<th class="col1">단가</th>
					<td class="col2">
						<input type="text" name="price" class="chkt inprice" title="상품가격" value="${pdto.price}">
					</td>
				</tr>
				<tr>
					<th class="col1">상품분류</th>
					<td class="col2">
						<select name="state" title="상품분류">
							<option value="소설" ${pdto.state=="소설" ? "selected" : "" }>소설</option>
							<option value="만화" ${pdto.state=="만화" ? "selected" : "" }>만화</option>
							<option value="시집" ${pdto.state=="시집" ? "selected" : "" }>시집</option>
							<option value="참고서" ${pdto.state=="참고서" ? "selected" : "" }>참고서</option>
							<option value="자기계발" ${pdto.state=="자기계발" ? "selected" : "" }>자기계발</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="col1">상품설명</th>
					<td class="col2">
						<input type="text" name="detail" title="싱품설명" value="${pdto.detail}">
					</td>
				</tr>
				<tr>
					<th class="col1">상품이미지</th>
					<td class="col2">
						<img alt="이미지" src="upload/${pdto.image}" style="width: 150px; height: 180px;">
						<c:choose>
							<c:when test="${pdto.image==null or pdto.image==ready.gif}">
								<input type="file" name="image2" class="chkt" title="이미지" value="${pdto.image}">
							</c:when>
							<c:when test="${pdto.image!=null and pdto.image!=ready.gif}">
								<input type="file" name="image2" title="이미지" value="${pdto.image}">
							</c:when>
						</c:choose>
						<input type="hidden" name="image" value="${pdto.image}">
					</td>
				</tr>
				</tbody>
				<tr>
					<th colspan="2" class="proButton"><input class="submit1" type="button" value="상품수정전송"></th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>