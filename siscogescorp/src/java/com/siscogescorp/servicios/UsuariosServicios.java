/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcEmpleados;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcRoles;
import com.siscogescorp.modelo.LcUsuarios;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
public class UsuariosServicios {

    public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select nextval('lc_usuarios_id_usuario_seq')");
        Long key = ((BigInteger) query.uniqueResult()).longValue();
        tx.commit();
        session.close();
        return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }

    public ArrayList<LcUsuarios> getLcUsuarios() {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("from LcUsuarios E WHERE E.estado = :estado ");
        q.setParameter("estado", "A");
        List<LcUsuarios> lista = q.list();
        Iterator<LcUsuarios> iter = lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while (iter.hasNext()) {
            LcUsuarios rol = (LcUsuarios) iter.next();
            arreglo.add(rol);
        }

        return arreglo;
    }

    public List<LcUsuarios> getLcUsuarioss() {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("from LcUsuarios E WHERE E.estado = :estado ");
        q.setParameter("estado", "A");
        List<LcUsuarios> lista = q.list();
        for (LcUsuarios mrol : lista) {
            System.out.print("ok: " + mrol.getIdUsuario() + ", " + mrol.getLcEmpresa().getRazonSocial()+mrol.getLcEmpleados().getIdentificacion()+mrol.getUsuario());
            System.out.print("ok: " + mrol.getIdUsuario() + ", " + mrol.getLcRoles().getDescripcion()+mrol.getFechaCreacion()+mrol.getObservacion());
        }
    tx.commit();
    session.close();
        return lista;
    }

    public ArrayList<LcUsuarios> getUserempresa(String user, int empresa) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("from LcUsuarios E WHERE E.usuario = :usuario and E.lcEmpresa.idEmpresa= :idEmpresa ");
        q.setParameter("usuario", user);
        q.setParameter("idEmpresa", empresa);
        //q.setParameter("estado","A");
        List<LcUsuarios> lista = q.list();
        Iterator<LcUsuarios> iter = lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while (iter.hasNext()) {
            LcUsuarios rol = (LcUsuarios) iter.next();
            arreglo.add(rol);
        }

        return arreglo;
    }

    public ArrayList<LcUsuarios> getLcAutorizaUser(String user, String pass) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("from LcUsuarios E WHERE E.usuario= :id_user and  E.estado = :estado ");
        q.setParameter("id_user", user);
        // q.setParameter("estado",pass);
        q.setParameter("estado", "A");
        List<LcUsuarios> lista = q.list();
        Iterator<LcUsuarios> iter = lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while (iter.hasNext()) {
            LcUsuarios rol = (LcUsuarios) iter.next();
            arreglo.add(rol);
        }

        return arreglo;
    }

    public ArrayList<LcUsuarios> getUsuarioEncontrado(int empresa, String usuario) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("From LcUsuarios E WHERE E.usuario= :usuario and E.lcEmpresa.idEmpresa= :idEmpresa and E.estado = :estado");
        q.setParameter("idEmpresa", empresa);
        q.setParameter("usuario", usuario);
        q.setParameter("estado", "A");
        List<LcUsuarios> lista = q.list();
        Iterator<LcUsuarios> iter = lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while (iter.hasNext()) {
            LcUsuarios rol = (LcUsuarios) iter.next();
            arreglo.add(rol);
        }

        return arreglo;
    }

    public ArrayList<LcUsuarios> getDatoEncontrado(int empresa, int idEmpleado, String usuario, int idRol) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("From LcUsuarios E WHERE  E.lcEmpleados.idEmpleado = :idEmpleado and E.lcEmpresa.idEmpresa= :idEmpresa and E.lcRoles.idRol= :idRol and E.usuario= :usuario and E.estado = :estado");
        q.setParameter("idEmpleado", idEmpleado);
        q.setParameter("idEmpresa", empresa);
        q.setParameter("idRol", idRol);
        q.setParameter("usuario", usuario);
        q.setParameter("estado", "A");
        List<LcUsuarios> lista = q.list();
        Iterator<LcUsuarios> iter = lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while (iter.hasNext()) {
            LcUsuarios rol = (LcUsuarios) iter.next();
            arreglo.add(rol);
        }

        return arreglo;
    }

    public void addUsuario(LcUsuarios usuarios) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(usuarios);
        tx.commit();
        session.close();
    }

    public void updateUsuario(int idUsuario, int idEmpresa, int idEmpleado, int idRol, String usuario, String contrasenia, String observacion) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        LcUsuarios agen = (LcUsuarios) session.get(LcUsuarios.class, idUsuario);
        //.setLcEmpresa(lcEmpresa.idEmpresa);//setIdEmpresa(idEmpresa);
        agen.setLcEmpresa(new LcEmpresa(idEmpresa));
        agen.setLcEmpleados(new LcEmpleados(idEmpleado));
        agen.setLcRoles(new LcRoles(idRol));//setIdRol(idRol);
        agen.setUsuario(usuario);
        agen.setContrasenia(contrasenia);
        agen.setObservacion(observacion);
        session.update(agen);
        tx.commit();
        session.close();
    }

    public void deleteUsuario(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        LcUsuarios agen = (LcUsuarios) session.get(LcUsuarios.class, id);
        agen.setEstado("I");
        session.update(agen);
        tx.commit();
        session.close();
    }

    //consulta datos por ID
    public List<LcUsuarios> getDatosLCUsuariosID(int IDUsuario) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("from LcUsuarios E WHERE E.idUsuario = :idUsuario ");
        q.setParameter("idUsuario", IDUsuario);
        List<LcUsuarios> lista = q.list();
        for (LcUsuarios mrol : lista) {
            System.out.print("ok: " + mrol.getIdUsuario() + ", " + mrol.getLcEmpresa().getRazonSocial()+mrol.getLcEmpleados().getIdentificacion()+mrol.getUsuario());
            System.out.print("ok: " + mrol.getIdUsuario() + ", " + mrol.getLcRoles().getDescripcion()+mrol.getFechaCreacion()+mrol.getObservacion());
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
        int q = (int) session.createQuery("SELECT MAX(idUsuario) FROM LcUsuarios").uniqueResult();
        tx.commit();
        session.close();
        return q;
    }

    public List<LcUsuarios> getUsuariocambio(int empresa, String nombre) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("from LcUsuarios E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.usuario= :usuario");
        q.setParameter("idEmpresa", empresa);
        q.setParameter("usuario", nombre);
        //q.setParameter("estado","A");
        List<LcUsuarios> lista = q.list();
        for (LcUsuarios mrol : lista) {
            System.out.println("ok: " + mrol.getIdUsuario() + ", " + mrol.getLcEmpresa().getRazonSocial());
            System.out.println("ok: " + mrol.getIdUsuario() + ", " + mrol.getLcEmpleados().getIdentificacion());
        }
        tx.commit();
        session.close();
        return lista;
    }

    public void Cambio_clave(int idUsuario, String contrasenia) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        LcUsuarios agen = (LcUsuarios) session.get(LcUsuarios.class, idUsuario);
        Date expiracionTmp = new Date();
        agen.setContrasenia(contrasenia);
        agen.setEstado("A");
        agen.setExpiracionTmp(expiracionTmp);
        session.update(agen);
        tx.commit();
        session.close();
    }

    public List<LcUsuarios> getUsuarioxempresa(int empresa) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("from LcUsuarios E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.estado= :estado ");
        q.setParameter("idEmpresa", empresa);
        q.setParameter("estado", "A");
        List<LcUsuarios> lista = q.list();
        for (LcUsuarios mrol : lista) {
            System.out.println("ok: " + mrol.getIdUsuario() + ", " + mrol.getLcEmpresa().getRazonSocial());
            System.out.println("ok: " + mrol.getIdUsuario() + ", " + mrol.getLcEmpleados().getIdentificacion()+mrol.getLcRoles().getDescripcion()+mrol.getLcEmpleados().getIdentificacion());
        }
    tx.commit();
    session.close();
        return lista;
    }
    
    public ArrayList<LcUsuarios> getDatoFind(int idEmpleado, int empresa) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("From LcUsuarios E WHERE E.lcEmpleados.idEmpleado = :idEmpleado  and E.lcEmpresa.idEmpresa= :idEmpresa and E.estado = :estado");
        q.setParameter("idEmpleado", idEmpleado);
        //q.setParameter("idRol",idRol);
        q.setParameter("idEmpresa", empresa);
        q.setParameter("estado", "A");
        List<LcUsuarios> lista = q.list();
        Iterator<LcUsuarios> iter = lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while (iter.hasNext()) {
            LcUsuarios rol = (LcUsuarios) iter.next();
            arreglo.add(rol);
        }

        return arreglo;
    }
    public ArrayList<LcUsuarios> getDatoFindExp(int idEmpleado, int empresa) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("From LcUsuarios E WHERE E.lcEmpleados.idEmpleado = :idEmpleado  and E.lcEmpresa.idEmpresa= :idEmpresa and E.estado = :estado");
        q.setParameter("idEmpleado", idEmpleado);
        //q.setParameter("idRol",idRol);
        q.setParameter("idEmpresa", empresa);
        q.setParameter("estado", "E");
        List<LcUsuarios> lista = q.list();
        Iterator<LcUsuarios> iter = lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while (iter.hasNext()) {
            LcUsuarios rol = (LcUsuarios) iter.next();
            arreglo.add(rol);
        }

        return arreglo;
    }
    
    public String getConsultaUsuariosEmpresa(int empresa) {
        JSONObject json = new JSONObject();
        JSONArray itemSelectedJson = new JSONArray(); 
        String resultado="", estado="", color="";
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("from LcUsuarios E WHERE E.lcEmpresa.idEmpresa= :idEmpresa ORDER BY E.estado ");
        q.setParameter("idEmpresa", empresa);
        //q.setParameter("estado", "A");
        List<LcUsuarios> lista = q.list();
        for (LcUsuarios datos : lista) {
            
            if(datos.getEstado().equals("A")){
             estado="<p class='text-primary'>ACTIVO</p>";
            color="text-primary";
            }
            if(datos.getEstado().equals("E")){
            estado="<p class='text-warning'>CAMBIAR CLAVE</p>";
            color="text-warning";
            }
            if(datos.getEstado().equals("I")){
            estado="<p class='text-danger'>INACTIVO</p>";
            color="text-danger";
            }
            if(datos.getEstado().equals("A") || datos.getEstado().equals("E") || datos.getEstado().equals("I")){
                json = new JSONObject();
               json.put("idUsuario","<p class='"+color+"'>"+datos.getIdUsuario()+"</p>");
               json.put("empresa","<p class='"+color+"'>"+datos.getLcEmpresa().getRazonSocial()+"</p>");
               json.put("rol","<p class='"+color+"'>"+datos.getLcRoles().getDescripcion()+"</p>");
               json.put("identificacion","<p class='"+color+"'>"+datos.getLcEmpleados().getIdentificacion()+"</p>");
               json.put("usuario","<p class='"+color+"'>"+datos.getUsuario()+"</p>");
               json.put("fecha_ingreso","<p class='"+color+"'>"+datos.getFechaCreacion()+"</p>");
               json.put("estado",estado);
               json.put("accion",  "<a  onclick='ConnsultaDatosID("+datos.getIdUsuario()+")' ><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>\n" +
                                   "<a onclick='deleteusuario("+datos.getIdUsuario()+")'> <span  class='glyphicon glyphicon-trash' aria-hidden='true'></span></a>"  );
               itemSelectedJson.add(json);
            }
           
          }
    tx.commit();
    session.close();
    
        return itemSelectedJson.toString();
    }
    
}
