/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcClientes;
import com.siscogescorp.modelo.LcDatosDeudores;
import com.siscogescorp.modelo.LcDeudor;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.utils.Conexion;
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
public class DeudorServicios {
    
       public ArrayList<LcDeudor> getLcDeudor(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcDeudor> arreglo = new ArrayList<LcDeudor>();
        Query q = session.createQuery("from LcDeudor E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcDeudor> lista=q.list();
        Iterator<LcDeudor> iter=lista.iterator();
   
        //agrega los datos en la lista
        while(iter.hasNext())
        {
            LcDeudor rol= (LcDeudor) iter.next();
            arreglo.add(rol);
        }
              tx.commit();
        session.close();
        return arreglo;
    }
     
   public List<LcDeudor> getLcDeudores(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcDeudor E WHERE E.estado = :estado ");
        q.setParameter("estado","A");
        List<LcDeudor> lista=q.list();
        
         for(LcDeudor mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDeudor()+", "+mrol.getLcEmpresa().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDeudor()+", "+mrol.getLcClientes().getRazonSocial());
             
             
        }
          tx.commit();
        session.close();
         return lista;
    }    
   
   public List<LcDeudor> getLcDeudorxEmpresa(int empresa){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcDeudor E WHERE E.lcEmpresa.idEmpresa= :idEmpresa and E.estado = :estado ");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("estado","A");
        List<LcDeudor> lista=q.list();
       
         for(LcDeudor mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDeudor()+", "+mrol.getLcEmpresa().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDeudor()+", "+mrol.getLcClientes().getRazonSocial());
             
             
        }
           tx.commit();
        session.close();
         return lista;
    }
   
    public void addDeudor(LcDeudor deudor){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(deudor);
    tx.commit();
    session.close();
    }
   
   public void updateDeudor(int idDeudor, int idEmpresa, int idCliente, String nombreCartera, String observacion, String datosAdicional){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcDeudor agen = (LcDeudor)session.get(LcDeudor.class, idDeudor);
    agen.setLcEmpresa(new LcEmpresa(idEmpresa));
    agen.setLcClientes(new LcClientes(idCliente));
    agen.setNombreCartera(nombreCartera);
    agen.setObservacion(observacion);
    agen.setDatosAdicional(datosAdicional);
    tx.commit();
    session.close();
    }
   
    public void deleteDeudor(int id){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    LcDeudor agen = (LcDeudor)session.get(LcDeudor.class, id);
    agen.setEstado("I");
    session.update(agen);
    tx.commit();
    session.close();
    }
    
    //consulta datos por ID
    public List<LcDeudor> getDatosLCDeudorID(int IDDeudor){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcDeudor E WHERE E.idDeudor = :idDeudor ");
        q.setParameter("idDeudor",IDDeudor);
        List<LcDeudor> lista=q.list();
       
         for(LcDeudor mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDeudor()+", "+mrol.getLcEmpresa().getRazonSocial());
             System.out.println("ok: "+mrol.getIdDeudor()+", "+mrol.getLcClientes().getRazonSocial());
             
             
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
        int q = (int)session.createQuery("SELECT MAX(idDeudor) FROM LcDeudor").uniqueResult();
         tx.commit();
        session.close();
        
        return q;
    }
        public List<LcDatosDeudores> getDatosDeudorID(int IDDeudor){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcDatosDeudores E WHERE E.idDatosDeudor = :idDatosDeudor ");
        q.setParameter("idDatosDeudor",IDDeudor);
        List<LcDatosDeudores> lista=q.list();
       
         for(LcDatosDeudores mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDatosDeudor());
             System.out.println("ok: "+mrol.getNombresCompleto());
             
             
        }
         tx.commit();
        session.close();
         return lista;
    }  
     public String getDatosTransaccion(int idDeudor)throws SQLException
    {   
        Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String valor = "";
           try
            {
            String SQL="";
             
            SQL = "select \n" +
                "(select d.razon_social from lc_clientes d where d.id_cliente=14 and estado='A') razon_social, \n" +
                "(select nombres_completo from lc_datos_deudores where id_datos_deudor=d.id_deudor) nombres_completos,\n" +
                "(select identificacion from lc_datos_deudores where id_datos_deudor=d.id_deudor) identificacion,\n" +
                "(select  total_vencidos+interes_mora+interes_capital+interes_adicional+interes_otros deuda  from lc_transacciones where id_deudor=d.id_deudor and id_cliente=d.id_cliente and estado!='E' limit 1) total_Deuda,\n" +
                "(select  dias_mora  from lc_transacciones where id_deudor=d.id_deudor and id_cliente=d.id_cliente and estado!='E' limit 1) dias_mora,\n" +
                "(select valor_recaudado from lc_recaudaciones where id_deudor= d.id_deudor) abono,\n" +
                "(select persona_recibe from lc_documentos_generados  where id_deudor= d.id_deudor) persona_recibe,\n" +
                "(select identificacion_recibe from lc_documentos_generados  where id_deudor= d.id_deudor) ide_recibe,\n" +
                "(select to_char(fecha_recepcion,'YYYY-MM-DD hh24:mi:ss') from lc_documentos_generados  where id_deudor= d.id_deudor) fecha_recibe,\n" +
                "(d.ruta_documento)as ruta,\n" +
                "d.id_ticket, d.id_deudor\n" +
                "from lc_documentos_generados d\n" +
                "where d.id_deudor= "+idDeudor+"\n" +
                "and estado='A' ";
            
            String contenido = "";
            String razon_social, total_Deuda, dias_mora,persona_recibe,abono,ide_recibe,fecha_recibe,ruta;
            
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            int cont = 0;
            while( rs.next() )    //Mientras haya una sig. tupla
            {    
                razon_social= rs.getString("razon_social");
                total_Deuda = rs.getString("total_Deuda");
                dias_mora = rs.getString("dias_mora");
                abono = rs.getString("abono");
                persona_recibe = rs.getString("persona_recibe");
                ide_recibe = rs.getString("ide_recibe");
                fecha_recibe = rs.getString("fecha_recibe");
                ruta = rs.getString("ruta");
                if(abono==null){abono="";}
                if(ide_recibe==null){ide_recibe="";}
                if(fecha_recibe==null){fecha_recibe="";}
                if(persona_recibe==null){persona_recibe="";}
                
                contenido = contenido + "<tr id=\"fila_"+cont+"\">\n"
                + "<td class=\"text-center \"><input id=\"cedente_"+cont+"\" style=\"display:none\" value=\'"+razon_social+"\'>" + razon_social + "</td>\n"        
                + "<td class=\"text-center \"><input id=\"total_"+cont+"\" style=\"display:none\" value=\'"+total_Deuda+"\'>" + total_Deuda + "</td>\n"
                + "<td class=\"text-center\"><input id=\"diamora_"+cont+"\" style=\"display:none\" value=\'"+dias_mora+"\'>" + dias_mora + "</td>\n"
                + "<td class=\"text-center\"><input id=\"abono_"+cont+"\" style=\"display:none\" value=\'"+abono+"\'>"+abono+"</td>\n"
                + "<td class=\"text-center \"><input id=\"perecibe_"+cont+"\" style=\"display:none\" value=\'"+persona_recibe+"\'>" + persona_recibe + "</td>\n"
                + "<td class=\"text-center\"><input id=\"iderecibe_"+cont+"\" style=\"display:none\" value=\'"+ide_recibe+"\'>" + ide_recibe + "</td>\n"
                + "<td class=\"text-center\"><input id=\"fechrecibe_"+cont+"\" style=\"display:none\" value=\'"+fecha_recibe+"\'>"+fecha_recibe+"</td>\n"        
                + "<td class=\"text-center\"><a><span  onclick=\"EnviarRuta()\"  class=\"glyphicon glyphicon-eye-open\" aria-hidden=\"true\"></span></a></td></tr>";
             
               cont++;
            }
            
            //footer="<div class=\"col-lg-2\"><strong><input type=\"text\" value=\'"+suma+"\'></strong> </div></th> ";
            
            
                rs.close();
                pst.close();
                conexion.cierraConexion();
            valor = contenido;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
    }          
}
