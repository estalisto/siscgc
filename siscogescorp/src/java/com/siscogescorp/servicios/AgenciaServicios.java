/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcAgencia;
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
public class AgenciaServicios {

    public ArrayList<LcAgencia> getLcAgencia() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcAgencia> arreglo = new ArrayList<LcAgencia>();
        Query q = session.createQuery("from LcAgencia E WHERE E.estado = :estado ");
        q.setParameter("estado", "A");
        List<LcAgencia> lista = q.list();
        Iterator<LcAgencia> iter = lista.iterator();
       
        //agrega los datos en la lista
        while (iter.hasNext()) {
            LcAgencia rol = (LcAgencia) iter.next();
            arreglo.add(rol);
        }
        tx.commit();
        session.close();
        return arreglo;
    }

    public List<LcAgencia> getLcAgencias() {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from LcAgencia E WHERE E.estado = :estado ");
        q.setParameter("estado", "A");
        List<LcAgencia> lista = q.list();
        for (LcAgencia mrol : lista) {
            System.out.println("ok: " + mrol.getIdAgencia() + ", " + mrol.getLcEmpresa().getRazonSocial());
        }
 tx.commit();
        session.close();
        return lista;
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

    public void addAgencia(LcAgencia agencia) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(agencia);
        tx.commit();
        session.close();
    }

    public void updateAgencia(int idAgencia, int idEmpresa, String nombre, String opcion, String direccion, String telefono, String telefono2, String celular, String email) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        LcAgencia agen = (LcAgencia) session.get(LcAgencia.class, idAgencia);
        //agen.setIdEmpresa(idEmpresa);
        agen.setNombre(nombre);
        agen.setOpcion(opcion);
        agen.setDireccion(direccion);
        agen.setTelefono(telefono);
        agen.setTelefono2(telefono2);
        agen.setCelular(celular);
        session.update(agen);
        tx.commit();
        session.close();
    }

    public void deleteAgencia(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        LcAgencia agen = (LcAgencia) session.get(LcAgencia.class, id);
        agen.setEstado("I");
        session.update(agen);
        tx.commit();
        session.close();
    }

    public List<LcAgencia> getDatosLCAgenciaID(int IDAgencia) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from LcAgencia E WHERE E.idAgencia = :idAgencia ");
        q.setParameter("idAgencia", IDAgencia);
        List<LcAgencia> lista = q.list();
        for (LcAgencia mrol : lista) {
            System.out.println("ok: " + mrol.getIdAgencia() + ", " + mrol.getLcEmpresa().getRazonSocial());
        }
        tx.commit();
        session.close();
        return lista;
    }

    public int SecuenciaModulo() {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        int q = (int) session.createQuery("SELECT MAX(idAgencia) FROM LcAgencia").uniqueResult();
         tx.commit();
        session.close();
        
        return q;
    }

    public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_agencia_id_agencia_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
}
