package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class get_closeConnect {
	
	public Connection getConnection() throws SQLException  {	
		Connection con = null;
		String connectionUrl = "jdbc:sqlserver://LAPTOP-K5H0614D:1433;databaseName=FastFoodManagement;"
                + "user=sa;password=Tien01062005!;"
                + "encrypt=true;trustServerCertificate=true;"; 
		 con  = DriverManager.getConnection(connectionUrl);
		return con;
	}
	
	public void closeConnect(Connection con,PreparedStatement preparestatement)
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparestatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnect(Connection con,Statement statement)
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
