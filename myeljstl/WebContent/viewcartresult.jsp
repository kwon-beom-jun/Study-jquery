<%@page import="java.util.Set"%>
<%@page import="com.kitri.dto.Product"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
div.viewcartresult{
    text-align: center;
}
div.viewcartresult>h3{
    font-size: 24px;
    font-weight: bold;
    color: #222;
    
    padding-bottom: 18px;
    margin-bottom: 20px;
}
div.viewcartresult>table{
	width: 80%;
	margin: 5px;
}
div.viewcartresult>table, div.viewcartresult>table th, div.viewcartresult>table td{    
	border : 1px solid #222;
	border-collapse: collapse;
	text-align: left;
}
</style>
<script>
$(function(){
	var $btRemoveCart = $("div.viewcartresult>table tr>td>button.removecart");
	$btRemoveCart.click(function(){
		alert("장바구니비우기 클릭!");
		/* $.ajax({
			url:"removecart",
			method:"get",
			success:function(result){
				
			}
		}); */
		return false;
	});
	var $btAddOrder = $("div.viewcartresult>table tr>td>button.vieworder");
	$btAddOrder.click(function(){

		alert("주문하기 클릭!");
		$.ajax({
			url:"addorder",
			method:"get",
				success:function(result){
				if (result.trim() == '-1') {
					alert("주문실패");
				} else if (result.trim() == '1') {
					alert("주문성공");
					$("nav>ul>li>a[href=vieworder]").trigger("click");
					//location.href="vieworder";
					return false;
				}	
			}
		});
		return false;
	});
});
</script>
<div class="viewcartresult">
<h3>장바구니 내용</h3>


<c:set var = "rc" value="${requestScope.rcart}"/>

 <table>
   <tr>
     <th>상품번호</th><th>상품명</th><th>상품가격</th><th>수량</th>     
   </tr>
   <c:forEach var="e" items="${rc}">
     <c:set var="p" value="${e.key}"/>
     <c:set var="quantity" value="${e.value }"/>
   
  
   <tr>
     <td>${p.prod_no}</td>
     <td>${p.prod_name}</td>
     <td>${p.prod_price}</td>
     <td>${quantity}</td>
   </tr>
   </c:forEach>
   
   <tr>
     <td colspan="4" style="text-align:center;">
       <button style="margin:10px;" class="removecart">장바구니 비우기</button>

<c:if test="${!empty sessionScope.loginInfo}">
       <button style="margin:10px;"class="vieworder">주문하기</button>
</c:if>
     </td>
   </tr>
 
   
 </table>
</div>