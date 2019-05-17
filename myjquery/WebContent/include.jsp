<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>포함지시자</h3>
<%@include file="loginresult.jsp" %>
<!-- 변하지 않는 똑같은 값 -->
<hr>
<h3>포함 Tag</h3>
<jsp:include page="loginresult.jsp"/>
<!-- </jsp:include> -->

</body>
</html>