/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

//import com.laticobsa.modelo.AudSesiones;
//import com.laticobsa.modelo.AudTransacciones;
import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.utils.Conexion;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ViewSoft
 */
public class AudTrasaccionesServicios {
    Conexion conexion=new Conexion();
   
    PreparedStatement pst;
    ResultSet rs;
    public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('aud_transacciones_id_transacciones_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; 
    }
    /* public void addAudTransacciones(AudTransacciones transaccion){
     SessionFactory factory=HibernateUtil.getSessionFactory();
     Session session= factory.openSession();
     Transaction tx=session.beginTransaction();
     session.save(transaccion);
     tx.commit();
     session.close();
     }*/
    
    //Inicio Jimmy Guaranda 
    public void GuardarAudTransacciones(String usuario,String Ip,String proceso,String datos,String origen) throws SQLException{
        pst = conexion.getconexion().prepareCall("SELECT fnc_aud_transacciones('"+usuario+"','"+Ip+"','"+proceso+"','"+datos+"','"+origen+"');");   
        //connBD.createStatement();
        rs = pst.executeQuery();       
        rs.close();    
        pst.close();
        conexion.cierraConexion();
    }
    //Fin Jimmy Guaranda 
}
