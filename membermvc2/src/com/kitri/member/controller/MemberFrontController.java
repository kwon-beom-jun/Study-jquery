package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.service.MemberServiceImple;
import com.kitri.util.MoveUrl;
import com.kitri.util.SiteConstance;

@WebServlet("/user")
public class MemberFrontController  extends HttpServlet { // 어디로만 가라는 것.
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String act = request.getParameter("act");
		String path = "/index.jsp";
		
//		if ("mvlogin".equals(act)) {
//			MoveUrl.redirect(request, response);
//		}
		
		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}










