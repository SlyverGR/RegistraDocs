/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.slyver.db;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static org.slyver.db.oracledb.*;
import org.slyver.ui.MainScreenUI;
import org.slyver.ui.login;

/**
 *
 * @author Andres
 */
public class LoginDb {
    
    public static int userid = 0;
    public static int useremp = 0;
    public static int userlvl = 0;
    
    public void UserLogin() throws SQLException {
        
        new oracledb();
        
        String user = login.user.getText();
        char[] input = login.pass.getPassword();
        String s= new String(input);
        
        sql = "SELECT ID, ID_EMPRESA, NIVEL FROM GEN_USERS WHERE USUARIO = '"+user+"' AND CONTRASENA = '"+s+"'";
        
        System.out.println(sql);
        
        rs = statement.executeQuery(sql); 
        
        if (!rs.isBeforeFirst() ) {    
            JOptionPane.showMessageDialog(null, "El usuario o contraseña es incorrecto.", "Mensaje", JOptionPane.INFORMATION_MESSAGE); 
            Runtime.getRuntime().exit(0);
        } 
        
        while(rs.next()){
            userid = rs.getInt(1);
            useremp = rs.getInt(2);
            userlvl = rs.getInt(3);
        }
        
        if(userid != 0 && userlvl != 0){
            MainScreenUI portal = new MainScreenUI();
            portal.setVisible(true);
        }
        
    }
    
}
