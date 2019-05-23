<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kitri.dto.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 -->
 
<%--
Product p = (Product)request.getAttribute("product");
--%>

<c:set var="p" value="${requestScope.product}"></c:set>
<c:set var="detail" value="${p.prod_detail}"></c:set>
<c:set var="name" value="${p.prod_name}"></c:set>
<c:set var="no" value="${p.prod_no}"></c:set>
<c:set var="price" value="${p.prod_no}"></c:set>

<script type="text/javascript">


$(function(){
	var $bt = $("dl>dt>a");
	$bt.click(function () {
			$.ajax({
				url:'cart',
				method:'get',
				data:
					'no=${no}&quantity='+$("input[name=quantity]").val(),
				
				
				success:function(result){ 
					// 완료되면 제거하라는 뜻.
					$("div.addcartresult").remove();
					//$("section").html(result.trim());
					$("section").append(result.trim());
				}
				
			});
			return false;
		});
	});

</script>



<img src="/myeljstl/img/${no}.jpg" alt="${name}"><br>
	이름 : ${name}<br>
	번호 : ${no}<br>
	가격 : ${price}<br>
	디테일 : ${detail}<br>
<!-- <form action="cart" method="get">  -->
	<input type="hidden" name = "no" value = "${p.prod_no}">
	수량 : <input type="number" name="quantity" value="1" min="1" max="99">
	<dl>
		<dt>
			<a><button>장바구니넣기</button></a>
		</dt>
	</dl>  
<!-- </form> -->
