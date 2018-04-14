/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcCompromisosPago;
import com.siscogescorp.utils.Conexion;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/**
 *
 * @author CIMA2015
 */
public class CompromisosPagosServicios {
    
    
public List<LcCompromisosPago> getLcCompromisosPago(Date fechaCompromiso, int IDEmpleado) {
     
         
     
     try {
         SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
         String result;
         result = dateFormatter.format(fechaCompromiso);
         Date beginDate = dateFormatter.parse(result);
         
         
         SessionFactory sesion = HibernateUtil.getSessionFactory();
         Session session;
         session = sesion.openSession();
         Transaction tx= session.beginTransaction();
         Query q = session.createQuery("FROM LcCompromisosPago E WHERE E.lcDatosDeudores.idEmpleado = :IDEmpleado  AND E.fechaCompromiso >= :beginDate");
         q.setParameter("IDEmpleado",IDEmpleado);
         q.setParameter("beginDate",beginDate);
         List<LcCompromisosPago> lista=q.list();
         for(LcCompromisosPago compp:lista )
         {
             
             System.out.println("compp: "+compp.getLcDatosDeudores().getNombres()+", "+compp.getIdCliente()+" "+compp.getLcDatosDeudores().getApellidos()+""+compp.getIdCliente()+compp.getLcDatosDeudores().getIdDatosDeudor());
             System.out.println("compp: ");
         }
          tx.commit();
        session.close();
         return lista;
     } catch (ParseException ex) {
         Logger.getLogger(CompromisosPagosServicios.class.getName()).log(Level.SEVERE, null, ex);
     } 
         return null;
    }
 
