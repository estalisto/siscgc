/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CIMA2015
 */
public class NotificacionesServicios {
    
    public String fnc_ConsultaNotificaciones(String query){
         String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_consulta_cartera('"+query+"');");
             System.out.println("select fnc_consulta_cartera('"+query+"');");
                 
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString(1);
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor;      
    }
    public String PreparandoNotificaciones(int id_empresa, int id_agencia, int id_empleado){
            
            String valor = "",miQuery="select fnc_preparando_notificacion("+id_empresa+","+id_agencia+","+id_empleado+");";
            boolean ok=false;
            int campania=0;
         try{
              
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(miQuery);
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString(1);
              
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
           
             return valor;
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
                return valor;
             } 
        }
    public String fnc_ConsultaMisNotificaciones(int id_empresa, int id_agencia, int id_empleado){
         String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_consulta_mis_notificaciones("+id_empresa+","+id_agencia+","+id_empleado+");");
             //System.out.println("select fnc_consulta_cartera('"+query+"');");
                 
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString(1);
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor;      
    }
     public String fnc_elimina_notificacion(int id_recordatorio){
         String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_deudor_gestionado_ok("+id_recordatorio+");");
             //System.out.println("select fnc_consulta_cartera('"+query+"');");
                 
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString(1);
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor;      
    }
}
