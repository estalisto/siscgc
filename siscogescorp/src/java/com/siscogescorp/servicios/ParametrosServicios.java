/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcParametros;
import com.siscogescorp.utils.Conexion;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ParametrosServicios {
    
    
    public ArrayList<LcParametros> getLcParametros(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcParametros> arreglo = new ArrayList<LcParametros>();
        Query q = session.createQuery("from LcParametros E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcParametros> lista=q.list();
        Iterator<LcParametros> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcParametros rol= (LcParametros) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    } 
   
   public boolean ValidaLCParametros(String parametro){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcParametros> arreglo = new ArrayList<LcParametros>();
        Query q = session.createQuery("from LcParametros E WHERE E.parametro = :parametro ");
        q.setParameter("parametro",parametro);
        
        boolean resp = false;
        List<LcParametros> lista=q.list();
        Iterator<LcParametros> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcParametros rol= (LcParametros) iter.next();
            arreglo.add(rol);
        }
        
        if(arreglo.size() > 0){
            resp = true;
        }
        
        return resp;
    }
   
    public ArrayList<LcParametros> getDatoEncontrado(int codigo){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcParametros> arreglo = new ArrayList<LcParametros>();
        Query q = session.createQuery("From LcParametros E WHERE E.idParametro= :idParametro and E.estado = :estado" );
        q.setParameter("idParametro",codigo);
        q.setParameter("estado", "A");
        List<LcParametros> lista=q.list();
        Iterator<LcParametros> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcParametros rol= (LcParametros) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
    public void addLcParametros(LcParametros parametros){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(parametros);
    tx.commit();
    session.close();
    }
    public void updateParametros(int id, int codigo, String nombre, String valor, String descripcion){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcParametros agen = (LcParametros)session.get(LcParametros.class, id);
    agen.setIdParametro(codigo);
    agen.setParametro(nombre);
    agen.setValor(valor);
    agen.setDescripcion(descripcion);
    session.update(agen);
    tx.commit();
    session.close();
    }
   
    public void deleteParametros(int id){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcParametros agen = (LcParametros)session.get(LcParametros.class, id);
    agen.setEstado("I");
    session.update(agen);
    tx.commit();
    session.close();
    }
    
    //consulta datos por ID
   public ArrayList<LcParametros> getDatosLCParametrosID(int ID){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcParametros> arreglo = new ArrayList<LcParametros>();
        Query q = session.createQuery("from LcParametros E WHERE E.id = :id ");
        q.setParameter("id",ID);
        List<LcParametros> lista=q.list();
        Iterator<LcParametros> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcParametros rol= (LcParametros) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
        public int  SecuenciaModulo(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        int q = (int)session.createQuery("SELECT MAX(id) FROM LcParametros").uniqueResult();
            tx.commit();
    session.close();
        return q;
    }
        public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_parametros_id_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
        
         public String getValorParametro(String param)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
            try
            {
            String SQL="select valor from lc_parametros where parametro='"+param+"' and estado='A'";    
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
                {
                valor= rs.getString("valor");
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
    public List<LcParametros> getParametros(String parametro){
      // List<String> lista2 = null;
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        //ArrayList<LcParametros> arreglo = new ArrayList<LcParametros>();
        Query q = session.createQuery("From LcParametros E WHERE E.parametro= :parametro and E.estado = :estado" );
        q.setParameter("parametro",parametro);
        q.setParameter("estado", "A");
        List<LcParametros> lista=q.list();
         for(LcParametros datos:lista )
        {
             System.out.println("ok: "+datos.getValor());
            // lista2.add(datos.getValor());
        }
        tx.commit();
        session.close();
        //agrega los datos en la lista

        
        return lista;
    }
    public boolean permisos_admin(String id_rol_empleado){
    
     boolean ok=false;
        int cliente=0;
       List<LcParametros> dato = getParametros("LB_PERMISOS_ADMIN");       
       if(!dato.isEmpty()){
           
           for(int i=0; i< dato.size(); i++) {
               if(dato.get(i).getValor().contains(id_rol_empleado)){
                   ok=true;  break;
               }           
           }
       }else{
       ok=false;
       }
       return ok;
       
    }
    public String Consulta_Parametro(String parametro){
      // List<String> lista2 = null;
        String Valor="";
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        //ArrayList<LcParametros> arreglo = new ArrayList<LcParametros>();
        Query q = session.createQuery("From LcParametros E WHERE E.parametro= :parametro and E.estado = :estado" );
        q.setParameter("parametro",parametro);
        q.setParameter("estado", "A");
        List<LcParametros> lista=q.list();
         for(LcParametros datos:lista )
        {
             //System.out.println("ok: "+datos.getValor());
             Valor=datos.getValor();
            // lista2.add(datos.getValor());
        }
        tx.commit();
        session.close();
        //agrega los datos en la lista

        
        return Valor;
    }
    
        
}
