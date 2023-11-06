<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet" type="text/css" href="/css/product.css">
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/product.js"></script>
</head>
<div id="productMgt">
<table>
		<thead>
		<tr>
	   		<td class="title">상 품 목 록</td>
		</tr>
		<tr>
	   		<td id="productCnt" colspan="6">총 상품수 : ${totCnt} 가지</td>
		</tr>
	</thead>
	</table>
	<table id="productList">
		<tr class="border">
			<th>번호</th>
			<th>책제목</th>
			<th>저자</th>
			<th>가격</th>
			<th>분류</th>
			<th>등록일</th>
			<th>재 고</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(pList)>0}">
				<c:forEach var="product" items="${pList}">
					<tr class="border">
						<td class="pco1">${product.p_no}</td>
						<td class="pco2"><a href="/productDetail?p_no=${product.p_no}">${product.p_name}</a></td>
						<td class="pco3">${product.writer}</td>
						<td class="pco4 price">${product.price}</td>
						<td class="pco5">${product.state}</td>
						<td class="pco6">${product.pr_date}</td>
						<td class="pco7">${product.stock}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${fn:length(pList)==0}">
				<tr><th colspan="7">등록된 상품이 없습니다.</th></tr>
			</c:when>
		</c:choose>
		<tfoot>
		<tr id="page">
			<td colspan="7">
			<c:if test="${pageDto.startPg>pBlock}">
				<a href="productMgt?curPage=${pageDto.startPg-pBlock}&curBlock=${pageDto.curBlock-1}">[이전]
				</a>
			</c:if>
			<c:forEach begin="${pageDto.startPg}" end="${pageDto.endPg}" var="p" step="1">
				<a href="productMgt?curPage=${p}&curBlock=${pageDto.curBlock}">
					<span><c:out value="${p}"/></span>
					</a>&nbsp;&nbsp;
			</c:forEach>
			<c:if test="${pageDto.endPg<pageDto.pgCnt}">
				<a href="productMgt?curPage=${pageDto.startPg+pBlock}&curBlock=${pageDto.curBlock+1}">[다음]
				</a>
			</c:if>
			</td>
		</tr>
		<tr>
			<th class="proButton" colspan="7">
				<button onclick="location.href='/productInForm'">상품등록</button>
			</th>
		</tr>
	</tfoot>
	</table>
</div>
