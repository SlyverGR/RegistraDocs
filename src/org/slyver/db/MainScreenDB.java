/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.slyver.db;

import java.sql.SQLException;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import static org.slyver.db.LoginDb.userid;
import static org.slyver.db.oracledb.rs;
import static org.slyver.db.oracledb.sql;
import static org.slyver.db.oracledb.statement;
import org.slyver.ui.MainScreenUI;
import static org.slyver.ui.MainScreenUI.dynamicTable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFormattedTextField;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author Andres
 */
public class MainScreenDB {
    
    public void DynmicTableST(String Report) throws SQLException{
        
        String[] Columns=  ColumnsStructure(Report);
        Object[][] Rows= RowsStructure(Report);
    
        MainScreenUI.dynamicTable.setModel(new javax.swing.table.DefaultTableModel(

            Rows
            ,
    
            Columns
                
        ) {
    
            Class[] types = Tipos(Report, false);
            
            boolean[] canEdit = Editable(Report);

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });



    MainScreenUI.dynamicTable.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyPressed(java.awt.event.KeyEvent evt) {
            
        }
    });
   }
    
    public void ReportsListsDB() throws SQLException {
        
        
        int userlist = LoginDb.useremp;
        
        sql = "SELECT DISTINCT NOMBRE_TABLA FROM GEN_COLUMNAS WHERE ID_EMPRESA = "+userlist+"";
        
        rs = statement.executeQuery(sql);
        
        //DefaultComboBoxModel model = (DefaultComboBoxModel) MainScreenUI.ReportsList.getModel();
        
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        model.addElement("--");

        while(rs.next()){

           model.addElement(rs.getString(1));

        }
        
        MainScreenUI.ReportsList.setModel(model);
        
    }
    
    public String[] ColumnsStructure(String Report) throws SQLException{
        
        sql = "SELECT NOMBRE_COLUMNA FROM GEN_COLUMNAS WHERE NOMBRE_TABLA = '"+Report+"' ORDER BY ORDEN ASC";
        rs = statement.executeQuery(sql);
        int sizeH = 0;
        rs.last();
        sizeH = rs.getRow();
        rs.beforeFirst();
        
        String[] Columnas = new String[sizeH+1];
        int i= 1;
        
        Columnas[0]="ID";
        
        while(rs.next()){
            
            Columnas[i] = rs.getString(1);
            i++;
            
        }  
        
        return Columnas;
    }
    
    public Object[][] RowsStructure(String Report) throws SQLException{
        
        sql = "SELECT ID_REGISTRO, NOMBRE_TABLA from GEN_DATOS WHERE NOMBRE_TABLA = '"+Report+"'  group by ID_REGISTRO, NOMBRE_TABLA ORDER BY ID_REGISTRO";
        rs = statement.executeQuery(sql);
        int sizeH = 0;
        rs.last();
        sizeH = rs.getRow();
        rs.beforeFirst();
        
        
        sql = "SELECT NOMBRE_COLUMNA FROM GEN_COLUMNAS WHERE NOMBRE_TABLA = '"+Report+"' ORDER BY ORDEN ASC";
        rs = statement.executeQuery(sql);
        int sizeW = 0;
        rs.last();
        sizeW = rs.getRow();
        rs.beforeFirst();
        
        int i = 1;
        int j = 0;
        
        Object[][] Rows = new Object[sizeH+1][sizeW+1];
        
        
        for(j=0;j<sizeH;j++){
        
            sql = "SELECT D.DATO, D.TIPO_DATO FROM GEN_COLUMNAS C, GEN_DATOS D WHERE C.ID = D.ID_COLUMNA AND C.NOMBRE_TABLA = '"+Report+"' AND D.ID_REGISTRO = "+(j+1)+" ORDER BY C.ORDEN, D.ID_REGISTRO ASC";
        
            rs = statement.executeQuery(sql);

            Rows[j][0] = j+1;
            
            while(rs.next()){
                //System.out.println(rs.getString(1));
                Rows[j][i] = rs.getString(1);
                i++;
                
            }
            i=1;
        }
        
        Rows[j][0] = 0;
        
        for(int h=1; h<=sizeW; h++){
           Rows[j][h] = "";
        }
        
        return Rows;
        
    }
    
    public Class[] Tipos(String Report, Boolean viewT) throws SQLException{
        
        sql = "SELECT NOMBRE_COLUMNA FROM GEN_COLUMNAS WHERE NOMBRE_TABLA = '"+Report+"' ORDER BY ORDEN ASC";
        rs = statement.executeQuery(sql);
        int sizeW = 0;
        rs.last();
        sizeW = rs.getRow();
        rs.beforeFirst();
        
        Class[] types = new Class[sizeW+1];
        String switcher = "";
        
        int i = 1;
        
        types[0] = java.lang.Integer.class;
        
        sql = "SELECT TIPO_DATO FROM GEN_COLUMNAS WHERE NOMBRE_TABLA = '"+Report+"' ORDER BY ORDEN ASC";
        
        rs = statement.executeQuery(sql);
        
        while(rs.next()){
            
            if(viewT == false){
                          
                if("Combo".equals(rs.getString(1).substring(0, 5))){

                   switcher = "Combo";

                }else{

                   switcher = rs.getString(1);

                }

                //System.out.println(switcher);

                switch (switcher){

                    case "Texto":

                      types[i] = java.lang.String.class;

                    break;

                    case "Numero":

                      types[i] = java.lang.Integer.class;

                    break;

                    case "Fecha":

                      types[i] = java.lang.Object.class;

                    break;

                    case "Combo":

                      types[i] = java.lang.Object.class;

                    break;

                }
            }
            i++;
        }
        
        return types;
        
    }
    
    public boolean[] Editable(String Report) throws SQLException{
        
        sql = "SELECT NOMBRE_COLUMNA FROM GEN_COLUMNAS WHERE NOMBRE_TABLA = '"+Report+"' ORDER BY ORDEN ASC";
        rs = statement.executeQuery(sql);
        int sizeW = 0;
        rs.last();
        sizeW = rs.getRow();
        rs.beforeFirst();
        
        boolean[] canEdit = new boolean[sizeW+1];
        
        canEdit[0] = false;
        
        int i = 1;
        
        sql = "SELECT ID_USUARIO FROM GEN_COLUMNAS WHERE NOMBRE_TABLA = '"+Report+"' ORDER BY ORDEN ASC";
        
        rs = statement.executeQuery(sql);
        
        while(rs.next()){
            
           if(rs.getInt(1)==userid){
               canEdit[i]=true;
           }else{
               canEdit[i]=false;
           }
           i++; 
        }
        
        return canEdit;
    }
    
    public void CellFormats(String Report) throws SQLException{
        
        sql = "SELECT TIPO_DATO, TAMANO FROM GEN_COLUMNAS WHERE NOMBRE_TABLA = '"+Report+"' ORDER BY ORDEN ASC";
        rs = statement.executeQuery(sql);
        
        int i = 1;
        
        dynamicTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        dynamicTable.getColumnModel().getColumn(0).setResizable(false);
        
        List Combos = new ArrayList<>();
        List Indexs = new ArrayList<>();
        List Findexs = new ArrayList<>();
        
        JComboBox[] JcomboB;
        JFormattedTextField[] DcomboB;
        
        int j = 0;
        
        while(rs.next()){
            
            dynamicTable.getColumnModel().getColumn(i).setPreferredWidth(rs.getInt(2));
            dynamicTable.getColumnModel().getColumn(i).setResizable(false);
            
            if("Combo".equals(rs.getString(1).substring(0, 5))){
               Indexs.add(i);
               Combos.add(rs.getString(1).substring(6));
            }
            
            if("Fecha".equals(rs.getString(1))){
               Findexs.add(i);
            }
            
            i++;
        }
        JcomboB = new javax.swing.JComboBox[Combos.size()];
        for (j = 0 ; j < Combos.size(); j++) {
            
            JcomboB[j] = new JComboBox();
            JcomboB[j].setModel(new javax.swing.DefaultComboBoxModel(Lists(Combos.get(j).toString())));
            dynamicTable.getColumnModel().getColumn(Integer.parseInt(Indexs.get(j).toString())).setCellEditor(new DefaultCellEditor(JcomboB[j]));

        }
        
        DcomboB = new JFormattedTextField[Findexs.size()];
        for (j = 0 ; j < Findexs.size(); j++) {
            DcomboB[j] = new JFormattedTextField();
        
            try {
                DcomboB[j].setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            
            dynamicTable.getColumnModel().getColumn(Integer.parseInt(Findexs.get(j).toString())).setCellEditor(new DefaultCellEditor(DcomboB[j]));
        }
                       
        
        dynamicTable.setRowHeight(25);
        
        
        
    } 
    
    public void SaveTableDataDB(Object[][] DynamicTables, String Report) throws SQLException{
        for (Object[] result : DynamicTables) {
            
            sql = "SELECT ID_REGISTRO FROM GEN_DATOS WHERE NOMBRE_TABLA = '"+Report+"' GROUP BY ID_REGISTRO";
            rs = statement.executeQuery(sql);
            int sizeR = 0;
            rs.last();
            sizeR = rs.getRow();
            rs.beforeFirst();
            
            int i=0;
            int columnid = 0;
            String tipodato = "";
            int datanum = 0;
            int registro = Integer.parseInt(result[0].toString());
            
            for (Object resultI : result){
                    
                    if(registro < 1){
                        registro = sizeR+1;
                    }
                
                    sql = "SELECT D.ID_DATO FROM GEN_COLUMNAS C, GEN_DATOS D WHERE C.ORDEN = "+i+" AND D.NOMBRE_TABLA = '"+Report+"' AND C.NOMBRE_TABLA = '"+Report+"' AND C.ID = D.ID_COLUMNA AND D.ID_REGISTRO = "+registro+"";
                    System.out.println(sql);
                    rs = statement.executeQuery(sql);
                    while(rs.next()){
                      datanum = rs.getInt(1);
                    }               
                    
                    if(datanum == 0){
                       sql = "SELECT ID, TIPO_DATO FROM GEN_COLUMNAS WHERE ORDEN = "+i+" AND NOMBRE_TABLA = '"+Report+"'";
                       rs = statement.executeQuery(sql);
                       while(rs.next()){
                        columnid = rs.getInt(1);
                        tipodato = rs.getString(2);
                       }
                       System.out.println(sql);
                       
                       if(registro == 0){
                          registro = 1; 
                       }     
                    }
                    if(!"0".equals(resultI.toString()) && i != 0){
                        sql = "MERGE INTO GEN_DATOS USING dual ON ( ID_DATO="+datanum+" ) \n" +
                        "WHEN MATCHED THEN UPDATE SET DATO='"+resultI+"' \n" +
                        "WHEN NOT MATCHED THEN INSERT (ID_COLUMNA,DATO,ID_REGISTRO,NOMBRE_TABLA,TIPO_DATO) \n" +
                        "VALUES ( '"+columnid+"', '"+resultI+"', '"+registro+"', '"+Report+"', '"+tipodato+"')";
                        System.out.println(sql);
                        statement.executeUpdate(sql);
                    }
                    i++;
 
            }
        
        }
        DynmicTableST(Report);
    }
    
    public void GenerateSqlDB(String Report) throws SQLException{
        
        sql = "SELECT NOMBRE_COLUMNA FROM GEN_COLUMNAS WHERE NOMBRE_TABLA = '"+Report+"' ORDER BY ORDEN ASC";
        rs = statement.executeQuery(sql);
        int sizeH = 0;
        rs.last();
        sizeH = rs.getRow();
        rs.beforeFirst();
        
        int i= 0;

        String[] Columnas = new String[sizeH];
        String[] types = new String[sizeH];
        String switcher = "";
        
        while(rs.next()){
            
            Columnas[i] = rs.getString(1);
            i++;
            
        }
        
        i = 0;
        
        sql = "SELECT TIPO_DATO FROM GEN_COLUMNAS WHERE NOMBRE_TABLA = '"+Report+"' ORDER BY ORDEN ASC";
        
        rs = statement.executeQuery(sql);
        
        while(rs.next()){
            
           if("Combo".equals(rs.getString(1).substring(0, 5))){

                   switcher = "Combo";

                }else{

                   switcher = rs.getString(1);

                }

                //System.out.println(switcher);

                switch (switcher){

                    case "Texto":

                      types[i] = "VARCHAR2(250)";

                    break;

                    case "Numero":

                      types[i] = "DECIMAL(10,2)";

                    break;

                    case "Fecha":

                      types[i] = "DATE";

                    break;

                    case "Combo":

                      types[i] = "VARCHAR2(250)";

                    break;

                }
            
            i++;
            
        }
        
        String SQL = "SELECT";
        
        for(int j=0;j<Columnas.length;j++){
            
           SQL += " CAST( "+Columnas[j]+" AS "+types[j]+" ) AS "+Columnas[j]+","; 
  
        }
        
        SQL += " FROM GEN_DATOS D, GEN_COLUMNAS C where D.NOMBRE_TABLA = C.NOMBRE_TABLA AND D.ID_COLUMNA = C.ID AND D.NOMBRE_TABLA='BASE' ORDER BY D.ID_REGISTRO, C.ORDEN ";
        
        System.out.println(SQL);
        
    }
    
    public String[] Lists(String LName) throws SQLException{
        
        sql = "SELECT DATO FROM GEN_LISTS WHERE NOMBRE = '"+LName+"'";
        System.out.println(sql);
        rs = statement.executeQuery(sql);
        int sizeW = 0;
        rs.last();
        sizeW = rs.getRow();
        rs.beforeFirst();
        
        int i = 1;
        
        String[] List = new String[sizeW+1];
        
        List[0] = "--";
        
        while(rs.next()){
            
            List[i]=rs.getString(1);
            i++;
            
        }
        
        return List;
        
    }
    
    
}
