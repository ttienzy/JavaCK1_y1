package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class SummaryManager extends get_closeConnect{
	
	
	
	public String getNumCus()
	{
		String tmp = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();					
			String querySql = "select COUNT(IDCustomer) num from Customers";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				tmp = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}	
		return tmp;
	}
	
	public String getNumFoo()
	{
		String tmp = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();					
			String querySql = "select COUNT(IDFood) num from FoodItems";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				tmp = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}	
		return tmp;
	}
	
	public String getRevenue()
	{
		String tmp = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();					
			String querySql = "select sum(Total) tot from Orders\r\n"
					+ "where StatusOrder = N'Tiếp tục' ";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				tmp = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}	
		return tmp;
	}
	
	//----------------------------------//
	
	public String getNumOrder()
	{
		String tmp = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();					
			String querySql = "SELECT COUNT(IDOrder) number FROM ORDERS ";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				tmp = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}	
		return tmp;
	}
	
	public String getNumOrderContinue()
	{
		String tmp = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();					
			String querySql = "select count(IDOrder)  from Orders\r\n"
					+ "where StatusOrder = N'Tiếp tục'";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				tmp = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}	
		return tmp;
	}
	
	public String getNumOrderDisContinue()
	{
		String tmp = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();					
			String querySql = "SELECT count(IDOrder) FROM Orders\r\n"
					+ "where StatusOrder = N'Hủy'";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				tmp = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}	
		return tmp;
	}
	
	public Vector<Vector<String>> TopFood()
	{
		Vector<Vector<String>> top = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();	
			top = new Vector<Vector<String>>();
			String querySql = "select TOP 5 NameFood,SUM(Number) numbe from Orders inner join FoodItems on Orders.IDFood = FoodItems.IDFood \r\n"
					+ "where StatusOrder = N'Tiếp tục'\r\n"
					+ "group by NameFood\r\n"
					+ "ORDER BY numbe DESC;";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				Vector<String> v = new Vector<String>();
				v.add(result.getString(1));
				v.add(result.getString(2));
				top.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}	
		return top;
	}
	
	
	
}
