package com.kitri.member.controller;

import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.service.MemberServiceImple;

public class MemberController{ // 웹 로직을 자바로 담당하는 하는 컨트롤러
	
	
	private static MemberController memberController; // 2번째 전역변수 만들기
	
	static {
		memberController = new MemberController(); // 3번째 자기 안에서 쓸 수 있게 만들기
	}

	private MemberController() {} // 1번째 외부에서 생성 못하게 만듬.
	
	public static MemberController getMemberController() { // 4번째 getter 만들기.
		return memberController;
	}
	
	public String register(HttpServletRequest request, HttpServletResponse response) {
		// post로 왔는데 doGet(request, response); 있어서 돌아옴 그래서 한번 들렸다 오니 글자가 깨지므로  post에서 인코딩 해줘야뎀.
		
			String path = "/index.jsp";
		
			MemberDetailDto memberDetailDto = new MemberDetailDto();
				
			memberDetailDto.setName(request.getParameter("name"));
			memberDetailDto.setId(request.getParameter("id"));
			memberDetailDto.setPass(request.getParameter("pass"));
			memberDetailDto.setEmailid(request.getParameter("emailid"));
			memberDetailDto.setEmaildomain(request.getParameter("emaildomain"));
			memberDetailDto.setTel1(request.getParameter("tel1"));
			memberDetailDto.setTel2(request.getParameter("tel2"));
			memberDetailDto.setTel3(request.getParameter("tel3"));
			memberDetailDto.setZipcode(request.getParameter("zipcode"));
			memberDetailDto.setAddress(request.getParameter("address"));
			memberDetailDto.setAddressDetail(request.getParameter("address_detail"));
			
			int cnt = MemberServiceImple.getMemberService().registerMember(memberDetailDto);
			
			if (cnt != 0) {
				
				request.setAttribute("userInfo", memberDetailDto); // 이름은 아무거나 지정함.
				path = "/user/member/registerok.jsp"; // 갈것이다라는 지정만함. 프론트 컨트롤러에서 가는것을함. 가져가야되는 값이 오브젝트면 못보넴.
			
			} else {
				
				path = "/user/member/registerfail.jsp";
			
			}
			
			return path;
					
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		
		String path = "/index.jsp";
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDto memberDto = MemberServiceImple.getMemberService().loginMember(id, pass);
		
		if (memberDto != null) {
		//////////////////////////////////// Cookie///////////////////////////////
		
		String idsv = request.getParameter("idsave"); // 체크가 됬으면
		//서버에서 생성되고있으니 클라이언트에 보내야뎀.
		if ("idsave".equals(idsv)) {
			Cookie cookie = new Cookie("kid_inf", id); // 이름 아무거나 설정 아이디 저장 
			
			cookie.setDomain("localhost");
			cookie.setPath(request.getContextPath()); // 우리가 쓸 패키지 루트(경로) 지정??
			cookie.setMaxAge(60*60*24*365*50); // 초단위
			response.addCookie(cookie);
		} else {
			Cookie cookie[] = request.getCookies();

			if (cookie != null){
				for(Cookie c : cookie){ // 헷갈리면 쿠키의 length만큼 포문 돌리면 됨.
					if("kid_inf".equals(c.getName())){
						c.setDomain("localhost");
						c.setPath(request.getContextPath()); 
						c.setMaxAge(0); // 바로 죽어라 
						response.addCookie(c);
						break;
					}
				}
			}
		}
		
		
		//////////////////////////////////// Sesstion///////////////////////////////
		
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", memberDto);
			path = "/user/login/loginok.jsp";
		//////////////////////////////////// Sesstion///////////////////////////////
		} else {
			path = "/user/login/loginfail.jsp";
		}
		
		return path;
	}

	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
//		session.setAttribute("userInfo", null); 꼼수!!
//		session.removeAttribute("userInfo"); // session안에 있는것 하나씩 지울때
		session.invalidate(); // session안에 있는 것들을 모두 지워라
		
		return "/user/login/login.jsp";
		
	}
	
}