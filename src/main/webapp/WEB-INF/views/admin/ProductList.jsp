<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<div id="ProList">
		<table>
			<tr id="sort">
				<td colspan="3">
					<a href="/productList?state=${state}&flag=low">낮은가격순</a>&nbsp;&nbsp;&nbsp;
					<a href="/productList?state=${state}&flag=high">높은가격순</a>
				</td>
			</tr> 
			<c:choose>
				<c:when test="${fn:length(productList)>0}">
					<c:forEach var="product" items="${productList}" varStatus="status">
						<form name="productForm" method="post">
							<tr id="proLine">
								<td id="ProCol1"><a href="/productDetail?p_no=${product.p_no}"><img src="/upload/${product.image}"></a></td>
								<td id="ProCol2">
									<input id="bkpno" type="hidden" value="${product.p_no}"  name="p_no"/>
									<input id="bkp_stock" type="hidden" value="${product.stock}"  name="stock"/>
									<input id="bkname" type="hidden" value="${product.p_name}"  name="p_name" />
									<input id="bk_price" type="hidden" value="${product.price}"  name="price"/>
									<input id="bk_quantity" type="hidden" value="1" name="quantity"/>
									<ul>
										<li id="bookname"><a href="/productDetail?p_no=${product.p_no}">${product.p_name}</a></li>
										<li id="bookwriter">${product.writer}<br></li>
										<li id="bookprice" class="price">판매가 : ${product.price}원</li>
										<!-- <li id="booknum1" class="hidden">1개</li> -->								
									</ul>
								</td>
								<td id="ProCol3">
								</td>
							</tr>
						</form>
					</c:forEach>
					<tfoot>
						<tr id="page">
							<td colspan="7">
							<c:if test="${pageDto.startPg>pBlock}">
								<a href="productList?state=${state}&curPage=${pageDto.startPg-pBlock}&curBlock=${pageDto.curBlock-1}">[이전]
								</a>
							</c:if>
							<c:forEach begin="${pageDto.startPg}" end="${pageDto.endPg}" var="p" step="1">
								<a href="productList?state=${state}&curPage=${p}&curBlock=${pageDto.curBlock}">
									<span><c:out value="${p}"/></span>
									</a>&nbsp;&nbsp;
							</c:forEach>
							<c:if test="${pageDto.endPg<pageDto.pgCnt}">
								<a href="productList?state=${state}&curPage=${pageDto.startPg+pBlock}&curBlock=${pageDto.curBlock+1}">[다음]
								</a>
							</c:if>
							</td>
						</tr>
					</tfoot>
				</c:when>
			</c:choose>
		</table>
	</div>
</body>
</html>