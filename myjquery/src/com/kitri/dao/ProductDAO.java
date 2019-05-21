package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.Product;
import com.kitri.dto.ProductCategory;

public class ProductDAO {
	
	public Product SelectByNo(String no) {
		
		Product product = null;
		ProductCategory productCategory = null;
		
		// DB연결
		Connection conn = null;
		// SQL송신
		PreparedStatement pstmt = null;
		// 결과 수신
		ResultSet rs = null;
		// 연결 닫기
		
		// JDBC드라이버 로드
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// DB연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.30:1521:orcl","kitri","kitri");
			
			// SQL송신
			String sql = "SELECT prod_no, prod_name, prod_price, prod_detail \r\n" + 
					"FROM product p join product_category pc \r\n" + 
					"ON p.prod_cate = pc.cate_no \r\n" + 
					"WHERE prod_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,no);
			// 결과 수신
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				product = new Product();
				productCategory = new ProductCategory();
				
				product.setProd_no(rs.getString("prod_no"));
				product.setProd_name(rs.getString("prod_name"));
				product.setProd_price(rs.getInt("prod_price"));
				product.setProd_detail(rs.getString("prod_detail"));
				
				//productCategory.setCate_name(rs.getString(""));
				
			}

			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
					
		return product;
		// return하면 finally 수행 안되는것 아닌가 하지만 return하기 전에 finally 실행하고 return을 수행.
		

	}
	

	public List<Product> selectAll(){
		
		List<Product> list = new ArrayList<>();

		// DB연결
		Connection conn = null;
		// SQL송신
		PreparedStatement pstmt = null;
		// 결과 수신
		ResultSet rs = null;
		// 연결 닫기
		
		
		try {
			// JDBC드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// DB연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.30:1521:orcl","kitri","kitri");
			
			// SQL송신
			String selectAll = "SELECT cate_no, cate_name," + 
					" prod_no, prod_name, prod_price, prod_detail" + 
					" FROM product p JOIN product_category pc " + 
					" ON  p.prod_cate=pc.cate_no " + 
					" ORDER BY cate_no, prod_name";
			pstmt = conn.prepareStatement(selectAll);
			// 결과 수신
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_detail = rs.getString("prod_detail");
				
				String cate_no = rs.getString("cate_no");
				String cate_name = rs.getString("cate_name");
				
				ProductCategory pc = new ProductCategory(cate_no, cate_name);
				Product p = new Product(prod_no, prod_name, prod_price, prod_detail, pc);
				
				list.add(p);
				
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
