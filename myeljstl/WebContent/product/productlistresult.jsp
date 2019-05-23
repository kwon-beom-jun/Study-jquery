<%@page import="com.kitri.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<meta charset="UTF-8">
<title>semantic.html</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> -->
<script>



$(function() {
	//dom트리에서 nav>ul>li>a객체들 찾기. 한두개가 아니니 배열 형태로 받아오기.
	var aArr = $("dt>a");
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

<%--
<%!Product p;%>
<% List<Product> list = (List)request.getAttribute("productlist");
for (int i = 0; i < list.size(); i++) {    
	p = list.get(i);
%>
  	 <li class="menuDataSet" new="Y" sell="" recomm="0" sold="N">
	  <dt>
        <a href="productinfo?no=<%=p.getProd_no()%>">
        	<img src="/myeljstl/img/<%=p.getProd_no()%>.jpg" alt="<%=p.getProd_name()%>">
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
 --%>



<div class="product_list">
<ul>
<c:set var="list" value="${requestScope.productlist}"/>
<c:forEach var="p" items="${list}">
  
    <li class="menu">
       <dl>
        <dt>
          <a href="productinfo?no=${p.getProd_no()}">
            <img src="/myeljstl/img/${p.getProd_no()}.jpg" alt="${p.getProd_name()}">
          </a>
        </dt>
        <dd>카테고리:<span>${p.getProductCategory().getCate_name()}</span></dd>
        <dd>상품번호:<span>${p.getProd_no()}</span></dd>
        <dd>상품명:<span>${p.getProd_name()}</span></dd>
        <dd>가격:<span>${p.getProd_price()}</span></dd>
       </dl>
    </li>
</c:forEach>
</ul>    
</div>













