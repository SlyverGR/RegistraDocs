/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.slyver.runtime;

import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.slyver.db.MainScreenDB;
import org.slyver.ui.MainScreenUI;

/**
 *
 * @author Andres
 */
public class MainScreen {
    
     public void ReportsLists() throws SQLException {
         new MainScreenDB().ReportsListsDB();
     }
     
     public void DynmicTable(String Report) throws SQLException{
         new MainScreenDB().DynmicTableST(Report);
         new MainScreenDB().CellFormats(Report);
     }
     
     public void SaveTableData(String Report) throws SQLException{
         new MainScreenDB().SaveTableDataDB(this.getTableData(MainScreenUI.dynamicTable), Report);
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
