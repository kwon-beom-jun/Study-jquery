package com.kitri.member.model.service;

import java.util.*;

import com.kitri.member.model.*;
import com.kitri.member.model.dao.MemberDaoImpl;

public class MemberServiceImple implements MemberService{
	
	
	@Override
	public String idCheck(String id) {
		
		return null;
	}

	@Override
	public String zipSearch(String doro) {
		
		return null;
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		return 0;
	}

	@Override
	public MemberDto loginMember(String id, String pass) {
		return null; // 프레임워크는 값을 하나만 넣을 수 있어서 map을 사용함.
	}

	@Override
	public MemberDetailDto getMember(String id) {
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto memberDetailDto) {
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		return 0;
	}

	
	
}
