package com.kitri.service;

import com.kitri.dao.RepBoardDAO;
import com.kitri.dto.RepBoard;
import com.kitri.exception.AddException;

public class RepBoardService {


	private RepBoardDAO dao;
	
	public RepBoardService(){
		
		dao = new RepBoardDAO();
		
	}
	
	//본글
	public void write(RepBoard repBoard) throws AddException {
		repBoard.setParent_seq(0);
		dao.insert(repBoard);
	}
	
	//답글
	public void reply(RepBoard repBoard) throws AddException {
		if (repBoard.getParent_seq() == 0 ) {
			throw new AddException("부모글번호가 없는 답글입니다.");
		}
		dao.insert(repBoard);
	}
	
}
