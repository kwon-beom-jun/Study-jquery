package com.kitri.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveUrl {
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
// 	루트는 request.
		response.sendRedirect(request.getContextPath() + path);
	}

	public static void forwared(HttpServletRequest request, HttpServletResponse response, String path) throws IOException, ServletException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
		
		
		
	}

}
