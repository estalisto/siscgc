/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcArticulo;
import com.siscogescorp.modelo.LcAsignaEmpleadosTmp;
import com.siscogescorp.modelo.LcCartera;
import com.siscogescorp.modelo.LcCiudad;
import com.siscogescorp.modelo.LcClienteResultado;
import com.siscogescorp.modelo.LcClientes;
import com.siscogescorp.modelo.LcCompromisosPago;
import com.siscogescorp.modelo.LcCuotas;
import com.siscogescorp.modelo.LcDatosDeudores;
import com.siscogescorp.modelo.LcDireccion;
import com.siscogescorp.modelo.LcGestiones;
import com.siscogescorp.modelo.LcNotas;
import com.siscogescorp.modelo.LcRecordatorios;
import com.siscogescorp.modelo.LcSegmento;
import com.siscogescorp.modelo.LcSubcartera;
import com.siscogescorp.modelo.LcSubsegmento;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ViewSoft
 */
public class AsignacionCarteraServicios {
        
    
    public List<LcClientes> getLcDatoscliente(int empresa){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery(" from LcClientes  E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("estado","A");
        List<LcClientes> lista=q.list();
         for(LcClientes mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdCliente());
        }
        tx.commit();
        session.close();
         return lista;
    }
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
            Query q = session.createQuery("select DISTINCT E.lcClientes.idCliente  from LcTransacciones  E WHERE E.idEmpresa= :idEmpresa and E.idAgencia= :idAgencia and E.lcEmpleados.idEmpleado= :idEmpleado and E.estado= :estado");
            q.setParameter("idEmpresa",empresa);
            q.setParameter("idAgencia",agencia);
            q.setParameter("idEmpleado",empleado);
            q.setParameter("estado","A");
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
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getLcEstatus().getIdEstatus());
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
"                  WHERE  t_1.id_cliente=g.id_cliente AND   t_1.id_tipo_gestion =g.id_tipo_gestion   ) AS ultima_gestion,\n" +
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
            String SQL="";               
            SQL=query+" limit 1000";             
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
        Query q = session.createQuery("from LcTipoGestion  E WHERE E.idCliente=:IDCliente and E.estado= :estado");
        q.setParameter("IDCliente",IDCliente);
        q.setParameter("estado","A");
        List<LcTipoGestion> lista=q.list(); 
            for(LcTipoGestion datos:lista )
            {
                json = new JSONObject();
               //System.out.print("Datos: "+datos.getIdCliente());
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
        Query q = session.createQuery("from LcClienteResultado  E WHERE E.lcClientes.idCliente=:idcliente and E.estado= :estado");
                                      // from LcClienteResultado  E WHERE E.lcClientes.idCliente=:idcliente and E.estado= :estado
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
    
    public String getGestionCliente(int idCliente,int idDeudor){
        RecaudacionServicios rs = new RecaudacionServicios();
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();   
        BigDecimal tsaldo=BigDecimal.ZERO;
        BigDecimal valorPagado =BigDecimal.ZERO;
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcTransacciones  E WHERE E.lcClientes.idCliente= :idCliente and E.lcDatosDeudores.idDatosDeudor=:idDeudor");
        q.setParameter("idCliente",idCliente);
        q.setParameter("idDeudor",idDeudor);
        List<LcTransacciones> lista=q.list();
         for(LcTransacciones datos:lista )
        {
             json = new JSONObject();
             json.put("IdDeudor",idDeudor);
             json.put("Identificacion",datos.getLcDatosDeudores().getIdentificacion());
             json.put("NombresCompletos",datos.getLcDatosDeudores().getNombresCompleto());
             json.put("IdCliente",datos.getLcClientes().getIdCliente());
             json.put("RazonSocialCliente",datos.getLcClientes().getRazonSocial());
             json.put("NumCuenta",datos.getNumCuenta());
             json.put("TotalDeuda",datos.getTotalVencidos());
             json.put("TotalVencido",datos.getTotalVencidos());
             json.put("Pago",datos.getUltimoPago());
            try {
                valorPagado=rs.getValorRecaudado(datos.getIdEmpresa(),idCliente, idDeudor);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCarteraServicios.class.getName()).log(Level.SEVERE, null, ex);
               
            }
             tsaldo=datos.getTotalVencidos().subtract(valorPagado);
             json.put("Saldo",tsaldo);
             json.put("Notas",getLcNotas2(idCliente,idDeudor));
             json.put("IDNotas",getLcNotasID(idCliente,idDeudor));             
             json.put("DiasMora",datos.getDiasMora());
             json.put("IDCiudad",datos.getLcDatosDeudores().getLcCiudad().getIdCiudad());
             json.put("Ciudad",datos.getLcDatosDeudores().getLcCiudad().getCiudad()); 
             json.put("IDTransaccion",datos.getIdTransaccion()); 
            
             
             itemSelectedJson.add(json);
            
        }
        tx.commit();
        session.close();
        return  itemSelectedJson.toString();
    }
    
    public String getLcDireccionJSON(String ide){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray(); 
        String tablaDireccion="";
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcDireccion  E WHERE E.identificacionDeudor= :identificacionDeudor");      
        q.setParameter("identificacionDeudor",ide);
        List<LcDireccion> lista=q.list();
       
         for(LcDireccion mrol:lista )
        {
             json = new JSONObject();
             json.put("TipoDireccion",mrol.getLcTiposDireccion().getNombreTipoDireccion());
             json.put("Direccion",mrol.getDireccionCompleta());
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
        Query q = session.createQuery("from LcTelefonos  E WHERE E.identificacionDeudor= :identificacionDeudor");
        q.setParameter("identificacionDeudor",ide);
        List<LcTelefonos> lista=q.list();

         for(LcTelefonos mrol:lista )
        {
            // System.out.println("ok: "+mrol.getIdTelefono()+", "+mrol.getTelefono());
            // System.out.println("ok: "+mrol.getIdTelefono()+", "+mrol.getLcTiposTelefono().getNombreTipoTlf());
              json = new JSONObject();
             json.put("TipoTelefono",mrol.getLcTiposTelefono().getNombreTipoTlf());
             json.put("Telefono",mrol.getTelefono());
             json.put("Llamar","<a  href='#' ><span class='glyphicon glyphicon-phone-alt' aria-hidden='true'></span></a>");
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
    public String getNombreUsuario(int id_empleado){
    
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
            
            // System.out.println("ok: "+mrol.getNombreArticulo()+" "+ mrol.getReferencia()+" "+mrol.getFechaCompra()+""+mrol.getValorArticulo());
             FechaCompra = dateFormatter.format(mrol.getFechaCompra());
            // System.out.println("ok: "+mrol.getLcClientes().getRazonSocial()+" "+ mrol.getLcDatosDeudores().getNombres()+" "+mrol.getLcTipoGestion().getNombreTipoGestion()+" "+ mrol.getLcTipoResultado().getNombreTipoResultado()+""+mrol.getLcEmpleados().getNombres()+""+mrol.getLcEmpleados().getApellidos());
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
    
    public String getTiposCartera(int IDCliente){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();
        
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion     
        Query q = session.createQuery("from LcCartera  E WHERE E.lcClientes.idCliente=:idCliente and E.estado= :estado");
        q.setParameter("idCliente",IDCliente);
        q.setParameter("estado","A");
        List<LcCartera> lista=q.list(); 
            for(LcCartera datos:lista )
            {
               //System.out.print("Datos: "+datos.getLcClientes().getIdCliente());
               json = new JSONObject(); 
               json.put("idCartera",datos.getIdCartera());
               json.put("nombreCartera", datos.getNombreCartera());
               itemSelectedJson.add(json);
               
            }
            
            tx.commit();
            session.close();

         return  itemSelectedJson.toString();
      
    }
    public String getTiposubCartera(int IDCartera){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion     
        Query q = session.createQuery("from LcSubcartera  E WHERE E.lcCartera.idCartera=:idCartera and E.estado= :estado");
        q.setParameter("idCartera",IDCartera);
        q.setParameter("estado","A");
        List<LcSubcartera> lista=q.list(); 
            for(LcSubcartera datos:lista )
            {
               //System.out.print("Datos: "+datos.getLcCartera().getIdCartera());
               json = new JSONObject(); 
               json.put("idSubcartera",datos.getIdSubcartera());
               json.put("nombreSubcartera", datos.getNombreSubcartera());
               itemSelectedJson.add(json);
               
            }
            
            tx.commit();
            session.close();

         return  itemSelectedJson.toString();
      
    }
    public String getTipoSegmento(int IDsubCartera){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion     
        Query q = session.createQuery("from LcSegmento  E WHERE E.lcSubcartera.idSubcartera=:idSubcartera and E.estado= :estado");
        q.setParameter("idSubcartera",IDsubCartera);
        q.setParameter("estado","A");
        List<LcSegmento> lista=q.list(); 
            for(LcSegmento datos:lista )
            {
               //System.out.print("Datos: "+datos.getLcCartera().getIdCartera());
               json = new JSONObject(); 
               json.put("idSegmento",datos.getIdSegmento());
               json.put("nombreSegmento", datos.getNombreSegmento());
               itemSelectedJson.add(json);
               
            }
            
            tx.commit();
            session.close();

         return  itemSelectedJson.toString();
      
    }
    public String getTipoSubSegmento(int IDsegmento){
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion     
        Query q = session.createQuery("from LcSubsegmento  E WHERE E.lcSegmento.idSegmento=:idSegmento and E.estado= :estado");
        q.setParameter("idSegmento",IDsegmento);
        q.setParameter("estado","A");
        List<LcSubsegmento> lista=q.list(); 
            for(LcSubsegmento datos:lista )
            {
               //System.out.print("Datos: "+datos.getLcCartera().getIdCartera());
               json = new JSONObject(); 
               json.put("IdSubsegmento",datos.getIdSubsegmento());
               json.put("NombreSubsegmento", datos.getNombreSubsegmento());
               itemSelectedJson.add(json);
               
            }
            
            tx.commit();
            session.close();

         return  itemSelectedJson.toString();
    }
   public String getFiltroEmpleados(int cartera,int empresa,int sucursal)throws SQLException
    {   
        Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
           try
            {
            String SQL="";
            Date fecha_actual= new Date(); 
            SQL = "select (a.id_sec_campania)as id_asigna,(a.estado)as estado,(e.id_empleado)as id_empleado,(e.nombres||' '||e.apellidos)as nombres_empleados,(c.razon_social)as cartera\n" +
            "from lc_empleados e,lc_asigna_empleados_tmp a,lc_clientes c \n" +
            "where a.id_empleado=e.id_empleado and c.id_cliente=a.id_cliente and e.id_empresa="+empresa+"\n" +
            "and e.id_empresa=c.id_empresa and c.id_cliente ="+cartera+"\n" +
            "and e.id_agencia="+sucursal+"";
            
            String contenido = "";
            String id_empleado, nombres_empleados, carteras,id_asigna,estado;
            
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            int cont = 0,cont1=0,cont2=0;
            while( rs.next() )    //Mientras haya una sig. tupla
            {    
                estado= rs.getString("estado");
                id_asigna = rs.getString("id_asigna");
                id_empleado = rs.getString("id_empleado");
                nombres_empleados = rs.getString("nombres_empleados");
                carteras =rs.getString("cartera");
                contenido = contenido + "<tr id=\"fila_"+cont+"\">\n"
                + "<td class=\"text-center hidden\"><input id=\"id_asigna_"+cont+"\" style=\"display:none\" value=\'"+id_asigna+"\'>" + id_asigna + "</td>\n"        
                + "<td class=\"text-center hidden\"><input id=\"id_recaudacion_caja_"+cont+"\" style=\"display:none\" value=\'"+id_empleado+"\'>" + id_empleado + "</td>\n"
                + "<td class=\"text-center\"><input id=\"fecha_cobro_caja_"+cont+"\" style=\"display:none\" value=\'"+nombres_empleados+"\'>" + nombres_empleados + "</td>\n"
                + "<td class=\"text-center\"><input id=\"nombres_completo_caja_"+cont+"\" style=\"display:none\" value=\'"+carteras+"\'>" + carteras + "</a> </td>\n";
                //+ "<td> <center><label>Asignar &nbsp;</label><input id=\"check_active\" onclick=\"GuardarEmpleado("+id_empleado+")\" name=\"check_active\"  type=\"checkbox\">&nbsp;</center></tr>";
                List<LcAsignaEmpleadosTmp> empleados = getLcIDEmpleados(Integer.parseInt(id_empleado));
                //int idcheck=empleados.get(0).getLcEmpleados().getIdEmpleado();
                if(estado.equals("I")){
                    contenido = contenido + "<td> <center><label>Asignar &nbsp;</label><input id=\"check_asigna_"+cont+"\" onclick=\"CheckeoGuardaGeneraal("+id_empleado+","+cont+")\" name=\"check_asigna_"+cont+"\"  type=\"checkbox\" >&nbsp;</center></tr>";
                }else{
                    contenido = contenido + "<td> <center><label>Asignar &nbsp;</label><input id=\"check_asigna_"+cont+"\" onclick=\"CheckeoGuardaGeneraal("+id_empleado+","+cont+")\" name=\"check_asigna_"+cont+"\"  type=\"checkbox\" checked=\"true\">&nbsp;</center></tr>";
                }cont++;
            }
            
            //footer="<div class=\"col-lg-2\"><strong><input type=\"text\" value=\'"+suma+"\'></strong> </div></th> ";
            
            
                rs.close();
                pst.close();
                conexion.cierraConexion();
            valor = contenido;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
    }   
    public String getTodosEmpleados(int empresa,int sucursal)throws SQLException
    {   
        Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
           try
            {
            String SQL="";
            Date fecha_actual= new Date(); 
            SQL = "select (e.id_empleado)as id_empleado,(e.nombres||' '||e.apellidos)as nombres_empleados,(c.razon_social)as cartera\n" +
"                from lc_empleados e,lc_asigna_empleados_tmp a,lc_clientes c \n" +
"                where a.id_empleado=e.id_empleado and e.id_empresa="+empresa+" and e.id_agencia="+sucursal+"\n" +
"                and e.id_empresa=c.id_empresa and e.estado='A' group by e.id_empleado,c.razon_social;";
            
            String contenido = "",footer="";
            String id_empleado, nombres_empleados, carteras;
            
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            int cont = 0;
            while( rs.next() )    //Mientras haya una sig. tupla
            {      
                id_empleado = rs.getString("id_empleado");
                nombres_empleados = rs.getString("nombres_empleados");
                carteras =rs.getString("cartera");
                contenido = contenido + "<tr id=\"fila_"+cont+"\">"
                + "<td class=\"text-center hidden\"><input id=\"id_recaudacion_caja_"+cont+"\" style=\"display:none\" value=\'"+id_empleado+"\'>" + id_empleado + "</td>\n"
                + "<td class=\"text-center\"><input id=\"fecha_cobro_caja_"+cont+"\" style=\"display:none\" value=\'"+nombres_empleados+"\'>" + nombres_empleados + "</td>\n"
                + "<td class=\"text-center\"><input id=\"nombres_completo_caja_"+cont+"\" style=\"display:none\" value=\'"+carteras+"\'>" + carteras + "</a> </td>\n"
                
                + "<td> <center><label>Asignar &nbsp;</label><input id=\"check_active\" onclick=\"GuardarEmpleado("+id_empleado+")\" name=\"check_active\"  type=\"checkbox\">&nbsp;</center></tr>";

            }
            
            //footer="<div class=\"col-lg-2\"><strong><input type=\"text\" value=\'"+suma+"\'></strong> </div></th> ";
            
            
                rs.close();
                pst.close();
                conexion.cierraConexion();
            valor = contenido;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
    }   
    public List<LcAsignaEmpleadosTmp> getLcIDEmpleados(int empleado){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery(" from LcAsignaEmpleadosTmp  E WHERE E.lcEmpleados.idEmpleado= :idEmpleado and E.estado= :estado");
        q.setParameter("idEmpleado",empleado);
        q.setParameter("estado","A");
        List<LcAsignaEmpleadosTmp> lista=q.list();
         for(LcAsignaEmpleadosTmp mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdSecCampania());
        }
        tx.commit();
        session.close();
         return lista;
    }
   public void addEmpleadoAsigna(LcAsignaEmpleadosTmp empleado){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(empleado);
    tx.commit();
    session.close();
    }
    public Long getNext5() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('lc_asigna_empleados_tmp_id_sec_campania_seq')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
    return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
    public void updateAsignacion(int id_sec_campania){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcAsignaEmpleadosTmp agen = (LcAsignaEmpleadosTmp)session.get(LcAsignaEmpleadosTmp.class, id_sec_campania);
    agen.setEstado("I");
    session.update(agen);
    tx.commit();
    session.close();
    }
    public String getEmpledosAsginadosCarteras(int idempresa, int idagencia, int cartera) {
        String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select  fnc_empleados_x_carteras2("+idempresa+","+idagencia+","+cartera+");");
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
    
     public String getEmpledosAsginadosAll(int idempresa, int idagencia) {
        String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select  fnc_empleados_x_carteras("+idempresa+","+idagencia+");");
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
    public String getTotalesAsignados(int secuencia, int idcliente) {
        String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select  fnc_total_monto_asignados("+secuencia+","+idcliente+");");
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
    public String getEjecutaQuery(String Query) {
        String valor = "0";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select  fnc_ejecuta_query('"+Query+"');");
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
                 return valor;  
             } 
        return valor;   
        }
    public String getRegistraTransTem(int secuencia, int cartera){
        String valor = "0";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select  fnc_asignacion_trans_tmp("+secuencia+","+cartera+");");
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
                 return valor;  
             } 
        return valor;   
        }
    public String getProcesaAsignacion(int secuencia, double monto_promedio){
        String valor = "0";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select  fnc_procesa_asignacion("+secuencia+","+monto_promedio+");");
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
                 return valor;  
             } 
        return valor;   
        }
    public String getEmpleadosAsignados(int secuencia){
        String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select json_agg(empleados)\n" +
                                            "from (\n" +
                                            "  select e.id_empleado, \n" +
                                            "(select sum(total_vencidos) from lc_asignacion_trans_tmp where id_secuencial=e.secuencial and id_empleado=e.id_empleado) totales,\n" +
                                            "(select count(*) from lc_asignacion_trans_tmp where id_secuencial=e.secuencial and id_empleado=e.id_empleado) cantidad,\n" +
                                            "(select m.nombres||' '||m.apellidos from lc_empleados m where m.id_empleado=e.id_empleado) empleado\n" +
                                            "from lc_empleados_selec e\n" +
                                            "where secuencial="+secuencia+" \n" +
                                            ") as empleados");
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
