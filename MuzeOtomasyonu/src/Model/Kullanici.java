package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Kullanici extends User {
	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;

	public Kullanici() {
		super();
	}

	public Kullanici(int id, String tcno, String adi, String soyadi, String turu, String sifre) {
		super(id, tcno, adi, soyadi, turu, sifre);
	}

	public boolean addWhour(int kullanici_id, String kullanici_adi, String tarih) throws SQLException {
		int key=0;
		int count=0;
		String query = "INSERT INTO whour" + "(kullanici_id, kullanici_adi, tarih) VALUES" + "(?,?,?)";
		
		try {
			st = con.createStatement();
			rs =st.executeQuery("SELECT * FROM whour WHERE statu= 'a' AND kullanici_id = kullanici_id AND tarih ='"+tarih+"'");
			while (rs.next()) {
				count ++;
				break;
			}
			
			if (count ==0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, kullanici_id);
				preparedStatement.setString(2, kullanici_adi);
				preparedStatement.setString(3, tarih);
				preparedStatement.executeUpdate();
			}
			
			key=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(key==1)
			return true;
		else 
			return false;
		
	}
	public ArrayList <Whour> getWhourList(int kullanici_id) throws SQLException{
   	 ArrayList<Whour> list = new ArrayList<>();
   	
   	Whour obj;
   	 try {
   		 st = con.createStatement();
   		 rs = st.executeQuery("SELECT * FROM whour WHERE statu ='a' AND kullanici_id =" + kullanici_id);
       	 while (rs.next()) {
       		 
       		 obj = new Whour();
       		 obj.setId(rs.getInt("id"));
       		 obj.setKullanici_id(rs.getInt("kullanici_id"));
       		 obj.setKullanici_adi(rs.getString("kullanici_adi"));
       		 obj.setTarih(rs.getString("tarih"));
       		 obj.setStatu(rs.getString("statu"));
       		 
       		 
       		 list.add(obj);
       	 }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
   	 return list;
    }
	
	public boolean  deleteWhour(int id ) throws SQLException {
   	 
   	 String query = "DELETE FROM whour WHERE id =?";
   	 boolean key =false;
   	 try {
   		 st = con.createStatement();
       	 preparedStatement = con.prepareStatement(query);
       	 preparedStatement.setInt(1, id);
       	 
       	 preparedStatement.executeUpdate();
       	 key = true;
       	 
		} catch (Exception e) {
			e.printStackTrace();
		}   
   	 if (key)
   		 return true;
   	 else 
   		 return false;
    }

}
