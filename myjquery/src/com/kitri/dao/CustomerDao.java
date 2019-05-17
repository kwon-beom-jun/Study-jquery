package com.kitri.dao;

import java.sql.*;
import java.util.List;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import com.kitri.dto.Customer;
import com.kitri.exception.NotFoundException;

public class CustomerDao {
	
	
	
	public Customer selectById(String id) throws com.kitri.exception.NotFoundException{
		// exception을 넘길때는 상대방도 Exception을 상속받아야뎀.
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.30:1521:orcl","kitri","kitri");
			
			//SQL구문 DB서버로 송신 : executeQuery();
			//결과수신 : rs
			String selectByIdSQL = "select * from customer where id = ?";
			pstmt = conn.prepareStatement(selectByIdSQL);
			
			pstmt.setString(1, id);
			pstmt.executeQuery();
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Customer c = new Customer();
				c.setId(id);
				c.setPass(rs.getString("pass"));
				c.setName(rs.getString("name"));
				System.out.println(c.getId() + " " + c.getPass());
				return c;
			
			}else {
				// 가져온 id가 없을때.
				throw new NotFoundException("아이디에 해당하는 고객이 없습니다.");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		
	}
	
	public List<Customer> selectByName(String name){
		
		return null;
	}
	
	public List<Customer> selectAll(Customer c){
		
		return null;
	}

}
