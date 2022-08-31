package shop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	static Connection conn=null;
	
	/**
	   * This is to establish connection with database
	   */
	public static Connection setConnection() 
	{
		try	{
			
			  DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			  Connection conn = DriverManager.getConnection (
			  "jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
			  return conn;
			  
		}
		catch(Exception e){ 
        	System.out.println(e.getMessage());
        }
		return null;
	}
	
	/**
	   * This is to cloase connection with database
	   * @exception SQLException
	   */
	public static void closeConnection() throws SQLException 
	{
		conn.close();
	}

}
