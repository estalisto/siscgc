/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcTelefonos;
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
public class TelefonoServicios {
    
    
    public List<LcTelefonos> getLcTelefono(String ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcTelefonos  E WHERE E.identificacionDeudor= :identificacionDeudor");
        q.setParameter("identificacionDeudor",ide);
        //q.setParameter("estado","P");
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
    
    public void addTelefonos(LcTelefonos telefono) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(telefono);
        tx.commit();
        session.close();
    }
    
        public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_telefonos_id_telefono_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
        
         public String getTelefonoReferencia2(int idReferncia,int idTipoPers )throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
            try
            {
            String SQL="select telefono from lc_telefonos where id_persona ="+idReferncia+" and tipo_persona="+idTipoPers;    
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
                {
                valor= rs.getString("telefono");
                //System.out.println("ok");
                }
                rs.close();
                pst.close();
                conexion.cierraConexion();
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
            
    }
        
        
}
