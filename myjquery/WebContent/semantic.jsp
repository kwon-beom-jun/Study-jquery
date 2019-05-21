<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semantic.html</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<script>
	$(function() {
		//dom트리에서 nav>ul>li>a객체들 찾기. 한두개가 아니니 배열 형태로 받아오기.
		var aArr = $("nav>ul>li>a");
		$(aArr).click(function() { // for문 안돌리고도 모두 가능함.
			var vurl = $(this).attr("href");
			if (vurl == 'logout') {
				$.ajax({ // 객체를 인자러 써줌
					url : vurl, // url 프로퍼티
					method : 'get', // method프로퍼티
					success : function(result) {
						//$("section").html(result);
						location.href = "semantic.jsp";
					}
				});
			} else {
				$.ajax({ // 객체를 인자러 써줌
					url : vurl, // url 프로퍼티
					method : 'get', // method프로퍼티
					success : function(result) {
						$("section").html(result);
					}
				});
			}
			return false;
		});

	});
</script>

<style type="text/css">
header {
	background-image:
		url("https://cdn.crowdpic.net/list-thumb/thumb_l_0A9AB2B109BEF6EBCCDCA144E5BB036B.jpg");
	height: 300px;
	width: 100%; <!--
	background-repeat: no-repeat;
	-->
}

nav>ul {
	list-style: none;
	<!--
	li는
	기본적으로
	padding이
	들어가
	있으므로
	지워줌
	검사.
	-->
	padding
	:
	0px;
}

nav>ul>li {
	display: inline-block;
}

footer {
	position: fixed;
	bottom: 0px;
	width: 100%;
	background-color: gray;
}
</style>



</head>
<body>
	<header>
		<h1>My Web</h1>
	</header>
	<nav>
		메뉴
		<jsp:include page="menu.jsp"></jsp:include>
	</nav>
	<section>본문</section>
	<footer>사업자 : 현대자동차(주) | 대표 : 오문정 </footer>
</body>
</html>



