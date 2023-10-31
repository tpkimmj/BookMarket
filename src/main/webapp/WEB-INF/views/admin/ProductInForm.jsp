<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/member.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="clear"></div>
	<div id="productmgt">
		<h2 style="height: 40px; text-align: center;margin: 10px;">상품 등록</h2>
		<form action="/productMgtProc?flag=insert" method="post" name="topForm" enctype="multipart/form-data">
			<table class="product">
				<tr>
					<th class="col1">상품명</th>
					<td class="col2">
						<input type="text" name="p_name" class="chkt" title="상품명" placeholder="상품명을 입력하세요.">
					</td>
				</tr>
				<tr>
					<th class="col1">재고수량</th>
					<td class="col2">
						<input type="text" name="stock" class="chkt" title="재고수량" placeholder="재고수량을 입력하세요.">&nbsp;개
					</td>
				</tr>
				<tr>
					<th class="col1">상품가격</th>
					<td class="col2">
						<input type="text" name="price" class="chkt inprice" title="상품가격" placeholder="단가를 입력하세요.">&nbsp;원
					</td>
				</tr>
				<tr>
					<th class="col1">상품분류</th>
					<td class="col2">
						<select name="state" title="상품분류">
							<option value="">선택하세요</option>
							<option value="소설">소설</option>
							<option value="만화">만화</option>
							<option value="시집">시집</option>
							<option value="참고서">참고서</option>
							<option value="자기계발">자기계발</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="col1">상품설명</th>
					<td class="col2">
						<input type="text" name="detail" title="싱품설명" placeholder="상품상세 설명을 입력하세요.">
					</td>
				</tr>
				<tr>
					<th class="col1">상품이미지</th>
					<td class="col2">
						<input type="file" name="image2" class="chkt" title="이미지" placeholder="이미지를 첨부하세요.">
					</td>
				</tr>
				<tr>
					<th colspan="2" class="tableBtn"><input class="submit1" type="button" value="상품등록전송"></th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>