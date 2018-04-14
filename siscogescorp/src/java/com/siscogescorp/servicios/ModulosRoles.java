/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcModuloRol;
import com.siscogescorp.modelo.LcModulos;
import com.siscogescorp.modelo.LcRoles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
public class ModulosRoles {
   
     public List<LcModuloRol> getLcModulosRoles(int rolID, int empresaID){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcModuloRol> arreglo = new ArrayList<LcModuloRol>();
        Query q = session.createQuery("From LcModuloRol E WHERE E.nivelModulo = :nivel_Modulo and E.lcEmpresa.idEmpresa = :id_empresa and E.lcRoles.idRol = :idRol AND E.estado= :estado ORDER BY E.ordenReg" );
        q.setParameter("nivel_Modulo",0);
        q.setParameter("idRol",rolID);
        q.setParameter("id_empresa",empresaID);
         q.setParameter("estado","A");
        
        List<LcModuloRol> lista=q.list();
         for(LcModuloRol mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcModulos().getMenuOpciones()+", "+mrol.getLcModulos().getGrupo());
        }
        
          tx.commit();
    session.close();
         return lista;
    }
     
      public List<LcModuloRol> getCabecera(int empresaID ,int rolID, int nivel_cab){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("From LcModuloRol E WHERE E.grupoMod = :grupoMod  and E.lcEmpresa.idEmpresa = :id_empresa and E.lcRoles.idRol = :idRol and E.estado = :estado" );
        q.setParameter("grupoMod",nivel_cab);
        q.setParameter("idRol",rolID);
        q.setParameter("id_empresa",empresaID);
        q.setParameter("estado", "A");
        
        List<LcModuloRol> lista=q.list();
         for(LcModuloRol mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcModulos().getMenuOpciones());
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcEmpresa().getRazonSocial());
        }
        
          tx.commit();
    session.close();
         return lista;
    }
      
    public ArrayList<LcModulos> getLcModuloCabecera(int IDempresa, int nivel){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcModulos> arreglo = new ArrayList<LcModulos>();
        Query q = session.createQuery("from LcModulos E WHERE E.grupo = :grupo and E.idEmpresa = :idEmpresa ");
        q.setParameter("grupo",nivel);
        q.setParameter("idEmpresa",IDempresa);
        List<LcModulos> lista=q.list();
        Iterator<LcModulos> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcModulos rol= (LcModulos) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }  
    public void addModulosCabecera(LcModuloRol modulo){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(modulo);
    tx.commit();
    session.close();
    }
 
      public List<LcModuloRol> getDatoEncontrado(int empresaID ,int rolID, int modulo){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("From LcModuloRol E WHERE E.lcModulos.idModulo = :idModulo and E.lcEmpresa.idEmpresa = :id_empresa and E.lcRoles.idRol = :idRol and E.estado = :estado" );
        q.setParameter("idModulo",modulo);
        q.setParameter("idRol",rolID);
        q.setParameter("id_empresa",empresaID);
        q.setParameter("estado", "A");
        
        List<LcModuloRol> lista=q.list();
         for(LcModuloRol mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcModulos().getMenuOpciones());
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcEmpresa().getRazonSocial());
        }
            tx.commit();
    session.close();
      
         return lista;
    }  
      
    public List<LcModuloRol> getLcModulosRolesROL(int rolID, int empresaID){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("From LcModuloRol E WHERE E.nivelModulo != :nivel_Modulo and E.lcEmpresa.idEmpresa = :id_empresa and E.lcRoles.idRol = :idRol and E.estado = :estado ORDER BY E.ordenReg");
         q.setParameter("nivel_Modulo",0);
         q.setParameter("idRol",rolID);
         q.setParameter("id_empresa",empresaID);
         q.setParameter("estado", "A");
        List<LcModuloRol> lista=q.list();
  
         for(LcModuloRol mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcModulos().getMenuOpciones());
        }
              tx.commit();
        session.close();
      
         return lista;
    }
      
    public List<LcModuloRol> getLcAgencia3(){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcModuloRol E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcModuloRol> lista=q.list();
         for(LcModuloRol mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcModulos().getMenuOpciones());
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcRoles().getDescripcion());
        }
            tx.commit();
    session.close();
      
         return lista;
    } 
    
    public ArrayList<LcModuloRol> getLcmodulorol(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcModuloRol> arreglo = new ArrayList<LcModuloRol>();
        Query q = session.createQuery("from LcModuloRol E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcModuloRol> lista=q.list();
        Iterator<LcModuloRol> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcModuloRol rol= (LcModuloRol) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
    
    public List<LcModuloRol> getDatosLCModuloRoll(int ID_rol, int IDEmpresa){
         List<LcModuloRol> lista = new  ArrayList();
try{
    SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        
        Query q = session.createQuery("from LcModuloRol E  WHERE E.lcRoles.idRol = :idRol AND E.lcEmpresa.idEmpresa = :id_empresa AND E.estado = :estado order by E.ordenReg asc");
        q.setParameter("idRol",ID_rol);
        q.setParameter("id_empresa",IDEmpresa);
        q.setParameter("estado","A"); 
        lista=q.list();
         for(LcModuloRol mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcModulos().getMenuOpciones());
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcRoles().getDescripcion());
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcEmpresa().getRazonSocial());
        }  
             tx.commit();
    session.close();
 } catch (Exception e) {
                System.out.println(e);
            }       
    return lista;     
    }

    public List<LcModuloRol> getDatosLCModuloID(int IDModulo_rol){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcModuloRol> arreglo = new ArrayList<LcModuloRol>();
        Query q = session.createQuery("from LcModuloRol E WHERE E.idModuloRol = :idModuloRol ");
        q.setParameter("idModuloRol",IDModulo_rol);
        List<LcModuloRol> lista=q.list();
        for(LcModuloRol mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdModuloRol()+", "+mrol.getLcModulos().getMenuOpciones());
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
        int q = (int)session.createQuery("SELECT MAX(idModuloRol) FROM LcModuloRol").uniqueResult();
            tx.commit();
        session.close();
        return q;
    }
    
    public void addModulosrol(LcModuloRol modulo){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(modulo);
    tx.commit();
    session.close();
    }
    
    public ArrayList<LcModuloRol> getLcdeleteModulo(int IDEmpresa, int IdRol, int IdModulo){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcModuloRol> arreglo = new ArrayList<LcModuloRol>();
        Query q = session.createQuery("from LcModuloRol E WHERE E.estado = :estado AND E.lcRoles.idRol = :idRol AND E.lcEmpresa.idEmpresa = :id_empresa AND E.lcModulos.idModulo= :idModulo");
        q.setParameter("idModulo",IdModulo);
        q.setParameter("id_empresa",IDEmpresa);
        q.setParameter("idRol",IdRol);
        q.setParameter("estado","A");
        List<LcModuloRol> lista=q.list();
        Iterator<LcModuloRol> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcModuloRol rol= (LcModuloRol) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
    
    public void deleteModal(int id){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcModuloRol agen = (LcModuloRol)session.get(LcModuloRol.class, id);
    
    //if(agen.getEstado().equals("A"))
    //{
    agen.setEstado("I");
    
   // }
    session.update(agen);
    tx.commit();
    session.close();
    }
     public void deleteModal2(int id,int IdEmpresa,int IdRol){
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session= factory.openSession();
        session.clear();
        Transaction tx=session.beginTransaction();
        String hqlupdate="UPDATE LcModuloRol  "
                + "set estado = :estadoI "
                + "WHERE idModuloRol = :idModuloRol "
                + "and estado=:estadoA  "
                + "and idEmpresa= :Empresa "
                + "and idRol= :Rol " ;
        int valor;
         valor = session.createQuery(hqlupdate)
                 .setParameter("idModuloRol", id)
                 .setParameter("estadoI", "I")
                 .setParameter("estadoA", "A")
                 .setParameter("Empresa", IdEmpresa)
                 .setParameter("Rol", IdRol)
                 .executeUpdate();
        tx.commit();
        session.flush();
        session.close();
     }
    
    public ArrayList<LcModuloRol> getLcModulo(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcModuloRol> arreglo = new ArrayList<LcModuloRol>();
        Query q = session.createQuery("from LcModuloRol");
       // q.setParameter("estado","A");
        List<LcModuloRol> lista=q.list();
        Iterator<LcModuloRol> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcModuloRol rol= (LcModuloRol) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
    public ArrayList<LcModulos> getLcModel(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcModulos> arreglo = new ArrayList<LcModulos>();
        Query q = session.createQuery("from LcModulos E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcModulos> lista=q.list();
        Iterator<LcModulos> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcModulos rol= (LcModulos) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
    
        public ArrayList<LcModulos> getLcModelXEmpresa(int empresa){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcModulos> arreglo = new ArrayList<LcModulos>();
        Query q = session.createQuery("from LcModulos E WHERE E.idEmpresa= :idEmpresa and E.estado = :estado ORDER BY E.orden ");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("estado","A");
        List<LcModulos> lista=q.list();
        Iterator<LcModulos> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcModulos rol= (LcModulos) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
    public ArrayList<LcRoles> getDatosLCRolID(int IDModulo_rol){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcRoles> arreglo = new ArrayList<LcRoles>();
        Query q = session.createQuery("from LcRoles E WHERE E.idRol = :idRol ");
        q.setParameter("idRol",IDModulo_rol);
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
    
    
    
    
}
