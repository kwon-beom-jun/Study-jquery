<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kitri.dto.Product" %>

<!-- 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 -->
<%System.out.println("g2");
Product p = (Product)request.getAttribute("product");
%>


<script type="text/javascript">


$(function(){
	var $bt = $("dl>dt>a");
	$bt.click(function () {
			$.ajax({
				url:'cart',
				method:'get',
				data:
					'no=<%=p.getProd_no()%>&quantity='+$("input[name=quantity]").val(),
				
				
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



<img src="/myjquery/img/<%=p.getProd_no()%>.jpg" alt="<%=p.getProd_name()%>"><br>
	디테일 : <%=p.getProd_detail() %><br>
	이름 : <%=p.getProd_name() %><br>
	번호 : <%=p.getProd_no() %><br>
	가격 : <%=p.getProd_price() %><br>
<!-- <form action="cart" method="get">  -->
	<input type="hidden" name = "no" value = "<%=p.getProd_no()%>">
	수량 : <input type="number" name="quantity" value="1" min="1" max="99">
	<dl>
		<dt>
			<a><button>장바구니넣기</button></a>
		</dt>
	</dl>  
<!-- </form> -->
