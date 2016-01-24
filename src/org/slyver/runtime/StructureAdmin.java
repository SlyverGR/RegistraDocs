/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.slyver.runtime;

import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.slyver.db.LoginDb;
import org.slyver.db.StructureAdminDB;
import org.slyver.ui.StructureAdminUI;

/**
 *
 * @author Andres
 */
public class StructureAdmin {
    
    public void AdminStatus() throws SQLException{
        
        int adminStatus = LoginDb.userlvl;
        int adminEmp = LoginDb.useremp;
        
        if(adminStatus == 1 && adminEmp == 0){
            
            new StructureAdminDB().AdminStatusDB();
            
        }
        else {
            StructureAdminUI.EmpresasAdmin.setVisible(false);
        }
    }
    
    public void UsersTable() throws SQLException{
        
        new StructureAdminDB().UsersTableDB();
        
    }

    public void addNewUser(){
        
        DefaultTableModel model = (DefaultTableModel) StructureAdminUI.userTable.getModel();
        Object[] rowData = new Object[] {
            0,"", "", "" 
        };
          
        model.addRow(rowData);
        StructureAdminUI.userTable.setModel(model);
        
    }
    
    public void SaveUserTable() throws SQLException{
        
        new StructureAdminDB().SaveUserTableDB(this.getTableData(StructureAdminUI.userTable));
        
    }
    
    public void setUserEmp() throws SQLException{
        
        new StructureAdminDB().setUserEmpDB();
        
    }
    
    public void EmpLists() throws SQLException{
        
        new StructureAdminDB().EmpListsDB();       

    }
    
    public void ListTable() throws SQLException{
        Object Nombre = StructureAdminUI.Lists.getSelectedItem();
        new StructureAdminDB().ListTableDB(Nombre.toString());
        StructureAdminUI.NewList.setText(Nombre.toString());
        StructureAdminUI.NewList.setEditable(false);
        if(Nombre.toString() == "--"){
            StructureAdminUI.NewList.setEditable(true);
            StructureAdminUI.NewList.setText("");
            addNewDataList();
        }
     }
    
    public void SaveListTable() throws SQLException{
        
        new StructureAdminDB().SaveListTableDB(this.getTableData(StructureAdminUI.ListTable));
        
    }
    
    public void TablesLists() throws SQLException{
        
        new StructureAdminDB().TablesListsDB();       

    }
    
    public void ListColumns() throws SQLException{
        Object Nombre = StructureAdminUI.TableList.getSelectedItem();
        new StructureAdminDB().ListColumnsDB(Nombre.toString());
        StructureAdminUI.NewTable.setText(Nombre.toString());
        StructureAdminUI.NewTable.setEditable(false);
        if(Nombre.toString() == "--"){
            StructureAdminUI.NewTable.setEditable(true);
            StructureAdminUI.NewTable.setText("");
            addNewDataColumns();
        }
     }
    
    public void SaveColumnsTable() throws SQLException{
        
        new StructureAdminDB().SaveColumnsTableDB(this.getTableData(StructureAdminUI.TablaColumnas));
        
    }
    
    public void addNewDataList(){
        
        DefaultTableModel model = (DefaultTableModel) StructureAdminUI.ListTable.getModel();
        Object[] rowData = new Object[] {
            0,"" 
        };
          
        model.addRow(rowData);
        StructureAdminUI.ListTable.setModel(model);
        
    }
    
    public void addNewDataColumns(){
        
        DefaultTableModel model = (DefaultTableModel) StructureAdminUI.TablaColumnas.getModel();
        Object[] rowData = new Object[] {
            0,"","","","" 
        };
          
        model.addRow(rowData);
        StructureAdminUI.TablaColumnas.setModel(model);
        
    }
    
    public Object[][] getTableData (JTable table) {
    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
    Object[][] tableData = new Object[nRow][nCol];
    for (int i = 0 ; i < nRow ; i++)
        for (int j = 0 ; j < nCol ; j++)
            tableData[i][j] = dtm.getValueAt(i,j);
    return tableData;
   }
    
}

