/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcArticulo;
import com.siscogescorp.modelo.LcCiudad;
import com.siscogescorp.modelo.LcClientes;
import com.siscogescorp.modelo.LcCompromisosPago;
import com.siscogescorp.modelo.LcCuotas;
import com.siscogescorp.modelo.LcDatosDeudores;
import com.siscogescorp.modelo.LcDireccion;
import com.siscogescorp.modelo.LcGestiones;
import com.siscogescorp.modelo.LcNotas;
import com.siscogescorp.modelo.LcRecordatorios;
import com.siscogescorp.modelo.LcTelefonos;
import com.siscogescorp.modelo.LcTipoGestion;
import com.siscogescorp.modelo.LcTipoResultado;
import com.siscogescorp.modelo.LcTransacciones;
import com.siscogescorp.utils.Conexion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.json.simple.JSONObject;

import com.siscogescorp.modelo.LcClienteResultado;
import com.siscogescorp.modelo.LcRecaudaciones;
import com.siscogescorp.modelo.LcSegmento;
import com.siscogescorp.modelo.LcUsuarios;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 *
 * @author ViewSoft
 */
public class ConsultaxCarteraServicios {
    

         public List<LcDatosDeudores> getLcDatosDeudores(int empresa, int agencia,int empleado){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        // Buscar cliente de la tabla Transacciones [Cambios20170709]
        Query q = session.createQuery("select DISTINCT(E.lcClientes.idCliente)  from LcDatosDeudores  E WHERE E.lcAgencia.idAgencia= :idAgencia and E.idEmpleado= :idEmpleado and E.lcEmpresa.idEmpresa= :idEmpresa and E.estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("idAgencia",agencia);
        q.setParameter("idEmpleado",empleado);
        q.setParameter("estado","P");
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDatosDeudor());//+", "+mrol.getLcClientes().getIdCliente());
             System.out.println("ok: "+mrol.getIdDatosDeudor());//+", "+mrol.getLcClientes().getRazonSocial());
        }
        tx.commit();
        session.close();
         return lista;
    }
          public List<LcClientes> getLcDatoscliente(int empresa,int idCliente){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery(" from LcClientes  E WHERE E.idCliente= :idCliente and E.lcEmpresa.idEmpresa= :idEmpresa ");
        q.setParameter("idEmpresa",empresa);
        //q.setParameter("idAgencia",agencia);
        q.setParameter("idCliente",idCliente);
        //q.setParameter("estado","P");
        List<LcClientes> lista=q.list();
         for(LcClientes mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdCliente());
        }
        tx.commit();
        session.close();
         return lista;
    }
         
        public Integer getIdCliente(int empresa, int agencia,int empleado) {
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session;
            session = sesion.openSession();
            Transaction tx= session.beginTransaction();
            Query q = session.createQuery("select DISTINCT E.lcClientes.idCliente  from LcTransacciones  E WHERE E.idEmpresa= :idEmpresa and E.idAgencia= :idAgencia and E.lcEmpleados.idEmpleado= :idEmpleado and E.estado in ('A','P','I','C')");
            q.setParameter("idEmpresa",empresa);
            q.setParameter("idAgencia",agencia);
            q.setParameter("idEmpleado",empleado);
           // q.setParameter("estado","A");
             //List listDatos = q.list();
            List listResult = q.list();
            Integer number;
            if(listResult.size()==0){
               number=0;
            }else{
            number = (Integer) listResult.get(0);
            }
            
            tx.commit();  
            session.close();
        return number; 
       
        } 
        
        public String getIdCliente2(int empresa, int agencia,int empleado)
        {
            
            String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_consulta_clientes("+empresa+","+agencia+","+empleado+");");
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
        
        
        public String getTiposEstatus(int empresa)
        {
            
            String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_estatus_clientes("+empresa+");");
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
         public String getCountCompromisos(int empresa, int agencia, int empleado,String nombre_rol)
        {
            
            String valor = "";
            String sql1="select fnc_consulta_all_compromisos_mensuales("+empresa+","+agencia+","+empleado+",'"+nombre_rol+"');";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(sql1);
            //select fnc_consulta_all_compromisos_mensuales(1,1,96,'GENRENTE');
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
        public String getIdCliente3(int empresa, int agencia)
        {
            
            String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_consulta_clientes2("+empresa+","+agencia+");");
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
        
        public String getIdClienteAll(int empresa, int agencia)
        {
            
            String valor = "";
            String sql="select fnc_consulta_all_clientes("+empresa+","+agencia+");";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(sql); 
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
        
        public String getIdClienteAllxEmpleado(int empresa, int agencia, int idEmpleado)
        {
            
            String valor = "";
            String sql="select fnc_consulta_all_clientes("+empresa+","+agencia+");";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(sql); 
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
        
        public String getSubCarterasClientesAll(int empresa, String mi_rol)
        {
            
            String valor = "";
            String sql1="select fnc_consulta_mis_sub_carteras("+empresa+");";
            

         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(sql1); 
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
     public String getMisCompromisosAll(int empresa, int agenda, int id_empleado, String mi_rol)
        {
            
            String valor = "";
            String sql1="select fnc_consulta_all_compromisos_mensuales("+empresa+","+agenda+","+id_empleado+",'"+mi_rol+"');";
            

         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(sql1); 
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
         
public List<LcDatosDeudores> getLcDatosposicion(int empresa, int agencia,int posicion, int IDEmpleado){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        //[Cambios 20170709]
        Query q = session.createQuery("from LcDatosDeudores  E WHERE E.idDatosDeudor>= :idDatosDeudor and E.lcAgencia.idAgencia= :idAgencia and  E.lcEmpresa.idEmpresa= :idEmpresa and E.idEmpleado = :IDEmpleado and E.estado= :estado").setMaxResults(10);
        q.setParameter("idEmpresa",empresa);
        q.setParameter("idAgencia",agencia);
        
        q.setParameter("IDEmpleado",IDEmpleado); 
        q.setParameter("idDatosDeudor",posicion); 
        q.setParameter("estado","P");
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
            // System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getIdCliente());
             //System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getIdTipocredito());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getDescripcion());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getIdEstatus());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getDescripcion());
        }
        tx.commit();
        session.close();
         return lista;
    }       
         
        public List<LcDatosDeudores> getLcDatos(int empresa, int agencia,int cliente, int IDEmpleado){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcDatosDeudores  E WHERE E.lcAgencia.idAgencia= :idAgencia and E.lcClientes.idCliente= :idCliente and E.lcEmpresa.idEmpresa= :idEmpresa and E.idEmpleado = :IDEmpleado and E.estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("idAgencia",agencia);
        q.setParameter("idCliente",cliente);
        q.setParameter("IDEmpleado",IDEmpleado);        
        q.setParameter("estado","P");
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
            // System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getIdCliente());
            // System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getIdTipocredito());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getDescripcion());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getIdEstatus());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getDescripcion());
        }
        tx.commit();
        session.close();
         return lista;
    } 
        public List<LcDatosDeudores> getLcDatos2(int empresa, int agencia, int IDEmpleado){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcDatosDeudores  E WHERE E.lcAgencia.idAgencia= :idAgencia and E.lcEmpresa.idEmpresa= :idEmpresa and E.idEmpleado = :IDEmpleado and E.estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("idAgencia",agencia);
        q.setParameter("IDEmpleado",IDEmpleado);        
        q.setParameter("estado","A");
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
          //   System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getIdCliente());
           //  System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getIdTipocredito());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getDescripcion());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getIdEstatus());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getDescripcion());
        }
        tx.commit();
        session.close();
         return lista;
    }         
    public ArrayList<LcDatosDeudores> getLcobteneregistros(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcDatosDeudores> arreglo = new ArrayList<LcDatosDeudores>();
        Query q = session.createQuery("from LcDatosDeudores  E WHERE  E.estado= :estado");
        
        q.setParameter("estado","P");
        List<LcDatosDeudores> lista=q.list();
        Iterator<LcDatosDeudores> iter=lista.iterator();
        while(iter.hasNext())
        {
            LcDatosDeudores rol= (LcDatosDeudores) iter.next();
            arreglo.add(rol);
        }
         tx.commit();
        session.close();
        return arreglo;

    }        
    public Integer numeroregistros() {
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session;
            session = sesion.openSession();
            Transaction tx= session.beginTransaction();
            Query q = session.createQuery("select max(*) from LcDatosDeudores E WHERE E.estado= :estado");
            q.setParameter("estado","P");
           
            List listResult = q.list();
            Integer number = (Integer) listResult.size();
            tx.commit();  
            session.close();
        return number; 
       
        }

public List<LcDatosDeudores> getLcDatosidentifica(String identificacion,int empresa,int cliente){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
      //  Query q = session.createQuery("from LcDatosDeudores  E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.lcClientes.idCliente= :idCliente and E.identificacion= :identificacion");
        Query q = session.createQuery("from LcDatosDeudores  E WHERE  E.identificacion= :identificacion");

        // q.setParameter("idCliente",cliente);
        q.setParameter("identificacion",identificacion);
       // q.setParameter("idEmpresa",empresa);
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
          //   System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getIdCliente());
           //  System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEmpresa().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getIdTipocredito());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getDescripcion());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getIdEstatus());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getDescripcion());
             System.out.println("Ciudad: "+mrol.getLcCiudad().getIdCiudad()+mrol.getLcCiudad().getCiudad());
        }
        tx.commit();
        session.close();
         return lista;
    }          
public List<LcDatosDeudores> getLcDeudorId(int id,int idDeudor){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        //Query q = session.createQuery("from LcDatosDeudores  E WHERE E.lcClientes.idCliente= :idCliente and E.idDatosDeudor= :idDeudor");
         Query q = session.createQuery("from LcDatosDeudores  E WHERE E.idDatosDeudor= :idDeudor");

      //  q.setParameter("idCliente",id);
        q.setParameter("idDeudor",idDeudor);
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
             //System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getIdCliente());
             //System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEmpresa().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getIdTipocredito());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getDescripcion());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getIdEstatus()+mrol.getNombresCompleto());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getDescripcion());
             System.out.println("Ciudad: "+mrol.getLcCiudad().getIdCiudad()+mrol.getLcCiudad().getCiudad());
            
        }
        tx.commit();
        session.close();
         return lista;
    }  
public List<LcTransacciones> getTransaccionesId(int id,int idDeudor){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcTransacciones  E WHERE E.lcClientes.idCliente= :idCliente and E.lcDatosDeudores.idDatosDeudor= :idDeudor");
        q.setParameter("idCliente",id);
        q.setParameter("idDeudor",idDeudor);
        List<LcTransacciones> lista=q.list();
         for(LcTransacciones mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdTransaccion()+", "+mrol.getLcClientes().getIdCliente());
             System.out.println("ok: "+mrol.getIdTransaccion()+", "+mrol.getLcClientes().getRazonSocial());
             
             System.out.println("ok: "+mrol.getIdTransaccion()+", "+mrol.getLcDatosDeudores().getNombresCompleto());
             
            
             
        }
        tx.commit();
        session.close();
         return lista;
    }  
/*Codigo 003: Inicio cambio */
/*Desarrollador: Jimmy Guaranda*/
/*Objetivo: EnMetodo para obtener el usuario asignado */
public List<LcTransacciones> getTransaccionesRecaudo(int idcliente,int idDeudor,int empresa){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcTransacciones  E WHERE E.lcClientes.idCliente= :idCliente and E.lcDatosDeudores.idDatosDeudor= :idDeudor and E.idEmpresa =:idEmpresa");
        q.setParameter("idCliente",idcliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("idEmpresa",empresa);
        List<LcTransacciones> lista=q.list();
         for(LcTransacciones mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdTransaccion()+", "+mrol.getLcClientes().getIdCliente());
             System.out.println("ok: "+mrol.getIdTransaccion()+", "+mrol.getLcClientes().getRazonSocial());
             System.out.println("ok: "+mrol.getIdTransaccion()+", "+mrol.getLcEmpleados().getNombres());
             System.out.println("ok: "+mrol.getIdTransaccion()+", "+mrol.getLcEmpleados().getApellidos());
             System.out.println("ok: "+mrol.getIdTransaccion()+", "+mrol.getLcDatosDeudores().getNombresCompleto());
        }
        tx.commit();
        session.close();
         return lista;
    } 
public String getIdentificacionDeudor(int idDeudor){
       
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("Select E.identificacion from LcDatosDeudores  E WHERE E.idDatosDeudor= :idDeudor and estado= :estado");
        //q.setParameter("idCliente",id);
        q.setParameter("idDeudor",idDeudor);  
        q.setParameter("estado","A");    
       String Identificacion;
             Identificacion = "";
             Identificacion = q.list().toString();
        String cadena = Identificacion.replace("[", "");
               cadena =cadena.replace("]", "");
                
        System.out.println(cadena);
         tx.commit();
         session.close();

         return cadena;
    }
public String getMiParroquia(int idparroquia){
    String parroquia="";
    if(idparroquia== 0){
       
        return parroquia;
    
    }
    
       
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery(" select E.parroquia from LcParroquias  E WHERE E.idParriquia= :idParriquia and estado= :estado");
        //q.setParameter("idCliente",id);
        q.setParameter("idParriquia",idparroquia);  
        q.setParameter("estado","A");    
        parroquia = q.list().toString();

                
        System.out.println(parroquia);
         tx.commit();
         session.close();

         return parroquia;
    }

public List<LcDireccion> getLcDireccion(String ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcDireccion  E WHERE E.identificacionDeudor= :identificacionDeudor");      
        q.setParameter("identificacionDeudor",ide);
        List<LcDireccion> lista=q.list();
       
         for(LcDireccion mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDireccion()+", "+mrol.getDireccionCompleta());
             System.out.println("ok: "+mrol.getIdDireccion()+", "+mrol.getLcTiposDireccion().getNombreTipoDireccion());
             
        }
        tx.commit();
        session.close();
         return lista;
    }

public List<LcTelefonos> getLcTelefono(String ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcTelefonos  E WHERE E.identificacionDeudor= :identificacionDeudor");
        q.setParameter("identificacionDeudor",ide);
        List<LcTelefonos> lista=q.list();

         for(LcTelefonos mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdTelefono()+", "+mrol.getTelefono());
             System.out.println("ok: "+mrol.getIdTelefono()+", "+mrol.getLcTiposTelefono().getNombreTipoTlf());
             
        }
         tx.commit();
        session.close();
         return lista;
    }

