/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.utils.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CIMA2015
 */
public class CampaniasServicios {
    
    
    public String getCampanias(int empresa, int agencia)
        {
            
            String valor = "", Query="select fnc_mis_campanias("+empresa+","+agencia+");";
            
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(Query);
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
     public String getDetCampanias(int idcampania)
        {
            
            String valor = "", Query="select fnc_det_campanias("+idcampania+");";
            
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(Query);
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
    public Boolean eliminarCampania(int id_campania)
        {
            
            String valor = "",updateQuery="select elimina_campania("+id_campania+") as ok;";
            boolean ok=false;
         try{
              
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(updateQuery);
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString(1);
               // if(valor.equals("t")){
                    ok=true;
               // }
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
           
             return ok;
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
                return false;
             } 
        }
     public Boolean eliminarDetCampania(int id_det_campania)
        {
            
            String valor = "",updateQuery="select elimina_det_campania("+id_det_campania+") as ok;";
            boolean ok=false;
         try{
              
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(updateQuery);
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString(1);
                    ok=true;
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
           
             return ok;
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
                return false;
             } 
        }
    
    //select fnc_add_campanias('okoko','2017-12-24 00:00:00','2017-12-18 00:00:00',1,1)
    
     public int AddCampania(String descripcion, String fecha_desde, String fecha_hasta,int empresa, int agencia)
        {
            
            String valor = "",updateQuery="select fnc_add_campanias('"+descripcion+"','"+fecha_desde+"','"+fecha_hasta+"',"+empresa+","+agencia+");";
            boolean ok=false;
            int campania=0;
         try{
              
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(updateQuery);
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                campania=Integer.parseInt(rs.getString(1));
              
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
           
             return campania;
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
                return campania;
             } 
        }
     public int CreaDetCampania(String Query, int secuencia)
        {
            
            String valor = "",miQuery="select fnc_crea_det_campanias('"+Query+"',"+secuencia+");";
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
                campania=Integer.parseInt(rs.getString(1));
              
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
           
             return campania;
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
                return campania;
             } 
        }
      public int AgregarEmpleadoCampania(int id_campania, int id_empleado, String nombres)
        {
            
            String valor = "",miQuery="select fnc_empleado_campanias("+id_campania+","+id_empleado+",'"+nombres+"');";
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
                campania=Integer.parseInt(rs.getString(1));
              
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
           
             return campania;
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
                return campania;
             } 
        }
       public String AllEmpleadoCampania(int id_campania)
        {
            
            String valor = "",miQuery="select fnc_all_empleado_campanias("+id_campania+");";
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
       
       
       //select elimina_empleado_campania(1)
public Boolean eliminaEmpleadoCampania(int id_empleado_campania)
        {
            
            String valor = "",updateQuery="select elimina_empleado_campania("+id_empleado_campania+") as ok;";
            boolean ok=false;
         try{
              
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(updateQuery);
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString(1);
                    ok=true;
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
           
             return ok;
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
                return false;
             } 
        }

//procesar_asignación
 public String Procesar_Asignación(int id_campania, String query)
        {
            
            String valor = "",miQuery="select fnc_procesa_asignacion2("+id_campania+",'"+query+"');";
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
 
 
 public String AllEmpleadoAsigCampania(int id_campania)
        {
            
            String valor = "",miQuery="select fnc_empleado_asig_campanias("+id_campania+");";
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
 public String DatosCampania(int id_campania)
        {
            
            String valor = "",miQuery="select fnc_datos_campanias("+id_campania+");";
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
}
