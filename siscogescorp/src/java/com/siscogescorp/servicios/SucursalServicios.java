/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcSucursal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ViewSoft
 */
public class SucursalServicios {
  public ArrayList<LcSucursal> getLcSucursal(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcSucursal> arreglo = new ArrayList<LcSucursal>();
        Query q = session.createQuery("from LcSucursal E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcSucursal> lista=q.list();
        Iterator<LcSucursal> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcSucursal rol= (LcSucursal) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
  
  
        public ArrayList<LcSucursal> getLCSucursales(){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcSucursal> arreglo = new ArrayList<LcSucursal>();
        Query q = session.createQuery("from LcSucursal E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcSucursal> lista=q.list();
        Iterator<LcSucursal> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcSucursal rol= (LcSucursal) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
}
