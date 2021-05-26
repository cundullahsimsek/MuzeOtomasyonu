package Model;

import Helper.DBConnection;

public class User {
	private int id;
	String tcno,adi,soyadi,turu,sifre;
	DBConnection  conn = new DBConnection();
	
	
	public User(int id, String tcno, String adi, String soyadi, String turu, String sifre) {
		
		this.id = id;
		this.tcno = tcno;
		this.adi = adi;
		this.soyadi = soyadi;
		this.turu = turu;
		this.sifre = sifre;
	}
	
	public User() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTcno() {
		return tcno;
	}
	public void setTcno(String tcno) {
		this.tcno = tcno;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public String getSoyadi() {
		return soyadi;
	}
	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}
	public String getTuru() {
		return turu;
	}
	public void setTuru(String turu) {
		this.turu = turu;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	
	

}
