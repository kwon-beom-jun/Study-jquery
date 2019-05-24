package com.kitri.member.model.service;

import java.util.*;

import com.kitri.member.model.*;
import com.kitri.member.model.dao.MemberDaoImpl;

public class MemberServiceImple implements MemberService{
	
	//-----------------------------------------------------
	private static MemberService memberService;
	
	static {
		memberService = new MemberServiceImple();
	}
	
	private MemberServiceImple() {}
	// 외부에서 객체 생성 못하게 함.
	
	
	public static MemberService getMemberService() {
		return memberService;
	}

	// 싱글톤 패턴

	
	
	
	
	
	@Override
	public String idCheck(String id) {
		
		int cnt = MemberDaoImpl.getMemverDao().idCheck(id);
		System.out.println("cnt : " + cnt);

		String result = "";
		result += "<idcount>\n";
		result += "<cnt>" + cnt + "</cnt>\n";
		result += "</idcount>";

		return result;
	}

	@Override
	public String zipSearch(String doro) {
		
		String result = "";
		List<ZipcodeDto> list = MemberDaoImpl.getMemverDao().zipSearch(doro);
		
		result += "<ziplist>\n";
		for(ZipcodeDto zipDto : list) {
		result += "	<zip>\n";
		result += "		<zipcode>" + zipDto.getZipcode() + "</zipcode>\n";
		result += "		<address><![CDATA[" + zipDto.getZipcode() + " " + zipDto.getSido() + " " + zipDto.getGugun() + " " + zipDto.getUpmyon() + " " + zipDto.getDoro() + " " + zipDto.getBuildingNumber() + " " + zipDto.getSigugunBuildingName() +"]]></address>\n";
		result += "	</zip>\n";
		}
		result += "</ziplist>";
		
		return result;
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		return MemberDaoImpl.getMemverDao().registerMember(memberDetailDto);
	}

	@Override
	public MemberDto loginMember(String id, String pass) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("userpwd", pass);
		return MemberDaoImpl.getMemverDao().loginMember(map); // 프레임워크는 값을 하나만 넣을 수 있어서 map을 사용함.
	}

	@Override
	public MemberDetailDto getMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto memberDetailDto) {
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
