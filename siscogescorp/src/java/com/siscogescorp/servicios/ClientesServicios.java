/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcCiudad;
import com.siscogescorp.modelo.LcClientes;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcPais;
import com.siscogescorp.modelo.LcProvincia;
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
 * @author ViewSoft
 */
public class ClientesServicios {
    public ArrayList<LcClientes> getLcClientes(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcClientes> arreglo = new ArrayList<LcClientes>();
        Query q = session.createQuery("from LcClientes E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcClientes> lista=q.list();
        Iterator<LcClientes> iter=lista.iterator();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcClientes rol= (LcClientes) iter.next();
            arreglo.add(rol);
        }
         tx.commit();
        session.close();
        return arreglo;
    }
         public List<LcClientes> getLcClientess(){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcClientes E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcClientes> lista=q.list();
         for(LcClientes mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdCliente()+", "+mrol.getLcEmpresa().getRazonSocial());
        }
         tx.commit();
        session.close();
      
         return lista;
    }
         
         public List<LcClientes> getClientesxempresa(int empresa){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcClientes E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.estado = :estado ");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("estado","A");
        List<LcClientes> lista=q.list();
         for(LcClientes mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdCliente()+", "+mrol.getLcEmpresa().getRazonSocial());
        }
         tx.commit();
        session.close();
      
         return lista;
    }
         
    public void addclientes(LcClientes clientes){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(clientes);
    tx.commit();
    session.close();
    }
    
    public ArrayList<LcClientes> getDatoEncontrado(String identificacion){
     
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcClientes> arreglo = new ArrayList<LcClientes>();
        Query q = session.createQuery("From LcClientes E WHERE E.identificacion = :identificacion and E.estado = :estado" );
        q.setParameter("identificacion",identificacion);
        q.setParameter("estado", "A");
        List<LcClientes> lista=q.list();
        Iterator<LcClientes> iter=lista.iterator();
        while(iter.hasNext())
        {
            LcClientes rol= (LcClientes) iter.next();
            arreglo.add(rol);
        }
         tx.commit();
        session.close();
        return arreglo;
    }  
    
    public void updateClientes(int idCliente, int idEmpresa, int tipoIdentificacion, String identificacion, String razonSocial, String direccion, int pais, int provincia, int ciudad, String contacto, String email, String telefono, String extensioon, String celular){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcClientes agen = (LcClientes)session.get(LcClientes.class, idCliente);    
    agen.setLcEmpresa(new LcEmpresa(idEmpresa));
    agen.setLcPais(new LcPais(pais));
    agen.setLcCiudad(new LcCiudad(ciudad));
    agen.setLcProvincia(new LcProvincia(provincia));
    agen.setLcTiposIdentificacion(new LcTiposIdentificacion(tipoIdentificacion));
    agen.setIdentificacion(identificacion);
    agen.setRazonSocial(razonSocial);
    agen.setDireccion(direccion);
    agen.setContacto(contacto);
    agen.setEmail(email);
    agen.setTelefono(telefono);
    agen.setExtensioon(extensioon);
    agen.setCelular(celular);    
    session.update(agen);
    tx.commit();
    session.close();
    }
   
    public void deleteClientes(int id){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcClientes agen = (LcClientes)session.get(LcClientes.class, id);
    agen.setEstado("I");
    session.update(agen);
    tx.commit();
    session.close();
    }
    
    //consulta datos por ID
    public List<LcClientes> getDatosLClienteID(int IDCliente){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcClientes E WHERE E.idCliente = :idCliente ");
        q.setParameter("idCliente",IDCliente);
        List<LcClientes> lista=q.list();
         for(LcClientes mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdCliente()+", "
                     +mrol.getLcEmpresa().getRazonSocial()+mrol.getLcPais().getIdPais()+
                     mrol.getLcTiposIdentificacion().getIdTipoIdentificacion()+mrol.getLcTiposIdentificacion().getDescripcion()+
                     mrol.getLcPais().getPais()+mrol.getLcProvincia().getIdProvincia()+mrol.getLcProvincia().getProvincia()+mrol.getLcCiudad().getIdCiudad()+mrol.getLcCiudad().getCiudad());
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
        int q = (int)session.createQuery("SELECT MAX(idCliente) FROM LcClientes").uniqueResult();
         tx.commit();
        session.close();
        return q;
    }
        public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_clientes_id_cliente_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
}
