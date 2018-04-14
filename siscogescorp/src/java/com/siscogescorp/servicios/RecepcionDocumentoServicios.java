/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcDatosDeudores;
import com.siscogescorp.modelo.LcDeudor;
import com.siscogescorp.modelo.LcDocumentosGenerados;
import com.siscogescorp.modelo.LcTransacciones;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Date;
/**
 *
 * @author ViewSoft
 */
public class RecepcionDocumentoServicios {
    
        public String getDocRecepcion(String idQR) throws SQLException{
        DeudorServicios de = new DeudorServicios();
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();   
        BigDecimal tsaldo=BigDecimal.ZERO;
        BigDecimal valorPagado =BigDecimal.ZERO;
        int idDeudor;
        String table="";
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcDocumentosGenerados  E WHERE E.codigo= :codigo and E.estado=:estado");
        q.setParameter("codigo",idQR);
        q.setParameter("estado","A");
        List<LcDocumentosGenerados> lista=q.list();
         for(LcDocumentosGenerados datos:lista )
        {
             json = new JSONObject();
             json.put("IdDocGenerado",datos.getIdDocGenerado());
             json.put("ticket",datos.getIdTicket());
             json.put("IdDeudor",datos.getIdDeudor());
             json.put("rutaDoc",datos.getRutaDocumento());
             idDeudor= datos.getIdDeudor();
             List<LcDatosDeudores> datodeudor= de.getDatosDeudorID(idDeudor);           
             json.put("identificacion",datodeudor.get(0).getIdentificacion());
             json.put("nombres",datodeudor.get(0).getNombresCompleto());
             
             table= de.getDatosTransaccion(idDeudor);
            
             json.put("table",table);
             itemSelectedJson.add(json);
            
        }
        tx.commit();
        session.close();
        return  itemSelectedJson.toString();
    }
   public String getDatosRecibidos(String idQR) throws SQLException{
        DeudorServicios de = new DeudorServicios();
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray();   
        BigDecimal tsaldo=BigDecimal.ZERO;
        BigDecimal valorPagado =BigDecimal.ZERO;
        int idDeudor;
        String table="";
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcDocumentosGenerados  E WHERE E.codigo= :codigo and E.estado=:estado");
        q.setParameter("codigo",idQR);
        q.setParameter("estado","A");
        List<LcDocumentosGenerados> lista=q.list();
         for(LcDocumentosGenerados datos:lista )
        {
             json = new JSONObject();
             
             json.put("observacion_recepcion",datos.getObservacion());
             json.put("name_recepcion",datos.getPersonaRecibe());
             json.put("Ide_recepcion",datos.getIdentificacionRecibe());
             json.put("fecha_recepcion",datos.getFechaRecepcion());
             
             
             itemSelectedJson.add(json);
            
        }
        tx.commit();
        session.close();
        return  itemSelectedJson.toString();
    }
    public void updateDocumento(int recepcion,String Ide_recepcion, String name_recepcion, Date fecha_recepcion,String observacion,String condicional){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcDocumentosGenerados agen = (LcDocumentosGenerados)session.get(LcDocumentosGenerados.class, recepcion);
    agen.setFechaRecepcion(fecha_recepcion);
    agen.setPersonaRecibe(name_recepcion);
    agen.setIdentificacionRecibe(Ide_recepcion);
    agen.setObservacion(observacion);
    if(condicional.equals("1")){
        agen.setRecepcion("SI");
    }
    if(condicional.equals("0")){
        agen.setRecepcion("NO");
    }
    session.update(agen);
    tx.commit();
    session.close();
    }
       
    
}
