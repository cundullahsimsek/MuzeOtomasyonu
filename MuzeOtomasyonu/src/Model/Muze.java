package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Muze {
	
	private int id;
	private String adi;
	 DBConnection  conn = new DBConnection();
	 Statement st = null;
	 ResultSet rs = null;
	 PreparedStatement preparedStatement =null;
	
	public Muze() {}
	public Muze(int id, String adi) {
		super();
		this.id = id;
		this.adi = adi;
	}

     
	
     public ArrayList <Muze> getList() throws SQLException{
    	 ArrayList<Muze> list = new ArrayList<>();
    	 Muze obj;
    	 Connection con = conn.connDb();

    	 try {
    		 st=con.createStatement();
    		 rs= st.executeQuery("SELECT * FROM muze");
    		 while (rs.next()) {
    			 obj=new Muze();
    			 obj.setId(rs.getInt("id"));
    			 obj.setAdi(rs.getString("adi"));
    			 list.add(obj);
    		 }
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }finally {
    		 st.close();
    		 rs.close();
    		 con.close();
    	 }
    	 return list;
     }
     
     public Muze getFetch (int id) {
    	 Connection con = conn.connDb();
    	 Muze m = new Muze ();
    	 try {
			st=con.createStatement();
	    	 rs = st.executeQuery("SELECT * FROM muze WHERE id = " + id);
	    	 while (rs.next()) {
	    		m.setId(rs.getInt("id"));
	    		m.setAdi(rs.getString("adi"));
	    		break;
	    	 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return m;
     }
    	 public boolean  addMuze( String adi) throws SQLException {
        	 
        	 String query = "INSERT INTO muze " + "(adi) VALUES" + "(?)";
        	 boolean key =false;
        	 Connection con = conn.connDb();
        	 try {
        		 st = con.createStatement();
            	 preparedStatement = con.prepareStatement(query);
            	 preparedStatement.setString(1, adi);
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
	
    	 public boolean  deleteMuze(int id ) throws SQLException {
        	 
        	 String query = "DELETE FROM muze WHERE id =?";
        	 boolean key =false;
        	 Connection con = conn.connDb();
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
    	 public boolean  updateMuze(int id, String adi ) throws SQLException {
    		 
    		 String query = "UPDATE muze SET  adi=?  WHERE id=? ";
    		 boolean key =false;
    		 Connection con = conn.connDb();
    		 try {
    			 st = con.createStatement();
    	    	 preparedStatement = con.prepareStatement(query);
    	    	 preparedStatement.setString(1, adi);
    	    	 preparedStatement.setInt(2, id); 
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
