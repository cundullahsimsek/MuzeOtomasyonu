package Model;

public class Muze {
	private int id;
	private String adi;
	
	public Muze() {}
	public Muze(int id, String adi) {
		super();
		this.id = id;
		this.adi = adi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}

}
