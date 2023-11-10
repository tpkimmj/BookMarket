<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/ClientCenter.css">   
<script src="/jquery/jquery-3.7.0.min.js"></script>
</head>
<body>
<div id="notice">
 <div class="head">
    <p id="noticeTot"><span>${totCnt}</span> 건</p>
 </div>
 <div id="list">
<table class="Anounce">
  <tr class="headcolor border">
	  <th>번호</th>
	  <th>제목</th>
	  <th>작성자</th>
	  <th>작성일시</th>
	  <th>조회수</th>
  </tr>
  <c:choose>
  	<c:when test="${fn:length(cList)>0}"> 
	     <c:forEach var="client" items="${cList}"> 
		    <tr>
		      <td class="col1">${client.cno}</td>
		      <td class="col2"><a href="noticeDetail?cno=${client.cno}">${client.subject}</a></td>
		      <td class="col3">${client.writer}</td>
		      <td class="col4">${client.regdate}</td>
		      <td class="col5">${client.readcount}</td>
		    </tr>
	     </c:forEach>
  	</c:when>
  </c:choose>
</table>
	 <div id="notiBtn"><a href="/NoticeWriteForm">공지사항 등록&nbsp;&nbsp;&nbsp;</a></div>
 </div>
 </div>
</body>
</html>