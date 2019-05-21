<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
	<ul>
		<%String id = (String)session.getAttribute("loginInfo");
			if(id == null){ // 로그인 안했을때
		%>
		<li><a href="user/login.html" id="log">로그인</a></li><br>
		<li><a href="user/member.html" id="join">가입</a></li><br>
		<%} else{ //로그인 했을때
		%>
		<li><a href="logout">로그아웃</a></li><br>
		<li><a href="vieworder" >주문내역 보기</li><br>
		<%} %>
		<li><a href="ProductListServlet">상품목록</a></li><br>
		<li><a href="ViewCartServlet">장바구니보기</a><br>


	</ul>