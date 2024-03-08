package Model;

public class Shippers {
	private String IDShi,NameShi,IDOr,PhoneSh,DiliverStatus;

	public Shippers() {
	}

	public Shippers(String iDShi, String nameShi) {
		IDShi = iDShi;
		NameShi = nameShi;
	}

	public Shippers(String iDShi, String nameShi, String iDOr, String phoneSh, String diliverStatus) {
		IDShi = iDShi;
		NameShi = nameShi;
		IDOr = iDOr;
		PhoneSh = phoneSh;
		DiliverStatus = diliverStatus;
	}

	public String getIDShi() {
		return IDShi;
	}

	public void setIDShi(String iDShi) {
		IDShi = iDShi;
	}

	public String getNameShi() {
		return NameShi;
	}

	public void setNameShi(String nameShi) {
		NameShi = nameShi;
	}

	public String getIDOr() {
		return IDOr;
	}

	public void setIDOr(String iDOr) {
		IDOr = iDOr;
	}

	public String getPhoneSh() {
		return PhoneSh;
	}

	public void setPhoneSh(String phoneSh) {
		PhoneSh = phoneSh;
	}

	public String getDiliverStatus() {
		return DiliverStatus;
	}

	public void setDiliverStatus(String diliverStatus) {
		DiliverStatus = diliverStatus;
	}

	@Override
	public String toString() {
		return "Shippers [IDShi=" + IDShi + ", NameShi=" + NameShi + ", IDOr=" + IDOr + ", PhoneSh=" + PhoneSh
				+ ", DiliverStatus=" + DiliverStatus + "]";
	}
	

}
