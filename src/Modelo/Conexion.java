
package Modelo;
import java.sql.*;

/**
 *
 * @author Cristian RD
 */
public class Conexion {
    public Conexion() {
    }

    public Connection getConexion(){
        Connection con=null;
        try{
            /* Carga|Registra el driver JDBC */
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            /* Obtener la conexion */
	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb","root","crd3194");
        }catch(SQLException ex){
        }catch(Exception e){   
        }
        return con;
    }  
}
