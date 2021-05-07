package covid;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

	
		
		static String driver="com.mysql.cj.jdbc.Driver";
		public static final String url = "jdbc:mysql://localhost:3306/covid";
		
		public static final String username="root";
		public static final String password="root";
		public static Connection conn;
		
		public static Connection getConnection() {
		
			try {
		Class.forName(driver);
		
		 conn =DriverManager.getConnection(url,username,password);
		System.out.println("connection success");
		}catch(Exception e) {
			e.printStackTrace();
		}
			
			return conn;
}
		
		}
		
	
	
	

