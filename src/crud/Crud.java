
package crud;
import Modelo.*;
import Vista.*;
import Controlador.*;


/**
 *
 * @author Cristian RD
 */
public class Crud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFcrud vistac = new JFcrud();
        user modeloc = new user();
        Controladorcrud controlc = new Controladorcrud(vistac, modeloc);
        
        vistac.setVisible(true);
        vistac.setLocationRelativeTo(null);
        
    }
    
}
