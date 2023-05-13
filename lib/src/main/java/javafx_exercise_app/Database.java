package javafx_exercise_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private static Connection conn = null; 

	
    public static void connect() {  
	   
	     try {  
	         // db parameters  
	         String url = "jdbc:sqlite:C:\\sqlite\\GetFit_db.db";  
	         // create a connection to the database  
	         conn = DriverManager.getConnection(url);  
	              
	         System.out.println("Connection to SQLite has been established.");  
	              
	     } catch (SQLException e) {  
	         System.out.println(e.getMessage());  
	     } 
	 } 
	
	public static void closeConnection() {
		
		try {  
            if (conn != null) {  
                conn.close();  
                System.out.println("Connection Terminated");
            }  
        } catch (SQLException ex) {  
            System.out.println(ex.getMessage()); 
		
	}

}
	
	public void insertNewUser(String username, String password, String email) {}
}