public List<LcCiudad> getLcCiudad(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcCiudad  E WHERE E.estado= :estado");
        q.setParameter("estado","A");
        List<LcCiudad> lista=q.list();
         for(LcCiudad mrol:lista )
        {
            
             System.out.println("ok: "+mrol.getIdCiudad()+", "+mrol.getCiudad());
             
        }
        tx.commit();
        session.close();
         return lista;
    }


public List<LcGestiones> getGestionesTRX(int idCliente,int idDeudor){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcGestiones  E WHERE  E.lcDatosDeudores.idDatosDeudor = :idDeudor  and   E.lcClientes.idCliente= :idCliente and  E.estado= :estado order by E.fechaTransaccion desc");
        
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcGestiones> lista=q.list();
         for(LcGestiones mrol:lista )
        {
            
             System.out.println("ok: "+mrol.getLcClientes().getRazonSocial()+" "+ mrol.getLcDatosDeudores().getNombres()+" "+mrol.getLcTipoGestion().getNombreTipoGestion()+" "+ mrol.getLcTipoResultado().getNombreTipoResultado()+""+mrol.getLcEmpleados().getNombres()+""+mrol.getLcEmpleados().getApellidos());
             
        }
          tx.commit();
        session.close();

         return lista;
    }
public List<LcArticulo> getArticulos(int idCliente,int idDeudor){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcArticulo  E WHERE  E.lcDatosDeudores.idDatosDeudor = :idDeudor  and   E.idCliente= :idCliente and  E.estado= :estado order by E.fechaRegistro");       
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcArticulo> lista=q.list();
         for(LcArticulo mrol:lista )
        {
            
             System.out.println("ok: "+mrol.getNombreArticulo()+" "+ mrol.getReferencia()+" "+mrol.getFechaCompra()+""+mrol.getValorArticulo());
             
        }
          tx.commit();
        session.close();

         return lista;
    }
    public String getArticulos2(int idCliente,int idDeudor)throws SQLException
    {   
        Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
           try
            {
            String SQL="";
            
            SQL="select (a.referencia)as referencia,(a.nombre_articulo)as nombre_articulo,\n" +
"(a.valor_articulo)as valor_articulo,to_char(a.fecha_compra,'YYYY-MM-DD hh24:mi:ss')as fecha_compra,\n" +
"(sum(a.valor_articulo))as suma_valor \n" +
"from lc_articulo a where a.id_deudor="+idDeudor+"and a.id_cliente="+idCliente+"group by id_articulo order by fecha_compra desc"; 
            
            String contenido="",footer="";
            String referencia,nombre_articulo,valor_articulo,fecha_compra,suma_valor;
            BigDecimal valores = BigDecimal.ZERO;
            BigDecimal suma = BigDecimal.ZERO;
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
            {
                referencia = rs.getString("referencia");
                nombre_articulo = rs.getString("nombre_articulo");
                valor_articulo =rs.getString("valor_articulo");
                fecha_compra = rs.getString("fecha_compra");
                valores = new BigDecimal(valor_articulo);
                suma= suma.add(valores);
                
                
             // contenido=contenido+"<tbody>\n" +
                contenido=contenido+ "<tr>  \n" +
                                    "<td>"+referencia+"</td>\n" +
                                    "<td>"+nombre_articulo+"</td>\n" +
                                    "<td>"+valor_articulo+"</td>\n" +
                                    "<td>"+fecha_compra+"</td>\n" +
                                    "</tr>\n";// +
                                   // "</tbody>";
                                      
            }
                footer="<tr>\n" +
                        "<th> Total Deuda: </th>  <th><strong>"+suma+"</strong></th>\n" +
                        "</tr>";
                rs.close();
                pst.close();
                conexion.cierraConexion();
                 valor=contenido+"&"+footer;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
    } 
public List<LcCuotas> getCuotas(int idCliente,int idDeudor){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcCuotas  E WHERE  E.lcDatosDeudores.idDatosDeudor = :idDeudor  and   E.idCliente= :idCliente and  E.estado= :estado order by E.numCuotas DESC");       
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcCuotas> lista=q.list();
         for(LcCuotas mrol:lista )
        {
            
             System.out.println("ok: "+mrol.getIdCuota()+" "+ mrol.getLcArticulo().getReferencia()+" "+mrol.getInteresCuota()+""+mrol.getNumCuotas()+mrol.getGastosAdicional()+
                     mrol.getGastosCobranzas()+mrol.getInteresCuota()+mrol.getValorMora()+mrol.getOtrosValores()+mrol.getTotalCuotas()+mrol.getFechaMaxPago()+mrol.getValorCuota());
             
        }
          tx.commit();
        session.close();

         return lista;
    }
    public String getCuotas2(int idCliente,int idDeudor)throws SQLException
    {   
        Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
           try
            {
            String SQL="";
            
            SQL="select (a.referencia)as referencia,(c.num_cuotas)as num_cuotas,(c.interes_cuota)as interes,(c.valor_mora)as mora,(c.gastos_cobranzas)as gastos_cobranzas,\n" +
                "(c.gastos_adicional)as gastos_adicional,(c.otros_valores)as otros_valores,(c.valor_cuota)as valor_cuota,(c.total_cuotas)as total_cuotas,\n" +
                "to_char(c.fecha_max_pago,'YYYY-MM-DD hh24:mi:ss') fecha_transaccion \n" +
                "from lc_cuotas c, lc_articulo a where c.id_deudor="+idDeudor+"\n" +
                "and c.id_cliente="+idCliente+" and c.id_articulo=a.id_articulo"; 
            
            String contenido="";
            String referencia,num_cuotas,interes,mora,gastos_cobranzas,valor_cuota,footer;
            String gastos_adicional,otros_valores,total_cuotas,fecha_transaccion;
            BigDecimal valores = BigDecimal.ZERO;
            BigDecimal suma = BigDecimal.ZERO;
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
            {
                referencia = rs.getString("referencia");
                num_cuotas = rs.getString("num_cuotas");
                interes =rs.getString("interes");
                mora = rs.getString("mora");
                gastos_cobranzas = rs.getString("gastos_cobranzas");
                gastos_adicional = rs.getString("gastos_adicional");
                otros_valores = rs.getString("otros_valores");
                valor_cuota = rs.getString("valor_cuota");
                total_cuotas = rs.getString("total_cuotas");
                fecha_transaccion = rs.getString("fecha_transaccion");
                valores = new BigDecimal(total_cuotas);
                suma= suma.add(valores);
              contenido=contenido+"<tr> \n" +
                                    " <td>"+referencia+"</td>\n" +
                                    " <td>"+num_cuotas+"</td>\n" +
                                    " <td>"+interes+"</td>\n" +
                                    " <td>"+mora+"</td>\n" +
                                    " <td>"+gastos_cobranzas+"</td>\n" +
                                    " <td>"+gastos_adicional+"</td>\n" +
                                    " <td>"+otros_valores+"</td>\n" +
                                    " <td>"+valor_cuota+"</td>\n" +
                                    " <td>"+total_cuotas+"</td>\n" +
                                    " <td>"+fecha_transaccion+"</td>  \n" +
                                    " </tr>";  
            }
            footer="<tr>\n" +
                        "<th> Total Cuotas: </th>  <th><strong>"+suma+"</strong></th>\n" +
                        "</tr>";
                rs.close();
                pst.close();
                conexion.cierraConexion();
                 valor=contenido+"&"+footer;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
    }   
    public void addRecordatorio(LcRecordatorios recordatorio){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(recordatorio);
    tx.commit();
    session.close();
    }

    public void addGestiones(LcGestiones gestion){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(gestion);
    tx.commit();
    session.close();
    }
    
    public List<LcDatosDeudores> getLcDeudorRecordatorio(int id,int empresa,int sucursal,int empleado){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        //Query q = session.createQuery("from LcDatosDeudores  E WHERE E.idDatosDeudor= :idDatosDeudor and E.lcEmpresa.idEmpresa= :idEmpresa and E.lcAgencia.idAgencia= :idAgencia and E.idEmpleado= :idEmpleado");
        Query q = session.createQuery("from LcDatosDeudores  E WHERE E.idDatosDeudor= :idDatosDeudor and E.lcEmpresa.idEmpresa= :idEmpresa and E.lcAgencia.idAgencia= :idAgencia");
        q.setParameter("idDatosDeudor",id);
        q.setParameter("idEmpresa",empresa);
        q.setParameter("idAgencia",sucursal);
      //  q.setParameter("idEmpleado",empleado);
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
             //System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getIdCliente());
             //System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEmpresa().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getIdTipocredito());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getDescripcion());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getIdEstatus());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getDescripcion());
        }
        tx.commit();
        session.close();
         return lista;
    }
    
    public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('lc_recordatorios_id_recordatorio_seq')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
    return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
    public Long getNext2() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('lc_gestiones_id_gestion_seq')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
    return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
    public List<LcTipoGestion> getLcTipoGestionComp(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcTipoGestion  E WHERE  E.estado= :estado");
        
        q.setParameter("estado","A");
        List<LcTipoGestion> lista=q.list();
         for(LcTipoGestion mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdTipoGestion());

        }
 tx.commit();
        session.close();
         return lista;
    }
    
    public List<LcTipoGestion> getLcTipoGestion(int cliente){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcTipoGestion  E WHERE E.idCliente= :idCliente and E.estado= :estado");
        q.setParameter("idCliente",cliente);
        q.setParameter("estado","A");
        List<LcTipoGestion> lista=q.list();
         for(LcTipoGestion mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdTipoGestion());

        }
 tx.commit();
        session.close();
         return lista;
    }
    public ArrayList<LcTipoResultado> getLcTipoResultado(int gestion){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcTipoResultado> arreglo = new ArrayList<LcTipoResultado>();
        Query q = session.createQuery("from LcTipoResultado  E WHERE E.lcTipoGestion.idTipoGestion= :idTipoGestion and E.estado= :estado");
        q.setParameter("idTipoGestion",gestion);
        q.setParameter("estado","A");
        List<LcTipoResultado> lista=q.list();
        Iterator<LcTipoResultado> iter=lista.iterator();
        while(iter.hasNext())
        {
            LcTipoResultado rol= (LcTipoResultado) iter.next();
            arreglo.add(rol);
        }
         tx.commit();
        session.close();
        return arreglo;

    }
    public List<LcNotas> getLcNotas(int id,int idDeudor){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        ArrayList<LcNotas> arreglo = new ArrayList<LcNotas>();
        Query q = session.createQuery("from LcNotas  E WHERE E.idCliente= :idCliente and E.idDeudor= :idDeudor and E.estado= :estado");
        q.setParameter("idCliente",id);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcNotas> lista=q.list();
         for(LcNotas mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdNota());
             System.out.println("ok: "+mrol.getIdNota()+", "+mrol.getIdDeudor());
             System.out.println("ok: "+mrol.getIdNota()+", "+mrol.getIdCliente());
        }
        tx.commit();
        session.close();
         return lista;
    }
    
    public List<LcNotas> getLcNotasId(int idNota,int idDeudor,int idCliente){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcNotas  E WHERE E.idCliente= :idCliente and E.idDeudor= :idDeudor and E.idNota= :idNota and E.estado= :estado");
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("idNota",idNota);
        q.setParameter("estado","A");
        List<LcNotas> lista=q.list();
         for(LcNotas mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdNota());
             System.out.println("ok: "+mrol.getIdNota()+", "+mrol.getIdDeudor());
             System.out.println("ok: "+mrol.getIdNota()+", "+mrol.getIdCliente());
        }
          tx.commit();
        session.close();
         return lista;
    }
    public void addNota(LcNotas nota){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(nota);
    tx.commit();
    session.close();
    }
    
    public void updateNota(int idNota, String txtnota){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcNotas agen = (LcNotas)session.get(LcNotas.class, idNota);
    agen.setObservacion(txtnota);
    session.update(agen);
    tx.commit();
    session.close();
    }
    public void updateTransactionState(int idTransaccion,String estado){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcTransacciones agen = (LcTransacciones)session.get(LcTransacciones.class, idTransaccion);
    agen.setEstado(estado);
    session.update(agen);
    tx.commit();
    session.close();
    }
    public Long getNext3() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_notas_id_nota_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; 
    }
      public Long getNext4() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_compromisos_pago_id_compromiso_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; 
    }  
    public void addCompPago(LcCompromisosPago pago){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(pago);
    tx.commit();
    session.close();
    }
   public List<LcDatosDeudores> getLcDatossgte(int empresa, int cliente, int IDEmpleado){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        //[Cambios 20170709]
        Query q = session.createQuery("from LcDatosDeudores  E WHERE E.lcClientes.idCliente= :idCliente and E.lcEmpresa.idEmpresa= :idEmpresa and E.idEmpleado = :IDEmpleado and E.estado= :estado order by E.idDatosDeudor");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("idCliente",cliente);
        q.setParameter("IDEmpleado",IDEmpleado);        
        q.setParameter("estado","A");
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
           //  System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getIdCliente());
            // System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcClientes().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getIdTipocredito());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcTipoCredito().getDescripcion());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getIdEstatus());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getDescripcion());
        }
        tx.commit();
        session.close();
         return lista;
    } 
   public String getDatosCarteras(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
            int opcion=0;
            opcion=IdFiltro;
            String Ordenar="";
            String SQL="",SQL2="";   
            
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado\n" +
"  from vw_consulta_cartera s\n" +
"where s.id_cliente="+IdCliente+"\n" +
" and s.id_empleado="+IdEmpleado; 
            
            String contenidoTabla="";
           if (opcion==0){
                // ordena por Monto de Mayor a Menor
               Ordenar="order by s.total_vencidos desc"; 
                SQL2= SQL+Ordenar;
             }
            if (opcion==1){
                // Ultima Gestion
               Ordenar="order by s.dias_mora asc"; 
                SQL2= SQL+Ordenar;
             }
            if(opcion==2){
                Ordenar="order by s.dias_mora desc";
                SQL2= SQL+Ordenar;
             }
            if(opcion==3){// ordena por Dias Mora de Menor a Mayor
                Ordenar="order by s.total_vencidos asc"; 
                SQL2= SQL+Ordenar;
            }
            if(opcion==4){ //ordena por Dias Mora de Mayor a Menor
                Ordenar="order by s.fech_ultima_gestion asc"; 
                SQL2= SQL+Ordenar;
            }
            if(opcion==5){ //ordena por fecha ultima gestion de Mayor a Menor
                Ordenar="order by s.fech_ultima_gestion desc";
                SQL2= SQL+Ordenar;
            }
           
          
               
           String id_datos_deudor;
String identificacion;
String nombres_completo;
String dias_mora;
String total_vencidos;
String pagos;
String saldo;
String valor_compro;
String fecha_comp;
String fech_ultima_gestion;
String ultima_gestion;
String resultado_gestion;
String estado;
String id_cliente;
String color;
            try
            {
           
            pst = conexion.getconexion().prepareStatement(SQL2);
                rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
                {
 id_datos_deudor=rs.getString("id_datos_deudor");
 identificacion=rs.getString("identificacion");
 nombres_completo=rs.getString("nombres_completo");
 dias_mora=rs.getString("dias_mora");
 total_vencidos=rs.getString("total_vencidos");
 pagos=rs.getString("pagos");
 saldo=rs.getString("saldo");
 valor_compro=rs.getString("valor_compro");
 fecha_comp=rs.getString("fecha_comp");
 fech_ultima_gestion=rs.getString("fech_ultima_gestion");
 ultima_gestion=rs.getString("ultima_gestion"); 
 resultado_gestion=rs.getString("resultado_gestion");
 estado=rs.getString("estado");
 if(!estado.equals("A")){
 color="class=\"text-warning\"";
 }else{
 color="";
 }
 
 id_cliente=rs.getString("id_cliente");      
  
// contenidoTabla=contenidoTabla+"<tr onclick='cobranzas2("+id_cliente+","+id_datos_deudor+");'>"+

 
  contenidoTabla=contenidoTabla+"<tr  bgcolor=\"#E0ECF8\" onclick=\"cobranzas2("+id_cliente+","+id_datos_deudor+");\"  >\n" +
"                                                                    <td class=\"hidden\"><h6><p "+color+" >"+id_datos_deudor+"</p></h6></td>\n" +
"                                                                    <td><h6><p "+color+" >"+identificacion+"</p> </h6></td>\n" +
"                                                                    <td><h6><p "+color+" >"+nombres_completo+"</p></h6></td>\n" +
"                                                                    <td align=\"center\"><h6><p "+color+" >"+dias_mora+"</p></h6></td>\n" +
"                                                                    <td align=\"right\"><h6><p "+color+" >"+total_vencidos+"</p></h6></td>\n" +
"                                                                    <td align=\"right\"><h6><p "+color+" >"+pagos+"</p></h6></td>\n" +
          "                                                         <td align=\"right\"><h6><p "+color+" >"+rs.getString("fecha_ult_pagos")+"</p></h6></td>\n" +
"                                                                    <td align=\"right\"><h6><p "+color+" >"+saldo+"</p></h6></td>\n" +
"                                                                    <td align=\"right\"><h6><p "+color+" >"+valor_compro+"</p></h6> </td>\n" +
"                                                                    <td align=\"center\"><h6><p "+color+" >"+fecha_comp+"</p></h6></td>\n" +          
"                                                                    <td align=\"center\"><SUB><p "+color+" >"+fech_ultima_gestion+"</p></SUB></td>\n" +
"                                                                    <td align=\"center\"> <h6><p "+color+" >"+ultima_gestion+"</p> </h6></td>\n" +
"                                                                    <td align=\"center\"><h6><p "+color+" > "+resultado_gestion+"</p> </h6></td>\n" +
"                                                                </tr>";
 
            }
            
                rs.close();
                pst.close();
                conexion.cierraConexion();
                 valor=contenidoTabla;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
            
    }
   
  
     public String getRetornaQuery(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){
               
               Ordenar="order by s.total_vencidos desc"; 
                
             }
            if (opcion==1){
                // Ultima Gestion
               Ordenar="order by s.fech_ultima_gestion asc"; 
                
             }
            if(opcion==2){
                Ordenar="order by s.total_vencidos asc"; 
             }
             if(opcion==3){// ordena por Dias Mora de Menor a Mayor
                Ordenar="order by s.dias_mora asc"; 
            }
            if(opcion==4){ //ordena por Dias Mora de Mayor a Menor
                Ordenar="order by s.dias_mora desc"; 
            }
            if(opcion==5){ //ordena por fecha ultima gestion de Mayor a Menor
                Ordenar="order by s.fech_ultima_gestion desc"; 
            }
            if(opcion==6){ //ordena por fecha ultima gestion de Menor a Mayor
                Ordenar="order by s.fech_ultima_gestion asc"; 
            }
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado\n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
            
    }
        public String getRetornaQuery2(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.dias_mora desc"; }
            if (opcion==1){ Ordenar="order by s.dias_mora asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
            
    }
   
   public List<String> getDatosCarterasSiguienteAnterior(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){
                // ordena por Monto de Mayor a Menor
               Ordenar="order by s.total_vencidos desc"; 
             }
            if (opcion==1){
                // Ultima Gestion
               Ordenar="order by s.fech_ultima_gestion asc"; 
                
             }
            if(opcion==2){
                Ordenar="order by s.total_vencidos asc"; 
             }
             if(opcion==3){// ordena por Dias Mora de Menor a Mayor
                Ordenar="order by s.dias_mora asc"; 
            }
            if(opcion==4){ //ordena por Dias Mora de Mayor a Menor
                Ordenar="order by s.dias_mora desc"; 
            }
            if(opcion==5){ //ordena por fecha ultima gestion de Mayor a Menor
                Ordenar="order by s.fech_ultima_gestion desc"; 
            }
            if(opcion==6){ //ordena por fecha ultima gestion de Menor a Mayor
                Ordenar="order by s.fech_ultima_gestion asc"; 
             }
            if(opcion==7){ //ordena por fecha ultima gestion de Menor a Mayor solo cuando es igual el registro y el numero de de registro
                Ordenar="order by s.fech_ultima_gestion asc"; 
             }
            String SQL="";   
            
            SQL="select  id_datos_deudor\n" +
                "  from vw_consulta_cartera s\n" +
                "where s.id_cliente="+IdCliente+"\n" +
                " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
           String id_datos_deudor;
          // List idDeudorCartera = new ArrayList();
           List<String> idDeudorCartera = new ArrayList<String>();
            try
            {
             pst = conexion.getconexion().prepareStatement(SQL);
             rs = pst.executeQuery();
              
            while( rs.next() )    //Mientras haya una sig. tupla
                {
                    id_datos_deudor=rs.getString("id_datos_deudor");
                    idDeudorCartera.add(id_datos_deudor);
            }
            
                rs.close();
                pst.close();
                conexion.cierraConexion();
                
                return idDeudorCartera;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return idDeudorCartera;
            
    }
   public List<String> getEjecutaQueryDatosDeudor(String sql)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            
           
            String SQL="";   
            
            SQL=sql; 
           String id_datos_deudor;
          // List idDeudorCartera = new ArrayList();
           List<String> idDeudorCartera = new ArrayList<String>();
            try
            {
             pst = conexion.getconexion().prepareStatement(SQL);
             rs = pst.executeQuery();
              
            while( rs.next() )    //Mientras haya una sig. tupla
                {
                    id_datos_deudor=rs.getString("id_datos_deudor");
                    idDeudorCartera.add(id_datos_deudor);
            }
            
                rs.close();
                pst.close();
                conexion.cierraConexion();
                
                return idDeudorCartera;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return idDeudorCartera;
            
    }
    public String getGestiones(int IdCliente, int IdEmpleado)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
           
            String SQL="";
            
            SQL="select (SELECT t_1.nombre_tipo_gestion\n" +
"                   FROM \n" +
"                    lc_tipo_gestion t_1\n" +
"                  WHERE  t_1.id_tipo_gestion =g.id_tipo_gestion   ) AS ultima_gestion,\n" +
"\n" +
"( SELECT lc_tipo_resultado.nombre_tipo_resultado\n" +
"                           FROM lc_tipo_resultado\n" +
"                          WHERE lc_tipo_resultado.id_tipo_resultado = g.id_tipo_resultado) AS nombre_tipo_resultado,\n" +
"g.observacion, (SELECT t_1.nombres||' '||t_1.apellidos nombres\n" +
"                   FROM \n" +
"                    lc_empleados t_1\n" +
"where id_empleado=g.id_empleado) empleado ,to_char(g.fecha_transaccion,'YYYY-MM-DD hh24:mi:ss') fecha_transaccion from lc_gestiones g\n" +
"where id_deudor="+IdEmpleado+"\n" +
"and id_cliente="+IdCliente+"\n" +
"and estado='A'\n" +
"order by g.fecha_transaccion desc  limit 25"; 
            
            String contenidoTabla="";

            try
            {
           
            pst = conexion.getconexion().prepareStatement(SQL);
                rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
            {
              contenidoTabla=contenidoTabla+" <tr   bgcolor=\"#E0ECF8\">";  
              contenidoTabla=contenidoTabla+"<td>"+rs.getString("ultima_gestion")+"</td>";  
              contenidoTabla=contenidoTabla+"<td>"+rs.getString("nombre_tipo_resultado")+"</td>";  
              contenidoTabla=contenidoTabla+"<td>"+rs.getString("observacion")+"</td>";  
              contenidoTabla=contenidoTabla+"<td>"+rs.getString("empleado")+"</td>";  
              contenidoTabla=contenidoTabla+"<td>"+rs.getString("fecha_transaccion")+"</td>";  
              contenidoTabla=contenidoTabla+"</tr>"; 
            }
            
                rs.close();
                pst.close();
                conexion.cierraConexion();
                 valor=contenidoTabla;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
            
    }
    
 
    
    
     public String getDatosCarteras2(String query)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";  
            String orderby = "ORDER BY";
            if(!query.toUpperCase().contains(orderby)){
                query=query+" ORDER BY s.id_datos_deudor";
                
            }
            String SQL="";               
            SQL=query+" limit 1000";  
            System.out.println("NUeva Consulta: "+query);
            String contenidoTabla="";
            String id_datos_deudor;
            String identificacion;
            String nombres_completo;
            String dias_mora;
            String total_vencidos;
            String pagos;
            String saldo;
            String valor_compro;
            String fecha_comp;
            String fech_ultima_gestion;
            String ultima_gestion;
            String resultado_gestion;
            String estado;
            String id_cliente;
            String color;
            try
            {
           
            pst = conexion.getconexion().prepareStatement(SQL);
                rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
                {
 id_datos_deudor=rs.getString("id_datos_deudor");
 identificacion=rs.getString("identificacion");
 nombres_completo=rs.getString("nombres_completo");
 dias_mora=rs.getString("dias_mora");
 total_vencidos=rs.getString("total_vencidos");
 pagos=rs.getString("pagos");
 saldo=rs.getString("saldo");
 valor_compro=rs.getString("valor_compro");
 fecha_comp=rs.getString("fecha_comp");
 fech_ultima_gestion=rs.getString("fech_ultima_gestion");
 ultima_gestion=rs.getString("ultima_gestion"); 
 resultado_gestion=rs.getString("resultado_gestion");
 estado=rs.getString("estado");
 if(!estado.equals("A")){
 color="class=\"text-warning\"";
 }else{
 color="";
 }
 
 id_cliente=rs.getString("id_cliente");      
  
// contenidoTabla=contenidoTabla+"<tr onclick='cobranzas2("+id_cliente+","+id_datos_deudor+");'>"+

 
  contenidoTabla=contenidoTabla+"<tr  bgcolor=\"#E0ECF8\" onclick=\"GestionCliente("+id_cliente+","+id_datos_deudor+");\"  >\n" +
"                                                                    <td class=\"hidden\"><h6><p "+color+" >"+id_datos_deudor+"</p></h6></td>\n" +
"                                                                    <td><h6><p "+color+" >"+identificacion+"</p> </h6></td>\n" +
"                                                                    <td><h6><p "+color+" >"+nombres_completo+"</p></h6></td>\n" +
"                                                                    <td align=\"center\"><h6><p "+color+" >"+dias_mora+"</p></h6></td>\n" +
"                                                                    <td align=\"right\"><h6><p "+color+" >"+total_vencidos+"</p></h6></td>\n" +
"                                                                    <td align=\"right\"><h6><p "+color+" >"+pagos+"</p></h6></td>\n" +
                                                                    " <td align=\"right\"><h6><p "+color+" >"+rs.getString("fecha_ult_pagos")+"</p></h6></td>\n" +
"                                                                    <td align=\"right\"><h6><p "+color+" >"+saldo+"</p></h6></td>\n" +
"                                                                    <td align=\"right\"><h6><p "+color+" >"+valor_compro+"</p></h6> </td>\n" +
"                                                                    <td align=\"center\"><h6><p "+color+" >"+fecha_comp+"</p></h6></td>\n" +          
"                                                                    <td align=\"center\"><SUB><p "+color+" >"+fech_ultima_gestion+"</p></SUB></td>\n" +
"                                                                    <td align=\"center\"> <h6><p "+color+" >"+ultima_gestion+"</p> </h6></td>\n" +
"                                                                    <td align=\"center\"><h6><p "+color+" > "+resultado_gestion+"</p> </h6></td>\n" +
"                                                                </tr>";
 
            }
            
                rs.close();
                pst.close();
                conexion.cierraConexion();
                 valor=contenidoTabla;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
            
    }
    public String getRetornaQuery3(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.identificacion desc"; }
            if (opcion==1){ Ordenar="order by s.identificacion asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
            
    }
    public String getRetornaQuery4(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.nombres_completo desc"; }
            if (opcion==1){ Ordenar="order by s.nombres_completo asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
    }   
    public String getRetornaQuery5(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.total_vencidos desc"; }
            if (opcion==1){ Ordenar="order by s.total_vencidos asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
    }   
    public String getRetornaQuery6(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.pagos desc"; }
            if (opcion==1){ Ordenar="order by s.pagos asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
    }
    public String getRetornaQuery7(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.saldo desc"; }
            if (opcion==1){ Ordenar="order by s.saldo asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
    }   
    public String getRetornaQuery8(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.valor_compro desc"; }
            if (opcion==1){ Ordenar="order by s.valor_compro asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
    }      
    public String getRetornaQuery9(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.fecha_comp desc"; }
            if (opcion==1){ Ordenar="order by s.fecha_comp asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos, fecha_ult_pagos,saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
    }
    public String getRetornaQuery10(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.fech_ultima_gestion desc"; }
            if (opcion==1){ Ordenar="order by s.fech_ultima_gestion asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
    }   
    public String getRetornaQuery11(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.ultima_gestion desc"; }
            if (opcion==1){ Ordenar="order by s.ultima_gestion asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
    } 
    public String getRetornaQuery12(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.resultado_gestion desc"; }
            if (opcion==1){ Ordenar="order by s.resultado_gestion asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
    }    
    public String getRetornaQuery13(int IdCliente, int IdEmpleado, int IdFiltro)throws SQLException{
    
          
            int opcion;
            opcion=IdFiltro;
            String Ordenar="";
            if (opcion==0){ Ordenar="order by s.fecha_ult_pagos desc"; }
            if (opcion==1){ Ordenar="order by s.fecha_ult_pagos asc"; }
            
           
            String SQL="";
            SQL="select  id_datos_deudor, id_cliente, identificacion, nombres_completo , dias_mora, total_vencidos, pagos,fecha_ult_pagos, saldo, valor_compro,fecha_comp, fech_ultima_gestion,ultima_gestion,resultado_gestion,estado \n" +
            "  from vw_consulta_cartera s\n" +
            " where s.id_cliente="+IdCliente+"\n" +
            " and s.id_empleado="+IdEmpleado+" "+Ordenar; 
        return SQL;
    }  
    
    public String getTiposGestion(int IDCliente){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray(); 
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion     
//        Query q = session.createQuery("from LcTipoGestion  E WHERE E.idCliente=:IDCliente and E.estado= :estado");
        Query q = session.createQuery("from LcTipoGestion  E WHERE  E.estado= :estado");
        //q.setParameter("IDCliente",IDCliente);
        q.setParameter("estado","A");
        List<LcTipoGestion> lista=q.list(); 
            for(LcTipoGestion datos:lista )
            {
               System.out.print("Datos: "+datos.getIdCliente());
               json = new JSONObject();
               json.put("idTipoGestion",datos.getIdTipoGestion());
               json.put("nombreTipoGestion", datos.getNombreTipoGestion());
               itemSelectedJson.add(json);
               
            }
            
            tx.commit();
            session.close();

         return  itemSelectedJson.toString();
      
    }
    public String getTiposResultados(int idcliente){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion     
        Query q = session.createQuery("from LcClienteResultado  E WHERE E.lcClientes.idCliente=:idcliente and E.estado= :estado and E.perfiles is null");
        q.setParameter("idcliente",idcliente);
        q.setParameter("estado","A");
        List<LcClienteResultado> lista=q.list(); 
    
        for(LcClienteResultado datos:lista )
        { 
           json = new JSONObject();
           json.put("idTipoResultado",datos.getLcTipoResultado().getIdTipoResultado());
           json.put("nombreTipoResultado", datos.getLcTipoResultado().getNombreTipoResultado());
           itemSelectedJson.add(json);
        }
        tx.commit();
        session.close();

         return  itemSelectedJson.toString();
      
    }
    public String getTiposResultados2(int idcliente, String perfiles){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion     
        Query q = session.createQuery("from LcClienteResultado  E WHERE E.lcClientes.idCliente=:idcliente and E.estado= :estado and E.perfiles = :perfiles");
        q.setParameter("idcliente",idcliente);
        q.setParameter("perfiles",perfiles);
        q.setParameter("estado","A");
        List<LcClienteResultado> lista=q.list(); 
    
        for(LcClienteResultado datos:lista )
        { 
           json = new JSONObject();
           json.put("idTipoResultado",datos.getLcTipoResultado().getIdTipoResultado());
           json.put("nombreTipoResultado", datos.getLcTipoResultado().getNombreTipoResultado());
           itemSelectedJson.add(json);
        }
        tx.commit();
        session.close();

         return  itemSelectedJson.toString();
      
    }
    public String getMisTiposResultados(){
         String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_consulta_tipos_resultados();");
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
    
     public String getConsultaCartera(String query){
         String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_consulta_cartera('"+query+"');");
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
    
    public String getGestionCliente(int idCliente,int idDeudor){
        RecaudacionServicios rs = new RecaudacionServicios();
        int mi_parroquia=0;
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();   
        BigDecimal tsaldo=BigDecimal.ZERO;
        BigDecimal valorPagado =BigDecimal.ZERO;
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcTransacciones  E WHERE E.lcClientes.idCliente= :idCliente and E.lcDatosDeudores.idDatosDeudor=:idDeudor and E.estado in ('A','I','P','C')");
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        List<LcTransacciones> lista=q.list();
         for(LcTransacciones datos:lista )
        {
            try {
             json = new JSONObject();
             json.put("IdDeudor",idDeudor);
             json.put("Identificacion",datos.getLcDatosDeudores().getIdentificacion());
             json.put("NombresCompletos",datos.getLcDatosDeudores().getNombresCompleto());
             json.put("IdCliente",datos.getLcClientes().getIdCliente());
             json.put("RazonSocialCliente",datos.getLcClientes().getRazonSocial());
             json.put("NumCuenta",datos.getNumCuenta());
             json.put("TotalDeuda",datos.getMontoAsignado());
             json.put("TotalVencido",datos.getTotalVencidos());
             json.put("Pago",getUltimoPago(idCliente, idDeudor));
             json.put("NombreEmpleado", getNombreUsuario(datos.getLcEmpleados().getIdEmpleado()));
             //nom_empleado
             //getNombreGrupo
             int idSegmento=0;
             try{
                if(!datos.getIdSegmento().equals("")){
                    idSegmento=datos.getIdSegmento();
                }
             }catch (Exception e){
                idSegmento=0;
             }
             
            
             json.put("Grupo",getNombreGrupo(idSegmento));
            
                valorPagado=rs.getValorRecaudado(datos.getIdEmpresa(),idCliente, idDeudor);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCarteraServicios.class.getName()).log(Level.SEVERE, null, ex);
               
            }
            
             tsaldo=datos.getMontoAsignado().subtract(getUltimoPago(idCliente, idDeudor));
             json.put("Saldo",tsaldo);
             json.put("Notas",getLcNotas2(idCliente,idDeudor));
             json.put("NotasAdm",getLcNotasAdm(idCliente,idDeudor));
             json.put("IDNotas",getLcNotasID(idCliente,idDeudor));     
             json.put("DiasMora",datos.getDiasMora());
             json.put("IDCiudad",datos.getLcDatosDeudores().getLcCiudad().getIdCiudad());
             mi_parroquia=datos.getLcDatosDeudores().getIdParroquia();
                     
             json.put("Ciudad",datos.getLcDatosDeudores().getLcCiudad().getCiudad()+""+getMiParroquia(mi_parroquia)); 
             json.put("IDTransaccion",datos.getIdTransaccion()); 
            
             
             itemSelectedJson.add(json);
            
        }
        tx.commit();
        session.close();
        return  itemSelectedJson.toString();
    }
    public String getGestionClienteTRX(int idTransaccion){
        RecaudacionServicios rs = new RecaudacionServicios();
        int mi_parroquia=0;
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();   
        BigDecimal tsaldo=BigDecimal.ZERO;
        BigDecimal valorPagado =BigDecimal.ZERO;
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcTransacciones  E WHERE E.idTransaccion= :idTransaccion and E.estado in ('A','I','P','C')");
        q.setParameter("idTransaccion",idTransaccion);
        //q.setParameter("idDeudor",idDeudor);
        List<LcTransacciones> lista=q.list();
         for(LcTransacciones datos:lista )
        {
            try {
             json = new JSONObject();
             json.put("IdDeudor",datos.getLcDatosDeudores().getIdDatosDeudor());
             json.put("Identificacion",datos.getLcDatosDeudores().getIdentificacion());
             json.put("NombresCompletos",datos.getLcDatosDeudores().getNombresCompleto());
             json.put("IdCliente",datos.getLcClientes().getIdCliente());
             json.put("RazonSocialCliente",datos.getLcClientes().getRazonSocial());
             json.put("NumCuenta",datos.getNumCuenta());
             json.put("TotalDeuda",datos.getMontoAsignado());
             json.put("TotalVencido",datos.getTotalVencidos());
             json.put("Pago",getUltimoPago(datos.getLcClientes().getIdCliente(), datos.getLcDatosDeudores().getIdDatosDeudor()));
             json.put("NombreEmpleado", getNombreUsuario(datos.getLcEmpleados().getIdEmpleado()));
             //nom_empleado
             //getNombreGrupo
             int idSegmento=0;
             try{
                if(!datos.getIdSegmento().equals("")){
                    idSegmento=datos.getIdSegmento();
                }
             }catch (Exception e){
                idSegmento=0;
             }
             
            
             json.put("Grupo",getNombreGrupo(idSegmento));
            
                valorPagado=rs.getValorRecaudado(datos.getIdEmpresa(),datos.getLcClientes().getIdCliente(), datos.getLcDatosDeudores().getIdDatosDeudor());
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCarteraServicios.class.getName()).log(Level.SEVERE, null, ex);
               
            }
            
             tsaldo=datos.getMontoAsignado().subtract(getUltimoPago(datos.getLcClientes().getIdCliente(), datos.getLcDatosDeudores().getIdDatosDeudor()));
             json.put("Saldo",tsaldo);
             json.put("Notas",getLcNotas2(datos.getLcClientes().getIdCliente(), datos.getLcDatosDeudores().getIdDatosDeudor()));
             json.put("NotasAdm",getLcNotasAdm(datos.getLcClientes().getIdCliente(), datos.getLcDatosDeudores().getIdDatosDeudor()));
             json.put("IDNotas",getLcNotasID(datos.getLcClientes().getIdCliente(), datos.getLcDatosDeudores().getIdDatosDeudor()));     
             json.put("DiasMora",datos.getDiasMora());
             json.put("IDCiudad",datos.getLcDatosDeudores().getLcCiudad().getIdCiudad());
             mi_parroquia=datos.getLcDatosDeudores().getIdParroquia();
                     
             json.put("Ciudad",datos.getLcDatosDeudores().getLcCiudad().getCiudad()+""+getMiParroquia(mi_parroquia)); 
             json.put("IDTransaccion",datos.getIdTransaccion()); 
            
             
             itemSelectedJson.add(json);
            
        }
        tx.commit();
        session.close();
        return  itemSelectedJson.toString();
    }
    
    public String getNombreUsuario(int IdEmpleado) {
        
        String  NombreEmpleado="";
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("from LcUsuarios E WHERE E.lcEmpleados.idEmpleado= :idEmpleado");
        q.setParameter("idEmpleado", IdEmpleado);
        //q.setParameter("estado", "A");
        List<LcUsuarios> lista = q.list();
        for (LcUsuarios datos : lista) {
            
            NombreEmpleado=datos.getUsuario();
          
           
          }
    tx.commit();
    session.close();
    
        return NombreEmpleado;
    }
    
    public String  getNombreGrupo(int id){
        String valor="";
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcSegmento  E WHERE E.idSegmento= :idSegmento");
        q.setParameter("idSegmento",id);
        List<LcSegmento> lista=q.list();

         for(LcSegmento data:lista )
        {
            // System.out.println("ok: "+mrol.getIdTelefono()+", "+mrol.getTelefono());
            // System.out.println("ok: "+mrol.getIdTelefono()+", "+mrol.getLcTiposTelefono().getNombreTipoTlf());
            valor=data.getNombreSegmento().toString();
             
        }
        tx.commit();
        session.close();
        return  valor;
    }
    public String getLcDireccionJSON(String ide){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray(); 
        String tablaDireccion="";
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcDireccion  E WHERE E.identificacionDeudor= :identificacionDeudor and E.estado='A'");      
        q.setParameter("identificacionDeudor",ide);
        List<LcDireccion> lista=q.list();
       
         for(LcDireccion mrol:lista )
        {
             json = new JSONObject();
             json.put("TipoDireccion",mrol.getLcTiposDireccion().getNombreTipoDireccion());
             json.put("Direccion",mrol.getDireccionCompleta());
             json.put("acciones","<a  href='#' class='text-red' onclick='inactivarDireccion("+mrol.getIdDireccion()+");'  ><span class='glyphicon glyphicon-trash  aria-hidden='true'></span></a>");
             itemSelectedJson.add(json);
           // tablaDireccion+="<tr bgcolor='#E0ECF8' width='100%'><td class='col-sm-2'>"+mrol.getLcTiposDireccion().getNombreTipoDireccion()+"</td><td class='col-sm-6'>"+mrol.getDireccionCompleta()+"</td></tr>";
             
        }
        tx.commit();
        session.close();
           // return  tablaDireccion;
        return  itemSelectedJson.toString();
    }
    public String  getLcTelefonoJSON(String ide){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();  
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcTelefonos  E WHERE E.identificacionDeudor= :identificacionDeudor and E.estado= 'A'");
        q.setParameter("identificacionDeudor",ide);
        List<LcTelefonos> lista=q.list();

         for(LcTelefonos mrol:lista )
        {
            // System.out.println("ok: "+mrol.getIdTelefono()+", "+mrol.getTelefono());
            // System.out.println("ok: "+mrol.getIdTelefono()+", "+mrol.getLcTiposTelefono().getNombreTipoTlf());
             json = new JSONObject();
             json.put("TipoTelefono",mrol.getLcTiposTelefono().getNombreTipoTlf());
             json.put("Telefono",mrol.getTelefono());
             json.put("Llamar","<a  href='#' class='text-red' onclick='inactivaTelefono("+mrol.getIdTelefono()+","+mrol.getTelefono()+");'  ><span class='glyphicon glyphicon-trash  aria-hidden='true'></span></a>");
             itemSelectedJson.add(json);
             
        }
        tx.commit();
        session.close();
        return  itemSelectedJson.toString();
    }
    
    public String getGestionesJSON(int idCliente,int idDeudor){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();  
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String FechaGestion="";
           
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcGestiones  E WHERE  E.lcDatosDeudores.idDatosDeudor = :idDeudor  and   E.lcClientes.idCliente= :idCliente and  E.estado= :estado order by E.fechaTransaccion desc");
        
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcGestiones> lista=q.list();
         for(LcGestiones mrol:lista )
        {
             FechaGestion = dateFormatter.format(mrol.getFechaTransaccion());
            // System.out.println("ok: "+mrol.getLcClientes().getRazonSocial()+" "+ mrol.getLcDatosDeudores().getNombres()+" "+mrol.getLcTipoGestion().getNombreTipoGestion()+" "+ mrol.getLcTipoResultado().getNombreTipoResultado()+""+mrol.getLcEmpleados().getNombres()+""+mrol.getLcEmpleados().getApellidos());
              json = new JSONObject();
             json.put("TipoGestion",mrol.getLcTipoGestion().getNombreTipoGestion());
             json.put("Gestion",mrol.getLcTipoResultado().getNombreTipoResultado());
             json.put("Descripcion",mrol.getObservacion());
             json.put("Oficial", getNombreUsuario(mrol.getLcEmpleados().getIdEmpleado()).toUpperCase());
             json.put("fecha",FechaGestion);
             itemSelectedJson.add(json);
        }
          tx.commit();
        session.close();

         return  itemSelectedJson.toString();
    }
    public String getNombreUsuario2(int id_empleado){
    
       SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("select E.usuario from LcUsuarios  E WHERE  E.lcEmpleados.idEmpleado = :id_empleado");
        
        q.setParameter("id_empleado",id_empleado);
        String  NombreUsuario="";
        NombreUsuario = (String) q.uniqueResult();
        
        tx.commit();
        session.close();

         return  NombreUsuario;
    
    
    
    }
     public String getLcNotas2(int idCliente,int idDeudor){
        String misnotas=""; 
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        ArrayList<LcNotas> arreglo = new ArrayList<LcNotas>();
        Query q = session.createQuery("from LcNotas  E WHERE E.idCliente= :idCliente and E.idDeudor= :idDeudor and E.estado= :estado");
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcNotas> lista=q.list();
         for(LcNotas notas:lista )
        {
            /* System.out.println("ok: "+mrol.getIdNota());
             System.out.println("ok: "+mrol.getIdNota()+", "+mrol.getIdDeudor());
             System.out.println("ok: "+mrol.getIdNota()+", "+mrol.getIdCliente());*/
             misnotas+= notas.getObservacion();
        }
        tx.commit();
        session.close();
         return misnotas;
    }
      public String getLcNotasAdm(int idCliente,int idDeudor){
        String misnotas=""; 
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        ArrayList<LcNotas> arreglo = new ArrayList<LcNotas>();
        Query q = session.createQuery("from LcNotas  E WHERE E.idCliente= :idCliente and E.idDeudor= :idDeudor and E.estado= :estado");
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcNotas> lista=q.list();
         for(LcNotas notas:lista )
        {
            
             misnotas+= notas.getNotaAdmin();
        }
        tx.commit();
        session.close();
         return misnotas;
    }
      public int getLcNotasID(int idCliente,int idDeudor){
        int id_notas = 0; 
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        ArrayList<LcNotas> arreglo = new ArrayList<LcNotas>();
        Query q = session.createQuery("from LcNotas  E WHERE E.idCliente= :idCliente and E.idDeudor= :idDeudor and E.estado= :estado");
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcNotas> lista=q.list();
         for(LcNotas notas:lista )
        {
            /* System.out.println("ok: "+mrol.getIdNota());
             System.out.println("ok: "+mrol.getIdNota()+", "+mrol.getIdDeudor());
             System.out.println("ok: "+mrol.getIdNota()+", "+mrol.getIdCliente());*/
             id_notas= notas.getIdNota();
        }
        tx.commit();
        session.close();
         return id_notas;
    }
      public int getLcNotasIDAdmin(int idCliente,int idDeudor){
        int id_notas = 0; 
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        ArrayList<LcNotas> arreglo = new ArrayList<LcNotas>();
        Query q = session.createQuery("from LcNotas  E WHERE E.idCliente= :idCliente and E.idDeudor= :idDeudor and E.estado= :estado");
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcNotas> lista=q.list();
         for(LcNotas notas:lista )
        {
            /* System.out.println("ok: "+mrol.getIdNota());
             System.out.println("ok: "+mrol.getIdNota()+", "+mrol.getIdDeudor());
             System.out.println("ok: "+mrol.getIdNota()+", "+mrol.getIdCliente());*/
             id_notas= notas.getIdNota();
        }
        tx.commit();
        session.close();
         return id_notas;
    }
    public String getDetallesComprasJSON(int idCliente,int idDeudor){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();  
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String FechaCompra=""; 
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcArticulo  E WHERE  E.lcDatosDeudores.idDatosDeudor = :idDeudor  and   E.idCliente= :idCliente and  E.estado= :estado order by E.fechaRegistro");       
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcArticulo> lista=q.list();
         for(LcArticulo mrol:lista )
        {
            
             FechaCompra = dateFormatter.format(mrol.getFechaCompra());
             json = new JSONObject();
             json.put("ReferenciaCompra",mrol.getReferencia());
             json.put("Descripcion",mrol.getNombreArticulo());
             json.put("ValorCompra",mrol.getValorArticulo());
             json.put("Fecha", FechaCompra);
             itemSelectedJson.add(json);
        }
          tx.commit();
        session.close();

         return  itemSelectedJson.toString();
    }
    
    public String getClientes(int empresa)
        {
            JSONObject json = new JSONObject();
            JSONArray itemSelectedJson = new JSONArray();  
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session;
            session = sesion.openSession();
            Transaction tx= session.beginTransaction();
            Query q = session.createQuery("from LcClientes  E WHERE E.lcEmpresa.idEmpresa= :empresa and  E.estado= :estado");  
            q.setParameter("empresa",empresa);
            q.setParameter("estado","A");
            List<LcClientes> lista=q.list();
             for(LcClientes datos:lista )
            {

              
                 json = new JSONObject();
                 json.put("id_cliente",datos.getIdCliente());
                 json.put("razon_social",datos.getRazonSocial());
                 itemSelectedJson.add(json);
            }
              tx.commit();
            session.close();

         return  itemSelectedJson.toString();
        }
    public String getClientesRol(int empresa, int id_empleado)
        {
            JSONObject json = new JSONObject();
            JSONArray itemSelectedJson = new JSONArray();  
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session;
            session = sesion.openSession();
            Transaction tx= session.beginTransaction();
            Query q = session.createQuery("from LcClientes  E WHERE E.lcEmpresa.idEmpresa= :empresa and  E.estado= :estado");  
            q.setParameter("empresa",empresa);
            q.setParameter("estado","A");
            List<LcClientes> lista=q.list();
             for(LcClientes datos:lista )
            {

              
                 json = new JSONObject();
                 json.put("id_cliente",datos.getIdCliente());
                 json.put("razon_social",datos.getRazonSocial());
                 itemSelectedJson.add(json);
            }
              tx.commit();
            session.close();

         return  itemSelectedJson.toString();
        }
    
     public String getDetallesComprasSTRING(int idCliente,int idDeudor){
       
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String FechaCompra="", Tabla=""; 
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcArticulo  E WHERE  E.lcDatosDeudores.idDatosDeudor = :idDeudor  and   E.idCliente= :idCliente and  E.estado= :estado order by E.fechaRegistro");       
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcArticulo> lista=q.list();
         for(LcArticulo mrol:lista )
        {
            
            // System.out.println("ok: "+mrol.getNombreArticulo()+" "+ mrol.getReferencia()+" "+mrol.getFechaCompra()+""+mrol.getValorArticulo());
             FechaCompra = dateFormatter.format(mrol.getFechaCompra());
            // System.out.println("ok: "+mrol.getLcClientes().getRazonSocial()+" "+ mrol.getLcDatosDeudores().getNombres()+" "+mrol.getLcTipoGestion().getNombreTipoGestion()+" "+ mrol.getLcTipoResultado().getNombreTipoResultado()+""+mrol.getLcEmpleados().getNombres()+""+mrol.getLcEmpleados().getApellidos());
             /*json = new JSONObject();
             json.put("ReferenciaCompra",mrol.getReferencia());
             json.put("Descripcion",mrol.getNombreArticulo());
             json.put("ValorCompra",mrol.getValorArticulo());
             json.put("Fecha", FechaCompra);
             itemSelectedJson.add(json);*/
             
             Tabla=Tabla+"<tr><td>"+mrol.getReferencia()+"</td><td>"+mrol.getNombreArticulo()+"</td><td>"+mrol.getValorArticulo()+"</td><td>"+FechaCompra+"</td></tr>";
             
        }
        tx.commit();
        session.close();

         return  Tabla;
    }
    public String getDetallesCuotasComprasJSON(int idCliente,int idDeudor){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();  
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String FechaCompra="",FechaMaxPago=""; 
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
       Query q = session.createQuery("from LcCuotas  E WHERE  E.lcDatosDeudores.idDatosDeudor = :idDeudor  and   E.idCliente= :idCliente and  E.estado= :estado order by E.fechaMaxPago DESC");       
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcCuotas> lista=q.list();
         for(LcCuotas mrol:lista )
        {
            
            // System.out.println("ok: "+mrol.getNombreArticulo()+" "+ mrol.getReferencia()+" "+mrol.getFechaCompra()+""+mrol.getValorArticulo());
             FechaCompra = dateFormatter.format(mrol.getFechaRegistro());
             FechaMaxPago = dateFormatter.format(mrol.getFechaMaxPago());
            // System.out.println("ok: "+mrol.getLcClientes().getRazonSocial()+" "+ mrol.getLcDatosDeudores().getNombres()+" "+mrol.getLcTipoGestion().getNombreTipoGestion()+" "+ mrol.getLcTipoResultado().getNombreTipoResultado()+""+mrol.getLcEmpleados().getNombres()+""+mrol.getLcEmpleados().getApellidos());
             json = new JSONObject();
             json.put("ReferenciaCompra",mrol.getLcArticulo().getReferencia());
             json.put("NumCuota",mrol.getNumCuotas());
             json.put("Interes",mrol.getInteresCuota());
             json.put("Mora",mrol.getValorMora());
             json.put("GastosCobranzas",mrol.getGastosCobranzas());
             json.put("GastosAdicionales",mrol.getGastosAdicional());
             json.put("OtrosGastos",mrol.getOtrosValores());
             json.put("ValorCuota",mrol.getValorCuota());
             json.put("Total",mrol.getValorMora().add(mrol.getInteresCuota()).add(mrol.getValorMora()).add(mrol.getGastosCobranzas()).add(mrol.getGastosAdicional()).add(mrol.getOtrosValores()).add(mrol.getValorCuota()));
             json.put("FechaMaxPago",FechaMaxPago);
             json.put("PagosRealizado","0");
             json.put("FechaPagoRealizado","");
             json.put("Fecha", FechaCompra);
             itemSelectedJson.add(json);
        }
          tx.commit();
        session.close();

         return  itemSelectedJson.toString();
    }
    public BigDecimal getUltimoPago(int idCliente,int idDeudor){
        BigDecimal UltimoPago=BigDecimal.ZERO;
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcRecaudaciones  E WHERE  E.lcDatosDeudores.idDatosDeudor = :idDeudor  and   E.idCliente= :idCliente and  E.estado= :estado and origen='SISTEMA' order by E.fechaRegistro DESC");       
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        q.setParameter("estado","A");
        List<LcRecaudaciones> lista=q.list();
         for(LcRecaudaciones datos:lista )
        {
            UltimoPago=UltimoPago.add(datos.getValorRecaudado());
            
        }
          tx.commit();
        session.close();

         return  UltimoPago;
    }
    
     public int seq_query(String str_query) {
        try{
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        String orderby = "ORDER BY";
            if(!str_query.toUpperCase().contains(orderby)){
                str_query=str_query+" ORDER BY s.id_datos_deudor";
                
            }
        
        Query query = session.createSQLQuery( "select ejecuta_sql('"+str_query+"')" );
        int secuencia = ((Integer) query.uniqueResult());   
        tx.commit();  
        session.close();
        return secuencia;
        } catch (Exception e) {
            System.out.println("Error Laticobsa: Al consultar la funcion ejecuta_sql");
            e.printStackTrace();
            return 0;
        }
    }
     
      public int getSiguiente(int secuencia, int idDeudor) {
        try{
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select fnc_siguiente("+secuencia+","+idDeudor+")");
        int idDeudorSig = ((Integer) query.uniqueResult());   
        tx.commit();  
        session.close();
        return idDeudorSig;
        } catch (Exception e) {
            System.out.println("Error Laticobsa: Al consultar la funcion fnc_siguiente");
            e.printStackTrace();
            return 0;
        }
    }
       public int getAnteiror(int secuencia, int idDeudor) {
      
       try{
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select fnc_anterior("+secuencia+","+idDeudor+")");
        int idDeudorAnt = ((Integer) query.uniqueResult());   
        tx.commit();  
        session.close();
        return idDeudorAnt;
        } catch (Exception e) {
            System.out.println("Error Laticobsa: Al consultar la funcion fnc_anterior");
            e.printStackTrace();
            return 0;
        }
    }
    
        public String getNuevaConsulta(String Query){
         String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_consulta_cartera('"+Query+"');");
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
            public String getConsultaCampania(int id_empleado){
                String valor = "";
                try{      
                   Conexion conexion=new Conexion();
                   PreparedStatement pst;
                   ResultSet rs;
                   pst = conexion.getconexion().prepareStatement("select fnc_campania_vigente("+id_empleado+");");
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
         public String fnc_ConsultaGestionesDiarias(){
         String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_consultagestiones_diarias(0,'',0);");
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
        
         public String fnc_ConsultaCarteraEmpleados1(){
             String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_consulta_cartera_empleados1();");
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString("fnc_consulta_cartera_empleados1");
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor; 
         }
         public String fnc_ConsultaCedenteSubCaretra(int id_empresa){
             String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_consulta_mis_asignaciones_clientes("+id_empresa+");");
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString("fnc_consulta_mis_asignaciones_clientes");
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor; 
         }
         public String fnc_ConsultaCuadroCalidad2(int id_empresa,int id_agencia){
             String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_cuadro_calidad2("+id_empresa+","+id_agencia+");");
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString("fnc_cuadro_calidad2");
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor; 
         }
         public String fnc_ConsultaCuadroCalidad(int id_empresa,int id_agencia,String fecha_ini, String fecha_hasta){
             String valor = "",sqlFuncion="select fnc_cuadro_calidad("+id_empresa+","+id_agencia+",'"+fecha_ini+"','"+fecha_hasta+"');";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(sqlFuncion);
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString("fnc_cuadro_calidad");
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor; 
         }
         
public String fnc_ConsultaCuadroCalidadEficiencia(int id_empresa,int id_agencia,int empleado_id, String fecha_ini, String fecha_hasta, int cartera, int sub_cartera){
             String valor = "",sqlFuncion="select fnc_calidad_eficiencia("+id_empresa+","+id_agencia+","+empleado_id+",'"+fecha_ini+"','"+fecha_hasta+"',"+cartera+","+sub_cartera+");";
                                            //      select fnc_calidad_eficiencia(1,1,96,'2018-04-11','2018-04-11',5,6)                                      //

             try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement(sqlFuncion);
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString("fnc_calidad_eficiencia");
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor; 
         }

         
         
         public String fnc_registra_notificacion(int id_empresa,int id_agencia, int id_deudor, int id_cliente, int id_empleado, String fecha){
             String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_registra_notificacion("+id_empresa+","+id_agencia+", "+id_deudor+", "+id_cliente+",  "+id_empleado+", '"+fecha+"');");
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString("fnc_registra_notificacion");
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor; 
         }
        public int getEmpleadoCartera( int id_empleado)
        {
         String valor = "0";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select distinct(id_cartera) cartera from lc_transacciones where id_empleado="+id_empleado+" and estado!='E';");
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getString("cartera");
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return Integer.parseInt(valor); 
        }
    
}
