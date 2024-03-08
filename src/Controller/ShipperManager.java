package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Model.InterFaceButton;
import Model.Shippers;

public class ShipperManager extends get_closeConnect implements InterFaceButton<Shippers>{
	private Vector<Shippers> shipperManagement = new Vector<Shippers>();

	public ShipperManager() {}

	public ShipperManager(Vector<Shippers> shipperManagement) {
		this.shipperManagement = shipperManagement;
	}

	public Vector<Shippers> getShipperManagement() {
		return shipperManagement;
	}

	public void setShipperManagement(Vector<Shippers> shipperManagement) {
		this.shipperManagement = shipperManagement;
	}

	@Override
	public void Add(Shippers t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		try {
			con = getConnection();
			String querySql = "insert into Shippers(IDShipper,NameShipper,IDOrder,PhoneShipper,DeliveryStatus) values (?,?,?,?,?) ";
			preparestatement = con.prepareStatement(querySql);
			
			preparestatement.setString(1,t.getIDShi());
			preparestatement.setString(2,t.getNameShi() );
			preparestatement.setString(3,t.getIDOr());
			preparestatement.setLong(4,Long.parseLong(t.getPhoneSh()) );					
			preparestatement.setString(5,t.getDiliverStatus());
			
			preparestatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, preparestatement);
		}
		
	}

	@Override
	public void Update(Shippers t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		try {
			con = getConnection();
			String querySql = "update Shippers set IDShipper = ?, NameShipper = ?, IDOrder = ?,PhoneShipper = ?,DeliveryStatus = ? where IDShipper = '"+t.getIDShi()+"'";
			preparestatement = con.prepareStatement(querySql);
			
			preparestatement.setString(1,t.getIDShi() );
			preparestatement.setString(2,t.getNameShi() );
			preparestatement.setString(3,t.getIDOr() );
			preparestatement.setLong(4,Long.parseLong(t.getPhoneSh()) );
			preparestatement.setString(5,t.getDiliverStatus());	
			
			preparestatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnect(con, preparestatement);
		}
		
	}

	@Override
	public void Dalete(Shippers t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		try {
			con = getConnection();
			String querySql = "delete from Shippers where IDShipper = '"+t.getIDShi()+"'";
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
		shipperManagement.clear();
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();					
			String querySql = "select * from Shippers;";					
			ResultSet result = state.executeQuery(querySql);
			while(result.next())
			{
				Shippers t = new Shippers(result.getString(1), result.getString(2), result.getString(3), result.getString(4),result.getString(5));
				shipperManagement.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}		
	}

	@Override
	public Vector<Shippers> Search(String rollno) {
		Vector<Shippers> check = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();	
			check = new Vector<Shippers>();
			String querySql = "select * from Shippers where IDShipper LIKE '%"+rollno+"%';";			
			ResultSet result = state.executeQuery(querySql);
			while(result.next())
			{
				check.add(new Shippers(result.getString("IDShipper"),result.getString("NameShipper"),result.getString("IDOrder"),result.getString("PhoneShipper"),result.getString("DeliveryStatus")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public Vector<String> getID() {
		Vector<String> IDShipper = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();
			IDShipper = new Vector<String>();
			String querySql = "select IDShipper from Shippers;";
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				IDShipper.add(result.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}
		return IDShipper;
	}

	@Override
	public Vector<String> getName() {
		return null;
	}

	
	
}	
