/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcRoles;
import com.siscogescorp.modelo.LcUsuarios;
import com.siscogescorp.utils.CifradoOK;
import com.siscogescorp.utils.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author CIMA2015
 */
public class ValidaUsuario {
    
    ArrayList<LcUsuarios> usuarioOK;
    List<LcUsuarios> usuarioOK2;
    

    ArrayList<LcRoles> rolesOk;
    ArrayList<LcEmpresa>empresaOk;
    public ArrayList<LcUsuarios> getLcValidaUser(String user ,String pass, int empresaID){
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("from LcUsuarios E WHERE E.usuario= :usuario and E.lcEmpresa.idEmpresa= :empresa and E.contrasenia= :contrasenia  and E.estado = :estado ");
        q.setParameter("usuario",user);
        q.setParameter("contrasenia",pass);
        q.setParameter("empresa",empresaID);
        q.setParameter("estado","A");
        List<LcUsuarios> lista=q.list();
        Iterator<LcUsuarios> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcUsuarios rol= (LcUsuarios) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
    public List<LcUsuarios> getLcValidaUser2(String user ,String pass, int empresaID){
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();

        Query q = session.createQuery("from LcUsuarios E WHERE E.usuario= :usuario and E.lcEmpresa.idEmpresa= :empresa and E.contrasenia= :contrasenia  and E.estado = :estado ");
        q.setParameter("usuario",user);
        q.setParameter("contrasenia",pass);
        q.setParameter("empresa",empresaID);
        q.setParameter("estado","A");
        List<LcUsuarios> lista=q.list();
        
         for(LcUsuarios mrol:lista )
        {
              System.out.println("user1: "+mrol.getIdUsuario()+", Apellidos "+mrol.getLcEmpleados().getApellidos());
             System.out.println("user2: "+mrol.getLcEmpleados().getNombres()+", Id: "+mrol.getLcEmpleados().getIdEmpleado()+" Rol: "+mrol.getLcRoles().getDescripcion()+"Empresa"+mrol.getLcEmpresa().getRazonSocial()+mrol.getLcEmpleados().getLcGenero().getDescripcion());
        }
        tx.commit();
        session.close();
        return lista;
    }
    
    
    public List<LcUsuarios> getLcValidaUserEx(String user ,String pass, int empresaID){
        
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcUsuarios E WHERE E.usuario= :usuario and E.lcEmpresa.idEmpresa= :empresa and E.contrasenia= :contrasenia  and E.estado = :estado ");
        q.setParameter("usuario",user);
        q.setParameter("contrasenia",pass);
        q.setParameter("empresa",empresaID);
        q.setParameter("estado","E");
        List<LcUsuarios> lista=q.list();        
         for(LcUsuarios mrol:lista )
        {
              System.out.println("user1: "+mrol.getIdUsuario()+", Apellidos "+mrol.getLcEmpleados().getApellidos());
             System.out.println("user2: "+mrol.getLcEmpleados().getNombres()+", Id: "+mrol.getLcEmpleados().getIdEmpleado()+" Rol: "+mrol.getLcRoles().getDescripcion()+"Empresa"+mrol.getLcEmpresa().getRazonSocial());
        }
        tx.commit();
        session.close();
        return lista;
    }
    public ArrayList<LcRoles> getValidaRol(int strUsuarioRol){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcRoles> arreglo = new ArrayList<LcRoles>();
        Query q = session.createQuery("from LcRoles E WHERE E.idRol= :idRol ");
        q.setParameter("idRol",strUsuarioRol);
        //q.setParameter("descripcion",user);
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
        
    public ArrayList<LcEmpresa> getValidaEmpresa(String strempresa){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcEmpresa> arreglo = new ArrayList<LcEmpresa>();
        Query q = session.createQuery("from LcEmpresa E WHERE E.razonSocial= :razonSocial ");
        q.setParameter("razonSocial",strempresa);
        //q.setParameter("descripcion",user);
        List<LcEmpresa> lista=q.list();
        Iterator<LcEmpresa> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcEmpresa rol= (LcEmpresa) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }  
      
    public boolean getValidaUsuario(String user, String pass, int empresasID){
         String mensagemUsuario=null;
         String encript=DigestUtils.sha1Hex(pass);
         
         usuarioOK=getLcValidaUser(user,encript,empresasID );
           //  String encript=DigestUtils.sha1Hex(user);
        System.out.println("shaHex:"+DigestUtils.sha1Hex(pass));
      
         if (!usuarioOK.isEmpty() && usuarioOK.size()<= 1 ){
             int identificador =1;
             return !usuarioOK.isEmpty();  
             
            }else{
             int identificador =2;
                usuarioOK2=getLcValidaUserEx(user,encript,empresasID );
                return !usuarioOK2.isEmpty();     
            }
         
        }
    
    public ArrayList<LcUsuarios> getLcValidacion(String user ,String pass,int strempresa, int strUsuarioRol){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcUsuarios> arreglo = new ArrayList<LcUsuarios>();
        Query q = session.createQuery("from LcUsuarios E WHERE E.usuario= :usuario and E.contrasenia= :contrasenia "
                + "and E.idEmpresa= :idEmpresa and E.idRol= :idRol and E.estado = :estado ");
        q.setParameter("usuario",user);
        q.setParameter("contrasenia",pass);
        q.setParameter("idEmpresa",strempresa);
        q.setParameter("idRol",strUsuarioRol);
        q.setParameter("estado","A");
        List<LcUsuarios> lista=q.list();
        Iterator<LcUsuarios> iter=lista.iterator();
        tx.commit();
        session.close();
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcUsuarios rol= (LcUsuarios) iter.next();
            arreglo.add(rol);
        }
        
        return arreglo;
    }
    
    public boolean IsRoot(String users, String passwd)throws SQLException{
    
            Conexion conexion=new Conexion();
            String encript=DigestUtils.sha1Hex(passwd);
            PreparedStatement pst;
            ResultSet rs;
            boolean existe = false;
            try
            {
            String SQL="select case when count(1)>0 then  true else false end  ok from lc_usuarios u, lc_roles r  where u.id_rol=r.id_rol  and usuario='"+users+"' and contrasenia='"+encript+"' and nivel=0";    
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
                {
                existe= rs.getBoolean("ok");
                //System.out.println("ok");
                }
                rs.close();
                pst.close();
                conexion.cierraConexion();
                return existe;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return false;
            
    }
    public void GuardarAudSesiones(int IDusuario,String Ip,String hostname,String descripcion) throws SQLException{
             Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
        pst = conexion.getconexion().prepareCall("SELECT fnc_aud_sesion("+IDusuario+",'"+Ip+"','"+hostname+"','"+descripcion+"');");   
        //connBD.createStatement();
        rs = pst.executeQuery();       
        rs.close();    
        pst.close();
        conexion.cierraConexion();
    }
}
