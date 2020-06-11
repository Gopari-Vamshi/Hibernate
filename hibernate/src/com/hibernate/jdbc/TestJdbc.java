package com.hibernate.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
				
		String jdbcUrl= "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
		String user = "vamshi";
		String pass = "1234";
		try {
			
			System.out.println("Connecting to Database:"+jdbcUrl);
			
			Connection conn = DriverManager.getConnection(jdbcUrl,user,pass);
			System.out.println("Connection successful!");
			
			
			System.out.println(conn.isClosed());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
