<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/product.css">
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/product.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="clear"></div>
	<div id="productInForm">
		<form action="/productMgtProc?flag=insert" method="post" name="topForm1" enctype="multipart/form-data">
			<table>
				<thead style="margin-bottom: 20px;">
					<tr>
						<td class="title" colspan="2">상 품 등 록</td>
					</tr>
				</thead>
				<tbody class="border2">
					<tr>
						<th>책제목</th>
						<td>
							<input type="text" name="p_name" class="chkt" title="책제목" placeholder="책제목을 입력하세요.">
						</td>
					</tr>
					<tr>
						<th>저자</th>
						<td>
							<input type="text" name="writer" class="chkt" title="저자" placeholder="저자명을 입력하세요.">
						</td>
					</tr>
					<tr>
						<th>재고</th>
						<td>
							<input type="text" name="stock" class="chkt" title="재고수량" placeholder="재고수량을 입력하세요.">
						</td>
					</tr>
					<tr>
						<th>가격</th>
						<td >
							<input type="text" name="price" class="chkt" title="상품가격" placeholder="단가를 입력하세요.">
						</td>
					</tr>
					<tr>
						<th>분류</th>
						<td>
							<select name="state" title="상품분류">
								<option value="소설">소설</option>
								<option value="만화">만화</option>
								<option value="시집">시집</option>
								<option value="참고서">참고서</option>
								<option value="자기계발">자기계발</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>설명</th>
						<td>
							<textarea cols="70" rows="20" name="detail" title="상품설명"></textarea>
						</td>
					</tr>
					<tr>
						<th>이미지</th>
						<td>
							<input type="file" name="image2" class="chkt" title="이미지" placeholder="이미지를 첨부하세요.">
						</td>
					</tr>
				</tbody>
					<tr>
						<td colspan="2" class="proButton"><input class="submitProc" type="button" value="상품등록전송"></td>
					</tr>
			</table>
		</form>
	</div>
</body>
</html>