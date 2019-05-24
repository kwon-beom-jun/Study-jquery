<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.member.model.MemberDetailDto"%>
<%@ include file="/template/header.jsp" %>
<strong> </strong>님 회원가입을 환영합니다.<br>
로그인 후 모든 서비스를 이용할 수 있습니다.<br>
가입하신 정보는 다음과 같습니다.
이메일 : <br>
<!-- 객체를 담아오는것이므로 한글이 깨지지 않는다. -->
아이디 : <br>
전화번호 : <br>
주소 : <br>
<a href="<%=root%>/user/login.jsp">로그인</a>
<br>
<%@ include file="/template/footer.jsp" %>