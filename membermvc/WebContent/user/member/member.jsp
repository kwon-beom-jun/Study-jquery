<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp" %>

<script type="text/javascript" src="<%=root%>/js/httpRequest.js"></script>

<script type="text/javascript">


$(document).ready(function() {
	 
	$('#zipcode').focusin(function() {
		$('#zipModal').modal();
	});

});

var resultView;
var idcount = 1;

// 누를때마다 이벤트 생성하기 키보드 누를때마다 중복확인.  var는 자바스크립트에서 모든 변수로 사용 가능한 변수.
function idcheck() {
	
	resultView = document.getElementById("idresult");
	var searchId = document.getElementById("id").value;
	console.log(searchId); //콘솔에서 확인
	
	if (searchId.length < 5 || searchId.length > 16) {
		resultView.innerHTML = '<font color = "gray">아이디는 5자 이상 16자리 이하입니다.</font>';
	} else {
		var params = "act=idcheck&sid=" + searchId;
		sendRequest("<%=root%>/user", params, idcheckResult, "GET"); // 함수호출 3번째 collback은 갔다가 돌아올대 함수.
		resultView.innerHTML = ''
	}
	
}

function idcheckResult() {
	if (httpRequest.readyState == 4) { // 데이터 정상으로 넘어왔다. 하지만 이상한 데이터(에러)로 넘어올 수 있음.
		if (httpRequest.status == 200) {
			//정상 처리
			var result = httpRequest.responseXML;
//			alert(result);
			idcount = parseInt(result.getElementsByTagName("cnt")[0].firstChild.data); 
			// <cnt>0</cnt> 전체를 얻어온거임. 그래서 .firstChild써줌 엘리먼트 안에 엘리먼트가 들어올 수 있음 그래서 .data를 써야뎀.
			// parseInt 자바 스크립트 것임. 자바 아님.
			
			if (idcount == 0) {
				resultView.innerHTML = '<font color = "steelblue">사용 가능합니다.</font>';
			} else {
				resultView.innerHTML = '<font color = "magenta">사용중입니다. 다른 아이디를 써주세요.</font>';
			}
			
		} else {
			//에러 처리
		}
	} else {
		//로딩중 아이디 중복검사는 안함.
	}
}

function register() {
	if(document.getElementById("name").value == "") {
		alert("이름 입력!!!!");
		return;
	} else if(idcount != 0) {
		alert("아이디 중복 검사를  하세요!!!");
		return;
	} else if(document.getElementById("pass").value == "") {
		alert("비밀번호 입력!!!!");
		return;
	} else if(document.getElementById("pass").value != document.getElementById("passcheck").value) {
		alert("비밀번호 확인!!!!");
		return;
	} else {
		document.getElementById("memberform").action = "<%=root%>/user";
		//<input type="hidden" name="act" value="register"> 개발자가 안보이게 넘길때.
		document.getElementById("memberform").submit();
	}
}
</script>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>회원가입</h2>
		<form id="memberform" method="post" action="">
			<input type="hidden" name="act" value="register">
			<div class="form-group" align="left">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="이름입력">
			</div>
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="id" name="id" placeholder="4자이상 16자 이하" onkeyup="javascript:idcheck();">
				<div id="idresult"></div>
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="pass" name="pass" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호재입력</label>
				<input type="password" class="form-control" id="passcheck" name="passcheck" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="email">이메일</label><br>
				<div id="email" class="custom-control-inline">
				<input type="text" class="form-control" id="emailid" name="emailid" placeholder="" size="25"> @
				<select class="form-control" id="emaildomain" name="emaildomain">
					<option value="naver.com">naver.com</option>
					<option value="google.com">google.com</option>
					<option value="daum.net">daum.net</option>
					<option value="nate.com">nate.com</option>
					<option value="hanmail.net">hanmail.net</option>
				</select>
				</div>
			</div>
			<div class="form-group" align="left">
				<label for="tel">전화번호</label>
				<div id="tel" class="custom-control-inline">
				<select class="form-control" id="tel1" name="tel1">
					<option value="010">010</option>
					<option value="02">02</option>
					<option value="031">031</option>
					<option value="032">032</option>
					<option value="041">041</option>
					<option value="051">051</option>
					<option value="061">061</option>
				</select> _
				<input type="text" class="form-control" id="tel2" name="tel2" placeholder="1234"> _
				<input type="text" class="form-control" id="tel3" name="tel3" placeholder="5678">
				</div>
			</div>
			<div class="form-group" align="left">
				<label for="">주소</label><br>
				<div id="addressdiv" class="custom-control-inline">
					<input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" size="7" maxlength="5" readonly="readonly">
					<!--<button type="button" class="btn btn-primary" onclick="javascript:">우편번호</button>-->
				</div>
				<input type="text" class="form-control" id="address" name="address" placeholder="" readonly="readonly">
				<input type="text" class="form-control" id="address_detail" name="address_detail" placeholder="">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="registerBtn" onclick="javascript:register();">회원가입</button>
				<button type="reset" class="btn btn-warning">초기화</button>
			</div>
		</form>
	</div>
</div>
<%@ include file="/user/member/zipsearch.jsp" %>
<%@ include file="/template/footer.jsp" %>