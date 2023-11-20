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
					<a href="/search?searchText=${searchText}&flag=low">낮은가격순</a>&nbsp;&nbsp;&nbsp;
					<a href="/search?searchText=${searchText}&flag=high">높은가격순</a>
				</td>
			</tr> 
			<c:choose>
				<c:when test="${fn:length(productList)>0}">
					<c:forEach var="product" items="${productList}">
						<tr id="ProLine">
							<td id="ProCol1"><a href="/productDetail?p_no=${product.p_no}"><img src="/upload/${product.image}"></a></td>
							<td id="ProCol2">
								<ul>
									<li id="bookname"><a href="/productDetail?p_no=${product.p_no}">${product.p_name}</a>
									</li>
									<li id="bookwriter">${product.writer}</li><br>
									<li id="bookprice" class="price">판매가 : ${product.price}원</li>
								</ul>
							</td>
							<td id="ProCol3">
							</td>
						</tr>
						<tr></tr>
						<tr></tr>
						<tr></tr>
					</c:forEach>
 					<tfoot>
						<tr id="page">
							<td colspan="7">
							<c:if test="${pageDto.startPg>pBlock}">
								<a href="search?searchText=${searchText}&curPage=${pageDto.startPg-pBlock}&curBlock=${pageDto.curBlock-1}">[이전]
								</a>
							</c:if>
							<c:forEach begin="${pageDto.startPg}" end="${pageDto.endPg}" var="p" step="1">
								<a href="search?searchText=${searchText}&curPage=${p}&curBlock=${pageDto.curBlock}">
									<span><c:out value="${p}"/></span>
									</a>&nbsp;&nbsp;
							</c:forEach>
							<c:if test="${pageDto.endPg<pageDto.pgCnt}">
								<a href="search?searchText=${searchText}&curPage=${pageDto.startPg+pBlock}&curBlock=${pageDto.curBlock+1}">[다음]
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