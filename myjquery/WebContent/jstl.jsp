<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL:JspStandardTagLib -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%-- int num=Integer.parseInt(request.getparameter) --%>
<c:set var="num" value="${param.num}"></c:set>
요청전달데이터 num=${num}<br>

<%-- if(){}else if(){}else{} --%>
<c:if test="${num%2==0 }">
짝수입니다.<br>
</c:if>
<hr>
<c:choose>
	<c:when test="${num%2==0 }">
	짝수입니다.
	</c:when>
	<c:otherwise>
	홀수입니다.
	</c:otherwise>
</c:choose>




<hr>
<br>
<%-- for(int i=1; i<=10; i++){} --%>
<c:forEach begin="1" end="10" step="1" var="i">
${i}
</c:forEach>



<hr>
<br>
<!--1~10 합. -->
<c:set var="total" value="0" ></c:set>
<c:forEach begin="1" end="10" var="i">
	<c:set var="total" value="${total+i}"></c:set><!-- 변수갑 중복 x 변수 만드는것 아님. 있으면 재사용 없으면 새로만듬. -->
</c:forEach>
1~10 합: ${total }

<hr>
<%
List<String> list = new ArrayList<>();
list.add("one"); list.add("two"); list.add("tree");
request.setAttribute("list", list);
%>
<c:forEach var="e" items="${requestScope.list }">
${e}<br>
</c:forEach>

<hr>
<!-- varStatus 아무거나 넣어두 뎀. 한번 반복할때마다 요소의 인덱스값이나 카운트값을 알아낼 수 있음 -->
<c:forEach var="e" items="${requestScope.list }" varStatus="obj">
${obj.index} - ${e} : ${obj.count}회<br>
</c:forEach>


</body>
</html>