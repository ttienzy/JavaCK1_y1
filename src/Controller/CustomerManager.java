package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Model.Customers;
import Model.InterFaceButton;

public class CustomerManager extends get_closeConnect implements InterFaceButton<Customers>{
	private Vector<Customers> customerManagement = new Vector<Customers>();

	public CustomerManager() {}

	public CustomerManager(Vector<Customers> customerManagement) {
		this.customerManagement = customerManagement;
	}

	public Vector<Customers> getCustomerManagement() {
		return customerManagement;
	}

	public void setCustomerManagement(Vector<Customers> customerManagement) {
		this.customerManagement = customerManagement;
	}

	@Override
	public void Add(Customers t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		try {
			con = getConnection();
			String querySql = "insert into Customers(IDCustomer,NameCustomer,PhoneCustomer,AddressCustomer) values (?,?,?,?) ";
			preparestatement = con.prepareStatement(querySql);
			
			preparestatement.setString(1,t.getIDCus());
			preparestatement.setString(2,t.getNameCus() );
			preparestatement.setLong(3,Long.parseLong(t.getPhoneCus()) );
			preparestatement.setString(4,t.getAddressCus());
			preparestatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnect(con, preparestatement);
		}
	}

	@Override
	public void Update(Customers t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		
		try {
			con = getConnection();	
			String querySql = "update Customers set IDCustomer = ?, NameCustomer = ?, PhoneCustomer = ?,AddressCustomer = ? where IDCustomer = '"+t.getIDCus()+"'";
			preparestatement = con.prepareStatement(querySql);
			
			preparestatement.setString(1,t.getIDCus());
			preparestatement.setString(2,t.getNameCus() );
			preparestatement.setLong(3,Long.parseLong(t.getPhoneCus()) );
			preparestatement.setString(4,t.getAddressCus());
			preparestatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnect(con, preparestatement);
		}
		
	}

	@Override
	public void Dalete(Customers t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		try {
			con = getConnection();
			String querySql = "delete from Customers where IDCustomer = '"+t.getIDCus()+"'";
			preparestatement = con.prepareStatement(querySql);
			preparestatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnect(con, preparestatement);
		}
		
	}

	@Override
	public void ShowInfomation() {
		customerManagement.clear();
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();					
			String querySql = "select * from Customers;";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				Customers t = new Customers(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
				customerManagement.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}		
	}

	@Override
	public Vector<Customers> Search(String rollno) {
		Vector<Customers> check = null ;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();	
			check = new Vector<Customers>();
			String querySql = "select * from Customers where IDCustomer LIKE '%"+rollno+"%';";			
			ResultSet result = state.executeQuery(querySql);
			while(result.next())
			{
				check.add(new Customers(result.getString("IDCustomer"), result.getString("NameCustomer"),result.getString("PhoneCustomer"),result.getString("AddressCustomer")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}
		return check;
	}

	@Override
	public Vector<String> getID() {
		Vector<String> idCust = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();
			idCust = new Vector<String>();
			String querySql = "select Customers.IDCustomer from Customers;";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				idCust.add(result.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}
		return idCust;
	}

	@Override
	public Vector<String> getName() {
		Vector<String> nameCust = new Vector<String>();
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();
			String querySql = "select Customers.NameCustomer from Customers;";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				nameCust.add(result.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}
		return nameCust;
	}







	

}
