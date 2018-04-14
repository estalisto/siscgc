/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcDatosDeudores;
import com.siscogescorp.modelo.LcDetRecaudaciones;
import com.siscogescorp.modelo.LcEmpleados;
import com.siscogescorp.modelo.LcFormapagoRecaudacion;
import com.siscogescorp.modelo.LcRecaudaciones;
import com.siscogescorp.modelo.LcTiposIdentificacion;
import com.siscogescorp.utils.Conexion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author CIMA2015
 */
public class PagosServicios {
    ClientesServicios cli = new ClientesServicios();
    EmpleadosServicios emp = new EmpleadosServicios();
    ConsultaxCarteraServicios con = new ConsultaxCarteraServicios();
     public List<LcDetRecaudaciones> getLcDatosPagoActual(){
        Date fecha_reg = new Date();
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String convertido = fecha.format(fecha_reg);
        System.out.println(convertido);
        try {
             SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
             Date ActualDate = dateFormatter.parse(convertido);
              
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcDetRecaudaciones  E WHERE E.fechaRegistro>= :fechaRegistro AND estado= :estado");
        q.setParameter("estado","A");
        q.setParameter("fechaRegistro",ActualDate);
        List<LcDetRecaudaciones> lista=q.list();
         for(LcDetRecaudaciones mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDetRecaudacion()+", "+mrol.getLcRecaudaciones().getLcDatosDeudores().getIdDatosDeudor());
        }
        tx.commit();
        session.close();
         return lista;
         } catch (ParseException ex) {
             Logger.getLogger(PagosServicios.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }

    public List<LcRecaudaciones> getLcDatosRecaudoActual(int empresa, int empleado){
         JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();  
        Date fecha_reg = new Date();
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String convertido = fecha.format(fecha_reg);
        System.out.println(convertido);
        try {
             SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
             Date ActualDate = dateFormatter.parse(convertido);
              int cont=0;
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcRecaudaciones  E WHERE E.idEmpresa= :idEmpresa AND E.idEmpleado= :idEmpleado AND E.fechaRegistro>= :fechaRegistro AND estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("idEmpleado",empleado);
        q.setParameter("estado","A");
        q.setParameter("fechaRegistro",ActualDate);
        List<LcRecaudaciones> lista=q.list();
         for(LcRecaudaciones mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdRecaudacion()+", "+mrol.getLcDatosDeudores().getIdDatosDeudor()+mrol.getLcDatosDeudores().getNombresCompleto());
              json = new JSONObject();
             json.put("IdRecaudacion",mrol.getIdRecaudacion());
             json.put("NombreDeudor",mrol.getLcDatosDeudores().getNombresCompleto());
             json.put("Valor",mrol.getValorRecaudado());
             json.put("Accion","<a onclick=\"VistaPago("+mrol.getIdRecaudacion() +")\"> <span class=\"glyphicon glyphicon-eye-open\" aria-hidden=\"true\"><a id=\"admini_"+cont+"\" onclick=\"autorizacion("+mrol.getIdRecaudacion() +","+cont+")\" data-toggle=\"modal\"> <span  class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>");
             cont ++;
             itemSelectedJson.add(json);
        }
        tx.commit();
        session.close();
         return lista;
         } catch (ParseException ex) {
             Logger.getLogger(PagosServicios.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }
    
    public String getLcRecaudacionesActual(int empresa, int idAgencia, String fechaInicio, String fechaHasta,int EmpleadoID){
         JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();  
        SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormatter3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String Fechapago=""; 
        BigDecimal sumando = BigDecimal.ZERO;
        Date fecha_reg = new Date();
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String convertido = fecha.format(fecha_reg);
        int idDeudor=0;
                
        System.out.println(convertido);
        try {
             SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
             Date ActualDate = dateFormatter.parse(convertido);
              
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session;
            session = sesion.openSession();
            Transaction tx= session.beginTransaction();
            // hacemos la transaccion
            if (fechaInicio == null){
                int cont=0;
                Query q = session.createQuery("from LcRecaudaciones  E WHERE E.idEmpresa= :idEmpresa AND E.idAgencia= :idAgencia AND E.fechaRegistro>= :fechaRegistro AND estado= :estado");
                q.setParameter("idEmpresa",empresa);
                q.setParameter("idAgencia",idAgencia);
                q.setParameter("estado","A");
                q.setParameter("fechaRegistro",ActualDate);
                List<LcRecaudaciones> lista=q.list();
                 for(LcRecaudaciones mrol:lista )
                {     Fechapago=dateFormatter2.format(mrol.getFechaRegistro());
                     //System.out.println("ok: "+mrol.getIdRecaudacion()+", "+mrol.getLcDatosDeudores().getIdDatosDeudor()+mrol.getLcDatosDeudores().getNombresCompleto());
                     json = new JSONObject();
                     json.put("IdRecaudacion",mrol.getIdRecaudacion());
                     json.put("TipoPago",getPagoFormaPago(mrol.getIdRecaudacion()));
                     json.put("Cliente",cli.getDatosLClienteID(mrol.getIdCliente()).get(0).getRazonSocial());  
                     idDeudor = mrol.getLcDatosDeudores().getIdDatosDeudor();
                     json.put("IdentificacionDeudor",mrol.getLcDatosDeudores().getIdentificacion()); 
                     json.put("NombreDeudor",mrol.getLcDatosDeudores().getNombresCompleto());
                     json.put("Recaudador",emp.getDatosLCEmpleadosID(mrol.getIdEmpleado()).get(0).getNombres()+" "+emp.getDatosLCEmpleadosID(mrol.getIdEmpleado()).get(0).getApellidos());
                     json.put("UserSist",emp.getDatosLCEmpleadosID(EmpleadoID).get(0).getNombres()+" "+emp.getDatosLCEmpleadosID(EmpleadoID).get(0).getApellidos());
                     json.put("UserAsig",con.getTransaccionesRecaudo(mrol.getIdCliente(), idDeudor, empresa).get(0).getLcEmpleados().getNombres()+" "+con.getTransaccionesRecaudo(mrol.getIdCliente(), idDeudor, empresa).get(0).getLcEmpleados().getApellidos());
                     json.put("Valor",mrol.getValorRecaudado());
                     json.put("FechaPago",Fechapago);
                     json.put("Accion","<a onclick=\"VistaPago("+mrol.getIdRecaudacion() +")\"> <span class=\"glyphicon glyphicon-eye-open\" aria-hidden=\"true\"><a id=\"admini_"+cont+"\" onclick=\"autorizacion("+mrol.getIdRecaudacion() +","+cont+")\" data-toggle=\"modal\"> <span  class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>");
                     cont ++;
                     itemSelectedJson.add(json);
                    //sumando=sumando.add(mrol.getValorRecaudado());
                }
            }else{
                String beginDate="", endDate="";
                beginDate = fechaInicio+" 00:00:00";
                endDate = fechaHasta+" 23:59:59";
                //beginDate=dateFormatter2.format(beginDate);
                Date beginDate2 = dateFormatter3.parse(beginDate);
                Date endDate2 = dateFormatter3.parse(endDate);
                //endDate=dateFormatter2.format(endDate);
                int cont2=0;
                Query q = session.createQuery("from LcRecaudaciones  E WHERE E.idEmpresa= :idEmpresa AND E.idAgencia= :idAgencia AND E.fechaRegistro>= :beginDate AND E.fechaRegistro<= :endDate AND estado= :estado");
                q.setParameter("idEmpresa",empresa);
                q.setParameter("idAgencia",idAgencia);
                q.setParameter("estado","A");
                q.setParameter("beginDate",beginDate2);             
                q.setParameter("endDate",endDate2);
                List<LcRecaudaciones> lista=q.list();
                 for(LcRecaudaciones mrol:lista )
                {     Fechapago=dateFormatter2.format(mrol.getFechaRegistro());
                     //System.out.println("ok: "+mrol.getIdRecaudacion()+", "+mrol.getLcDatosDeudores().getIdDatosDeudor()+mrol.getLcDatosDeudores().getNombresCompleto());
                     json = new JSONObject();
                     json.put("IdRecaudacion",mrol.getIdRecaudacion());
                     json.put("TipoPago",getPagoFormaPago(mrol.getIdRecaudacion()));
                     json.put("Cliente",cli.getDatosLClienteID(mrol.getIdCliente()).get(0).getRazonSocial());   
                     json.put("IdentificacionDeudor",mrol.getLcDatosDeudores().getIdentificacion());
                     idDeudor = mrol.getLcDatosDeudores().getIdDatosDeudor();
                     json.put("NombreDeudor",mrol.getLcDatosDeudores().getNombresCompleto());
                     json.put("Recaudador",emp.getDatosLCEmpleadosID(mrol.getIdEmpleado()).get(0).getNombres()+" "+emp.getDatosLCEmpleadosID(mrol.getIdEmpleado()).get(0).getApellidos());
                     json.put("UserSist",emp.getDatosLCEmpleadosID(EmpleadoID).get(0).getNombres()+" "+emp.getDatosLCEmpleadosID(EmpleadoID).get(0).getApellidos());
                     json.put("UserAsig",con.getTransaccionesRecaudo(mrol.getIdCliente(), idDeudor, empresa).get(0).getLcEmpleados().getNombres()+" "+con.getTransaccionesRecaudo(mrol.getIdCliente(), idDeudor, empresa).get(0).getLcEmpleados().getApellidos());
                     json.put("Valor",mrol.getValorRecaudado());
                     json.put("FechaPago",Fechapago);
                     json.put("Accion","<a onclick=\"VistaPago("+mrol.getIdRecaudacion() +")\"> <span class=\"glyphicon glyphicon-eye-open\" aria-hidden=\"true\"><a id=\"admini_"+cont2+"\" onclick=\"autorizacion("+mrol.getIdRecaudacion() +","+cont2+")\" data-toggle=\"modal\"> <span  class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>");
                     cont2 ++;
                     itemSelectedJson.add(json);
                    //sumando=sumando.add(mrol.getValorRecaudado());
                }
            }
            tx.commit();
            session.close();
            
            return  itemSelectedJson.toString();
         } catch (ParseException ex) {
             Logger.getLogger(PagosServicios.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("Error al Consultar los Datos Recaudaciones");

         }
        return null;
    }
    
     public String getConsultaTotales(int empresa, String Fecha_desde, String Fecha_Hasta)
        {
            
            String valor = "", query="";
            
                
            
         try{   
             SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             SimpleDateFormat dateFormatter3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
             String beginDate=null, endDate=null;
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
              if (Fecha_desde != null){
                  
                beginDate =  Fecha_desde+" 00:00:00";
                endDate = Fecha_Hasta+" 23:59:59";
                Date beginDate2 = dateFormatter3.parse(beginDate);
                Date endDate2 = dateFormatter3.parse(endDate);
                beginDate2 = dateFormatter2.parse(beginDate);
                endDate2 = dateFormatter2.parse(endDate);
                
                SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
                Date date,date2;
                try {
                  date = originalFormat.parse(Fecha_desde+" 00:00:00");
                  System.out.println("Old Format :   " + originalFormat.format(date));
                  System.out.println("New Format :   " + targetFormat.format(date));
                  
                  date2 = originalFormat.parse(Fecha_Hasta+" 23:59:59");
                  System.out.println("Old Format :   " + originalFormat.format(date2));
                  System.out.println("New Format :   " + targetFormat.format(date2));

               
                query="SELECT  '{\"data\":['||row_to_json((SELECT d FROM (SELECT (select (select  case when sum(r.valor_recaudado) is null then 0.00 else sum(r.valor_recaudado) end   from lc_formapago_recaudacion f, lc_recaudaciones r\n" +
"where r.id_recaudacion=f.id_recaudacion and r.id_empresa="+empresa+" and r.fecha_registro >= '"+targetFormat.format(date)+"' and r.fecha_registro <= '"+targetFormat.format(date2)+"' and f.tipo_forma_pago=1 and r.estado='A' ) ) AS efectivo,  \n" +
"(select  case when sum(r.valor_recaudado) is null then 0.00 else sum(r.valor_recaudado) end   from lc_formapago_recaudacion f, lc_recaudaciones r\n" +
"where r.id_recaudacion=f.id_recaudacion and r.id_empresa="+empresa+" and r.fecha_registro >= '"+targetFormat.format(date)+"' and r.fecha_registro <= '"+targetFormat.format(date2)+"' and f.tipo_forma_pago=2 and r.estado='A' ) as cheque,\n" +
"(select case when sum(r.valor_recaudado) is null then 0.00 else sum(r.valor_recaudado) end    from lc_formapago_recaudacion f, lc_recaudaciones r\n" +
"where r.id_recaudacion=f.id_recaudacion and r.id_empresa="+empresa+" and r.fecha_registro >= '"+targetFormat.format(date)+"' and r.fecha_registro <= '"+targetFormat.format(date2)+"' and f.tipo_forma_pago=3 and r.estado='A' ) as tcredito) d))\n" +
"||']}' datos";
                  } catch (ParseException ex) {
                   // Handle Exception.
                 }
   
                
                  pst = conexion.getconexion().prepareStatement(query);
                //pst = conexion.getconexion().prepareStatement("select  fnc_consulta_sumatorias("+empresa+",to_date('"+beginDate2+"','dd-mm-yyyy hh24:mi:ss'),to_date('"+endDate2+"','dd-mm-yyyy hh24:mi:ss'));");
              }else{
                //pst = conexion.getconexion().prepareStatement("select  fnc_consulta_sumatorias("+empresa+",to_date('"+Fecha_desde+"','dd-mm-yyyy hh24:mi:ss'),to_date('"+Fecha_Hasta+"','dd-mm-yyyy hh24:mi:ss'));");
                 pst = conexion.getconexion().prepareStatement("select  fnc_consulta_sumatorias("+empresa+","+Fecha_desde+","+Fecha_Hasta+");");
              }
             
             
            
            //query="select  fnc_consulta_sumatorias("+empresa+","+Fecha_desde+","+Fecha_Hasta+");";
          
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
             } catch (ParseException ex) { 
            Logger.getLogger(PagosServicios.class.getName()).log(Level.SEVERE, null, ex);
        } 
            return valor;
        }
    
    
    public BigDecimal getSumLcRecaudaciones(int empresa, int empleado, String fechaInicio, String fechaHasta){
   
        String Fechapago=""; 
        BigDecimal sumando = BigDecimal.ZERO, sumando2 = BigDecimal.ZERO;
        sumando=sumando.add(BigDecimal.ZERO);

               
        
        Date fecha_reg = new Date();
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        SimpleDateFormat dateFormatter3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String convertido = fecha.format(fecha_reg);
        System.out.println(convertido);
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date ActualDate = dateFormatter.parse(convertido);              
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session;
            session = sesion.openSession();
            Transaction tx= session.beginTransaction();
            if (fechaInicio == null){
                Query q = session.createQuery("select sum(valorRecaudado) from LcRecaudaciones  E WHERE E.idEmpresa= :idEmpresa AND E.idEmpleado= :idEmpleado AND E.fechaRegistro>= :fechaRegistro AND estado= :estado");
                q.setParameter("idEmpresa",empresa);
                q.setParameter("idEmpleado",empleado);
                q.setParameter("estado","A");
                q.setParameter("fechaRegistro",ActualDate);
                sumando = ((BigDecimal)  q.uniqueResult());  
                   System.out.println("Suman sumando: " +sumando);
                
                
                
                
                
            }else{
                String beginDate="", endDate="";
                beginDate = fechaInicio+" 00:00:00";
                endDate = fechaHasta+" 23:59:59";
                Date beginDate2 = dateFormatter3.parse(beginDate);
                Date endDate2 = dateFormatter3.parse(endDate);
                Query q = session.createQuery("select sum(valorRecaudado) from LcRecaudaciones  E WHERE E.idEmpresa= :idEmpresa AND E.idEmpleado= :idEmpleado AND E.fechaRegistro>= :beginDate AND E.fechaRegistro<= :endDate AND estado= :estado");
                q.setParameter("idEmpresa",empresa);
                q.setParameter("idEmpleado",empleado);
                q.setParameter("estado","A");
                q.setParameter("beginDate",beginDate2);             
                q.setParameter("endDate",endDate2);
                 sumando = ((BigDecimal)  q.uniqueResult());   
                
            }            
            if(sumando != null){
              sumando2=sumando;  
            }else{
            sumando2=sumando2.add(BigDecimal.ZERO);
            }
            tx.commit();
            session.close();
            
            return  sumando2;
         } catch (ParseException ex) {
             Logger.getLogger(PagosServicios.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("Error al Consultar la sumatoria los Datos Recaudaciones");

         }
        return null;
    }
    
    
    
    
    
    public List<LcRecaudaciones> getConsultaLcDatosRecaudaciones(int empresa, int empleado, String fechaInicio, String fechaFin){

        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
           //  SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
             Date beginDate = dateFormatter.parse(fechaInicio+" 00:00:00");
             Date endDate = dateFormatter.parse(fechaFin+" 23:59:59");             
             System.out.print("fechaInicio: "+fechaInicio+" 00:00:00");
             System.out.print("fechaFin: "+fechaFin+" 23:59:59");             

              
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcRecaudaciones  E WHERE E.idEmpresa= :idEmpresa AND E.idEmpleado= :idEmpleado AND E.fechaRegistro>= :beginDate AND E.fechaRegistro<= :endDate AND estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("idEmpleado",empleado);
        q.setParameter("estado","A");
        q.setParameter("beginDate",beginDate);             
        q.setParameter("endDate",endDate);
        List<LcRecaudaciones> lista=q.list();
         for(LcRecaudaciones mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdRecaudacion()+", "+mrol.getLcDatosDeudores().getIdDatosDeudor()+mrol.getLcDatosDeudores().getNombresCompleto());
        }
        tx.commit();
        session.close();
         return lista;
         } catch (ParseException ex) {
             Logger.getLogger(PagosServicios.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    } 
    
    public String getPagoFormaPago( int idRecaudacion){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        String forma_pago="";
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcFormapagoRecaudacion  E WHERE  E.lcRecaudaciones.idRecaudacion= :idRecaudacion and E.estado= :estado");
        q.setParameter("idRecaudacion",idRecaudacion);
        
        q.setParameter("estado","A");
        List<LcFormapagoRecaudacion> lista=q.list();
         for(LcFormapagoRecaudacion mrol:lista )
        {
             forma_pago=mrol.getLcTipoFormaPago().getDescripcion();
        }
        tx.commit();
        session.close();
         return forma_pago;
    } 
    
    public List<LcDatosDeudores> getLcDatosDeudoIDE(int  ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcDatosDeudores  E WHERE E.idDatosDeudor= :idDatosDeudor  and E.estado= :estado");
        q.setParameter("idDatosDeudor",ide);
        q.setParameter("estado","P");
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getNombres());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getApellidos());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getNombresCompleto());
        }
        tx.commit();
        session.close();
         return lista;
    }  
    public List<LcRecaudaciones> getLcREcaudacionIDE(int  ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcRecaudaciones  E WHERE E.idDeudor= :idDeudor  and E.estado= :estado");
        q.setParameter("idDeudor",ide);
        q.setParameter("estado","A");
        List<LcRecaudaciones> lista=q.list();
         for(LcRecaudaciones mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdRecaudacion()+", "+mrol.getLcDatosDeudores().getIdDatosDeudor());
             
        }
        tx.commit();
        session.close();
         return lista;
    } 
    
    public List<LcTiposIdentificacion> getLctipoIde(int  ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcTiposIdentificacion  E WHERE E.idTipoIdentificacion= :idTipoIdentificacion  and E.estado= :estado");
        q.setParameter("idTipoIdentificacion",ide);
        q.setParameter("estado","A");
        List<LcTiposIdentificacion> lista=q.list();
         for(LcTiposIdentificacion mrol:lista )
        {
             System.out.println("ok: "+mrol.getTipoIdentificacion()+", "+mrol.getDescripcion());
             
        }
        tx.commit();
        session.close();
         return lista;
    }
    public List<LcEmpleados> getLcoficial(int  ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcEmpleados  E WHERE E.idEmpleado= :idEmpleado  and E.estado= :estado");
        q.setParameter("idEmpleado",ide);
        q.setParameter("estado","A");
        List<LcEmpleados> lista=q.list();
         for(LcEmpleados mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdEmpleado()+", "+mrol.getNombres());
             
        }
        tx.commit();
        session.close();
         return lista;
    }
    public List<LcRecaudaciones> getLcREcaudacion(int  ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcRecaudaciones  E WHERE E.idRecaudacion= :idRecaudacion  and E.estado= :estado");
        q.setParameter("idRecaudacion",ide);
        q.setParameter("estado","A");
        List<LcRecaudaciones> lista=q.list();
         for(LcRecaudaciones mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdRecaudacion()+", "+mrol.getLcDatosDeudores().getIdDatosDeudor());
             
        }
        tx.commit();
        session.close();
         return lista;
    }
    public List<LcDetRecaudaciones> getDEtalle(int  ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcDetRecaudaciones  E WHERE E.lcRecaudaciones.idRecaudacion= :idRecaudacion  and E.estado= :estado");
        q.setParameter("idRecaudacion",ide);
        q.setParameter("estado","A");
        List<LcDetRecaudaciones> lista=q.list();
         for(LcDetRecaudaciones mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDetRecaudacion()+", "+mrol.getValorRecaudado());
             System.out.println("ok: "+mrol.getIdDetRecaudacion()+", "+mrol.getIdArticulo());
        }
        tx.commit();
        session.close();
         return lista;
    }  
    public List<LcDetRecaudaciones> getDEtalleid( int idRecaudacion){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcDetRecaudaciones  E WHERE  E.lcRecaudaciones.idRecaudacion= :idRecaudacion and E.estado= :estado");
        q.setParameter("idRecaudacion",idRecaudacion);
        
        q.setParameter("estado","A");
        List<LcDetRecaudaciones> lista=q.list();
         for(LcDetRecaudaciones mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDetRecaudacion()+", "+mrol.getValorRecaudado());
             System.out.println("ok: "+mrol.getIdDetRecaudacion()+", "+mrol.getIdArticulo());
        }
        tx.commit();
        session.close();
         return lista;
    }  
    public List<LcFormapagoRecaudacion> getPagoid( int idRecaudacion){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcFormapagoRecaudacion  E WHERE  E.lcRecaudaciones.idRecaudacion= :idRecaudacion and E.estado= :estado");
        q.setParameter("idRecaudacion",idRecaudacion);
        
        q.setParameter("estado","A");
        List<LcFormapagoRecaudacion> lista=q.list();
         for(LcFormapagoRecaudacion mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdFormpago()+", "+mrol.getTotalRecaudado());
             System.out.println("ok: "+mrol.getIdFormpago()+", "+mrol.getIdEntidadFinanciera());
        }
        tx.commit();
        session.close();
         return lista;
    }  
     public List<LcFormapagoRecaudacion> getFormPago(int  ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcFormapagoRecaudacion  E WHERE E.lcRecaudaciones.idRecaudacion= :idRecaudacion  and E.estado= :estado");
        q.setParameter("idRecaudacion",ide);
        q.setParameter("estado","A");
        List<LcFormapagoRecaudacion> lista=q.list();
         for(LcFormapagoRecaudacion mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdFormpago()+", "+mrol.getLcRecaudaciones().getIdRecaudacion());
             
        }
        tx.commit();
        session.close();
         return lista;
    }  
    
    public void deletePago(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        LcDetRecaudaciones agen = (LcDetRecaudaciones) session.get(LcDetRecaudaciones.class, id);
        agen.setEstado("I");
        session.update(agen);
        tx.commit();
        session.close();
    }    
    public void deleteCabecera(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        LcRecaudaciones agen = (LcRecaudaciones) session.get(LcRecaudaciones.class, id);
        agen.setEstado("I");
        session.update(agen);
        tx.commit();
        session.close();
    }
        public void deleteFormaPago(int idRecaudacion) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        LcFormapagoRecaudacion agen = (LcFormapagoRecaudacion) session.get(LcFormapagoRecaudacion.class, idRecaudacion);
        agen.setEstado("I");
        session.update(agen);
        tx.commit();
        session.close();
    }
    //idRecaudacion,idArticulo,numCuota,Valor_cuota,Valor_Pagar,Interes,Mora,Saldo,fecha_act
    public void updateCabecera(int idRecaudacion, BigDecimal valor,Date fecha){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcRecaudaciones agen = (LcRecaudaciones)session.get(LcRecaudaciones.class, idRecaudacion);
    agen.setValorRecaudado(valor);
    agen.setFechaActualizado(fecha);
    session.update(agen);
    tx.commit();
    session.close();
    }    
    public void updateDetalle(int idDetRecaudacion,BigDecimal Valor_Pagar,BigDecimal Saldo,Date fecha){ //int idArticulo,int numCuota,BigDecimal Valor_cuota,BigDecimal Interes,BigDecimal Mora,
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcDetRecaudaciones agen = (LcDetRecaudaciones)session.get(LcDetRecaudaciones.class, idDetRecaudacion);
//    agen.setIdArticulo(idArticulo);
//    agen.setNumeroCuotas(numCuota);
//    agen.setValorCuota(Valor_cuota);
    agen.setValorRecaudado(Valor_Pagar);
//    agen.setInteres(Interes);
//    agen.setMora(Mora);
    agen.setSaldo(Saldo);
    agen.setFechaActualizado(fecha);
    session.update(agen);
    tx.commit();
    session.close();
    }
    
    public void updateTipoPago(int idPago,int tipopago,BigDecimal ValorEfectivo,BigDecimal ValorTarjeta,int inst_tarjeta,String num_tarjeta,BigDecimal ValorCheque,int inst_cheque,String num_cheque,Date fecha){ //int idArticulo,int numCuota,BigDecimal Valor_cuota,BigDecimal Interes,BigDecimal Mora,
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcFormapagoRecaudacion agen = (LcFormapagoRecaudacion)session.get(LcFormapagoRecaudacion.class, idPago);
    if(tipopago ==1){
        agen.setTotalRecaudado(ValorEfectivo);
        agen.setFechaActualizado(fecha);
    }
    if(tipopago == 2){
        agen.setTotalRecaudado(ValorTarjeta);
        agen.setNumTarjetaCred(num_tarjeta);
        agen.setIdEntidadFinanciera(inst_tarjeta);
        agen.setFechaActualizado(fecha);
    }
    if(tipopago == 3){
        agen.setTotalRecaudado(ValorCheque);
        agen.setNumCheque(num_cheque);
        agen.setIdEntidadFinanciera(inst_cheque);
        agen.setFechaActualizado(fecha);
    }
    session.update(agen);
    tx.commit();
    session.close();
    }
    public void addFormaPago(LcFormapagoRecaudacion formaPago){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(formaPago);
    tx.commit();
    session.close();
    } 
    
    public Long getNext3() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_formapago_recaudacion_id_formpago_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key;
    }
    public void updateTipoPagoEstado(int idPago){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcFormapagoRecaudacion agen = (LcFormapagoRecaudacion)session.get(LcFormapagoRecaudacion.class, idPago);
    agen.setEstado("I");
    session.update(agen);
    tx.commit();
    session.close();
    }
    
    public List<LcDetRecaudaciones> getDEtalleide( int iddetalle){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcDetRecaudaciones  E WHERE  E.idDetRecaudacion= :idDetRecaudacion and E.estado= :estado");
        q.setParameter("idDetRecaudacion",iddetalle);
        
        q.setParameter("estado","A");
        List<LcDetRecaudaciones> lista=q.list();
         for(LcDetRecaudaciones mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDetRecaudacion()+", "+mrol.getValorRecaudado());
             System.out.println("ok: "+mrol.getIdDetRecaudacion()+", "+mrol.getIdArticulo());
        }
        tx.commit();
        session.close();
         return lista;
    }
    public void updateDEtallEstado(int idetalle){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcDetRecaudaciones agen = (LcDetRecaudaciones)session.get(LcDetRecaudaciones.class, idetalle);
    agen.setEstado("I");
    session.update(agen);
    tx.commit();
    session.close();
    }
    public String getConsultaRecaudadores(int emppresa, int agencia, int empleado){
         String valor = "";
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_consulta_recadudadores("+emppresa+","+agencia+","+empleado+");");
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
    public String getHistorialPagos(int id_empresa, int id_deudor, int id_cliente){
        
    String datos="";
    
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_historial_pagos("+id_empresa+","+id_deudor+","+id_cliente+");");
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                datos=rs.getString(1);
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return datos; 
    }
    public String getSumHistorialPagos(int id_empresa, int id_deudor, int id_cliente){
        
    String datos="";
    
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_sum_historial_pagos("+id_empresa+","+id_deudor+","+id_cliente+");");
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                datos=rs.getString(1);
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return datos; 
    }
    
}
