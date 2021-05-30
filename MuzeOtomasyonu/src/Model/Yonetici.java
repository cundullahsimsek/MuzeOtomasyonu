package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Yonetici extends User {
	 Statement st = null;
	 ResultSet rs = null;
	 Connection con = conn.connDb();
	 PreparedStatement preparedStatement =null;	 

	public Yonetici(int id, String tcno, String adi, String soyadi, String turu, String sifre) {
		super(id, tcno, adi, soyadi, turu, sifre);
		// TODO Auto-generated constructor stub
	}
	
     public Yonetici() {}
     
     public ArrayList <User> getPersonelList() throws SQLException{
    	 ArrayList<User> list = new ArrayList<>();
    	
    	 User obj;
    	 try {
    		 st = con.createStatement();
    		 rs = st.executeQuery("SELECT * FROM kullanici WHERE turu = 'gise_personeli'  ");
        	 while (rs.next()) {
        		 
        		 obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("adi"),rs.getString("soyadi"), rs.getString("turu"), rs.getString("sifre") );
        		 list.add(obj);
        	 }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	 
  
    	 return list;
     }
     
     public ArrayList <User> getMuzePersonelList(int muze_id) throws SQLException{
    	 ArrayList<User> list = new ArrayList<>();
    	
    	 User obj;
    	 try {
    		 st = con.createStatement();
    		 rs = st.executeQuery("SELECT u.id, u.tcno, u.adi,u.soyadi, u.turu, u.sifre  FROM calisan c LEFT JOIN kullanici u ON c.kullanici_id= u.id WHERE muze_id= " + muze_id);
        	 while (rs.next()) {
        		 
        		 obj = new User(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.adi"),rs.getString("u.soyadi"), rs.getString("u.turu"), rs.getString("u.sifre") );
        		 list.add(obj);
        	 }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	 return list;
     }
     
     public boolean  addPersonel(String tcno, String adi, String soyadi, String sifre ) throws SQLException {
    	 
    	 String query = "INSERT INTO kullanici " + "(tcno, adi, soyadi, turu,sifre) VALUES" + "(?,?,?,?,?)";
    	 boolean key =false;
    	 try {
    		 st = con.createStatement();
        	 preparedStatement = con.prepareStatement(query);
        	 preparedStatement.setString(1, tcno);
        	 preparedStatement.setString(2, adi);
        	 preparedStatement.setString(3, soyadi);
        	 preparedStatement.setString(4, "Gise_Personeli");
        	 preparedStatement.setString(5, sifre);
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
     
 public boolean  deletePersonel(int id ) throws SQLException {
    	 
    	 String query = "DELETE FROM kullanici WHERE id =?";
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
 public boolean  updatePersonel(int id, String tcno, String adi, String soyadi, String sifre ) throws SQLException {
	 
	 String query = "UPDATE kullanici SET tcno =?, adi=?, soyadi=?, sifre=? WHERE id=? ";
	 boolean key =false;
	 try {
		 st = con.createStatement();
    	 preparedStatement = con.prepareStatement(query);
    	 preparedStatement.setString(1, tcno);
    	 preparedStatement.setString(2, adi);
    	 preparedStatement.setString(3, soyadi);
    	 preparedStatement.setString(4, sifre);
    	 preparedStatement.setInt(5, id);
    	 
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
 
 public boolean  addcalisan(int kullanici_id ,int muze_id) throws SQLException {
	 
	 String query = "INSERT INTO calisan " + "(kullanici_id, muze_id) VALUES" + "(?,?)";
	 boolean key =false;
	 int count=0;
	 try {
		 st = con.createStatement();
		 //rs= st.executeQuery("SELECT * FROM calisan WHERE muze_id=" + muze_id + "AND kullanici_id= " +kullanici_id );
		 rs= st.executeQuery("SELECT * FROM calisan WHERE muze_id= ' muze_id' AND kullanici_id='kullanici_id' ");
		 
		 while (rs.next()) {
			 count++; 
		 } 
		 if (count==0) {
		 
    	 preparedStatement = con.prepareStatement(query);
    	 preparedStatement.setInt(1, kullanici_id);
    	 preparedStatement.setInt(2, muze_id);
    	 preparedStatement.executeUpdate();
		 }
		 
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

