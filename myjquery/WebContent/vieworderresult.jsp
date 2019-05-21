<%@page import="com.kitri.dto.Product"%>
<%@page import="com.kitri.dto.OrderLine"%>
<%@page import="java.util.Date"%>
<%@page import="com.kitri.dto.OrderInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<style>
div.vieworder>table, div.vieworder>table th, div.vieworder>table td{
border:1px solid; border-collapse: collapse;
}
</style>
<div class="vieworder" >
<h4 sytel="text-align : center;">주문내역 보기</h4> 
 <table style="width : 80%; margin : 0 auto;"><!-- 중앙에 오기 위한 Tip -->
   <tr><th>주문번호</th><th>주문일자</th>
       <th>주문상품번호</th><th>상품명</th><th>가격</th><th>주문수량</th></tr>
<% 
	List<OrderInfo> list = (List)request.getAttribute("orderlist");
    for(OrderInfo info: list){
%>  <tr>   	   
<%	 int order_no = info.getOrder_no();//주문번호
	 Date order_dt = info.getOrder_dt();//주문일자
	 List<OrderLine> lines = info.getLines();
%>   <td rowspan="<%=lines.size() %>"><%=order_no%></td>
     <td rowspan="<%=lines.size() %>"><%=order_dt%></td>
     
<%	 
	 //for(OrderLine line: lines){
	for(int i=0; i<lines.size(); i++){
		OrderLine line = lines.get(i);
		Product p = line.getProduct();
		String prod_no = p.getProd_no();
		String prod_name = p.getProd_name();
		int prod_price = p.getProd_price();
		int order_quantity = line.getOrder_quantity();
%>    
       <%=i>0?"</tr><tr>":""%>
       <td><%=prod_no %></td><td><%=prod_name %></td><td><%=prod_price %></td><td><%=order_quantity %></td>
       
<%	
	 }//end line
%>	
   </tr> 
<% }//end info
%>
 </table>
</div>


