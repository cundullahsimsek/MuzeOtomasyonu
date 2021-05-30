package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Helper.DBConnection;

public class Whour {
private int id,kullanici_id;
private String kullanici_adi,tarih,statu;
DBConnection  conn = new DBConnection();
Statement st = null;
ResultSet rs = null;
PreparedStatement preparedStatement =null;

public Whour(int id, int kullanici_id, String kullanici_adi, String tarih, String statu) {
	
	this.id = id;
	this.kullanici_id = kullanici_id;
	this.kullanici_adi = kullanici_adi;
	this.tarih = tarih;
	this.statu = statu;
}
public Whour() {}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getKullanici_id() {
	return kullanici_id;
}

public void setKullanici_id(int kullanici_id) {
	this.kullanici_id = kullanici_id;
}

public String getKullanici_adi() {
	return kullanici_adi;
}

public void setKullanici_adi(String kullanici_adi) {
	this.kullanici_adi = kullanici_adi;
}

public String getTarih() {
	return tarih;
}

public void setTarih(String tarih) {
	this.tarih = tarih;
}

public String getStatu() {
	return statu;
}

public void setStatu(String statu) {
	this.statu = statu;
}

}
