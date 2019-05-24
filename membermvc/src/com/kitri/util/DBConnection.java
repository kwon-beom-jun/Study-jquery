package com.kitri.util;

import java.sql.*;

public class DBConnection {
	
	static {
		try {
			Class.forName(SiteConstance.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection makeConnection() throws SQLException { 
		//static은 객체생성 안할려고 씀. 하지만 생성자가 생성안뎀. 그래서 생성자(생성자는 한번 생성할때 한번만 불러옴)대신에 static 블록 초기화를 해줌.
		//static 생성 시점은 A a1 = new A(); 에서  A a1의 A시점에서 생성됨. 해당 클래스에서 읽어드리는 시점에서 생성됨.
		
		return DriverManager.getConnection(SiteConstance.DB_URL, SiteConstance.DB_USERNAME, SiteConstance.DB_PASWORD);
		
	}
	
}
