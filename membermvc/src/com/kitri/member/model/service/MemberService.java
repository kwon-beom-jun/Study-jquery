package com.kitri.member.model.service;

import java.util.List;

import com.kitri.member.model.*;

public interface MemberService {
	
	// 추상이라 열고닫고 안함. 0이면 사용할 수 있다.
	String idCheck(String id);
	
	String zipSearch(String doro);
	// 우편번호도 받지만 주소도 받음. zipcodedto에 있는 list를 리턴해야뎀
	int registerMember(MemberDetailDto memberDetailDto);
	MemberDto loginMember(String id, String pass);
	
	MemberDetailDto getMember(String id);
	int modifyMember(MemberDetailDto memberDetailDto); //0이면 수정 1이면 수정 못함
	int deleteMember(String id);
	
}
