package Model;

public class FoodItems {
	private String IDFood,NameFood;
	private Float PriceFood;
	private String Description;
	
	public FoodItems() {
	}
	

	public FoodItems(String iDFood, String nameFood) {
		IDFood = iDFood;
		NameFood = nameFood;
	}


	public FoodItems(String iDFood, String nameFood, Float priceFood, String description) {
		IDFood = iDFood;
		NameFood = nameFood;
		PriceFood = priceFood;
		Description = description;
	}

	public String getIDFood() {
		return IDFood;
	}

	public void setIDFood(String iDFood) {
		IDFood = iDFood;
	}

	public String getNameFood() {
		return NameFood;
	}

	public void setNameFood(String nameFood) {
		NameFood = nameFood;
	}

	public Float getPriceFood() {
		return PriceFood;
	}

	public void setPriceFood(Float priceFood) {
		PriceFood = priceFood;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return "FoodItems [IDFood=" + IDFood + ", NameFood=" + NameFood + ", PriceFood=" + PriceFood + ", Description="
				+ Description + "]";
	}
	
	

}
