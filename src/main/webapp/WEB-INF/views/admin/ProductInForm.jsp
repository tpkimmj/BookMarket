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
		<form action="/productMgtProc?flag=insert" method="post" name="topForm" enctype="multipart/form-data">
			<table>
				<thead style="margin-bottom: 20px;">
					<tr>
						<td class="title" colspan="2">상 품 등 록</td>
					</tr>
				</thead>
				<tbody class="border">
					<tr>
						<th>상품명</th>
						<td>
							<input type="text" name="p_name" class="chkt" title="상품명" placeholder="상품명을 입력하세요.">
						</td>
					</tr>
					<tr>
						<th>재고수량</th>
						<td>
							<input type="text" name="stock" class="chkt" title="재고수량" placeholder="재고수량을 입력하세요.">
						</td>
					</tr>
					<tr>
						<th>상품가격</th>
						<td >
							<input type="text" name="price" class="chkt inprice" title="상품가격" placeholder="단가를 입력하세요.">
						</td>
					</tr>
					<tr>
						<th>상품분류</th>
						<td>
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
						<th>상품설명</th>
						<td>
							<input type="text" name="detail" title="싱품설명" placeholder="상품상세 설명을 입력하세요.">
						</td>
					</tr>
					<tr>
						<th>상품이미지</th>
						<td>
							<input type="file" name="image2" class="chkt" title="이미지" placeholder="이미지를 첨부하세요.">
						</td>
					</tr>
				</tbody>
					<tr>
						<td colspan="2" class="proButton"><input class="submit1" type="button" value="상품등록전송"></td>
					</tr>
			</table>
		</form>
	</div>
</body>
</html>