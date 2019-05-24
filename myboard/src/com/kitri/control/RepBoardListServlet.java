package com.kitri.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.PageBean;
import com.kitri.dto.RepBoard;
import com.kitri.exception.AddException;
import com.kitri.service.RepBoardService;

@WebServlet("/boardlist")
public class RepBoardListServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	RepBoardService service;
	
	public void init() {
		service = new RepBoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("RepBoardListServlet 들어옴.");
		
		// 요청전달 데이터 없으면 1페이지
		String cp = request.getParameter("currentPage");
		
		int currentPage = 1; // 보여줄 현재 페이지.
		
		if (cp != null) {
			currentPage = Integer.parseInt(cp);
		}
		
		int cntPerPage = 10; // 페이지별 보여줄 목록수
		// 현재페이지, startRow, endRow
		//    1		  1		    10
		//    2		  11		20
		//    5		  41		50
		
//		int startRow = 1+cntPerPage*(currentPage-1);
//		int endRow = cntPerPage*currentPage;
		
		
//		List<RepBoard> list = service.findByRows(startRow, endRow);
		
//		request.setAttribute("result", list);
		
//		-------------------------------------------------------------
		
		// 총페이지수 계산
//		int cntPerPageGroup = 3; // 페이지그룹에 보여줄 페이지수
//		int totalPage = 1; // 총 페이지 수
		
		int totalCnt = service.getTotalCnt();
		
		//ceil 무조건 올림하는 함수 , 총페이지 수
//		totalPage = (int)Math.ceil((float)totalCnt/cntPerPage);
//		request.setAttribute("totalPage", totalPage);
//		--------------------------------------------------------------
		
		int cntPerPageGroup = 3; // 페이지그룹에 보여줄 페이지수
//		int startPage = ((currentPage-1)/cntPerPageGroup)*cntPerPageGroup+1;
//		int endPage = startPage + cntPerPageGroup -1;
//		if (endPage > totalPage) {
//			endPage = totalPage;
//		}
		
//		request.setAttribute("strtPage", startPage);
//		request.setAttribute("endPage", endPage);
		
		String url = "boardlist";
		
		PageBean pb = new PageBean(cntPerPage, totalCnt, cntPerPageGroup, url, currentPage);
		List<RepBoard> list = service.findByRows(pb.getStartRow(), pb.getEndRow());

		pb.setList(list);
		request.setAttribute("pb", pb);		
		System.out.println(pb.getTotalPage());
		
		String path = "/boardresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
