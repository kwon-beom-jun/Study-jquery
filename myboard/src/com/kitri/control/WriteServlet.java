package com.kitri.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.RepBoard;
import com.kitri.exception.AddException;
import com.kitri.service.RepBoardService;

@WebServlet("/writeboard")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	RepBoardService service;
	
	public void init() {
		service = new RepBoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");

		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String password = request.getParameter("password");
		String content = request.getParameter("content");
		
		RepBoard repBoard = new RepBoard();
		
		repBoard.setBoard_subject(subject);
		repBoard.setBoard_writer(writer);
		repBoard.setBoard_password(password);
		repBoard.setBoard_contents(content);
		
		try {
			service.write(repBoard);
			request.setAttribute("result", "글쓰기 성공");
		} catch (AddException e) {
			request.setAttribute("result", "글쓰기 실패");
		}
		
		
		String path = "/writeboardresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		
	}

}
