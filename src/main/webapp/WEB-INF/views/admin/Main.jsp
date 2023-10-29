<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<!DOCTYPE html>
<html>
<head>                                
<meta charset="UTF-8">
<title>BookMarket</title>
</head>
<body>
<c:import url="./Top.jsp"></c:import>
<div class="clear"></div>
<div id="contents">
	<c:import url="./${contentsJsp}.jsp"></c:import>
</div>
<c:import url="./Bottom.jsp"></c:import>
</body>
</html>