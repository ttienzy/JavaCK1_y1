package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Model.InterFaceButton;
import Model.Order;

public class OrderManager extends get_closeConnect implements InterFaceButton<Order>{
	private Vector<Order> orderManagement = new Vector<Order>();
	public CustomerManager custmn = new CustomerManager();
	public FoodItemsManager foomn = new FoodItemsManager();
	public OrderManager() {}

	public OrderManager(Vector<Order> orderManagement) {
		this.orderManagement = orderManagement;
	}

	public Vector<Order> getOrderManagement() {
		return orderManagement;
	}

	public void setOrderManagement(Vector<Order> orderManagement) {
		this.orderManagement = orderManagement;
	}

	@Override
	public void Add(Order t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		try {
			con = getConnection();
			String querySql = "insert into Orders values (?,?,?,?,?,?,?) ";
			preparestatement = con.prepareStatement(querySql);
			
			preparestatement.setString(1,t.getIDord());
			preparestatement.setString(2,t.getNameCust() );
			preparestatement.setString(3,t.getNameFood() );
			
			preparestatement.setInt(4,t.getNumber());
			preparestatement.setInt(5,t.getTotal());
			
			preparestatement.setString(6,t.getDateOrder());
			preparestatement.setString(7,t.getStatu());
			
			preparestatement.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, preparestatement);
		}
	}

	@Override
	public void Update(Order t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		try {
			con = getConnection();
			String querySql = "update Order set IDOrder = ?, IDCustomer = ?, IDFood = ?,Number = ? ,Total=?,DateOrder = ?,StatusOrder = ? where '"+t.getIDord()+"'";
			preparestatement = con.prepareStatement(querySql);
			
			preparestatement.setString(1,t.getIDord());
			preparestatement.setString(2,t.getNameCust() );
			preparestatement.setString(3,t.getNameFood() );			
			preparestatement.setInt(4,t.getNumber());
			preparestatement.setInt(5,t.getTotal());			
			preparestatement.setString(6,t.getDateOrder());
			preparestatement.setString(7,t.getStatu());
			
			preparestatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, preparestatement);
		}
		
	}

	@Override
	public void Dalete(Order t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		try {
			con = getConnection();
			String querySql = "delete from FoodItems where IDOrder = '"+t.getIDord()+"'";
			preparestatement = con.prepareStatement(querySql);
			preparestatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, preparestatement);
		}
		
	}

	@Override
	public void ShowInfomation() {
		orderManagement.clear();
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();					
			String querySql = "SELECT TOP 5 IDOrder,NameCustomer,NameFood,Number,Total,DateOrder,StatusOrder from Orders inner Join Customers on Orders.IDCustomer=Customers.IDCustomer Join FoodItems on Orders.IDFood=FoodItems.IDFood  order by NameFood ASC";
//			String querySql = "select TOP 5 NameFood,SUM(Number) numbe from Orders inner join FoodItems on Orders.IDFood = FoodItems.IDFood \r\n"
//					+ "where StatusOrder = N'Tiếp tục'\r\n"
//					+ "group by NameFood\r\n"
//					+ "ORDER BY numbe DESC;";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				Order t = new Order(result.getString("IDOrder"), result.getString("NameCustomer"), result.getString("NameFood"), result.getInt("Number"),result.getInt("Total"),result.getString("DateOrder"),result.getString("StatusOrder"));
				orderManagement.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}
		
	}

	@Override
	public Vector<Order> Search(String rollno) {
		Vector<Order> check = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();
			check = new Vector<Order>();
			String querySql ="select IDOrder,NameCustomer,NameFood,Number,Total,DateOrder,StatusOrder from Orders inner join Customers on Orders.IDCustomer=Customers.IDCustomer\r\n"
					+ "	      join FoodItems on Orders.IDFood=FoodItems.IDFood where IDOrder LIKE '%"+rollno+"%'";
			
			ResultSet result = state.executeQuery(querySql);
			while(result.next())
			{
				check.add(new Order(result.getString("IDOrder"),result.getString("NameCustomer"), result.getString("NameFood"), result.getInt("Number"), result.getInt("Total"), result.getString("DateOrder"), result.getString("StatusOrder")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public Vector<String> getID() {
		Vector<String> IDOrder = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();	
			IDOrder = new Vector<String>();
			String querySql = "select Orders.IDOrder from Orders;";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				IDOrder.add(result.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}
		return IDOrder;
	}

	@Override
	public Vector<String> getName() {
		return null;
	}

	
}
