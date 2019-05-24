<%@tag import="java.util.Date"%>
<%@tag import="java.text.SimpleDateFormat"%>
<%@ tag body-content="empty" pageEncoding="UTF-8"%>
<%-- 엘리먼트 바디 안에 사용하지 않겠다. empty --%>
<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>

