<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.member.model.MemberDto,com.kitri.util.MoveUrl"%>
<%@ include file="/template/header.jsp" %>


<strong></strong>님 안녕하세요.<br>
<a herf = "<%=root%>/user?act=logout" >로그아웃</a>
<a herf = "<%=root%>/admin?act=mvmodify" >정보 수정</a>
<a herf = "#" onclick = "javascript:deletMember();">회원탈퇴</a>
<a herf = "<%=root%>/admin?act=memberlist" >관리자</a>

<%@ include file="/template/footer.jsp" %>















