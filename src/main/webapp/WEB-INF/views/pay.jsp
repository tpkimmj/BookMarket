<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<body>

<div class="container">
    <h3>메세지 : ${payResult.responseMsg }</h3>
    <h3>코드 : ${payResult.responseCode }</h3>
    <h3>시간 : ${timestamp}</h3>
    <h3>${test}</h3>
</div>

</body>
</html>