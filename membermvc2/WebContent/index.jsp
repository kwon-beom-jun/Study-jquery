<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
String root = request.getContextPath();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h3>MVC패턴을 이용한 회원가입 &amp; 로그인</h3>
<a href="<%=root%>/user?act=mvjoin">회원가입</a><br>
<!-- 어디로 가는지 만들어줌 -->
<a href="<%=root%>/user?act=mvlogin">로그인</a><br>
</div>
</body>
