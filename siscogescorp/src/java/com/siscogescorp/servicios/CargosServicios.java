/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcCargos;
import com.siscogescorp.modelo.LcEmpresa;
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
public class CargosServicios {
    public ArrayList<LcCargos> getLcCargos(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcCargos> arreglo = new ArrayList<LcCargos>();
        Query q = session.createQuery("from LcCargos E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcCargos> lista=q.list();
        Iterator<LcCargos> iter=lista.iterator();

        while(iter.hasNext())
        {
            LcCargos rol= (LcCargos) iter.next();
            arreglo.add(rol);
        }
         tx.commit();
        session.close();
        return arreglo;
    }
     public List<LcCargos> getLcCargosxEmpresa(int empresa){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcCargos E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.estado = :estado ");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("estado","A");
        List<LcCargos> lista=q.list();
         for(LcCargos mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdCargo()+", "+mrol.getLcEmpresa().getRazonSocial());
        }
         tx.commit();
        session.close();
      
         return lista;
    }
    public List<LcCargos> getLcCargoss(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcCargos E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
         List<LcCargos> lista=q.list();
         for(LcCargos mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdCargo()+", "+mrol.getLcEmpresa().getRazonSocial());
        }
         tx.commit();
        session.close();      
         return lista;
    }
    public List<LcCargos> getLcCargoxEmpresa(int empresa){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcCargos E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.estado = :estado ");
         q.setParameter("idEmpresa",empresa);
        q.setParameter("estado","A");
         List<LcCargos> lista=q.list();
         for(LcCargos mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdCargo()+", "+mrol.getLcEmpresa().getRazonSocial());
        }
        
       tx.commit();
        session.close();
         return lista;
    }
    
       public ArrayList<LcCargos> getDatoEncontrado(int empresa,String cargo){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        ArrayList<LcCargos> arreglo = new ArrayList<LcCargos>();
        Query q = session.createQuery("From LcCargos E WHERE E.lcEmpresa.idEmpresa = :idEmpresa and E.cargo= :cargo and E.estado = :estado" );
        q.setParameter("idEmpresa",empresa);
        q.setParameter("cargo",cargo);
        q.setParameter("estado", "A");
        List<LcCargos> lista=q.list();
        Iterator<LcCargos> iter=lista.iterator();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcCargos rol= (LcCargos) iter.next();
            arreglo.add(rol);
        }
         tx.commit();
        session.close();
        return arreglo;
    }    
    
    public void addCargos(LcCargos cargos){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(cargos);
    tx.commit();
    session.close();
    }
        
    public void updateCargo(int idCargo, int idEmpresa, String cargo, String observacion){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcCargos agen = (LcCargos)session.get(LcCargos.class, idCargo);
    agen.setLcEmpresa(new LcEmpresa(idEmpresa));
    agen.setCargo(cargo);
    agen.setObservacion(observacion);
    session.update(agen);
    tx.commit();
    session.close();
    }
   
    public void deleteCargos(int id){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcCargos agen = (LcCargos)session.get(LcCargos.class, id);
    agen.setEstado("I");
    session.update(agen);
    tx.commit();
    session.close();
    }
    
    //consulta datos por ID
    public List<LcCargos> getDatosLCargosID(int IDCargo){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcCargos E WHERE E.idCargo = :idCargo ");
        q.setParameter("idCargo",IDCargo);
        List<LcCargos> lista=q.list();
         for(LcCargos mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdCargo()+", "+mrol.getLcEmpresa().getRazonSocial());
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
        int q = (int)session.createQuery("SELECT MAX(idCargo) FROM LcCargos").uniqueResult();
         tx.commit();
        session.close();
        return q;
    }
         
    public Long getNextValCargos() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('lc_cargos_id_cargo_seq')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
        return key; 
    }
         
}
