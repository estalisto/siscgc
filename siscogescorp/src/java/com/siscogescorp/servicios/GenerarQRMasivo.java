/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcDeudoresDocumentos;
import com.siscogescorp.utils.CodigoQR;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author CIMA2015
 */
public class GenerarQRMasivo {
    CodigoQR qr = new CodigoQR();
    
     public List<LcDeudoresDocumentos> getGeneraDocumentos(int idTicket){
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcDeudoresDocumentos  E WHERE E.idTicket= :idTicket");
        q.setParameter("idTicket",idTicket);
        List<LcDeudoresDocumentos> lista=q.list();
        for(LcDeudoresDocumentos mrol:lista )
        {
             System.out.println("ok: ");
             qr.generaImgQrID(mrol.getIdTicket(),mrol.getLcDatosDeudores().getIdDatosDeudor());
        }
        tx.commit();
        session.close();
        return lista;
    }
    
    
}
