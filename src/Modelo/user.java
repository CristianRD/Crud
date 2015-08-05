
package Modelo;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Cristian RD
 */
public class user {
   
    Conexion conexion;
    
    public user (){
        conexion = new Conexion();
    }


    public String InsertPersona(String user_id, String username , String pasword, String fullname,String email){
     String rptaregistro = null;
     
        try {
            String sql = "INSERT INTO Users (user_id, username, pasword, fullname, email) VALUES (?, ?, ?, ?, ?)";
            Connection acceso = conexion.getConexion();
            PreparedStatement st = acceso.prepareStatement(sql);
           
            st.setString(1, user_id);
            st.setString(2, username);
            st.setString(3, pasword);
            st.setString(4, fullname);
            st.setString(5, email);
            
            int filafect= st.executeUpdate();
            
            if (filafect > 0){
                rptaregistro = "Registro exitoso";
            }  
        } catch (Exception e) {
        }
     
     return rptaregistro;
    }
    
    public ArrayList<users> ListPersona(){
        
        ArrayList al = new ArrayList();
        users us;
        try {
            Connection acdb = conexion.getConexion();
            PreparedStatement ps = acdb.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                us = new users();
                us.setUser_id(rs.getString(1));
                us.setUsername(rs.getString(2));
                us.setPasword(rs.getString(3));
                us.setFullname(rs.getString(4));
                us.setEmail(rs.getString(5));
                
                al.add(us);
            }
        } catch (Exception e) {
        }
        return al;
    }
    
   public int deletePersona(String user_id){
        int filAfectadas= 0;
        try {
            Connection conex = conexion.getConexion();
            String sql = "DELETE FROM Users WHERE user_id=?";
            PreparedStatement cs = conex.prepareStatement(sql);
	    cs.setString(1,user_id);
            
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        
        return filAfectadas;
    
}
    public String editPersona(String username, String pasword, String fullname, String email,String user_id){
        String rptaregistro = null;
        try {
            Connection conex = conexion.getConexion();
            
            String sql = "UPDATE Users SET username=?, pasword=?, fullname=?, email=? WHERE user_id=?";
            
           PreparedStatement cs = conex.prepareStatement(sql);
           
            cs.setString(1, username);
            cs.setString(2, pasword);
            cs.setString(3, fullname);
            cs.setString(4, email);
            cs.setString(5, user_id);
            
             int filafect= cs.executeUpdate();
            
            if (filafect > 0){
               rptaregistro = "Actualizacion exitosa!!";
            }
        } catch (Exception e) {
        }
        return rptaregistro;  
    }
    
     public ArrayList<users> buscaPersona(String name){
        ArrayList listaPersona = new ArrayList();
        users persona;
        try {
            Connection acceDB = conexion.getConexion();
            
			String sql = "select * from users where username=?";
			
			PreparedStatement st = acceDB.prepareStatement(sql);
                        
                        
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while(rs.next()==true){
                persona = new users();
                persona.setUser_id(rs.getString(1));
                persona.setUsername(rs.getString(2));
                persona.setPasword(rs.getString(3));
                persona.setFullname(rs.getString(4));
                persona.setEmail(rs.getString(5));
                listaPersona.add(persona);
            }
        } catch (Exception e) {
        }
        return listaPersona;
    }
}

