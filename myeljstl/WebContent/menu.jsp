<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	<ul>
		<c:set var="id" value="${sessionScope.loginInfo}"></c:set>
		<c:choose>
		<c:when test="${empty id}">
		<%--String id = (String)session.getAttribute("loginInfo"); 
			if(id == null){ // 로그인 안했을때
		--%>
		<li><a href="user/login.html" id="log">로그인</a></li><br>
		<li><a href="user/member.html" id="join">가입</a></li><br>
		</c:when>
		<%--} else{ //로그인 했을때 --%>
		<c:otherwise>
		<li><a href="logout">로그아웃</a></li><br>
		<li><a href="vieworder" >주문내역 보기</li><br>
		<%--} --%>
		</c:otherwise>
		</c:choose>
		<li><a href="ProductListServlet">상품목록</a></li><br>
		<li><a href="ViewCartServlet">장바구니보기</a><br>
	</ul>