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
	private static final long serialVersionUID = 1L;
       
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String act = request.getParameter("act");
		
		String path = "/index.jsp";	// 이동경로 설정 변수 / defalte를 인덱스로 보내기.
		
//		if (act.equals("")) { // if(act != null)조건을 달아야뎀.
		if ("mvjoin".equals(act)) {
			MoveUrl.redirect(request, response, "/user/member/member.jsp");
			
		}else if ("mvlogin".equals(act)) {
			MoveUrl.redirect(request, response, "/user/login/login.jsp");
			
		}else if ("idcheck".equals(act)) {
			String sid = request.getParameter("sid");
			System.out.println("검색아이디 : " + sid);
			String resultXML = MemberServiceImple.getMemberService().idCheck(sid);
			// 넘기는 방법 json, xml, text
			
			System.out.println(resultXML);
			
			response.setContentType("text/xml;charset=UTF-8"); // text로 보내지만 xml로 인식해라.
			PrintWriter out = response.getWriter();
			
			out.print(resultXML);
			
		}else if ("zipsearch".equals(act)) {
			
			String doro = request.getParameter("doro");
			System.out.println("검색 도로명 : " + doro);
			
			String resultXML = MemberServiceImple.getMemberService().zipSearch(doro);
			System.out.println(resultXML);
			
			response.setContentType("text/xml;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.print(resultXML);
			
		}else if ("register".equals(act)) {
			
			path = MemberController.getMemberController().register(request,response);
			
//			MoveUrl.redirect(request, response, path);
			// sendredirect는 다 버리고 가므로 null값이뜸. 어디든 갈 수 있음.
			MoveUrl.forwared(request, response, path);
			// forward 가지고감. 하지만 클래스가 인터페이스라 하위클래스 request사용. 자주사용하므로 메소드를 util에 만듬
			// forward는 내 프로젝트 안에서만 가능함 
			// session,application이랑은 상관 없음. request안에 있는 session을 가져와라가 아님.
			
		}else if ("login".equals(act)) {
			path = MemberController.getMemberController().login(request,response);
			MoveUrl.forwared(request, response, path); 
		}else if ("logout".equals(act)) {
			path = MemberController.getMemberController().logout(request,response);
			MoveUrl.redirect(request, response, path);
		}else if ("".equals(act)) {
			
		}else if ("".equals(act)) {
			
		}else if ("".equals(act)) {
			
		}else if ("".equals(act)) {
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(SiteConstance.ENCODE);
		doGet(request, response);
	}

}










