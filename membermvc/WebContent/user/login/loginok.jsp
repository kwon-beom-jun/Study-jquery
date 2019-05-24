<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.member.model.MemberDto,com.kitri.util.MoveUrl"%>
<%@ include file="/template/header.jsp" %>


<% // 인클로드로 사용하면 사용가능!!
//MemberDto memberDto = (MemberDto)request.getAttribute("userInfo"); 
// 다른곳을 갔다오면 컨트롤로 안갔다와서 null로 됨. 컨트롤에서 바꿔줘야함.(로그인메소드)
MemberDto memberDto = (MemberDto)session.getAttribute("userInfo"); // 다른 사이트를 갔다와도 유지가 됨.
if(memberDto != null){
%>
<script>
	function deleteMember() {
		if (confirm("정말로 탈퇴??")) {
			document.location.href = "<%=root%>/user?act=deletemember";
		}
	}
</script>

<strong><%=memberDto.getName()%>(<%=memberDto.getId()%>)</strong>님 안녕하세요.<br>
<a href = "<%=root%>/user?act=logout" >로그아웃</a>
<a href = "<%=root%>/admin?act=mvmodify" >정보 수정</a>
<a href = "#" onclick = "javascript:deletMember();">회원탈퇴</a>
<%
	if("asdasd".equals(memberDto.getId())){

%>
<a href = "<%=root%>/admin?act=memberlist" >관리자</a>
<%
	}
} else {
	MoveUrl.redirect(request,response,"/user?act=mvlogin");
}
%>
<%@ include file="/template/footer.jsp" %>















