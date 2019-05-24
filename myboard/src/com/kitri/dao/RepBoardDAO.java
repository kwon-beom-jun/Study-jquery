package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.*;
import com.kitri.exception.AddException;

public class RepBoardDAO {
	
	
	public List<RepBoard> selectByRows(int startRow, int endRow){
		
		List<RepBoard> list = new ArrayList<>();
		
		String selectByRowsSQL = 
				"SELECT * \r\n" + 
				"    from(select rownum r, " +
				"       level lev, "+
				"		repboard.*\r\n" + 
				"    	from repboard\r\n" + 
				"    	start with parent_seq = 0\r\n" + 
				"   	connect by prior board_seq = parent_seq\r\n" + 
				"   	order siblings by board_seq desc)\r\n" + 
				"    where r between ? and ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.30:1521:orcl","kitri","kitri");
			pstmt = conn.prepareStatement(selectByRowsSQL);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				RepBoard board = new RepBoard();
				
				board.setBoard_seq(rs.getInt("board_seq"));
				board.setParent_seq(rs.getInt("parent_seq"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_writer(rs.getString("board_writer"));
				board.setBoard_contents(rs.getString("board_contents"));
				board.setBoard_date(rs.getTimestamp("board_date"));
				board.setBoard_password(rs.getString("board_password"));
				board.setBoard_viewcount(rs.getInt("board_viewcount"));
				board.setLevel(rs.getInt("lev"));
				
				list.add(board);
				
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		
		
		
		return list;
		
	}
	
	
	//글쓰기 용도 insert작업.
	public void insert(RepBoard repboard) throws AddException {
		
		String inserSQL = 
				" insert into repboard(" + 
				" BOARD_SEQ,\r\n" + 
				" PARENT_SEQ,\r\n" + 
				" BOARD_SUBJECT,\r\n" + 
				" BOARD_WRITER,\r\n" + 
				" BOARD_CONTENTS,\r\n" + 
				" BOARD_DATE,\r\n" + 
				" BOARD_PASSWORD,\r\n" + 
				" BOARD_VIEWCOUNT)\r\n " +
				" values(BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, systimestamp, ?, 0)";
		// systimestamp 세밀한 시간을 표현해줌.
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.30:1521:orcl","kitri","kitri");
			pstmt = conn.prepareStatement(inserSQL);
			
			
			pstmt.setInt(1, repboard.getParent_seq());
			pstmt.setString(2, repboard.getBoard_subject());
			pstmt.setString(3, repboard.getBoard_writer());
			pstmt.setString(4, repboard.getBoard_contents());
			pstmt.setString(5, repboard.getBoard_password());
			
				
			pstmt.executeUpdate(); // addexcption하기.
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	// TESt용.
	public static void main(String[] args) {
		
		RepBoardDAO dao = new RepBoardDAO();
		RepBoard board = new RepBoard();
//		board.setBoard_subject("테스트 제목");
//		board.setBoard_writer("test");
//		board.setBoard_contents("테스트 내용");
//		board.setBoard_password("password");
////		board.setParent_seq(1); // 답글쓰기용 테스트
//		try {
//			dao.insert(board);// 글쓰기용 테스트.
//		} catch (AddException e) {
//			e.printStackTrace();
//		}
		
		for(RepBoard repBoard:dao.selectByRows(2, 2)) {
			System.out.println(repBoard);
		}
		
	}


	public int selectTotalCnt() {
		
		String selectTotalCntSQL = "select count(*) from repboard";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		int totalCnt = -1;
		
		try {
			//1)JDBC드라이버로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2)DB연결
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(selectTotalCntSQL);			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCnt = rs.getInt(1);
			}	
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return totalCnt;
		
	}

}
