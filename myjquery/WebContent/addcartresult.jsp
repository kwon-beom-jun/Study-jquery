<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kitri.dto.Product" %>

<%
Product p = (Product)request.getAttribute("chartproduct");


%>
    <script>
    $(function () {
		var arr = $("div.addcartresult>button");
		$(arr[0]).click(function () {
			//메뉴중 상품목록 메뉴 찾기
			alert("상품목록으로 가기를 클릭했습니다.");
			//강제 클릭이벤트 발생시키기.
			$("nav>ul>li>a[href=ProductListServlet]").trigger("click");
//			$("nav>ul>li>a[href=logout]").trigger("click");
			return false;
		});
		$(arr[1]).click(function () {
			alert("장바구니 보기를 클릭하셨습니다..");
			//메뉴중 상품목록 메뉴 찾기
			//강제 클릭이벤트 발생시키기.
			$("nav>ul>li>a[href=ProductListServlet]").trigger("click");
			return false;
		});
	});
    </script>
<div class="addcartresult" 
	 style="position:absolute; top: 200px;left: 300px;width: 350px; border: 1;">
	
	<button>상품 목록가기</button>
	<button>장바구니 보기</button>
</div>