package com.kitri.member.model.dao;

import java.sql.*;
import java.util.*;

import com.kitri.member.model.*;
import com.kitri.util.DBClose;
import com.kitri.util.DBConnection;

public class MemberDaoImpl implements MemberDao{

	private MemberDaoImpl() {} // 1번째 외부에서 생성 못하게 만듬.
	
	private static MemberDao memverDao; // 2번째 전역변수 만들기
	
	static {
		memverDao = new MemberDaoImpl(); // 3번째 자기 안에서 쓸 수 있게 만들기
	}
	
	public static MemberDao getMemverDao() { // 4번째 getter 만들기.
		return memverDao;
	}

	@Override
	public int idCheck(String id) {

		int cnt = 1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBConnection.makeConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select count(id) \n");
			sql.append("from member \n");
			sql.append("where id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1); // ?
			
		} catch (SQLException e) {
			cnt = 1;
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public List<ZipcodeDto> zipSearch(String doro) {
		
		List<ZipcodeDto> list = new ArrayList<ZipcodeDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBConnection.makeConnection();
			
			StringBuffer sql = new StringBuffer();

			sql.append("select 	case  \n");
			sql.append("			when length(new_post_code) = 4 then '0'||new_post_code \n");
			sql.append("			else new_post_code \n");
			sql.append("		end zipcode,  \n");
			sql.append("		sido_kor sido, gugun_kor gugun,  \n");
			sql.append("		nvl(upmyon_kor, ' ') upmyon, doro_kor doro,  \n");
			sql.append("		case when building_refer_number != '0' \n");
			sql.append("			then building_origin_number||'-'||building_refer_number  \n");
			sql.append("			else trim(to_char(building_origin_number, '99999')) \n");
			sql.append("		end building_number, sigugun_building_name \n");
			sql.append("from 	postcode \n");
			sql.append("where 	doro_kor like '%'||?||'%' \n");// -> % + doro + % 변수로 쓰기 위해서 ||사용 오라클용
			sql.append("or sigugun_building_name like '%'||?||'%' \n");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, doro);
			pstmt.setString(2, doro);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipcodeDto zipDto = new ZipcodeDto();
				zipDto.setZipcode(rs.getString("zipcode"));
				zipDto.setSido(rs.getString("sido"));
				zipDto.setGugun(rs.getString("gugun"));
				zipDto.setUpmyon(rs.getString("upmyon"));
				zipDto.setDoro(rs.getString("doro"));
				zipDto.setBuildingNumber(rs.getString("building_number"));
				zipDto.setSigugunBuildingName(rs.getString("sigugun_building_name"));
				
				list.add(zipDto);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert all \n");
			sql.append("	into member (id, name, pass, emailid, emaildomain, joindate) \n");
			sql.append("	values (?, ?, ?, ?, ?, sysdate) \n");
			sql.append("	into member_detail (id, zipcode, address, address_detail, tel1, tel2, tel3) \n");
			sql.append("	values (?, ?, ?, ?, ?, ?, ?) \n");
			sql.append("select * from dual \n");
			pstmt = conn.prepareStatement(sql.toString());
			
			int idx = 0;
			
			pstmt.setString(++idx, memberDetailDto.getId());
			pstmt.setString(++idx, memberDetailDto.getName());
			pstmt.setString(++idx, memberDetailDto.getPass());
			pstmt.setString(++idx, memberDetailDto.getEmailid());
			pstmt.setString(++idx, memberDetailDto.getEmaildomain());
			pstmt.setString(++idx, memberDetailDto.getId());
			pstmt.setString(++idx, memberDetailDto.getZipcode());
			pstmt.setString(++idx, memberDetailDto.getAddress());
			pstmt.setString(++idx, memberDetailDto.getAddressDetail());
			pstmt.setString(++idx, memberDetailDto.getTel1());
			pstmt.setString(++idx, memberDetailDto.getTel2());
			pstmt.setString(++idx, memberDetailDto.getTel3());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return cnt;
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) {
		
		MemberDto memberDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select name, id, emailid, emaildomain, joindate \n");
			sql.append("from member \n");
			sql.append("where id = ? and pass = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, map.get("userid"));
			pstmt.setString(2, map.get("userpwd"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDto(); // 안으로 들어왔다는것은 일치한다는것음
				memberDto.setName(rs.getString("name"));
				memberDto.setId(rs.getString("id"));
				memberDto.setEmailid(rs.getString("emailid"));
				memberDto.setEmaildomain(rs.getString("emaildomain"));
				memberDto.setJoindate(rs.getString("joindate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		
		return memberDto;
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
