<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp" %>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>로그인</h2>
		<form id="loginform" method="post" action="">
			<input type="hidden" name="act" value="login">
			<div class="form-group" align="right">
				<label for=""><input type="checkbox" class="form-control" name = "idsave" value="idsave" >아이디</label>
			</div>
			<div class="form-group" align="left">
				<label for="" value = "">아이디</label>
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