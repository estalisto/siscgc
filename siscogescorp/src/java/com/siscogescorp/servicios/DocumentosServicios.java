/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcAgencia;
import com.siscogescorp.modelo.LcClientes;
import com.siscogescorp.modelo.LcDocumentos;
import com.siscogescorp.utils.Conexion;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author CIMA2015
 */
public class DocumentosServicios {
     public List<LcDocumentos> getLcDocumentos(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        //ArrayList<LcDocumentos> arreglo = new ArrayList<LcEmpresa>();
        Query q = session.createQuery("from LcDocumentos E WHERE E.estado = :estado");
         q.setParameter("estado", "A");
        List<LcDocumentos> lista=q.list();
         for(LcDocumentos list:lista )
        {
             System.out.print("datos: "+list.getIdDocumento()+list.getNombreDocumento()+list.getIdCliente()+list.getFechaRegistro()+list.getFechaActualizado());
        }
        tx.commit();
        session.close();
        return lista;
    }
    public void addDocumentos(LcDocumentos documentos){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(documentos);
    tx.commit();
    session.close();
    }
    public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_documentos_id_documento_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; 
    }
    public List<LcAgencia> getLcAgenciasxEmpresa(int empresa) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        ArrayList<LcAgencia> arreglo = new ArrayList<LcAgencia>();
        Query q = session.createQuery("from LcAgencia E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.estado = :estado ");
        q.setParameter("idEmpresa", empresa);
        q.setParameter("estado", "A");
        List<LcAgencia> lista = q.list();
        for (LcAgencia mrol : lista) {
            System.out.println("ok: " + mrol.getIdAgencia()+mrol.getLcEmpresa().getIdEmpresa() + ", " + mrol.getLcEmpresa().getRazonSocial()+mrol.getIdAgencia()+mrol.getNombre());
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
    public List<LcDocumentos> ConsultaDataDoc(int idDoc){
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcDocumentos E WHERE E.idDocumento= :idDocumento and E.estado = :estado ");
        q.setParameter("idDocumento",idDoc);
        q.setParameter("estado","A");
        List<LcDocumentos> lista=q.list();
         for(LcDocumentos mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDocumento());
        }
         tx.commit();
        session.close();
      
         return lista;
    }   
    public void updateDocumento(int idDocumento, int empresa, int sucursal, int cartera, String nombre_doc, String saludo, String cuerpo, String despedida, String firma){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcDocumentos agen = (LcDocumentos)session.get(LcDocumentos.class, idDocumento);
    agen.setIdEmpresa(empresa);
    agen.setIdAgencia(sucursal);
    agen.setIdCliente(cartera);
    agen.setNombreDocumento(nombre_doc);
    agen.setSaludo(saludo);
    agen.setCuerpo(cuerpo);
    agen.setDespedida(despedida);
    agen.setFirma(firma);
    session.update(agen);
    tx.commit();
    session.close();
    }   
    public Long getNextTicket() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_ticket')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
    
       public Integer getEjecutaSQL(String trama, int ticket)
        {
            
            int valor = 0;
         try{      
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            pst = conexion.getconexion().prepareStatement("select fnc_ejecuta_sentencia('"+trama+"',"+ticket+");");
            rs = pst.executeQuery();
            while(rs.next())    //Mientras haya una sig. tupla
            {
                valor=rs.getInt(1);
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            
            }catch (SQLException ex) {
                System.err.println( ex.getMessage() );
             } 
            return valor;
        }
   public void deleteDocumento(int id){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcDocumentos agen = (LcDocumentos)session.get(LcDocumentos.class, id);
    agen.setEstado("I");
    session.update(agen);
    tx.commit();
    session.close();
    }
   
   
    public String getMisDocumentos(int id_empresa, int id_sucursal){
     
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcDocumentos E WHERE E.idEmpresa= :idEmpresa and E.idAgencia= :idAgencia and E.estado = :estado ");
        q.setParameter("idEmpresa",id_empresa);
        q.setParameter("idAgencia",id_sucursal);
        q.setParameter("estado","A");
        List<LcDocumentos> lista=q.list();
         for(LcDocumentos datos:lista )
        {
            //
           json = new JSONObject();
           json.put("idDocumento",datos.getIdDocumento());
           json.put("nombreDocumento", datos.getNombreDocumento());
           itemSelectedJson.add(json);
        }
         tx.commit();
        session.close();
      
         return itemSelectedJson.toString();
    }  
    
}
