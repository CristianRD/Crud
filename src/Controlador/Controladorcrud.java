
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.*;
import Vista.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author Cristian RD
 */
public class Controladorcrud implements ActionListener, KeyListener{
    
    JFcrud vistacrud = new  JFcrud();
    user modelcrud = new user();
    
    public Controladorcrud(JFcrud vistacrud, user modelcrud){
        this.vistacrud = vistacrud;
        this.modelcrud = modelcrud;
        this.vistacrud.btnguardar.addActionListener(this);
        this.vistacrud.btnver.addActionListener(this);
        this.vistacrud.btnactualizar.addActionListener(this);
        this.vistacrud.btnborrar.addActionListener(this);
        this.vistacrud.btnok.addActionListener(this);
        this.vistacrud.txtbuscar.addKeyListener(this);
        this.vistacrud.txtuser_id.addKeyListener(this);
        this.vistacrud.txtusername.addKeyListener(this);
        this.vistacrud.txtpassword.addKeyListener(this);
        this.vistacrud.txtfullname.addKeyListener(this);
        this.vistacrud.txtemail.addKeyListener(this);
        
    }
    
    public void inicializarcrud(){
        
    }
    public void llenartable(JTable tabledb){
      
        DefaultTableModel modelotabla = new DefaultTableModel();
        tabledb.setModel(modelotabla);
        
        modelotabla.addColumn("user_id");
        modelotabla.addColumn("username");
        modelotabla.addColumn("password");
        modelotabla.addColumn("fullname");
        modelotabla.addColumn("email");
        
        Object[] columna = new Object[5];
        
        int numregistros = modelcrud.ListPersona().size();
        
        for (int i = 0; i < numregistros; i++) {
            columna[0] = modelcrud.ListPersona().get(i).getUser_id();
            columna[1] = modelcrud.ListPersona().get(i).getUsername();
            columna[2] = modelcrud.ListPersona().get(i).getPasword();
            columna[3] = modelcrud.ListPersona().get(i).getFullname();
            columna[4] = modelcrud.ListPersona().get(i).getEmail();
            modelotabla.addRow(columna);
            
        }
        
    }
     public void LimpiarCampos(){
         
        vistacrud.txtuser_id.setText(""); 
        vistacrud.txtusername.setText("");
        vistacrud.txtpassword.setText("");
        vistacrud.txtfullname.setText("");
        vistacrud.txtemail.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if (e.getSource() == vistacrud.btnguardar){
            
            String user_id =vistacrud.txtuser_id.getText();
            String username = vistacrud.txtusername.getText();
            String pasword = vistacrud.txtpassword.getText();
            String fullname = vistacrud.txtfullname.getText();
            String email = vistacrud.txtemail.getText();
            
            String rptaregistro = modelcrud.InsertPersona(user_id, username, pasword, fullname, email);
            
            if (rptaregistro!=null){
                llenartable(vistacrud.jtdatos);
                JOptionPane.showMessageDialog(null, rptaregistro);
                LimpiarCampos();
            }else{
                JOptionPane.showMessageDialog(null, "Registro Erroneo");
            }
        }
        
        if(e.getSource() == vistacrud.btnver){
            llenartable(vistacrud.jtdatos);
                
        }  
        if(e.getSource() == vistacrud.btnactualizar){
            int filaEditar = vistacrud.jtdatos.getSelectedRow();
            int numfilas = vistacrud.jtdatos.getSelectedRowCount();
            
            if(filaEditar>=0 && numfilas==1){
                vistacrud.txtuser_id.setText(String.valueOf(vistacrud.jtdatos.getValueAt(filaEditar,0)));
                vistacrud.txtuser_id.setEditable(false);
                vistacrud.btnok.setEnabled(true);
                vistacrud.btnactualizar.setEnabled(false);
                vistacrud.btnborrar.setEnabled(false);
                vistacrud.btnguardar.setEnabled(false);
                vistacrud.btnver.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione registro a editar");
            }
            
        }
          
        if(e.getSource() == vistacrud.btnok){
            
            String user_id = vistacrud.txtuser_id.getText();
            String username = vistacrud.txtusername.getText();
            String pasword = vistacrud.txtpassword.getText();
            String fullname = vistacrud.txtfullname.getText();
            String email = vistacrud.txtemail.getText();
            
            String rptEdit = modelcrud.editPersona(username, pasword, fullname, email, user_id);
            
            if(rptEdit!=null){
                JOptionPane.showMessageDialog(null,rptEdit );
                LimpiarCampos();
                llenartable(vistacrud.jtdatos);
            }else{
                JOptionPane.showMessageDialog(null, "Error, No se logro actualizar");
                llenartable(vistacrud.jtdatos);
                LimpiarCampos();
            }
            vistacrud.txtuser_id.setEditable(true);
            vistacrud.txtusername.setEditable(true);
            vistacrud.btnok.setEnabled(false);
            vistacrud.btnactualizar.setEnabled(true);
            vistacrud.btnborrar.setEnabled(true);
            vistacrud.btnguardar.setEnabled(true);
            vistacrud.btnver.setEnabled(true);
        }
        
        if(e.getSource() == vistacrud.btnborrar){
            int filInicio = vistacrud.jtdatos.getSelectedRow();
            int numfilas = vistacrud.jtdatos.getSelectedRowCount();
            ArrayList<String> listauser_id = new ArrayList<>();
            String user_id;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    user_id = String.valueOf(vistacrud.jtdatos.getValueAt(i+filInicio, 0));
                    listauser_id.add(i, user_id);
                }

                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro con el Numero: "+listauser_id.get(j)+"? ");
                    if(rpta==0){
                        modelcrud.deletePersona(listauser_id.get(j));
                        
                    }
                }
                llenartable(vistacrud.jtdatos);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }else{
                JOptionPane.showMessageDialog(null, "Elija al menos un registro para eliminar.");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
         if(e.getSource() == vistacrud.txtuser_id){
            char c = e.getKeyChar();
            if(c<'0' || c>'9'){
                e.consume();
            }
        }
        
     /*    
        if(e.getSource() == vistacrud.txtusername || e.getSource() == vistacrud.txtpassword || e.getSource() == vistacrud.txtfullname || e.getSource() == vistacrud.txtemail){
            char c = e.getKeyChar();
            if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c!=(char)KeyEvent.VK_SPACE)){
                e.consume();
            }
        }
             */
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()== vistacrud.txtbuscar){
            
            String username = vistacrud.txtbuscar.getText();
            
            DefaultTableModel  modelotabla = new DefaultTableModel();
            vistacrud.jtdatos.setModel(modelotabla);

              modelotabla.addColumn("user_id");
              modelotabla.addColumn("username");
              modelotabla.addColumn("password");
              modelotabla.addColumn("fullname");
              modelotabla.addColumn("email");

            Object[] columna = new Object[5];

            int numRegistros = modelcrud.buscaPersona(username).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = modelcrud.buscaPersona(username).get(i).getUser_id();
                columna[1] = modelcrud.buscaPersona(username).get(i).getUsername();
                columna[2] = modelcrud.buscaPersona(username).get(i).getPasword();
                columna[3] = modelcrud.buscaPersona(username).get(i).getFullname();
                columna[4] = modelcrud.buscaPersona(username).get(i).getEmail();
                modelotabla.addRow(columna);
            }
        }
        
    }
}
