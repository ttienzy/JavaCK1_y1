package Model;

public class Customers {
	private String IDCus,NameCus,PhoneCus,AddressCus;

	public Customers() {};
	
	public Customers(String iDCus, String nameCus) {
		IDCus = iDCus;
		NameCus = nameCus;
	}

	public Customers(String iDCus, String nameCus, String phoneCus, String addressCus) {
		IDCus = iDCus;
		NameCus = nameCus;
		PhoneCus = phoneCus;
		AddressCus = addressCus;
	}

	public String getIDCus() {
		return IDCus;
	}

	public void setIDCus(String iDCus) {
		IDCus = iDCus;
	}

	public String getNameCus() {
		return  NameCus;
	}

	public void setNameCus(String nameCus) {
		NameCus = nameCus;
	}

	public String getPhoneCus() {
		return PhoneCus;
	}

	public void setPhoneCus(String phoneCus) {
		PhoneCus = phoneCus;
	}

	public String getAddressCus() {
		return AddressCus;
	}

	public void setAddressCus(String addressCus) {
		AddressCus = addressCus;
	}

	@Override
	public String toString() {
		return "Customers [IDCus=" + IDCus + ", NameCus=" + NameCus + ", PhoneCus=" + PhoneCus + ", AddressCus="
				+ AddressCus + "]";
	};
	
	

}
