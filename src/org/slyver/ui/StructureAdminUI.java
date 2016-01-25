/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.slyver.ui;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.MOVE;
import javax.swing.table.DefaultTableModel;
import org.slyver.db.StructureAdminDB;
import org.slyver.runtime.StructureAdmin;

/**
 *
 * @author Andres
 */
public class StructureAdminUI extends javax.swing.JFrame {
    private final TransferHandler handler = new TableRowTransferHandler();
    /**
     * Creates new form StructureAdmin
     * @throws java.sql.SQLException
     */
    public StructureAdminUI() throws SQLException {
        initComponents();
        new StructureAdmin().AdminStatus();
        new StructureAdmin().UsersTable();
        new StructureAdminDB().PermissionCombo();
        new StructureAdminDB().TipoDatoCombo();
        new StructureAdmin().EmpLists();
        new StructureAdmin().TablesLists();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DelUser = new javax.swing.JButton();
        UserPermision = new javax.swing.JComboBox();
        TipoDato = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaColumnas = new javax.swing.JTable();
        TableList = new javax.swing.JComboBox();
        NewTable = new javax.swing.JTextField();
        SaveTable = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        SaveUserTable = new javax.swing.JButton();
        delUser = new javax.swing.JButton();
        addUser = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        EmpresasAdmin = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        Lists = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        NewList = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        DelUser.setText("jButton5");

        UserPermision.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        TipoDato.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Texto", "Numero", "Fecha" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TablaColumnas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(0), null, "", null, null}
            },
            new String [] {
                "ID", "Nombre Columna", "Tipo Dato", "Tamaño", "Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaColumnas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TablaColumnasKeyPressed(evt);
            }
        });
        TablaColumnas.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TablaColumnas.setTransferHandler(handler);
        TablaColumnas.setDropMode(DropMode.INSERT_ROWS);
        TablaColumnas.setDragEnabled(true);
        TablaColumnas.setFillsViewportHeight(true);
        //table.setAutoCreateRowSorter(true); //XXX

        //Disable row Cut, Copy, Paste
        ActionMap map = TablaColumnas.getActionMap();
        AbstractAction dummy = new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { /* Dummy action */ }
        };
        map.put(TransferHandler.getCutAction().getValue(Action.NAME),   dummy);
        map.put(TransferHandler.getCopyAction().getValue(Action.NAME),  dummy);
        map.put(TransferHandler.getPasteAction().getValue(Action.NAME), dummy);
        jScrollPane2.setViewportView(TablaColumnas);
        if (TablaColumnas.getColumnModel().getColumnCount() > 0) {
            TablaColumnas.getColumnModel().getColumn(0).setResizable(false);
            TablaColumnas.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaColumnas.getColumnModel().getColumn(1).setResizable(false);
            TablaColumnas.getColumnModel().getColumn(2).setResizable(false);
            TablaColumnas.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(TipoDato));
            TablaColumnas.getColumnModel().getColumn(3).setResizable(false);
            TablaColumnas.getColumnModel().getColumn(4).setResizable(false);
            TablaColumnas.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(UserPermision));
        }

        TableList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--" }));
        TableList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TableListItemStateChanged(evt);
            }
        });

        SaveTable.setText("Guardar Tabla");
        SaveTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveTableActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Estructura de tablas");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nueva Tabla");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TableList, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NewTable, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SaveTable))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TableList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NewTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaveTable)
                    .addComponent(jLabel3))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        SaveUserTable.setText("Guardar");
        SaveUserTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveUserTableActionPerformed(evt);
            }
        });

        delUser.setText("Eliminar");
        delUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delUserActionPerformed(evt);
            }
        });

        addUser.setText("Agregar");
        addUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserActionPerformed(evt);
            }
        });

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuario", "Contraseña", "Comentarios"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.setColumnSelectionAllowed(true);
        userTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(userTable);
        userTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(0).setResizable(false);
            userTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            userTable.getColumnModel().getColumn(1).setResizable(false);
            userTable.getColumnModel().getColumn(2).setResizable(false);
            userTable.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SaveUserTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delUser, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(addUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveUserTable)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Usuarios con permisos");

        EmpresasAdmin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--" }));
        EmpresasAdmin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EmpresasAdminItemStateChanged(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(0), null}
            },
            new String [] {
                "ID", "Informacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ListTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ListTableKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(ListTable);
        if (ListTable.getColumnModel().getColumnCount() > 0) {
            ListTable.getColumnModel().getColumn(0).setResizable(false);
            ListTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            ListTable.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Estructura de Listas");

        Lists.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--" }));
        Lists.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ListsItemStateChanged(evt);
            }
        });

        jButton4.setText("Guardar Lista");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nueva Lista");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Lists, 0, 90, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NewList)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lists, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NewList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EmpresasAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(EmpresasAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserActionPerformed
        // TODO add your handling code here:
        new StructureAdmin().addNewUser();
    }//GEN-LAST:event_addUserActionPerformed

    private void SaveUserTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveUserTableActionPerformed
        try {
            // TODO add your handling code here:
            new StructureAdmin().SaveUserTable();
        } catch (SQLException ex) {
            Logger.getLogger(StructureAdminUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SaveUserTableActionPerformed

    private void delUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delUserActionPerformed
        try {
            // TODO add your handling code here:
            int loc1 = userTable.getSelectedRow();
            int loc2 = userTable.getSelectedColumn();
            int urObjctInCell = (int)userTable.getValueAt(loc1, loc2);
            //System.out.println(urObjctInCell);
            new StructureAdminDB().DeleteUser(urObjctInCell);
        } catch (SQLException ex) {
            Logger.getLogger(StructureAdminUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_delUserActionPerformed

    private void ListTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ListTableKeyPressed
        // TODO add your handling code here:
        System.out.println(evt.getKeyCode());
         if (evt.getKeyCode() == 107){
             new StructureAdmin().addNewDataList();
         }
         if (evt.getKeyCode() == 109){
            try {
                int loc1 = ListTable.getSelectedRow();
                int loc2 = ListTable.getSelectedColumn();
                int urObjctInCell = (int)ListTable.getValueAt(loc1, loc2);
                new StructureAdminDB().DeleteDataList(urObjctInCell);
            } catch (SQLException ex) {
                Logger.getLogger(StructureAdminUI.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }//GEN-LAST:event_ListTableKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // TODO add your handling code here:
            new StructureAdmin().SaveListTable();
        } catch (SQLException ex) {
            Logger.getLogger(StructureAdminUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ListsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ListsItemStateChanged
        try {
            // TODO add your handling code here:
            new StructureAdmin().ListTable();
        } catch (SQLException ex) {
            Logger.getLogger(StructureAdminUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ListsItemStateChanged

    private void TablaColumnasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaColumnasKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 107){
             new StructureAdmin().addNewDataColumns();
         }
         if (evt.getKeyCode() == 109){
            try {
                int loc1 = TablaColumnas.getSelectedRow();
                int loc2 = TablaColumnas.getSelectedColumn();
                int urObjctInCell = (int)TablaColumnas.getValueAt(loc1, loc2);
                new StructureAdminDB().DeleteDataColumns(urObjctInCell);
            } catch (SQLException ex) {
                Logger.getLogger(StructureAdminUI.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }//GEN-LAST:event_TablaColumnasKeyPressed

    private void SaveTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveTableActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            new StructureAdmin().SaveColumnsTable();
        } catch (SQLException ex) {
            Logger.getLogger(StructureAdminUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SaveTableActionPerformed

    private void TableListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TableListItemStateChanged
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            new StructureAdmin().ListColumns();
        } catch (SQLException ex) {
            Logger.getLogger(StructureAdminUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TableListItemStateChanged

    private void EmpresasAdminItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EmpresasAdminItemStateChanged
        try {
            // TODO add your handling code here:
            new StructureAdmin().setUserEmp();
        } catch (SQLException ex) {
            Logger.getLogger(StructureAdminUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EmpresasAdminItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StructureAdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StructureAdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StructureAdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StructureAdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new StructureAdminUI().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(StructureAdminUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DelUser;
    public static javax.swing.JComboBox EmpresasAdmin;
    public static javax.swing.JTable ListTable;
    public static javax.swing.JComboBox Lists;
    public static javax.swing.JTextField NewList;
    public static javax.swing.JTextField NewTable;
    private javax.swing.JButton SaveTable;
    private javax.swing.JButton SaveUserTable;
    public static javax.swing.JTable TablaColumnas;
    public static javax.swing.JComboBox TableList;
    public static javax.swing.JComboBox TipoDato;
    public static javax.swing.JComboBox UserPermision;
    private javax.swing.JButton addUser;
    private javax.swing.JButton delUser;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
class TableRowTransferHandler extends TransferHandler {
    private final DataFlavor localObjectFlavor;
    private int[] indices;
    private int addIndex = -1; //Location where items were added
    private int addCount; //Number of items added.
    private JComponent source;

    public TableRowTransferHandler() {
        super();
        localObjectFlavor = new ActivationDataFlavor(Object[].class, DataFlavor.javaJVMLocalObjectMimeType, "Array of items");
    }
    @Override protected Transferable createTransferable(JComponent c) {
        source = c;
        JTable table = (JTable) c;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Object> list = new ArrayList<>();
        indices = table.getSelectedRows();
        for (int i: indices) {
            list.add(model.getDataVector().elementAt(i));
        }
        Object[] transferedObjects = list.toArray();
        return new DataHandler(transferedObjects, localObjectFlavor.getMimeType());
    }
    @Override public boolean canImport(TransferHandler.TransferSupport info) {
        JTable table = (JTable) info.getComponent();
        boolean isDropable = info.isDrop() && info.isDataFlavorSupported(localObjectFlavor);
        //XXX bug?
        table.setCursor(isDropable ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
        return isDropable;
    }
    @Override public int getSourceActions(JComponent c) {
        return MOVE; //TransferHandler.COPY_OR_MOVE;
    }
    @Override public boolean importData(TransferHandler.TransferSupport info) {
        if (!canImport(info)) {
            return false;
        }
        TransferHandler.DropLocation tdl = info.getDropLocation();
        if (!(tdl instanceof JTable.DropLocation)) {
            return false;
        }
        JTable.DropLocation dl = (JTable.DropLocation) tdl;
        JTable target = (JTable) info.getComponent();
        DefaultTableModel model = (DefaultTableModel) target.getModel();
        int index = dl.getRow();
        //boolean insert = dl.isInsert();
        int max = model.getRowCount();
        if (index < 0 || index > max) {
            index = max;
        }
        addIndex = index;
        target.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        try {
            Object[] values = (Object[]) info.getTransferable().getTransferData(localObjectFlavor);
            if (Objects.equals(source, target)) {
                addCount = values.length;
            }
            for (int i = 0; i < values.length; i++) {
                int idx = index++;
                model.insertRow(idx, (Vector) values[i]);
                target.getSelectionModel().addSelectionInterval(idx, idx);
            }
            return true;
        } catch (UnsupportedFlavorException | IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    @Override protected void exportDone(JComponent c, Transferable data, int action) {
        cleanup(c, action == MOVE);
    }
    private void cleanup(JComponent c, boolean remove) {
        if (remove && indices != null) {
            c.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            DefaultTableModel model = (DefaultTableModel) ((JTable) c).getModel();
            if (addCount > 0) {
                for (int i = 0; i < indices.length; i++) {
                    if (indices[i] >= addIndex) {
                        indices[i] += addCount;
                    }
                }
            }
            for (int i = indices.length - 1; i >= 0; i--) {
                model.removeRow(indices[i]);
            }
        }
        indices  = null;
        addCount = 0;
        addIndex = -1;
    }
}