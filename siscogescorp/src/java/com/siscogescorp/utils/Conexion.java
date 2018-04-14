/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ViewSoft
 */
public class Conexion {
    
   String url2="jdbc:postgresql://localhost:5432/cogescorp_db";
 Connection cn=null;
     public Connection getconexion(){
        String url="jdbc:postgresql://localhost:5432/cogescorp_db";
        String user = "admin";
        String pass = "cogescorp2018";
        
        try{
            Class.forName("org.postgresql.Driver");
            cn=DriverManager.getConnection(url,user,pass);
        }catch(Exception e){
            System.out.println("Error:"+e.getMessage());
        }
        
        return cn;
    }
     
    public void cierraConexion()
    {
        try
        {
            cn.close();
        }catch(Exception e)
        {
            System.out.println("Problema para cerrar la Conexión a la base de datos ");
        }
    }
 
   
    
    public Connection getconexion2(){
        Connection cn=null;
        try{
            Class.forName("org.postgresql.Driver");
            cn=DriverManager.getConnection(url2,"admin","cogescorp2018");
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return cn;
    }

    public Connection getConexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
