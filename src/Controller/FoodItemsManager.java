package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Model.FoodItems;
import Model.InterFaceButton;

public class FoodItemsManager extends get_closeConnect implements InterFaceButton<FoodItems> {
	private Vector<FoodItems> fooditemsManagement = new Vector<FoodItems>();

	public FoodItemsManager() {}

	public FoodItemsManager(Vector<FoodItems> fooditemsManagement) {
		this.fooditemsManagement = fooditemsManagement;
	}

	public Vector<FoodItems> getFooditemsManagement() {
		return fooditemsManagement;
	}

	public void setFooditemsManagement(Vector<FoodItems> fooditemsManagement) {
		this.fooditemsManagement = fooditemsManagement;
	}

	@Override
	public void Add(FoodItems t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		
		try {
			con = getConnection();
			String querySql = "insert into FoodItems(IDFood,NameFood,PriceFood,DesciptionFood) values (?,?,?,?) ";
			preparestatement = con.prepareStatement(querySql);
			
			preparestatement.setString(1,t.getIDFood());
			preparestatement.setString(2,t.getNameFood() );
			preparestatement.setFloat(3, t.getPriceFood());
			preparestatement.setString(4,t.getDescription());	
			
			preparestatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnect(con, preparestatement);
		}
		
	}

	@Override
	public void Update(FoodItems t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		try {
			con = getConnection();
			String querySql = "update FoodItems set IDFood = ?, NameFood = ?, PriceFood = ?,DescriptionFood = ? where IDFood = '"+t.getIDFood()+"'";
			preparestatement = con.prepareStatement(querySql);
			
			preparestatement.setString(1,t.getIDFood() );
			preparestatement.setString(2,t.getNameFood() );
			preparestatement.setFloat(3, t.getPriceFood());
			preparestatement.setString(4,t.getDescription());	
			
			preparestatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, preparestatement);
		}
		
	}

	@Override
	public void Dalete(FoodItems t) {
		Connection con = null;
		PreparedStatement preparestatement = null;
		try {
			con = getConnection();
			String querySql = "delete from FoodItems where IDFood = '"+t.getIDFood()+"'";
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
		fooditemsManagement.clear();
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();					
			String querySql = "select Top 5 * from FoodItems ORDER BY NameFood DESC";					
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				FoodItems t = new FoodItems(result.getString(1), result.getString(2),Float.valueOf(result.getString(3)), result.getString(4));
				fooditemsManagement.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}
		
	}

	@Override
	public Vector<FoodItems> Search(String rollno) {
		Vector<FoodItems> check = null ;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();	
			check = new Vector<FoodItems>();
			String querySql = "select * from FoodItems where IDFood LIKE '%"+rollno+"%';";
			ResultSet result = state.executeQuery(querySql);
			while(result.next())
			{
				check.add(new FoodItems(result.getString("IDFood"), result.getString("NameFood"), result.getFloat("PriceFood"), result.getString("DescriptionFood")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}
		return check;
	}

	@Override
	public Vector<String> getID() {
		Vector<String> IDFood = null;
		Connection con = null;
		Statement state = null;
		try {
			con = getConnection();
			state = con.createStatement();		
			String querySql = "select FoodItems.IDFood from FoodItems;";
			IDFood = new Vector<String>();
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				IDFood.add(result.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}
		return IDFood;
	}

	@Override
	public Vector<String> getName() {
		Vector<String> NameFood = null;
		
		Connection con = null;
		Statement state = null;
		
		try {
			con = getConnection();
			state = con.createStatement();		
			String querySql = "select FoodItems.NameFood from FoodItems;";
			NameFood = new Vector<String>();
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				NameFood.add(result.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}
		return NameFood;
	};
	
	public String getPriceTotal(String NameFood,int Number)
	{
		fooditemsManagement.clear();
		Connection con = null;
		Statement state = null;
		float total=0;
		try {
			con = getConnection();
			state = con.createStatement();		
			String querySql = "select *from FoodItems;";			
			ResultSet result = state.executeQuery(querySql);
			while(result.next()) {
				fooditemsManagement.add(new FoodItems(result.getString(1),result.getString(2),Float.parseFloat(result.getString(3)),result.getString(4)));
			}
			for(FoodItems x : fooditemsManagement)
			{
				if(x.getNameFood().equals(NameFood))
				{
					total = x.getPriceFood()*Number;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnect(con, state);
		}
		return String.valueOf(total);
	}

}
