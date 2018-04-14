/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcDireccion;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author CIMA2015
 */
public class DireccionServicios {
 
    
    public List<LcDireccion> getLcDireccion(String ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
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
    
     public void addLcDireccion(LcDireccion Direccion) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(Direccion);
        tx.commit();
        session.close();
    }
    
        public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_direccion_id_direccion_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
    
    
}
