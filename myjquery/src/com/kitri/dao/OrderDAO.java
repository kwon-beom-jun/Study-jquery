package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.*;
import com.kitri.exception.AddException;

public class OrderDAO {
	
	public void insert(OrderInfo info) throws AddException{ // OrderInfo has a lines로 들고다닐 것임.
		
		// DB연결
		Connection conn = null;
		
		try {
			
			// JDBC드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
				
			// DB연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.30:1521:orcl","kitri","kitri");
			
			conn.setAutoCommit(false); // 디비에 두개가 정상적으로 다 들어가야 되기 때문에 자동 커밋 해제.
			
			// Connection하나를 같이쓰는 이유는 시퀀스 때문
			insertInfo(conn, info); // DB주문 기본 추가하기. throws로 넘겼으니 밑에 catch에서 롤백해줘야뎀.
			
			List<OrderLine> lines = info.getLines();
			insertLine(conn, lines); // has a관계로 들고다니면 뎀. 다대 일 관계이므로 여러개를 들고 다닐려고 함.
			
			conn.commit();// 다 완료되고 커밋.
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new AddException("주문추가 오류 : " + e.getMessage()); // 추가할때 물건이 없을 때 익셉션 만듬.
		} finally {
			try {
				
				if (conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		

	}
	
	public void insertInfo(Connection conn, OrderInfo info) throws SQLException {
		
		PreparedStatement pstmt = null;
		String insertInfoSQL = "INSERT INTO order_info(order_no, order_id) VALUES (order_seq.NEXTVAL, ?)";
		
		try {
			pstmt = conn.prepareStatement(insertInfoSQL);
			pstmt.setString(1, info.getCustomer().getId());
			pstmt.executeUpdate();
//		} catch (SQLException e) { 롤백하기위해 주석처리.
//			e.printStackTrace();
		} finally {
			if (pstmt != null) { // conn은 닫으면 안뎀.
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	
	
	
	public void insertLine(Connection conn, List<OrderLine> lines) throws SQLException {
		
		PreparedStatement pstmt = null;
		String insertLineSQL = "INSERT INTO order_line(order_no, order_prod_no, order_quantity) "
				+ "VALUES (order_seq.CURRVAL, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(insertLineSQL);
			
			for(OrderLine line:lines) {
				
				String prod_no = line.getProduct().getProd_no();
				pstmt.setString(1, prod_no);
				
				int quantity = line.getOrder_quantity();
				pstmt.setInt(2, quantity);
//				pstmt.executeUpdate();
				pstmt.addBatch(); // 일괄처리에 추가.(한꺼번에 작업하기위해서)
				
			}
			
			pstmt.executeBatch(); // 일괄처리.
			
//		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public List<OrderInfo> selectById(String id){
		// DB연결
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderInfo> list = new ArrayList<>();
		
		try {
				
			// JDBC드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
				
			// DB연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.30:1521:orcl","kitri","kitri");
			
			String selectByIdSQL = " select info.order_no, order_dt, prod_no, prod_name, prod_price, order_quantity\r\n" + 
					" from order_info info join order_line line\r\n" + 
					" on info.order_no = line.order_no\r\n" + 
					" join product p on p.prod_no = line.order_prod_no\r\n" + 
					" where order_id = ?\r\n" + 
					" order by order_dt desc, prod_no";
					
			pstmt = conn.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			OrderInfo info = null;
			OrderLine line = null;
			List<OrderLine> lines = null;
			int old_order_no = -1;
			
			while( rs.next() ) { 
//				첫행이거나 주문번호와 시간이 다를때만 만들면 됌.
//				OrderInfo info = new OrderInfo();
//				info.setOrder_no(rs.getInt("order_no")); // heading명으로 찾아와서 별칭.order_no 에서 별칭은 생략.
//				info.setOrder_dt(rs.getDate("order_dt"));
//				List<OrderLine> lines = new ArrayList<>();
				int order_no = rs.getInt("order_no");
				
				// 주문받은 번호가 다를때에만.
				if (old_order_no != order_no) {
					info = new OrderInfo();
					list.add(info); //미리 add시켜논다해도 참조메모리가 계속 있어서 상관없음. 레퍼런스 자료형이여서 상관없음.
//					info.setOrder_no(order_no);
					info.setOrder_no(rs.getInt("order_no")); // heading명으로 찾아와서 별칭.order_no 에서 별칭은 생략.
					info.setOrder_dt(rs.getDate("order_dt"));
					lines = new ArrayList<>();
					info.setLines(lines);
//					list.add(info); // 여기있어도 됨.
					old_order_no = order_no;
				}
				
				
				line = new OrderLine();
				
				// 상품번호, 명, 가격
				String prod_no = rs.getString("prod_no"); 
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				Product p = new Product();
				p.setProd_no(prod_no);
				p.setProd_name(prod_name);
				p.setProd_price(prod_price);
				
				line.setProduct(p);
				line.setOrder_quantity(rs.getInt("order_quantity"));
				
				lines.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;		
	
	}
}





