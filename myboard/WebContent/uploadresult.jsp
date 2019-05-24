<%@page import="java.io.File"%>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%String saveDir = "c:\\my";
  File dir = new File(saveDir);
  for(String fileName: dir.list()){
%><a href="download?filename=<%=fileName%>"><%=fileName %></a><br>
<%	  
  }
%>
