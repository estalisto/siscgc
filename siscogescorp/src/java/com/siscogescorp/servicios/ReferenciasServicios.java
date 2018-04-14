/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcReferencias;
import com.siscogescorp.utils.Conexion;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author CIMA2015
 */
public class ReferenciasServicios {
    
    
   public void addReferencias(LcReferencias referencias){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(referencias);
    tx.commit();
    session.close();
    }   
    
 public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('lc_referencias_id_referencia_seq')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
    return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
 
 public List<LcReferencias> getDatosLcReferencias(int idDeudor){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
       // ArrayList<LcReferencias> arreglo = new ArrayList<LcReferencias>();
        Query q = session.createQuery("from LcReferencias E WHERE E.idDeudor = :idDeudor ");
        q.setParameter("idDeudor",idDeudor);
         List<LcReferencias> lista=q.list();
         for(LcReferencias mrol:lista )
        {
             System.out.println("Referencia: "+mrol.getNombreReferencia()+", "+mrol.getLcTipoReferencia().getDescripcion()+mrol.getIdReferencia());
             
        }
    tx.commit();
    session.close();
         return lista;
    } 
    public String getReferencias(int idDeudor)throws SQLException
    {   
        Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
           try
            {
            String SQL="";
            
            SQL="select (select r.nombre_tipo from lc_tipo_referencia r where r.id_tipo_ref=f.tipo_referencia)as tipo_referencia\n" +
                ",(f.nombre_referencia)as nombre_ref,(t.telefono )as telefono\n" +
                "from lc_telefonos t,lc_referencias f \n" +
                "where f.id_deudor="+idDeudor+" and t.id_persona=f.id_referencia \n" +
                "and  t.estado='A'"; 
            
            String contenido="",footer="";
            String tipo_referencia,nombre_ref,telefono,fecha_compra,suma_valor;
           
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
            {
                tipo_referencia = rs.getString("tipo_referencia");
                nombre_ref = rs.getString("nombre_ref");
                telefono =rs.getString("telefono");
                
                contenido=contenido+ "<tr>\n" +
                                "<td>"+tipo_referencia+"</td>\n" +
                                "<td>"+nombre_ref+"</td>\n" +
                                "<td>"+telefono+"</td>\n" +
                                "<td></td>\n" +
                                "</tr>";
                                     
            }
              
                rs.close();
                pst.close();
                conexion.cierraConexion();
                 valor=contenido;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
    }     
}
