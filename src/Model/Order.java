package Model;

public class Order {
	private String IDord,NameCust,NameFood;
	private int Number,Total;
	private String DateOrder,Statu;
	
	public Order() {
	}

	public Order(String iDord, String nameCust, String nameFood, int number, int total, String dateOrder,
			String statu) {
		IDord = iDord;
		NameCust = nameCust;
		NameFood = nameFood;
		Number = number;
		Total = total;
		DateOrder = dateOrder;
		Statu = statu;
	}

	public String getIDord() {
		return IDord;
	}

	public void setIDord(String iDord) {
		IDord = iDord;
	}

	public String getNameCust() {
		return NameCust;
	}

	public void setNameCust(String nameCust) {
		NameCust = nameCust;
	}

	public String getNameFood() {
		return NameFood;
	}

	public void setNameFood(String nameFood) {
		NameFood = nameFood;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public int getTotal() {
		return Total;
	}

	public void setTotal(int total) {
		Total = total;
	}

	public String getDateOrder() {
		return DateOrder;
	}

	public void setDateOrder(String dateOrder) {
		DateOrder = dateOrder;
	}

	public String getStatu() {
		return Statu;
	}

	public void setStatu(String statu) {
		Statu = statu;
	}

	@Override
	public String toString() {
		return "Order [IDord=" + IDord + ", NameCust=" + NameCust + ", NameFood=" + NameFood + ", Number=" + Number
				+ ", Total=" + Total + ", DateOrder=" + DateOrder + ", Statu=" + Statu + "]";
	}
	
	
	
	
	

}
