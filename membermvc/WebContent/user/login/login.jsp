<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp" %>

<%
// 쿠키는 여러개 사용 할 수 있어서 배열로 사용함.
Cookie cookie[] = request.getCookies();

String svid = "";
String ckid = "";

if (cookie != null){
	for(Cookie c : cookie){ // 헷갈리면 쿠키의 length만큼 포문 돌리면 됨.
		if("kid_inf".equals(c.getName())){
			svid = c.getValue();
			ckid = " checked";
			break;
		}
	}
}

%>

<script type="text/javascript">
$(document).ready(function() {

});

function login() {
	if(document.getElementById("id").value == "") {
		alert("아이디 입력!!!!");
		return;
	} else if(document.getElementById("pass").value == "") {
		alert("비밀번호 입력!!!!");
		return;
	} else {
		document.getElementById("loginform").action = "<%=root%>/user"; //?act= controller로 가기위해선 act값을 무조건 보내줘야뎀
		document.getElementById("loginform").submit();
	}
}

function mvjoin() {
	document.location.href = "<%=root%>/user?act=mvjoin";
}
</script>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>로그인</h2>
		<form id="loginform" method="post" action="">
			<input type="hidden" name="act" value="login">
			<div class="form-group" align="right">
				<label for=""><input type="checkbox" class="form-control" name = "idsave" value="idsave" <%=ckid%> >아이디</label>
			</div>
			<div class="form-group" align="left">
				<label for="" value = "<%=svid%>">아이디</label>
				<input type="text" class="form-control" id="id" name="id" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="pass" name="pass" placeholder="">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-warning" id="loginBtn" onclick="javascript:login();">로그인</button>
				<button type="button" class="btn btn-primary" id="moveRegisterBtn" onclick="javascript:mvjoin();">회원가입</button>
			</div>
		</form>
	</div>
</div>

<%@ include file="/template/footer.jsp" %>