/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.slyver.db;

import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.slyver.ui.StructureAdminUI;
import static org.slyver.db.oracledb.*;



public class StructureAdminDB {
    
    
    
  public void AdminStatusDB() throws SQLException{
      
      
      sql = "SELECT NOMBRE_EMPRESA FROM GEN_EMPRESA";
      
      rs = statement.executeQuery(sql);
      
      DefaultComboBoxModel model = (DefaultComboBoxModel) StructureAdminUI.EmpresasAdmin.getModel(); 
      
      while(rs.next()){
          
         model.addElement(rs.getString(1));
          
      }
      
      StructureAdminUI.EmpresasAdmin.setModel(model);
      
    }
  
  public void UsersTableDB() throws SQLException{
      
      int userlist = LoginDb.useremp;
      
      Object[] rowData = null;
      
      sql = "SELECT * FROM GEN_USERS WHERE ID_EMPRESA = "+userlist+"";
      
      DefaultTableModel model = (DefaultTableModel) StructureAdminUI.userTable.getModel(); 
      
      rs = statement.executeQuery(sql);
      
      model.setNumRows(0);
      
      while(rs.next()){
        
        rowData = new Object[] {
            rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(5) 
        };
          
        model.addRow(rowData);
          
      }
      
      StructureAdminUI.userTable.setModel(model);
      
  }
  
  public void SaveUserTableDB(Object[][] UserList) throws SQLException{
      
      
    for (Object[] result : UserList) {
        if(result[1] != "" || result[2] != "" || "" != result[3]){
        sql = "MERGE INTO GEN_USERS USING dual ON ( ID="+result[0]+" ) \n" +
        "WHEN MATCHED THEN UPDATE SET USUARIO='"+result[1]+"' , CONTRASENA='"+result[2]+"', COMENTARIOS='"+result[3]+"' \n" +
        "WHEN NOT MATCHED THEN INSERT (USUARIO,CONTRASENA,ID_EMPRESA,COMENTARIOS) \n" +
        "VALUES ( '"+result[1]+"', '"+result[2]+"', '"+LoginDb.useremp+"', '"+result[3]+"')";
        System.out.println(sql);
        statement.executeUpdate(sql);
        }
        
    }
    UsersTableDB();
    PermissionCombo();
      
  }
  
  public void DeleteUser(int ID) throws SQLException{
      
      
    int dialogResult = JOptionPane.showConfirmDialog(null, "Desea eliminar este usuario?", "Aviso", 1);
    
    if(dialogResult == JOptionPane.YES_OPTION){
        sql="DELETE FROM GEN_USERS WHERE ID="+ID;
        statement.executeUpdate(sql);
        DefaultTableModel model = (DefaultTableModel) StructureAdminUI.userTable.getModel();
        int row = StructureAdminUI.userTable.getSelectedRow();
        model.removeRow(row);
    }
     PermissionCombo(); 
  }
  
    public void setUserEmpDB() throws SQLException{
        
        sql="SELECT ID_EMPRESA FROM GEN_EMPRESA WHERE NOMBRE_EMPRESA = '"+StructureAdminUI.EmpresasAdmin.getSelectedItem().toString()+"'";
        
        rs = statement.executeQuery(sql);
                
        while(rs.next()){
            LoginDb.useremp = rs.getInt(1);
        }
        UsersTableDB();
    }
    
    public void EmpListsDB() throws SQLException {
        
        
        int userlist = LoginDb.useremp;
        
        sql = "SELECT DISTINCT NOMBRE FROM GEN_LISTS WHERE ID_EMPRESA = "+userlist+"";
        
        rs = statement.executeQuery(sql);
        
        DefaultComboBoxModel model = (DefaultComboBoxModel) StructureAdminUI.Lists.getModel();
        

        while(rs.next()){

           model.addElement(rs.getString(1));

        }

        StructureAdminUI.Lists.setModel(model);
        
    }
    
    public void ListTableDB(String list) throws SQLException{
      
      
      int userlist = LoginDb.useremp;
      
      Object[] rowData = null;
      
      sql = "SELECT * FROM GEN_LISTS WHERE ID_EMPRESA = "+userlist+" AND NOMBRE = '"+list+"'";
      
      DefaultTableModel model = (DefaultTableModel) StructureAdminUI.ListTable.getModel(); 
      
      model.setRowCount(0);
      
      rs = statement.executeQuery(sql);
      
      while(rs.next()){
        
        rowData = new Object[] {
            rs.getInt(1),rs.getString(3) 
        };
          
        model.addRow(rowData);
          
      }
      
      StructureAdminUI.ListTable.setModel(model);
      
  }
    
  public void SaveListTableDB(Object[][] UserList) throws SQLException{
      
      
    for (Object[] result : UserList) {
        if(result[1] != ""){
        sql = "MERGE INTO GEN_LISTS USING dual ON ( ID="+result[0]+" ) \n" +
        "WHEN MATCHED THEN UPDATE SET DATO='"+result[1]+"' \n" +
        "WHEN NOT MATCHED THEN INSERT (NOMBRE, DATO, ID_EMPRESA) \n" +
        "VALUES ( '"+StructureAdminUI.NewList.getText()+"', '"+result[1]+"', '"+LoginDb.useremp+"')";
        System.out.println(sql);
        statement.executeUpdate(sql);
        }
        
    }
    EmpListsDB();
    ListTableDB(StructureAdminUI.NewList.getText());
      
  }
  