 public List<LcCompromisosPago> getLcCompromisosPagoFechas(String fechaInicio,String fechaFin, int IDEmpleado){
     
         
         try {
             SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
             SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd");      
             Date beginDate = dateFormatter.parse(fechaInicio);
             Date endDate = dateFormatter.parse(fechaFin);             
             System.out.print("fechaInicio: "+fechaInicio);
             System.out.print("fechaFin: "+fechaFin);             
             SessionFactory sesion = HibernateUtil.getSessionFactory();
             Session session;
             session = sesion.openSession();
             Transaction tx= session.beginTransaction();
             Query q = session.createQuery("From LcCompromisosPago E WHERE E.lcDatosDeudores.idEmpleado= :IDEmpleado AND E.fechaCompromiso >= :beginDate AND E.fechaCompromiso <= :endDate");
             q.setParameter("IDEmpleado",IDEmpleado);
             q.setParameter("beginDate",beginDate);             
             q.setParameter("endDate",endDate);
             List<LcCompromisosPago> lista=q.list();
             for(LcCompromisosPago compp:lista )
             {
                 
                 System.out.println("compp: "+compp.getLcDatosDeudores().getNombres()+", "+compp.getIdCliente()+" "+compp.getLcDatosDeudores().getApellidos()+
                         compp.getLcDatosDeudores().getIdDatosDeudor()+compp.getFechaCompromiso());
                                  System.out.println("compp: ");
             }
              tx.commit();
        session.close();
             return lista; 
         } catch (ParseException ex) {
             Logger.getLogger(CompromisosPagosServicios.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    } 
    
  public String getCompromisoxdeudores(Date fechaCompromiso, int IDEmpleado)throws SQLException
    {   
        Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
           try
            {
            String SQL="";
             SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd ");
            String result;
            result = dateFormatter.format(fechaCompromiso);
            Date beginDate = dateFormatter.parse(result);
            SQL = "select (c.id_compromiso)as id_compromiso,to_char(c.fecha_registro, 'YYYY-MM-DD HH24:MI:SS'::text) as fecha_registro,(d.nombres_completo)as nombre_deudor,to_char(c.fecha_compromiso, 'YYYY-MM-DD'::text)  as fecha_compromiso,\n"
                    + "(c.valor_compromiso)as valor_compromiso,(c.id_deudor)as id_deudor,(c.id_cliente)as id_cliente\n"
                    + "from lc_transacciones t, lc_compromisos_pago c ,lc_datos_deudores d\n"
                    + "where t.id_deudor=c.id_deudor and t.id_empleado = " + IDEmpleado + "\n"
                    + "and c.id_deudor=d.id_datos_deudor\n"
                    + "and c.id_cliente=t.id_cliente\n" +
"and c.estado='A' and c.fecha_compromiso >='" + result + "' and t.estado!='E' order by c.fecha_compromiso";
            
            String contenido = "<tbody>",footer="";
            String id_compromiso, fecha_registro, nombre_deudor, fecha_compromiso, valor_compromiso;
            String id_deudor, id_cliente;
            BigDecimal valores = BigDecimal.ZERO;
            BigDecimal suma = BigDecimal.ZERO;
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
            {
                id_compromiso = rs.getString("id_compromiso");
                fecha_registro = rs.getString("fecha_registro");
                nombre_deudor =rs.getString("nombre_deudor");
                fecha_compromiso = rs.getString("fecha_compromiso");
                valor_compromiso = rs.getString("valor_compromiso");
                id_deudor = rs.getString("id_deudor");
                id_cliente = rs.getString("id_cliente");
                valores = new BigDecimal(valor_compromiso);
                suma= suma.add(valores);
                contenido = contenido + "<tr><td class=\"text-center\">" + id_compromiso + "</td>\n"
                        + "                                  <td class=\"text-center\">" + fecha_registro + "</td>\n"
                        + "                                  <td><a onclick=\"GestionClienteCompromisos(" + id_cliente + "," + id_deudor + ")\">" + nombre_deudor + "</a> </td>\n"
                        + "                                  <td class=\"text-center\">" + fecha_compromiso + "</td>\n"
                        + "                                  <td class=\"text-center\">" + valor_compromiso + "</td>\n"
                        + "                                  <td>Llamar a la Hora especificada </td></tr> ";
            }
            footer="</tbody><tfoot>\n" +
                               
                                    "<th class=\"col-sm-2  text-center\" style=\" font-size:18px; font-type:Arial\"> Total Compromiso: </th>  "+
                                    "<th class=\"col-sm-2\"              style=\" font-size:18px; color:#3c8dbc; font-type:Arial\"><strong>$ "+suma+"</strong></th>\n" +
                                    "<th></th><th></th><th></th><th></th> "+
                                  
                                    "</tfoot>";
            
 
            
                rs.close();
                pst.close();
                conexion.cierraConexion();
            valor = contenido+footer;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
    }   
  
   public String getCompromisoxRangos(String fechaInicio,String fechaFin, int IDEmpleado)throws SQLException
    {   
        Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
           try
            {
            String SQL="";
             
            SQL = "select (c.id_compromiso)as id_compromiso,to_char(c.fecha_registro, 'YYYY-MM-DD HH24:MI:SS'::text) as fecha_registro,(d.nombres_completo)as nombre_deudor,to_char(c.fecha_compromiso, 'YYYY-MM-DD'::text)   as fecha_compromiso,\n"
                    + "(c.valor_compromiso)as valor_compromiso,(c.id_deudor)as id_deudor,(c.id_cliente)as id_cliente\n"
                    + "from lc_transacciones t, lc_compromisos_pago c ,lc_datos_deudores d\n"
                    + "where t.id_deudor=c.id_deudor and t.id_empleado = " + IDEmpleado + "\n"
                    + "and c.id_deudor=d.id_datos_deudor\n"
                    + "and c.fecha_compromiso >='" + fechaInicio + "'and c.fecha_compromiso <='" + fechaFin + "'   order by c.fecha_compromiso";
            
            String contenido = "<tbody>",footer="";
            String id_compromiso, fecha_registro, nombre_deudor, fecha_compromiso, valor_compromiso;
            String id_deudor, id_cliente;
            BigDecimal valores = BigDecimal.ZERO;
            BigDecimal suma = BigDecimal.ZERO;            
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
            {
                id_compromiso = rs.getString("id_compromiso");
                fecha_registro = rs.getString("fecha_registro");
                nombre_deudor =rs.getString("nombre_deudor");
                fecha_compromiso = rs.getString("fecha_compromiso");
                valor_compromiso = rs.getString("valor_compromiso");
                id_deudor = rs.getString("id_deudor");
                id_cliente = rs.getString("id_cliente");
                valores = new BigDecimal(valor_compromiso);
                suma= suma.add(valores);
                contenido = contenido + "<tr><td class=\"text-center\">" + id_compromiso + "</td>\n"
                        + "                                  <td class=\"text-center\">" + fecha_registro + "</td>\n"
                        + "                                  <td><a onclick=\"cobranzas2(" + id_cliente + "," + id_deudor + ")\">" + nombre_deudor + "</a> </td>\n"
                        + "                                  <td class=\"text-center\">" + fecha_compromiso + "</td>\n"
                        + "                                  <td  class=\"text-center\">" + valor_compromiso + "</td>\n"
                        + "                                  <td>Llamar a la Hora especificada </td></tr> ";
            }
            footer="</tbody><tfoot>\n" +
                               
                                    "<th class=\"col-sm-2  text-center\" style=\" font-size:18px; font-type:Arial\"> Total Compromiso: </th>  "+
                                    "<th class=\"col-sm-2\"              style=\" font-size:18px; color:#3c8dbc; font-type:Arial\"><strong>$ "+suma+"</strong></th>\n" +
                                    "<th></th><th></th><th></th><th></th> "+
                                  
                                    "</tfoot>";
                rs.close();
                pst.close();
                conexion.cierraConexion();
            valor = contenido+footer;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
    }  
}
