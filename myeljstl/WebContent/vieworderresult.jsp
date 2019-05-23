<%@page import="com.kitri.dto.Product"%>
<%@page import="com.kitri.dto.OrderLine"%>
<%@page import="java.util.Date"%>
<%@page import="com.kitri.dto.OrderInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



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
       
       
       
       
<c:set var="list" value="${requestScope.orderlist}"/>
      
<%--
   List<OrderInfo> list = (List) request.getAttribute("orderlist");

   for(OrderInfo info : list){
--%>
<c:forEach var="info" items="${list}">
      <tr>
<c:set var="no" value="${info.order_no}"/>
<c:set var="dt" value="${info.order_dt}"/>
<c:set var="lines" value="${info.lines}"/>
<%--
      int order_no = info.getOrder_no();               //주문번호
      Date order_dt = info.getOrder_dt();               //주문일자
      List<OrderLine> lines = info.getLines();
--%>
         <td rowspan="${lines.size()}">${no}</td>
         <td rowspan="${lines.size()}">${dt}</td>

<%--      
      for(OrderLine line : lines){
         Product p = line.getProduct();
         String prod_no = p.getProd_no();            //상품번호
         String prod_name = p.getProd_name();         //상품명
         int prod_price = p.getProd_price();            //가격
         int order_quantity = line.getOrder_quantity();   //주문수량
--%>
      <c:forEach var="line" items="${lines}">
      
         <td>${line.product.prod_no}</td>
         <td>${line.product.prod_name}</td>
         <td>${line.product.prod_price}</td>
         <td>${line.order_quantity}</td>
      </tr>
      </c:forEach>
      
   
<%--
      } //end line

   } //end info
--%>
</c:forEach>
   </table>
</div>


