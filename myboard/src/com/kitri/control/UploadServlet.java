package com.kitri.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		MultipartRequest mr = new MultipartRequest(request, "C:\\my"); // 어디에 저장될것인지 지정
		MultipartRequest mr;
		
		String saveDirectory = "C:\\my"; // 경로지정
		int maxPostSize = 100*1024; //최대 100kbit까지 업로드 가능하게
//		mr = new MultipartRequest(request, saveDirectory, maxPostSize);
		
		
		FileRenamePolicy policy = new DefaultFileRenamePolicy(); // 이름 뒤에 일렬번호를 붙여서 중복된 이름 정리.
		String encoding = "UTF-8";
		mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		
		String a = mr.getParameter("a");
		File f1 = mr.getFile("f1");
		System.out.println("a=" + a);
		System.out.println("fileName=" + f1.getName());
		String path = "/uploadresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
