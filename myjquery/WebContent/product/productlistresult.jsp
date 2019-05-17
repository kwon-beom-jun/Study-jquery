<%@page import="com.kitri.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="UTF-8">
<title>semantic.html</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 -->
<script>



$(function() {
	//dom트리에서 nav>ul>li>a객체들 찾기. 한두개가 아니니 배열 형태로 받아오기.
	var aArr = $("li>dt>a");
	$(aArr).click(function() { // for문 안돌리고도 모두 가능함.
		var vurl = $(this).attr("href");
			$.ajax({ // 객체를 인자러 써줌
				url: vurl, // url 프로퍼티
				method:'get', // method프로퍼티
				success:function(result){
					$("section").html(result);
					//location.href="semantic.jsp";
				}
			});
		return false;
	});
});

</script>

<%!Product p;%>
<% List<Product> list = (List)request.getAttribute("productlist");
for (int i = 0; i < list.size(); i++) {    
	p = list.get(i);
%>
  	 <li class="menuDataSet" new="Y" sell="" recomm="0" sold="N">
	  <dt>
        <a href="productinfo?no=<%=p.getProd_no()%>">
        	<img src="/myjquery/img/<%=p.getProd_no()%>.jpg" alt="<%=p.getProd_name()%>">
        </a>
      </dt>
      <dl>
        <dd>카테고리:<%=p.getProductCategory().getCate_name() %></dd>
        <dd>상품번호:<%=p.getProd_no() %></dd>
        <dd>상품명:<%=p.getProd_name() %></dd>
        <dd>가격:<%=p.getProd_price() %></dd>
      </dl> 
    </li>
	
<%}//end for%>
	
