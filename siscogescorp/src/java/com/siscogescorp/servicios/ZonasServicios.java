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
import com.siscogescorp.modelo.LcZonas;
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
public class ZonasServicios {

    public ArrayList<LcZonas> getLCZonas() {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcZonas> arreglo = new ArrayList<LcZonas>();
        Query q = session.createQuery("from LcZonas E WHERE E.estado = :estado ");
        q.setParameter("estado", "A");
        List<LcZonas> lista = q.list();
        Iterator<LcZonas> iter = lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while (iter.hasNext()) {
            LcZonas rol = (LcZonas) iter.next();
            arreglo.add(rol);
        }

        return arreglo;
    }

    public List<LcZonas> getLcZonass() {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from LcZonas E WHERE E.estado = :estado ");
        q.setParameter("estado", "A");
        List<LcZonas> lista = q.list();
        for (LcZonas mrol : lista) {
            System.out.println("ok: " + mrol.getIdZona() + ", " + mrol.getLcEmpresa().getRazonSocial());
            System.out.println("ok: " + mrol.getIdZona() + ", " + mrol.getLcPais().getPais());
            System.out.println("ok: " + mrol.getIdZona() + ", " + mrol.getLcProvincia().getProvincia());
            System.out.println("ok: " + mrol.getIdZona() + ", " + mrol.getLcCiudad().getCiudad());

        }
    tx.commit();
    session.close();
        return lista;
    }

    public List<LcZonas> getLcZonasxEmpresa(int empresa) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from LcZonas E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.estado = :estado ");
        q.setParameter("idEmpresa", empresa);
        q.setParameter("estado", "A");
        List<LcZonas> lista = q.list();
        for (LcZonas mrol : lista) {
            System.out.println("ok: " + mrol.getIdZona() + ", " + mrol.getLcEmpresa().getRazonSocial());
            System.out.println("ok: " + mrol.getIdZona() + ", " + mrol.getLcPais().getPais());
            System.out.println("ok: " + mrol.getIdZona() + ", " + mrol.getLcProvincia().getProvincia());
            System.out.println("ok: " + mrol.getIdZona() + ", " + mrol.getLcCiudad().getCiudad());

        }
    tx.commit();
    session.close();
        return lista;
    }

    public boolean ValidaLCZonas(String nombre) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcZonas> arreglo = new ArrayList<LcZonas>();
        Query q = session.createQuery("from LcZonas E WHERE E.nombre = :nombre ");
        q.setParameter("nombre", nombre);

        boolean resp = false;
        List<LcZonas> lista = q.list();
        Iterator<LcZonas> iter = lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while (iter.hasNext()) {
            LcZonas rol = (LcZonas) iter.next();
            arreglo.add(rol);
        }

        if (arreglo.size() > 0) {
            resp = true;
        }

        return resp;
    }

    public void addZonas(LcZonas zona) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(zona);
        tx.commit();
        session.close();
    }

    public void updateZona(int id, int empresa, int pais, int provincia, int ciudad, String nombre, String sector) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        LcZonas agen = (LcZonas) session.get(LcZonas.class, id);
        agen.setLcEmpresa(new LcEmpresa(empresa));
        agen.setLcPais(new LcPais(pais));
        agen.setLcProvincia(new LcProvincia(provincia));
        agen.setLcCiudad(new LcCiudad(ciudad));
        agen.setNombreZona(nombre);
        agen.setSectorDescripcion(sector);
        session.update(agen);
        tx.commit();
        session.close();
    }

    public void deleteZona(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        LcZonas agen = (LcZonas) session.get(LcZonas.class, id);
        agen.setEstado("I");
        session.update(agen);
        tx.commit();
        session.close();
    }

    //consulta datos por ID
    public List<LcZonas> getDatosLCZonasID(int IDZona) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from LcZonas E WHERE E.idZona = :idZona ");
        q.setParameter("idZona", IDZona);
        List<LcZonas> lista = q.list();
        for (LcZonas mrol : lista) {
            System.out.println("ok: " + mrol.getIdZona() + ", " + mrol.getLcEmpresa().getRazonSocial());
        }
    tx.commit();
    session.close();
        return lista;
    }

    public ArrayList<LcPais> getLcEmpPais() {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcPais> arreglo = new ArrayList<LcPais>();
        Query q = session.createQuery("from LcPais  ");
        //q.setParameter("estado","A");
        List<LcPais> lista = q.list();
        Iterator<LcPais> iter = lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while (iter.hasNext()) {
            LcPais rol = (LcPais) iter.next();
            arreglo.add(rol);
        }

        return arreglo;
    }

    public int SecuenciaModulo() {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        int q = (int) session.createQuery("SELECT MAX(idZona) FROM LcZonas").uniqueResult();
        tx.commit();
        session.close();
        return q;
    }

    public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_zonas_id_zona_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }
}
