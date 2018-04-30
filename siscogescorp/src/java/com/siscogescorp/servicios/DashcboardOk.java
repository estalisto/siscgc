/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;


import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.utils.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 *
 * @author CIMA2015
 */
public class DashcboardOk {
    
    Conexion conexion=new Conexion();
   
    PreparedStatement pst;
    ResultSet rs;
    
    public String ObtenerCompromisosPagos() throws SQLException{
        String cadena="";
        pst = conexion.getconexion().prepareStatement("select \n" +
"'['''||a.mes||''','||a.cantidad||'],' campo1\n" +
" from (\n" +
"select * from (\n" +
"\n" +
"select count(*) cantidad  from public.lc_compromisos_pago c\n" +
"where c.fecha_compromiso BETWEEN to_date(extract (year  from (select date_trunc('month',current_date)))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date) ))||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date))),'YYYY-MM-DD') AND \n" +
"\n" +
"to_date(extract (year  from (select date_trunc('month',current_date) +'1month' ::interval  -'1sec' ::interval ))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date)+'1month' ::interval  -'1sec' ::interval )) ||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date)+'1month' ::interval  -'1sec' ::interval )),'YYYY-MM-DD')\n" +
"\n" +
") cantidad,\n" +
"(select to_char(current_date, 'TMMonth')  mes ) mes\n" +
"\n" +
"\n" +
"union all\n" +
"\n" +
"\n" +
"select * from (\n" +
"\n" +
"select count(*) cantidad from public.lc_compromisos_pago c\n" +
"where c.fecha_compromiso BETWEEN to_date(extract (year  from (select date_trunc('month',current_date) -'1month' ::interval ))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date)-'1month' ::interval ))||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date) -'1month' ::interval)),'YYYY-MM-DD') AND \n" +
"\n" +
"to_date(extract (year  from (select date_trunc('month',current_date)  -'1sec' ::interval ))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date) -'1sec' ::interval )) ||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date)  -'1sec' ::interval )),'YYYY-MM-DD') \n" +
") cantidad,\n" +
"(select to_char(current_date -'1month' ::interval , 'TMMonth') mes ) mes\n" +
"\n" +
"union all\n" +
"\n" +
"\n" +
"select * from (\n" +
"select count(*) cantidad from public.lc_compromisos_pago c\n" +
"where c.fecha_compromiso BETWEEN to_date(extract (year  from (select date_trunc('month',current_date) -'1month' ::interval -'1month' ::interval ))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date)-'1month' ::interval -'1month' ::interval ))||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date) -'1month' ::interval -'1month' ::interval)),'YYYY-MM-DD') AND \n" +
"\n" +
"to_date(extract (year  from (select date_trunc('month',current_date) -'1month' ::interval -'1sec' ::interval ))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date) -'1month' ::interval -'1sec' ::interval )) ||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date) -'1month' ::interval -'1sec' ::interval )),'YYYY-MM-DD')\n" +
") cantidad,\n" +
"(select to_char(current_date -'1month' ::interval -'1month' ::interval , 'TMMonth') mes ) mes\n" +
") a");    
        //connBD.createStatement();
        rs = pst.executeQuery();
        
        while( rs.next() )    //Mientras haya una sig. tupla
            {
            
            cadena=cadena+rs.getString("campo1");
            System.out.println(cadena);
        }
        
        
        rs.close();
        pst.close();
        conexion.cierraConexion();
        return cadena;
    
    } 
    
    
     public String ObtenerGestiones() throws SQLException{
        String cadena="";
        pst = conexion.getconexion().prepareStatement("select \n" +
"'['''||a.mes||' - '||extract (year  from (select date_trunc('month',current_date)))||''','||a.cantidad||'],' campo1\n" +
" from (\n" +
"\n" +
"\n" +
"select * from (\n" +
"select count(*) cantidad from public.lc_gestiones c\n" +
"where c.fecha_transaccion BETWEEN to_date(extract (year  from (select date_trunc('month',current_date) -'1month' ::interval -'1month' ::interval ))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date)-'1month' ::interval -'1month' ::interval ))||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date) -'1month' ::interval -'1month' ::interval)),'YYYY-MM-DD') AND \n" +
"\n" +
"to_date(extract (year  from (select date_trunc('month',current_date) -'1month' ::interval -'1sec' ::interval ))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date) -'1month' ::interval -'1sec' ::interval )) ||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date) -'1month' ::interval -'1sec' ::interval )),'YYYY-MM-DD')\n" +
") cantidad,\n" +
"(select to_char(current_date -'1month' ::interval -'1month' ::interval , 'TMMonth') mes ) mes\n" +
"\n" +
"union all\n" +
"\n" +
"\n" +
"select * from (\n" +
"\n" +
"select count(*) cantidad from public.lc_gestiones c\n" +
"where c.fecha_transaccion BETWEEN to_date(extract (year  from (select date_trunc('month',current_date) -'1month' ::interval ))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date)-'1month' ::interval ))||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date) -'1month' ::interval)),'YYYY-MM-DD') AND \n" +
"\n" +
"to_date(extract (year  from (select date_trunc('month',current_date)  -'1sec' ::interval ))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date) -'1sec' ::interval )) ||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date)  -'1sec' ::interval )),'YYYY-MM-DD') \n" +
") cantidad,\n" +
"(select to_char(current_date -'1month' ::interval , 'TMMonth') mes ) mes\n" +
"\n" +
"union all\n" +
"select * from (\n" +
"\n" +
"select count(*) cantidad  from public.lc_gestiones c\n" +
"where c.fecha_transaccion BETWEEN to_date(extract (year  from (select date_trunc('month',current_date)))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date) ))||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date))),'YYYY-MM-DD') AND \n" +
"\n" +
"to_date(extract (year  from (select date_trunc('month',current_date) +'1month' ::interval  -'1sec' ::interval ))||'-'||\n" +
"extract (month  from (select date_trunc('month',current_date)+'1month' ::interval  -'1sec' ::interval )) ||'-'||\n" +
"extract (day  from (select date_trunc('month',current_date)+'1month' ::interval  -'1sec' ::interval )),'YYYY-MM-DD')\n" +
"\n" +
") cantidad,\n" +
"(select to_char(current_date, 'TMMonth')  mes ) mes\n" +
"\n" +
") a");    
        //connBD.createStatement();
        rs = pst.executeQuery();       
        while( rs.next() )    //Mientras haya una sig. tupla
            {
            
            cadena=cadena+rs.getString("campo1");
            //System.out.println(cadena);
        }
        
         rs.close();    
         pst.close();
         conexion.cierraConexion();
        return cadena;
    
    } 
    public String ObtenerGestionmeses(int empleado) throws SQLException{
        String cadena="";
        pst = conexion.getconexion().prepareCall("SELECT fnc_consulta_grafico("+empleado+");");    
        //connBD.createStatement();
        rs = pst.executeQuery();
        while( rs.next() )    //Mientras haya una sig. tupla
            {
            
            cadena=cadena+rs.getString("fnc_consulta_grafico");
            //System.out.println(cadena);
        }
        
         rs.close();    
         pst.close();
         conexion.cierraConexion();
        return cadena;
    
    } 
    
