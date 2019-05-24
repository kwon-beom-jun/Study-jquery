<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
nav>ul{ list-style: none;}
nav>ul>li{ display: inline-block; margin: 10px; }

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
$(function(){
	$("nav>ul>li>a").click(function(){
		$("section").empty();
		var url = $(this).attr("href");
		$.ajax({
			url:url,
			method:'get',
			success:function(result){
				$("section").html(result.trim());
			}
		});
		return false;
	});
});
</script>
</head>
<body>
<nav>
<ul>
  <li><a href="write.html">글쓰기</a>
  <li><a href="boardlist">글목록</a>
  <li><a href="upload.html">파일업로드</a>
</ul>
</nav>
<section>
</section>
</body>
</html>