package com.kitri.dao;

import java.sql.*;

import com.kitri.dto.*;
import com.kitri.exception.AddException;

public class RepBoardDAO {
	
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
		board.setBoard_subject("테스트 제목");
		board.setBoard_writer("test");
		board.setBoard_contents("테스트 내용");
		board.setBoard_password("password");
//		board.setParent_seq(1); // 답글쓰기용 테스트
		try {
			dao.insert(board);// 글쓰기용 테스트.
		} catch (AddException e) {
			e.printStackTrace();
		} 
		
	}

}