  public void DeleteDataList(int ID) throws SQLException{
      
      
    int dialogResult = JOptionPane.showConfirmDialog(null, "Desea eliminar este dato?", "Aviso", 1);
    
    if(dialogResult == JOptionPane.YES_OPTION){
        sql="DELETE FROM GEN_LISTS WHERE ID="+ID;
        statement.executeUpdate(sql);
        DefaultTableModel model = (DefaultTableModel) StructureAdminUI.ListTable.getModel();
        int row = StructureAdminUI.ListTable.getSelectedRow();
        model.removeRow(row);
    }
    ListTableDB(StructureAdminUI.NewList.getText());
  }
  
  public void TablesListsDB() throws SQLException {

        
        int userlist = LoginDb.useremp;
        
        sql = "SELECT DISTINCT NOMBRE_TABLA FROM GEN_COLUMNAS WHERE ID_EMPRESA = "+userlist+"";
        
        rs = statement.executeQuery(sql);
        
        DefaultComboBoxModel model = (DefaultComboBoxModel) StructureAdminUI.TableList.getModel();
        

        while(rs.next()){

           model.addElement(rs.getString(1));

        }

        StructureAdminUI.TableList.setModel(model);
        
    }
    
    public void ListColumnsDB(String list) throws SQLException{
      
      
      int userlist = LoginDb.useremp;
      
      Object[] rowData = null;
      
      sql = "SELECT * FROM GEN_COLUMNAS WHERE ID_EMPRESA = "+userlist+" AND NOMBRE_TABLA = '"+list+"' ORDER BY ORDEN ASC";
      
      System.out.println(sql);
      
      DefaultTableModel model = (DefaultTableModel) StructureAdminUI.TablaColumnas.getModel(); 
      
      model.setRowCount(0);
      
      rs = statement.executeQuery(sql);
      
      while(rs.next()){
        
        rowData = new Object[] {
            rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(5)+" - "+rs.getString(8),rs.getString(9)
        };
          
        model.addRow(rowData);
          
      }
      
      StructureAdminUI.TablaColumnas.setModel(model);
      
  }
    
  public void SaveColumnsTableDB(Object[][] UserList) throws SQLException{
      
      
    for (Object[] result : UserList) {
        if(result[1] != "" || result[2] != "" || result[3] != "" || result[4] != "" || result[5] != ""){
        
        String[] uid = result[4].toString().split("-");
            
        sql = "MERGE INTO GEN_COLUMNAS USING dual ON ( ID="+result[0]+" ) \n" +
        "WHEN MATCHED THEN UPDATE SET NOMBRE_COLUMNA='"+result[1]+"', TIPO_DATO='"+result[2]+"', TAMANO='"+result[3]+"', ID_USUARIO='"+Integer.parseInt(uid[0].trim())+"', USER_NAME='"+uid[1].trim()+"', ORDEN="+result[5]+" \n" +
        "WHEN NOT MATCHED THEN INSERT (NOMBRE_TABLA, NOMBRE_COLUMNA, TIPO_DATO, ID_USUARIO, TAMANO, ID_EMPRESA, USER_NAME, ORDEN) \n" +
        "VALUES ( '"+StructureAdminUI.NewTable.getText()+"', '"+result[1]+"', '"+result[2]+"', "+Integer.parseInt(uid[0].trim())+", "+result[3]+", '"+LoginDb.useremp+"', '"+uid[1].trim()+"', "+result[5]+" )";
        System.out.println(sql);
        statement.executeUpdate(sql);
        }
        
    }
    TablesListsDB();
    ListColumnsDB(StructureAdminUI.NewTable.getText());
      
  }
  
  public void DeleteDataColumns(int ID) throws SQLException{
      
      
    int dialogResult = JOptionPane.showConfirmDialog(null, "Desea eliminar este dato?", "Aviso", 1);
    
    if(dialogResult == JOptionPane.YES_OPTION){
        sql="DELETE FROM GEN_COLUMNAS WHERE ID="+ID;
        statement.executeUpdate(sql);
        DefaultTableModel model = (DefaultTableModel) StructureAdminUI.TablaColumnas.getModel();
        int row = StructureAdminUI.TablaColumnas.getSelectedRow();
        model.removeRow(row);
    }
    ListColumnsDB(StructureAdminUI.NewTable.getText());
  }
  
  public void PermissionCombo() throws SQLException{
      
      
      int userlist = LoginDb.useremp;
      
      sql = "SELECT ID, USUARIO FROM GEN_USERS WHERE ID_EMPRESA ="+userlist;
      
      rs = statement.executeQuery(sql);
      
      DefaultComboBoxModel model = (DefaultComboBoxModel) StructureAdminUI.UserPermision.getModel(); 
      
      model.removeAllElements();
      
      while(rs.next()){
          
         model.addElement(rs.getString(1)+" - "+rs.getString(2));
          
      }
      
      StructureAdminUI.UserPermision.setModel(model);
      
    }
    
    public void TipoDatoCombo() throws SQLException{
      
      
      int userlist = LoginDb.useremp;
      
      sql = "SELECT DISTINCT NOMBRE FROM GEN_LISTS WHERE ID_EMPRESA ="+userlist;
      
      rs = statement.executeQuery(sql);
      
      DefaultComboBoxModel model = (DefaultComboBoxModel) StructureAdminUI.TipoDato.getModel(); 
      
      while(rs.next()){
          
         model.addElement("Combo:"+rs.getString(1));
          
      }
      
      StructureAdminUI.TipoDato.setModel(model);
      
    }
    
    public String getUser(int ID) throws SQLException{
        
        
        String uid = "";
        
        sql = "SELECT ID, USUARIO FROM GEN_USERS WHERE ID ="+ID;
        
        rs = statement.executeQuery(sql);
        
        while(rs.next()){
            uid = rs.getInt(1)+" - "+rs.getString(2) ;
        }
      
        
        return uid;
    }
  
}