    public String ObtenerGestionmesesHist(String empleado) throws SQLException{
        String cadena="";
        pst = conexion.getconexion().prepareStatement("SELECT fnc_ConsultaGestiones("+empleado+");");    
        rs = pst.executeQuery();
        while( rs.next() )   
         {            
            cadena=cadena+rs.getString("fnc_ConsultaGestiones");
         }
         
         rs.close();    
         pst.close();
         conexion.cierraConexion();
        return cadena;
      
    
    } 
    
    
    
    public String ObtenerEmpleadosActual() throws SQLException{
        String cadena="";
        pst = conexion.getconexion().prepareStatement("select '['''||a.usuario||''','||a.gestiones||'],' campo1\n" +
"from (\n" +
"select * from (\n" +
"	select distinct (u.usuario)as usuario,(count(g.id_empleado))as gestiones from public.lc_gestiones g,public.lc_clientes c,public.lc_empleados e,lc_usuarios u\n" +
"	where g.id_empleado= e.id_empleado and g.id_cliente= c.id_cliente and e.id_empleado=u.id_empleado and u.id_rol=62\n" +
"	and g.fecha_transaccion BETWEEN to_date(extract (year  from (select date_trunc('month',current_date)))||'-'||\n" +
"	extract (month  from (select date_trunc('month',current_date) ))||'-'||\n" +
"	extract (day  from (select date_trunc('month',current_date))),'YYYY-MM-DD') AND \n" +
"	to_date(extract (year  from (select date_trunc('month',current_date) +'1month' ::interval  -'1sec' ::interval ))||'-'||\n" +
"	extract (month  from (select date_trunc('month',current_date)+'1month' ::interval  -'1sec' ::interval )) ||'-'||\n" +
"	extract (day  from (select date_trunc('month',current_date)+'1month' ::interval  -'1sec' ::interval )),'YYYY-MM-DD' ) group by u.usuario,g.id_empleado\n" +
") gestiones\n" +
")a");    
        //connBD.createStatement();
        rs = pst.executeQuery();       
        while( rs.next() )    //Mientras haya una sig. tupla
            {
            
            cadena=cadena+rs.getString("campo1");
            //System.out.println(cadena);
        }
        
         rs.close();    
         pst.close();
         conexion.cierraConexion();
        return cadena;
    
    } 
    public String ObtenerEmpleadosActualAdmin(int rol) throws SQLException{
        String cadena="";
        pst = conexion.getconexion().prepareStatement("select '['''||a.usuario||''','||a.gestiones||'],' campo1\n" +
"from (\n" +
"select * from (\n" +
"	select distinct (u.usuario)as usuario,(count(g.id_empleado))as gestiones from public.lc_gestiones g,public.lc_clientes c,public.lc_empleados e,lc_usuarios u\n" +
"	where g.id_empleado= e.id_empleado and g.id_cliente= c.id_cliente and e.id_empleado=u.id_empleado and u.id_rol=62\n" +
"	and g.fecha_transaccion BETWEEN to_date(extract (year  from (select date_trunc('month',current_date)))||'-'||\n" +
"	extract (month  from (select date_trunc('month',current_date) ))||'-'||\n" +
"	extract (day  from (select date_trunc('month',current_date))),'YYYY-MM-DD') AND \n" +
"	to_date(extract (year  from (select date_trunc('month',current_date) +'1month' ::interval  -'1sec' ::interval ))||'-'||\n" +
"	extract (month  from (select date_trunc('month',current_date)+'1month' ::interval  -'1sec' ::interval )) ||'-'||\n" +
"	extract (day  from (select date_trunc('month',current_date)+'1month' ::interval  -'1sec' ::interval )),'YYYY-MM-DD' ) group by u.usuario,g.id_empleado\n" +
") gestiones\n" +
")a");    
        //connBD.createStatement();
        rs = pst.executeQuery();       
        while( rs.next() )    //Mientras haya una sig. tupla
            {
            
            cadena=cadena+rs.getString("campo1");
            //System.out.println(cadena);
        }
        
         rs.close();    
         pst.close();
         conexion.cierraConexion();
        return cadena;
    
    } 
    public String ObtenerEmpleadosxcartera(int empleado,int rol) throws SQLException{
        String cadena="";
        pst = conexion.getconexion().prepareStatement("select '['''||a.usuario||''','||a.gestiones||'],' campo1\n" +
"from (\n" +
"select * from (\n" +
"	select distinct (c.razon_social)as usuario,(count(g.id_empleado))as gestiones from public.lc_gestiones g,public.lc_clientes c,public.lc_empleados e,lc_usuarios u\n" +
"	where g.id_empleado= e.id_empleado and g.id_cliente= c.id_cliente and e.id_empleado=u.id_empleado and u.id_rol="+rol+" and g.id_empleado="+empleado+"\n" +
"	and g.fecha_transaccion BETWEEN to_date(extract (year  from (select date_trunc('month',current_date)))||'-'||\n" +
"	extract (month  from (select date_trunc('month',current_date) ))||'-'||\n" +
"	extract (day  from (select date_trunc('month',current_date))),'YYYY-MM-DD') AND \n" +
"	to_date(extract (year  from (select date_trunc('month',current_date) +'1month' ::interval  -'1sec' ::interval ))||'-'||\n" +
"	extract (month  from (select date_trunc('month',current_date)+'1month' ::interval  -'1sec' ::interval )) ||'-'||\n" +
"	extract (day  from (select date_trunc('month',current_date)+'1month' ::interval  -'1sec' ::interval )),'YYYY-MM-DD' ) group by c.razon_social\n" +
") gestiones\n" +
")a");    
        //connBD.createStatement();
        rs = pst.executeQuery();       
        while( rs.next() )    //Mientras haya una sig. tupla
            {
            
            cadena=cadena+rs.getString("campo1");
            //System.out.println(cadena);
        }
        
         rs.close();    
         pst.close();
         conexion.cierraConexion();
        return cadena;
    
    } 
    
     public String fnc_ConsultaGestionesDiarias(int id_empleado,String fecha ,int id_cliente){
         
         String consulta="select fnc_consultagestiones_diarias("+id_empleado+",'"+fecha+"',"+id_cliente+");";
         
         String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString("fnc_consultagestiones_diarias");
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor;      
    }
     public String fnc_ConsultaMisGestionesDiarias(int id_empleado,String fecha ,int id_cliente){
         
         String consulta="select fnc_grafica_gestiones("+id_empleado+",'"+fecha+"',"+id_cliente+");";
         
         String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString("fnc_grafica_gestiones");
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor;      
    }
     public String fnc_ConsultaMisGestionesDiariasOK(int id_empresa,int id_empleado,String fecha ,int id_cliente){
         
         String consulta="select fnc_grafica_gestiones_diarias("+id_empresa+","+id_empleado+",'"+fecha+"',"+id_cliente+");";
         
         String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString("fnc_grafica_gestiones_diarias");
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
