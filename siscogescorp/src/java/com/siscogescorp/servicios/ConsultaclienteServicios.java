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

/**
 *
 * @author ViewSoft
 */
public class ConsultaclienteServicios {
    public String NuevaBusquedaDeudor(String nombre,int cartera)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            
           
            String SQL="select (t.identificacion)as identificacion,(d.nombres_completo)as nombre_completo,(r.nombre_cartera)as cartera,(c.razon_social)as cliente,\n" +
                "(t.id_cliente)as id_cliente,(d.tipo_identificacion)as id_identificacion,(d.id_datos_deudor)as id_deudor from lc_transacciones t, lc_datos_deudores d,lc_clientes c,lc_cartera r\n" +
                "where t.id_deudor = d.id_datos_deudor and c.id_cliente=t.id_cliente\n" +
                "and t.id_cliente="+cartera+" and r.id_cartera=t.id_cartera and d.nombres_completo like '%"+nombre+"%'";   
            
             
           String nombre_deudor,identificacion,carteras,cliente,contenidoTabla = "",valor="";
           int id_cliente,id_identificacion,id_deudor,cont=0;
            try
            {
             pst = conexion.getconexion().prepareStatement(SQL);
             rs = pst.executeQuery();
            while( rs.next() )    //Mientras haya una sig. tupla
                {
                    identificacion=rs.getString("identificacion");
                    nombre_deudor=rs.getString("nombre_completo");
                    carteras=rs.getString("cartera");
                    cliente=rs.getString("cliente");
                    id_cliente=rs.getInt("id_cliente");
                    id_deudor=rs.getInt("id_deudor");
                    id_identificacion=rs.getInt("id_identificacion");
                    contenidoTabla=contenidoTabla+"<tr><td><input style=\"display:none\" id=\"IDentify_"+cont+"\" value=\'"+identificacion+"\'>"+identificacion+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"NomDeudor_"+cont+"\" value=\'"+nombre_deudor+"\'>"+nombre_deudor+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"TipCartera_"+cont+"\" value=\'"+carteras+"\'>"+carteras+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"ClientCartera_"+cont+"\" value=\'"+cliente+"\'>"+cliente+"</td>\n" +
                                                    "<td class=\"hidden\"><input id=\"idCliente_"+cont+"\" value=\'"+id_cliente+"\'></td>\n" +
                                                    "<td class=\"hidden\"><input id=\"TipIde_"+cont+"\" value=\'"+id_identificacion+"\'></td>\n" +
                                                    "<td class=\"hidden\"><input id=\"IdeDeudor_"+cont+"\" value=\'"+id_deudor+"\'></td>\n" +
                                                    "<td><a id=\"consultar\" type=\"button\" onclick=\"GestionCliente2("+id_cliente+","+id_deudor+")\" class=\"btn btn-primary fa fa-search\">Gestion</a>\n"+
                                                    "</td></tr>";
                    cont++;
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
        public String NuevaBusquedaDeudorIDE(String identifica,int cartera)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            
           
            String SQL="select (t.identificacion)as identificacion,(d.nombres_completo)as nombre_completo,(r.nombre_cartera)as cartera,(c.razon_social)as cliente,\n" +
                "(t.id_cliente)as id_cliente,(d.tipo_identificacion)as id_identificacion,(d.id_datos_deudor)as id_deudor from lc_transacciones t, lc_datos_deudores d,lc_clientes c,lc_cartera r\n" +
                "where t.id_deudor = d.id_datos_deudor and c.id_cliente=t.id_cliente\n" +
                "and t.id_cliente="+cartera+" and r.id_cartera=t.id_cartera and t.identificacion='"+identifica+"'";   
            
             
           String nombre_deudor,identificacion,carteras,cliente,contenidoTabla = "",valor="";
           int id_cliente,id_identificacion,id_deudor,cont=0;
            try
            {
             pst = conexion.getconexion().prepareStatement(SQL);
             rs = pst.executeQuery();
            while( rs.next() )    //Mientras haya una sig. tupla
                {
                    identificacion=rs.getString("identificacion");
                    nombre_deudor=rs.getString("nombre_completo");
                    carteras=rs.getString("cartera");
                    cliente=rs.getString("cliente");
                    id_cliente=rs.getInt("id_cliente");
                    id_deudor=rs.getInt("id_deudor");
                    id_identificacion=rs.getInt("id_identificacion");
                    contenidoTabla=contenidoTabla+"<tr><td><input style=\"display:none\" id=\"IDentify_"+cont+"\" value=\'"+identificacion+"\'>"+identificacion+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"NomDeudor_"+cont+"\" value=\'"+nombre_deudor+"\'>"+nombre_deudor+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"TipCartera_"+cont+"\" value=\'"+carteras+"\'>"+carteras+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"ClientCartera_"+cont+"\" value=\'"+cliente+"\'>"+cliente+"</td>\n" +
                                                    "<td class=\"hidden\"><input id=\"idCliente_"+cont+"\" value=\'"+id_cliente+"\'></td>\n" +
                                                    "<td class=\"hidden\"><input id=\"TipIde_"+cont+"\" value=\'"+id_identificacion+"\'></td>\n" +
                                                    "<td class=\"hidden\"><input id=\"IdeDeudor_"+cont+"\" value=\'"+id_deudor+"\'></td>\n" +
                                                    "<td><a id=\"consultar\" type=\"button\" onclick=\"GestionCliente2("+id_cliente+","+id_deudor+")\" class=\"btn btn-primary fa fa-search\">Gestion</a>\n"+
                                                    "</td></tr>";
                    cont++;
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
        
}
