package com.kitri.service;

import com.kitri.dao.CustomerDao;
import com.kitri.dto.Customer;
import com.kitri.exception.NotFoundException;

public class MemberService {
	
	public String login(String id, String pass) {
		
		CustomerDao dao = new CustomerDao();
		
		// dao에 데이터베이스에 있는 값을 저장해줌. dao에서 데이터베이스에 있는 모든 데이터를 넣어줌.
		try {
			Customer c = dao.selectById(id);
			if (c.getPass().equals(pass)) {
				// 데이터베이스서 Customer에 저장된 pass값이랑 받아온 pass값이랑 비교
				// id비교는 dao딴에서 비교를 함.
//				return "로그인 성공";
				return "1";
			}else {
//				return "로그인 실패";
				return "-1";
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
			return "로그인 실패 : " + e.getMessage(); 
		}
		
	}
	
}
