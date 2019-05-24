package com.kitri.member.model.dao;

import java.sql.*;
import java.util.*;

import com.kitri.member.model.*;
import com.kitri.util.DBClose;
import com.kitri.util.DBConnection;

public class MemberDaoImpl implements MemberDao{

	@Override
	public int idCheck(String id) {
		return 0;
	}

	@Override
	public List<ZipcodeDto> zipSearch(String doro) {
		
		return null;
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		
		return 0;
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) {
		return null;
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
