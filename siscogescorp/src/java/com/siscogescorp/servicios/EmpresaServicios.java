/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcCiudad;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcPais;
import com.siscogescorp.modelo.LcProvincia;
import com.siscogescorp.modelo.LcRoles;
import com.siscogescorp.modelo.LcTiposIdentificacion;
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
public class EmpresaServicios {
    
     public ArrayList<LcEmpresa> getLcEmpresa(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcEmpresa> arreglo = new ArrayList<LcEmpresa>();
        Query q = session.createQuery("from LcEmpresa  E WHERE E.estado= :estado  ");
        q.setParameter("estado","A");
        List<LcEmpresa> lista=q.list();
        Iterator<LcEmpresa> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcEmpresa rol= (LcEmpresa) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
        public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('lc_empresa_id_empresa_seq')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
    return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
     public ArrayList<LcEmpresa> getLcEmpresalog(int empresa){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcEmpresa> arreglo = new ArrayList<LcEmpresa>();
        Query q = session.createQuery("from LcEmpresa  E WHERE E.idEmpresa= :idEmpresa and E.estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("estado","A");
        List<LcEmpresa> lista=q.list();
        Iterator<LcEmpresa> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcEmpresa rol= (LcEmpresa) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    } 
     
          public List<LcRoles> getLcEmpresaRol(int empresa,int rol){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
       
        Query q = session.createQuery("from LcRoles  E WHERE E.idRol= :idRol and E.lcEmpresa.idEmpresa= :idEmpresa and E.estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("idRol",rol);
        q.setParameter("estado","A");
        List<LcRoles> lista=q.list(); 
            for(LcRoles mrol:lista )
            {
                 System.out.println("ok: "+mrol.getIdRol()+", "+mrol.getLcEmpresa().getIdEmpresa());
                 System.out.println("ok: "+mrol.getIdRol()+", "+mrol.getLcEmpresa().getRazonSocial());
            }
            tx.commit();
            session.close();

         return lista;
            
             
        
        
            
    }
    public List<LcEmpresa> getLcEmpresaRolSuper(int empresa, int nivel){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        if (nivel==0){
        Query q = session.createQuery("from LcEmpresa  E WHERE E.estado= :estado");
        q.setParameter("estado","A");
        List<LcEmpresa> lista=q.list(); 
            for(LcEmpresa mrol:lista )
            {
                 System.out.println("ok: "+mrol.getRazonSocial()+mrol.getIdEmpresa());
               
            }
            tx.commit();
            session.close();

         return lista;
            
        }else{
            Query q = session.createQuery("from LcEmpresa  E WHERE E.idEmpresa = :idEmpresa and  E.estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("estado","A");
        List<LcEmpresa> lista=q.list();
            for(LcEmpresa mrol2:lista )
            {
                 System.out.println("ok: "+mrol2.getRazonSocial()+mrol2.getIdEmpresa());
            }
            tx.commit();
            session.close();

         return lista;
        }
        
        
        
            
    }
          
     public ArrayList<LcEmpresa> getLcEmpresa2(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion 
        ArrayList<LcEmpresa> arreglo = new ArrayList<LcEmpresa>();
        Query q = session.createQuery("from LcEmpresa ");
        List<LcEmpresa> lista=q.list();
        Iterator<LcEmpresa> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcEmpresa rol= (LcEmpresa) iter.next();
            arreglo.add(rol);
        }
        
        
        return arreglo;
    }
     public ArrayList<LcPais> getLcEmpPais(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcPais> arreglo = new ArrayList<LcPais>();
        Query q = session.createQuery("from LcPais  ");
        //q.setParameter("estado","A");
        List<LcPais> lista=q.list();
        Iterator<LcPais> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcPais rol= (LcPais) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
     
     public ArrayList<LcProvincia> getLcEmpProvincia(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcProvincia> arreglo = new ArrayList<LcProvincia>();
        Query q = session.createQuery("from LcProvincia  ");
        //q.setParameter("estado","A");
        List<LcProvincia> lista=q.list();
        Iterator<LcProvincia> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcProvincia rol= (LcProvincia) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    } 
     public ArrayList<LcProvincia> getLcEmpProv(int IdPais){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcProvincia> arreglo = new ArrayList<LcProvincia>();
        Query q = session.createQuery("from LcProvincia E Where E.lcPais.idPais = :idPais AND E.estado = :estado ");
        q.setParameter("idPais",IdPais);
        q.setParameter("estado","A");
        List<LcProvincia> lista=q.list();
        Iterator<LcProvincia> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcProvincia rol= (LcProvincia) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    } 
    public List<LcProvincia> getLcEmpProvID(int IdPais){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcProvincia> arreglo = new ArrayList<LcProvincia>();
        Query q = session.createQuery("from LcProvincia Where E.estado = :estado ");
        q.setParameter("estado","A");
        q.setParameter("idRol",IdPais);
        List<LcProvincia> lista=q.list();
         for(LcProvincia mrol:lista )
        {
             //System.out.println("ok: "+mrol.getIdEmpleado()+", "+mrol.getLcCargos().getCargo());
             System.out.println("ok: "+mrol.getIdProvincia()+", "+mrol.getLcPais().getPais());
        }
    tx.commit();
    session.close();
         return lista;
    } 
     public ArrayList<LcCiudad> getLcEmpCiudad(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcCiudad> arreglo = new ArrayList<LcCiudad>();
        Query q = session.createQuery("from LcCiudad  ");
        q.setParameter("estado","A");
        List<LcCiudad> lista=q.list();
        Iterator<LcCiudad> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcCiudad rol= (LcCiudad) iter.next();
            arreglo.add(rol);
        }
        
        
        return arreglo;
    } 
      public ArrayList<LcCiudad> getLcEmpCiudadprov( int IdProvincia){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcCiudad> arreglo = new ArrayList<LcCiudad>();
        Query q = session.createQuery("from LcCiudad E  Where E.lcProvincia.idProvincia= :idProvincia  AND E.estado = :estado");
        q.setParameter("idProvincia",IdProvincia);
        q.setParameter("estado","A");
        List<LcCiudad> lista=q.list();
        Iterator<LcCiudad> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcCiudad rol= (LcCiudad) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
          public ArrayList<LcTiposIdentificacion> getLcEmpTipIDE(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcTiposIdentificacion> arreglo = new ArrayList<LcTiposIdentificacion>();
        Query q = session.createQuery("from LcTiposIdentificacion  ");
        //q.setParameter("estado","A");
        List<LcTiposIdentificacion> lista=q.list();
        Iterator<LcTiposIdentificacion> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcTiposIdentificacion rol= (LcTiposIdentificacion) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
    public ArrayList<LcEmpresa> getDatoEncontrado(String identificacion){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcEmpresa> arreglo = new ArrayList<LcEmpresa>();
        Query q = session.createQuery("From LcEmpresa E WHERE E.identificacion = :identificacion and E.estado = :estado" );
        q.setParameter("identificacion",identificacion);
        q.setParameter("estado", "A");
        List<LcEmpresa> lista=q.list();
        Iterator<LcEmpresa> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcEmpresa rol= (LcEmpresa) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }  
     
    public void addEmpresa(LcEmpresa empresa){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(empresa);
    tx.commit();
    session.close();
    }
    
    public void updateEmpresa(int idEmpresa, int idPais, int idCiudad, Integer idProvincia, int tipoIdentificacion, String identificacion, String razonSocial, String direccion, String telefonos, String email, String telfonos2,String celular){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcEmpresa agen = (LcEmpresa)session.get(LcEmpresa.class, idEmpresa);
    agen.setLcPais(new LcPais(idPais));
    agen.setLcCiudad(new LcCiudad(idCiudad));
    agen.setLcProvincia(new LcProvincia(idProvincia));
    agen.setLcTiposIdentificacion(new LcTiposIdentificacion(tipoIdentificacion));
    agen.setIdentificacion(identificacion);
    agen.setRazonSocial(razonSocial);
    agen.setDireccion(direccion);
    agen.setTelefonos(telefonos);
    agen.setEmail(email);
    agen.setTelfonos2(telfonos2);
    agen.setCelular(celular);
    session.update(agen);
    tx.commit();
    session.close();
    }
   
    public void deleteEmpresa(int id){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcEmpresa agen = (LcEmpresa)session.get(LcEmpresa.class, id);
    agen.setEstado("I");
    session.update(agen);
    tx.commit();
    session.close();
    }
    
    //consulta datos por ID
    public List<LcEmpresa> getDatosLCZonasID(int IDEmpresa){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcEmpresa> arreglo = new ArrayList<LcEmpresa>();
        Query q = session.createQuery("from LcEmpresa E WHERE E.idEmpresa = :idEmpresa ");
        q.setParameter("idEmpresa",IDEmpresa);
         List<LcEmpresa> lista=q.list();
         for(LcEmpresa mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdEmpresa()+", "+mrol.getLcPais().getPais());
             System.out.println("ok: "+mrol.getIdEmpresa()+", "+mrol.getLcProvincia().getProvincia());
             System.out.println("ok: "+mrol.getIdEmpresa()+", "+mrol.getLcCiudad().getCiudad());
             System.out.println("ok: "+mrol.getIdEmpresa()+", "+mrol.getLcTiposIdentificacion().getDescripcion());
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
        int q = (int)session.createQuery("SELECT MAX(idEmpresa) FROM LcEmpresa").uniqueResult();
            tx.commit();
    session.close();
        return q;
    }
     
        public List<LcEmpresa> getEmpresxempresa(int empresa){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcEmpresa> arreglo = new ArrayList<LcEmpresa>();
        Query q = session.createQuery("from LcEmpresa E WHERE E.idEmpresa= :idEmpresa and E.estado = :estado ");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("estado","A");
        List<LcEmpresa> lista=q.list();
         for(LcEmpresa mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdEmpresa());
        }
            tx.commit();
    session.close();
      
         return lista;
    } 
}
