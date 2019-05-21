<%@page import="com.kitri.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<%--
//request의 속성(이름 : "c", 타입 : com.kirti.dto.customer)얻기
Customer c = (Customer)request.getAttribute("c");

//2.1속성이 null인 경우
//	customer객체생성하여 c참조변수 대입
//	c참조변수를 request의 속성 (이름:"c")로 추가.
if(c==null){
	c = new Customer();
	request.setAttribute("c", c);
}
--%>
<jsp:useBean id="c" class="com.kitri.dto.Customer" scope="request"/><!-- 위의 5줄과 같음. scope는 Attribute를 가진애들. -->





<%--
c.setId("id1");
--%>
<jsp:setProperty name="c" property="id" value="id1"/><!-- 위에랑 똑같음. -->





<%--
c.setName(request.getParameter("n"));
 --%>
<jsp:setProperty property="name" name="c" param="n"/><!-- 위에랑 똑같음. -->





<%--=
c.getId()
 --%>
<jsp:getProperty property="Id" name="c"/><!-- 위에랑 똑같음. -->






</body>
</html>




















