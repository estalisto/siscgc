/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcRoles;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author CIMA2015
 */
public class RolesOperaciones {
    
    
    public ArrayList<LcRoles> getLCRoles(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcRoles> arreglo = new ArrayList<LcRoles>();
        Query q = session.createQuery("from LcRoles E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcRoles> lista=q.list();
        Iterator<LcRoles> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcRoles rol= (LcRoles) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
    
    public List<LcRoles> getLCRolesoper(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcRoles> arreglo = new ArrayList<LcRoles>();
        Query q = session.createQuery("from LcRoles E WHERE E.estado = :estado");
        q.setParameter("estado","A");
        List<LcRoles> lista=q.list();
         for(LcRoles mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdRol()+", "+mrol.getLcEmpresa().getRazonSocial());
        }
        
      
         return lista;
    }    
    
    public List<LcRoles> getLCRolesxEmpresa(int empresa){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcRoles> arreglo = new ArrayList<LcRoles>();
        Query q = session.createQuery("from LcRoles E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.estado = :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("estado","A");
        List<LcRoles> lista=q.list();
         for(LcRoles mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdRol()+", "+mrol.getLcEmpresa().getRazonSocial()+mrol.getDescripcion());
        }
        tx.commit();
        session.close();
      
         return lista;
    } 
        public List<LcRoles> getLCRolesxEmpresaUsuario(int empresa,int idRol){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcRoles> arreglo = new ArrayList<LcRoles>();
        Query q = session.createQuery("from LcRoles E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.nivel= :nivel and  E.estado = :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("nivel", idRol);
        q.setParameter("estado","A");
        List<LcRoles> lista=q.list();
         for(LcRoles mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdRol()+", "+mrol.getLcEmpresa().getRazonSocial()+mrol.getDescripcion());
        }
        tx.commit();
        session.close();
      
         return lista;
    } 
    public void addRol(LcRoles rol){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(rol);
    tx.commit();
    session.close();
    } 
    
    
    public void deleteRol(int id){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcRoles agen = (LcRoles)session.get(LcRoles.class, id);
    agen.setEstado("I");
    session.update(agen);
    tx.commit();
    session.close();
    }
    
    public List<LcRoles> getDatosLCRolesID(int IDRol){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcRoles> arreglo = new ArrayList<LcRoles>();
        Query q = session.createQuery("from LcRoles E WHERE E.idRol = :idRol ");
        q.setParameter("idRol",IDRol);
        List<LcRoles> lista=q.list();
         for(LcRoles mrol:lista )
        {
             //System.out.println("ok: "+mrol.getIdEmpleado()+", "+mrol.getLcCargos().getCargo());
             System.out.println("ok: "+mrol.getIdRol()+", "+mrol.getLcEmpresa().getRazonSocial());
        }
         tx.commit();
        session.close();
         return lista;
    }
    public int  SecuenciaModulo(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        int q = (int)session.createQuery("SELECT MAX(idRol) FROM LcRoles").uniqueResult();
        tx.commit();
        session.close();
        return q;
    }

    public void updateRoles(int idRol, int empresa, String rol){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcRoles agen = (LcRoles)session.get(LcRoles.class, idRol);
    agen.setLcEmpresa(new LcEmpresa(empresa));
    agen.setDescripcion(rol);
    agen.setObservacion(rol);
    session.update(agen);
    tx.commit();
    session.close();
    }

    public ArrayList<LcRoles> getRolesEmpresa(int IDEmpresa){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcRoles> arreglo = new ArrayList<LcRoles>();
        Query q = session.createQuery("from LcRoles E WHERE E.lcEmpresa.idEmpresa = :idEmpresa AND E.estado = :estado");
        q.setParameter("idEmpresa",IDEmpresa);
        q.setParameter("estado","A");
        List<LcRoles> lista=q.list();
        Iterator<LcRoles> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcRoles rol= (LcRoles) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
    
  
    public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('lc_roles_id_rol_seq')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
    return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
}
