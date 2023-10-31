<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet">
<title>고객센터 공지사항</title>
<script src="/jquery/jquery-3.7.0.min.js"></script>
</head>
<body>
<div id="notice">
 <div class="head">
    <p style="text-align: center; font-size: 30px;">공지사항</p>
    <p style="padding: 5px; margin-left: 950px; text-align: center; padding-right: 10px;
        font-size: 16px;">등록된 게시글이 없습니다: ${totCnt}개</p>
 </div>
 </div>
<table class="Notice">
   <tr style="height: 50px; " class="headcolor border">
    <th style="text-align: center;" class="num">번호</th>
    <th style="text-align: center;" class="title">제목</th>
    <th style="text-align: center;" class="writer">작성자</th>
    <th style="text-align: center;" class="regdate">작성일시</th>
    <th style="text-align: center;" class="pgview">조회수</th>
   </tr>
</table>
</body>
</html>