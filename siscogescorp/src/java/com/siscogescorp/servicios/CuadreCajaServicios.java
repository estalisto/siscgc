/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcCuadreCaja;
import com.siscogescorp.utils.Conexion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ViewSoft
 */
public class CuadreCajaServicios {
    
     public String getRecaudaciones()throws SQLException
    {   
        Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
           try
            {
            String SQL="";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha_actual= new Date(); 
            String fecha_registrohoy = formatter.format(fecha_actual);
            SQL = "select (f.total_recaudado)as total_recaudado,(f.tipo_forma_pago)as tipo_forma_pago,(t.tipo_pago)as tipo_pago,(f.fecha_cobro)as fecha_cobro,(f.id_recaudacion)as id_recaudacion,\n" +
                "(r.id_deudor)as id_deudor,(d.nombres_completo)as nombres_completo\n" +
                "from lc_formapago_recaudacion f,lc_recaudaciones r,lc_tipo_forma_pago t,lc_datos_deudores d\n" +
                "where f.id_recaudacion=r.id_recaudacion and f.tipo_forma_pago = t.id_tipo_fp and r.id_deudor=d.id_datos_deudor\n" +
                "and f.fecha_registro>='"+fecha_registrohoy+"'and f.estado='A'";
            
            String contenido = "",contenido2 = "",contenido3 = "",footer="";
            String total_recaudado, tipo_forma_pago, tipo_pago, fecha_cobro, id_recaudacion;
            String id_deudor, nombres_completo,contador,contador2,contador3;
            BigDecimal valores = BigDecimal.ZERO;
            BigDecimal suma = BigDecimal.ZERO; 
            BigDecimal sumaefectivo = BigDecimal.ZERO;
            BigDecimal valoresefectivo = BigDecimal.ZERO;
            BigDecimal sumacheque = BigDecimal.ZERO;
            BigDecimal valorcheque = BigDecimal.ZERO;
            BigDecimal sumatarjeta = BigDecimal.ZERO;
            BigDecimal valorestarjeta = BigDecimal.ZERO;
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            int cont = 0,cont1=0,cont2=0;
            while( rs.next() )    //Mientras haya una sig. tupla
            {      
                total_recaudado = rs.getString("total_recaudado");
                tipo_forma_pago = rs.getString("tipo_forma_pago");
                tipo_pago =rs.getString("tipo_pago");
                fecha_cobro = rs.getString("fecha_cobro");
                id_recaudacion = rs.getString("id_recaudacion");
                id_deudor = rs.getString("id_deudor");
                nombres_completo = rs.getString("nombres_completo");
                valores = new BigDecimal(total_recaudado);
                suma= suma.add(valores);
                
                if("1".equals(tipo_forma_pago)){
                    contenido = contenido + "<tr id=\"fila_"+cont+"\">"
                        + "<td class=\"text-center\"><input id=\"id_recaudacion_caja_"+cont+"\" style=\"display:none\" value=\'"+id_recaudacion+"\'>" + id_recaudacion + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"fecha_cobro_caja_"+cont+"\" style=\"display:none\" value=\'"+fecha_cobro+"\'>" + fecha_cobro + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"nombres_completo_caja_"+cont+"\" style=\"display:none\" value=\'"+nombres_completo+"\'>" + nombres_completo + "</a> </td>\n"
                        + "<td class=\"text-center\"><input id=\"total_recaudado_caja_"+cont+"\" style=\"display:none\" value=\'"+total_recaudado+"\'>" + total_recaudado + "</td>\n"
                        + "<td class=\"hidden\"><input id=\"id_deudor_caja_"+cont+"\" style=\"display:none\" value=\'"+id_deudor+"\'></td>\n"
                        + "<td><a ><span onclick=\"EliminaFila("+cont+")\" class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td></tr>";
                        valoresefectivo = new BigDecimal(total_recaudado);
                        sumaefectivo=sumaefectivo.add(valoresefectivo);
                    cont++;
                }
                    
                if("2".equals(tipo_forma_pago)){
                    contenido2 = contenido2 + "<tr id=\"fila_"+cont1+"\">"
                        + "<td class=\"text-center\"><input id=\"id_recaudacion_cheque_"+cont1+"\" style=\"display:none\" value=\'"+id_recaudacion+"\'>" + id_recaudacion + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"fecha_cobro_cheque_"+cont1+"\" style=\"display:none\" value=\'"+fecha_cobro+"\'>" + fecha_cobro + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"nombres_completo_cheque_"+cont1+"\" style=\"display:none\" value=\'"+nombres_completo+"\'>" + nombres_completo + "</a> </td>\n"
                        + "<td class=\"text-center\"><input id=\"total_recaudado_cheque_"+cont1+"\" style=\"display:none\" value=\'"+total_recaudado+"\'>" + total_recaudado + "</td>\n"
                        + "<td class=\"hidden\"><input id=\"id_deudor_cheque_"+cont1+"\" style=\"display:none\" value=\'"+id_deudor+"\'></td>\n"
                        + "<td><a ><span onclick=\"EliminaFila("+cont1+")\" class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td></tr>";
                        valorcheque = new BigDecimal(total_recaudado);
                        sumacheque=sumacheque.add(valorcheque);
                    cont1++;
                }
                if("3".equals(tipo_forma_pago)){
                    contenido3 = contenido3 + "<tr id=\"fila_"+cont2+"\">"
                        + "<td class=\"text-center\"><input id=\"id_recaudacion_tarjeta_"+cont2+"\" style=\"display:none\" value=\'"+id_recaudacion+"\'>" + id_recaudacion + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"fecha_cobro_tarjeta_"+cont2+"\" style=\"display:none\" value=\'"+fecha_cobro+"\'>" + fecha_cobro + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"nombres_completo_tarjeta_"+cont2+"\" style=\"display:none\" value=\'"+nombres_completo+"\'>" + nombres_completo + "</a> </td>\n"
                        + "<td class=\"text-center\"><input id=\"total_recaudado_tarjeta_"+cont2+"\" style=\"display:none\" value=\'"+total_recaudado+"\'>" + total_recaudado + "</td>\n"
                        + "<td class=\"hidden\"><input id=\"id_deudor_tarjeta_"+cont2+"\" style=\"display:none\" value=\'"+id_deudor+"\'></td>\n"    
                        + "<td><a ><span onclick=\"EliminaFila("+cont2+")\" class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td></tr>";
                        valorestarjeta = new BigDecimal(total_recaudado);
                        sumatarjeta=sumatarjeta.add(valorestarjeta);
                    cont2++;
                }
            }
            
            footer="<div class=\"col-lg-2\"><strong><input type=\"text\" value=\'"+suma+"\'></strong> </div></th> ";
            contador="<div class=\"row\">\n" +
"                                    <div class=\"col-lg-8\"></div>\n" +
"                                    <div class=\"col-lg-2\">Total Efectivo:</div>\n" +
"                                    <div class=\"col-lg-2\"><strong><input id=\"suma_efectivo\" type=\"text\" value=\'$"+sumaefectivo+"\' readonly></strong> </div>\n" +
"                                </div></strong> </div></th><input id=\"cont_caja\" style=\"display:none\" value=\'"+cont+"\'>";
            contador2="<div class=\"row\">\n" +
"                                    <div class=\"col-lg-8\"></div>\n" +
"                                    <div class=\"col-lg-2\">Total Cheque:</div>\n" +
"                                    <div class=\"col-lg-2\"><strong><input id=\"suma_cheque\" type=\"text\" value=\'$"+sumacheque+"\' readonly></strong> </div>\n" +
"                                </div></strong> </div></th><input id=\"cont_cheque\" style=\"display:none\" value=\'"+cont1+"\'>";
            contador3="<div class=\"row\">\n" +
"                                    <div class=\"col-lg-8\"></div>\n" +
"                                    <div class=\"col-lg-2\">Total Tarjeta:</div>\n" +
"                                    <div class=\"col-lg-2\"><strong><input id=\"suma_tarjeta\" type=\"text\" value=\'$"+sumatarjeta+"\' readonly></strong> </div>\n" +
"                                </div></strong> </div></th><input id=\"cont_tarjeta\" style=\"display:none\" value=\'"+cont2+"\'>";
            
                rs.close();
                pst.close();
                conexion.cierraConexion();
            valor = contenido+contador+"&"+contenido2+contador2+"&"+contenido3+contador3+"&"+suma;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
    }  
     public String getRecaudacionesxFecha(String fecha_desde, String fecha_hasta)throws SQLException
    {   
        Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
           try
            {
            String SQL="";
            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            //String fecha_d= formatter.format(fecha_desde);
            //String fecha_h= formatter.format(fecha_hasta);
            SQL = "select (c.id_forma_pago)as id_forma_pago,(c.fecha_pago)as fecha_pago,(c.numero_comprobante)as numero_comprobante,(c.id_deudor)as id_deudor,\n" +
                "(d.nombres_completo)as nombres_completo,(c.total)as total\n" +
                "from lc_cuadre_caja c ,lc_datos_deudores d\n" +
                "where fecha_cuadre >='"+fecha_desde+"' and fecha_cuadre<='"+fecha_hasta+"' and c.id_deudor=d.id_datos_deudor and c.estado='A'";
            
            String contenido = "",contenido2 = "",contenido3 = "",footer="";
            String total_recaudado, tipo_forma_pago, tipo_pago, fecha_cobro, id_recaudacion;
            String id_deudor, nombres_completo,contador,contador2,contador3;
            BigDecimal valores = BigDecimal.ZERO;
            BigDecimal suma = BigDecimal.ZERO;    
            BigDecimal sumaefectivo = BigDecimal.ZERO;
            BigDecimal valoresefectivo = BigDecimal.ZERO;
            BigDecimal sumacheque = BigDecimal.ZERO;
            BigDecimal valorcheque = BigDecimal.ZERO;
            BigDecimal sumatarjeta = BigDecimal.ZERO;
            BigDecimal valorestarjeta = BigDecimal.ZERO;
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            int cont = 0,cont1=0,cont2=0;
            while( rs.next() )    //Mientras haya una sig. tupla
            {      
                total_recaudado = rs.getString("total");
                tipo_forma_pago = rs.getString("id_forma_pago");
                //tipo_pago =rs.getString("tipo_pago");
                fecha_cobro = rs.getString("fecha_pago");
                id_recaudacion = rs.getString("numero_comprobante");
                id_deudor = rs.getString("id_deudor");
                nombres_completo = rs.getString("nombres_completo");
                valores = new BigDecimal(total_recaudado);
                suma= suma.add(valores);
                if("1".equals(tipo_forma_pago)){
                   contenido = contenido + "<tr id=\"fila_"+cont+"\">"
                        + "<td class=\"text-center\"><input id=\"id_recaudacion_caja_"+cont+"\" style=\"display:none\" value=\'"+id_recaudacion+"\'>" + id_recaudacion + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"fecha_cobro_caja_"+cont+"\" style=\"display:none\" value=\'"+fecha_cobro+"\'>" + fecha_cobro + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"nombres_completo_caja_"+cont+"\" style=\"display:none\" value=\'"+nombres_completo+"\'>" + nombres_completo + "</a> </td>\n"
                        + "<td class=\"text-center\"><input id=\"total_recaudado_caja_"+cont+"\" style=\"display:none\" value=\'"+total_recaudado+"\'>" + total_recaudado + "</td>\n"
                        + "<td class=\"hidden\"><input id=\"id_deudor_caja_"+cont+"\" style=\"display:none\" value=\'"+id_deudor+"\'></td>\n"
                        + "<td><a ><span onclick=\"EliminaFila("+cont+")\" class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td></tr>";
                    valoresefectivo = new BigDecimal(total_recaudado);
                    sumaefectivo=sumaefectivo.add(valoresefectivo);
                    cont++;
                }
                if("2".equals(tipo_forma_pago)){
                    contenido2 = contenido2 + "<tr id=\"fila_"+cont1+"\">"
                        + "<td class=\"text-center\"><input id=\"id_recaudacion_cheque_"+cont1+"\" style=\"display:none\" value=\'"+id_recaudacion+"\'>" + id_recaudacion + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"fecha_cobro_cheque_"+cont1+"\" style=\"display:none\" value=\'"+fecha_cobro+"\'>" + fecha_cobro + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"nombres_completo_cheque_"+cont1+"\" style=\"display:none\" value=\'"+nombres_completo+"\'>" + nombres_completo + "</a> </td>\n"
                        + "<td class=\"text-center\"><input id=\"total_recaudado_cheque_"+cont1+"\" style=\"display:none\" value=\'"+total_recaudado+"\'>" + total_recaudado + "</td>\n"
                        + "<td class=\"hidden\"><input id=\"id_deudor_cheque_"+cont1+"\" style=\"display:none\" value=\'"+id_deudor+"\'></td>\n"
                        + "<td><a ><span onclick=\"EliminaFila("+cont1+")\" class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td></tr>";
                    valorcheque = new BigDecimal(total_recaudado);
                    sumacheque=sumacheque.add(valorcheque);
                    cont1++;
                }
                if("3".equals(tipo_forma_pago)){
                    
                    contenido3 = contenido3 + "<tr id=\"fila_"+cont2+"\">"
                        + "<td class=\"text-center\"><input id=\"id_recaudacion_tarjeta_"+cont2+"\" style=\"display:none\" value=\'"+id_recaudacion+"\'>" + id_recaudacion + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"fecha_cobro_tarjeta_"+cont2+"\" style=\"display:none\" value=\'"+fecha_cobro+"\'>" + fecha_cobro + "</td>\n"
                        + "<td class=\"text-center\"><input id=\"nombres_completo_tarjeta_"+cont2+"\" style=\"display:none\" value=\'"+nombres_completo+"\'>" + nombres_completo + "</a> </td>\n"
                        + "<td class=\"text-center\"><input id=\"total_recaudado_tarjeta_"+cont2+"\" style=\"display:none\" value=\'"+total_recaudado+"\'>" + total_recaudado + "</td>\n"
                        + "<td class=\"hidden\"><input id=\"id_deudor_tarjeta_"+cont2+"\" style=\"display:none\" value=\'"+id_deudor+"\'></td>\n"    
                        + "<td><a ><span onclick=\"EliminaFila("+cont2+")\" class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td></tr>";
                        valorestarjeta = new BigDecimal(total_recaudado);
                        sumatarjeta=sumatarjeta.add(valorestarjeta);
                    cont2++;
                }
            }
            
            footer="<div class=\"col-lg-2\"><strong><input type=\"text\" value=\'"+suma+"\'></strong> </div></th> ";
             contador="<div class=\"row\">\n" +
"                                    <div class=\"col-lg-8\"></div>\n" +
"                                    <div class=\"col-lg-2\">Total Efectivo:</div>\n" +
"                                    <div class=\"col-lg-2\"><strong><input id=\"suma_efectivo\" type=\"text\" value=\'$"+sumaefectivo+"\' disable=\"true\"></strong> </div>\n" +
"                                </div></strong> </div></th>";
            contador2="<div class=\"row\">\n" +
"                                    <div class=\"col-lg-8\"></div>\n" +
"                                    <div class=\"col-lg-2\">Total Cheque:</div>\n" +
"                                    <div class=\"col-lg-2\"><strong><input id=\"suma_cheque\" type=\"text\" value=\'$"+sumacheque+"\' disable=\"true\"></strong> </div>\n" +
"                                </div></strong> </div></th>";
            contador3="<div class=\"row\">\n" +
"                                    <div class=\"col-lg-8\"></div>\n" +
"                                    <div class=\"col-lg-2\">Total Tarjeta:</div>\n" +
"                                    <div class=\"col-lg-2\"><strong><input id=\"suma_tarjeta\" type=\"text\" value=\'$"+sumatarjeta+"\' disable=\"true\"></strong> </div>\n" +
"                                </div></strong> </div></th>";
                
                rs.close();
                pst.close();
                conexion.cierraConexion();
            valor = contenido+contador+"&"+contenido2+contador2+"&"+contenido3+contador3+"&"+suma;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
    } 
    public void addCuadre(LcCuadreCaja caja){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(caja);
    tx.commit();
    session.close();
    }
    public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('lc_cuadre_caja_id_cuadre_caja_seq')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
    return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
}
