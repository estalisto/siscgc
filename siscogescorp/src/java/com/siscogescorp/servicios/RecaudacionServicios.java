/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.servicios;

import com.siscogescorp.modelo.HibernateUtil;
import com.siscogescorp.modelo.LcCuotas;
import com.siscogescorp.modelo.LcDatosDeudores;
import com.siscogescorp.modelo.LcDetRecaudaciones;
import com.siscogescorp.modelo.LcFormapagoRecaudacion;
import com.siscogescorp.modelo.LcInstitucion;
import com.siscogescorp.modelo.LcRecaudaciones;
import com.siscogescorp.utils.Conexion;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * @author CIMA2015
 */
public class RecaudacionServicios {
    public List<LcDatosDeudores> getLcDatosDeudores(int empresa, int empleado,String ide,int cliente,int tipo){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        // [Cambios 20170709]
        Query q = session.createQuery("from LcDatosDeudores  E WHERE E.lcClientes.idCliente= :idCliente and E.tipoIdentificacion= :tipoIdentificacion and  E.identificacion= :identificacion and E.idEmpleado= :idEmpleado and E.lcEmpresa.idEmpresa= :idEmpresa and E.estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("idCliente",cliente);
        q.setParameter("idEmpleado",empleado);
        q.setParameter("identificacion",ide);
        q.setParameter("tipoIdentificacion",tipo);
        q.setParameter("estado","A");
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDatosDeudor());//+", "+mrol.getLcClientes().getIdCliente());
             System.out.println("ok: "+mrol.getIdDatosDeudor());//+", "+mrol.getLcClientes().getRazonSocial());
        }
        tx.commit();
        session.close();
         return lista;
    }
    
    public List<LcDatosDeudores> getLcDatosDeudoIDE(int empresa,int tipo,String ide){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcDatosDeudores  E WHERE  E.tipoIdentificacion= :tipoIdentificacion  and E.identificacion= :identificacion and E.lcEmpresa.idEmpresa= :idEmpresa and E.estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("identificacion",ide);
        q.setParameter("tipoIdentificacion",tipo);
        q.setParameter("estado","A");
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getNombres());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getApellidos());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getNombresCompleto());
        }
        tx.commit();
        session.close();
         return lista;
    }    
    
     public List<LcDatosDeudores> getLcDatosDeudorNombre(int empresa,String nombres){
        
       
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcDatosDeudores  E WHERE  E.nombresCompleto= :nombresCompleto and E.lcEmpresa.idEmpresa= :idEmpresa and E.estado= :estado");
        q.setParameter("idEmpresa",empresa);
        q.setParameter("nombresCompleto",nombres);
        q.setParameter("estado","A");
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getNombres());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getApellidos());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getIdentificacion());
             System.out.println("ok: "+mrol.getIdDatosDeudor()+", "+mrol.getTipoIdentificacion());
        }
        tx.commit();
        session.close();
         return lista;
    } 
    public ArrayList<LcDatosDeudores> getLcNombresDeudor(String nombre){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        ArrayList<LcDatosDeudores> arreglo = new ArrayList<LcDatosDeudores>();
        Query q = session.createQuery("from LcDatosDeudores E WHERE upper(E.nombresCompleto) like :nombresCompleto and E.estado = :estado ").setMaxResults(10);
        q.setParameter("nombresCompleto","%"+nombre.toUpperCase()+"%");
        q.setParameter("estado","A");
        List<LcDatosDeudores> lista=q.list();
        Iterator<LcDatosDeudores> iter=lista.iterator();

        while(iter.hasNext())
        {
            LcDatosDeudores rol= (LcDatosDeudores) iter.next();
            arreglo.add(rol);
        }
         tx.commit();
        session.close();
        return arreglo;
    }
    public List<String> getNombreAutocomplete(String nombre,int cartera)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            
           
            String SQL="select (e.identificacion)as identificacion,(e.nombres_completo)as nombre_completo from lc_datos_deudores e,lc_transacciones t WHERE e.nombres_completo like '%"+nombre+"%'and t.id_cliente="+cartera+"\n" +
                       "and e.estado = 'A' and e.id_datos_deudor=t.id_deudor limit 15";   
            
             
           String nombre_deudor,identificacion,concat;
           List<String> NombreDeudor = new ArrayList<String>();
           //List<String> IDEDeudor = new ArrayList<String>();
            try
            {
             pst = conexion.getconexion().prepareStatement(SQL);
             rs = pst.executeQuery();
            while( rs.next() )    //Mientras haya una sig. tupla
                {
                    nombre_deudor=rs.getString("nombre_completo");
                    identificacion=rs.getString("identificacion");
                    concat=identificacion+"|"+nombre_deudor;
                    NombreDeudor.add(concat);
                    //IDEDeudor.add(identificacion);
            }
                rs.close();
                pst.close();
                conexion.cierraConexion();
                return NombreDeudor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return NombreDeudor;
            
    }
    public List<LcCuotas> getIDCuotas(int idCuota){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcCuotas  E WHERE E.idCuota= :idCuota and  E.estado= :estado");       
        q.setParameter("idCuota",idCuota);
        q.setParameter("estado","A");
        List<LcCuotas> lista=q.list();
         for(LcCuotas mrol:lista )
        {
            
             System.out.println("ok: "+mrol.getIdCuota()+" "+ mrol.getLcArticulo().getReferencia()+" "+mrol.getInteresCuota()+""+mrol.getNumCuotas()+mrol.getGastosAdicional()+
                     mrol.getGastosCobranzas()+mrol.getInteresCuota()+mrol.getValorMora()+mrol.getOtrosValores()+mrol.getTotalCuotas()+mrol.getFechaMaxPago()+mrol.getValorCuota());
             
        }
          tx.commit();
        session.close();

         return lista;
    }
    public void addRecaudacion(LcRecaudaciones recaudacion){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(recaudacion);
    tx.commit();
    session.close();
    }    
    
    public Long getNext() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('lc_recaudaciones_id_recaudacion_seq')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
    return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }    
    /*Codigo 003: Inicio cambio */
    /*Desarrollador: Jimmy Guaranda*/
    /*Objetivo: EnMetodo para obtener la secuencia configurable */
     public Long getNextSeq() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('seq_comprobante_pago')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
    return key; 
    }  
     public void addDetRecaudacion(LcDetRecaudaciones Detrecaudacion){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(Detrecaudacion);
    tx.commit();
    session.close();
    }    
    
    public Long getNext2() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('lc_det_recaudaciones_id_det_recaudacion_seq')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
    return key; // return ((BigInteger) query.uniqueResult()).longValue();
    }   
     public void addFormaPago(LcFormapagoRecaudacion formaPago){
    SessionFactory factory=HibernateUtil.getSessionFactory();
    Session session= factory.openSession();
    Transaction tx=session.beginTransaction();
    session.save(formaPago);
    tx.commit();
    session.close();
    }    
    
    public Long getNext3() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query query = session.createSQLQuery( "select nextval('lc_formapago_recaudacion_id_formpago_seq')" );
        Long key = ((BigInteger) query.uniqueResult()).longValue();   
        tx.commit();  
        session.close();
    return key; 
    }   
        public List<LcInstitucion> getLcInstituciones(){
         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        // hacemos la transaccion
        Query q = session.createQuery("from LcInstitucion  E WHERE E.estado= :estado");

        q.setParameter("estado","A");
        List<LcInstitucion> lista=q.list();
         for(LcInstitucion mrol:lista )
        {
             System.out.println("ok: "+mrol.getIdInstitucion()+", "+mrol.getNombreInstitucion());
             
        }
        tx.commit();
        session.close();
         return lista;
    }
     /*Codigo 006: Inicio cambio */
    /*Desarrollador: Jimmy Guaranda*/
    /*Objetivo: Metodo qcreado para obtener el valor del pago.*/ 
     public BigDecimal getValorRecaudado(int IdEmpleado,int IdCliente,  int idDeudor)throws SQLException{
        Conexion conexion=new Conexion();
        PreparedStatement pst;
        ResultSet rs;
        BigDecimal valorRecaudado=BigDecimal.ZERO;
        BigDecimal valornulo=BigDecimal.ZERO;
        pst = conexion.getconexion().prepareCall("SELECT fnc_valor_recaudado("+idDeudor+","+IdCliente+","+IdEmpleado+");");   
       try
            {
        rs = pst.executeQuery(); 
        while( rs.next() )    //Mientras haya una sig. tupla
            { 
            valorRecaudado= new BigDecimal(rs.getString("fnc_valor_recaudado"));
                if(valorRecaudado == valornulo){
                valorRecaudado = valornulo;
            } 
        }
        rs.close();    
        pst.close();
        conexion.cierraConexion();
        }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valorRecaudado;
    }   
    /*Codigo 006: Fin cambio */
    
     public BigDecimal getValorRecaudado2(int IdEmpleado,int IdCliente,  int idDeudor)throws SQLException{
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
          //  BigDecimal valor;   
            String SQL="";               
            SQL="select r.valor_recaudado from lc_recaudaciones r where r.id_deudor="+idDeudor+" "
                    + "and r.id_cliente="+IdCliente+" and r.id_empresa="+IdEmpleado+"and r.estado='A'";     
            BigDecimal valorRecaudado=BigDecimal.ZERO;

            try
            {
           
            pst = conexion.getconexion().prepareStatement(SQL);
                rs = pst.executeQuery();
            
            while( rs.next() )    //Mientras haya una sig. tupla
            { 
                valorRecaudado= new BigDecimal(rs.getString("valor_recaudado")) ;     
 
            }
            
                rs.close();
                pst.close();
                conexion.cierraConexion();
                // valor=valorRecaudado;
                return valorRecaudado;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valorRecaudado;
            
    }
        public String NuevaBusquedaDeudor(String nombre,int cartera)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            
           
            String SQL="select (t.identificacion)as identificacion,(d.nombres_completo)as nombre_completo,(r.nombre_cartera)as cartera,(c.razon_social)as cliente,\n" +
                "(t.id_cliente)as id_cliente,(d.tipo_identificacion)as id_identificacion,(d.id_datos_deudor)as id_deudor from lc_transacciones t, lc_datos_deudores d,lc_clientes c,lc_cartera r\n" +
                "where t.id_deudor = d.id_datos_deudor and c.id_cliente=t.id_cliente\n" +
                "and t.id_cliente="+cartera+" and r.id_cartera=t.id_cartera and d.nombres_completo like '%"+nombre+"%'";   
            
             
           String nombre_deudor,identificacion,carteras,cliente,contenidoTabla = "",valor="";
           int id_cliente,id_identificacion,id_deudor,cont=0;
            try
            {
             pst = conexion.getconexion().prepareStatement(SQL);
             rs = pst.executeQuery();
            while( rs.next() )    //Mientras haya una sig. tupla
                {
                    identificacion=rs.getString("identificacion");
                    nombre_deudor=rs.getString("nombre_completo");
                    carteras=rs.getString("cartera");
                    cliente=rs.getString("cliente");
                    id_cliente=rs.getInt("id_cliente");
                    id_deudor=rs.getInt("id_deudor");
                    id_identificacion=rs.getInt("id_identificacion");
                    contenidoTabla=contenidoTabla+"<tr><td><input style=\"display:none\" id=\"IDentify_"+cont+"\" value=\'"+identificacion+"\'>"+identificacion+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"NomDeudor_"+cont+"\" value=\'"+nombre_deudor+"\'>"+nombre_deudor+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"TipCartera_"+cont+"\" value=\'"+carteras+"\'>"+carteras+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"ClientCartera_"+cont+"\" value=\'"+cliente+"\'>"+cliente+"</td>\n" +
                                                    "<td class=\"hidden\"><input id=\"idCliente_"+cont+"\" value=\'"+id_cliente+"\'></td>\n" +
                                                    "<td class=\"hidden\"><input id=\"TipIde_"+cont+"\" value=\'"+id_identificacion+"\'></td>\n" +
                                                    "<td class=\"hidden\"><input id=\"IdeDeudor_"+cont+"\" value=\'"+id_deudor+"\'></td>\n" +
                                                    "<td><button id=\"consultar\" type=\"button\" onclick=\"ConsultaDeudor("+cont+")\" class=\"btn btn-primary btn-lg fa fa-search\" data-dismiss=\"modal\">Consultar</button></td></tr>";
                    cont++;
            }
                rs.close();
                pst.close();
                conexion.cierraConexion();
                valor=contenidoTabla;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
            
    }        
        public String NuevaBusquedaDeudorIDE(String identifica,int cartera)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            
           
            String SQL="select (t.identificacion)as identificacion,(d.nombres_completo)as nombre_completo,(r.nombre_cartera)as cartera,(c.razon_social)as cliente,\n" +
                "(t.id_cliente)as id_cliente,(d.tipo_identificacion)as id_identificacion,(d.id_datos_deudor)as id_deudor from lc_transacciones t, lc_datos_deudores d,lc_clientes c,lc_cartera r\n" +
                "where t.id_deudor = d.id_datos_deudor and c.id_cliente=t.id_cliente\n" +
                "and t.id_cliente="+cartera+" and r.id_cartera=t.id_cartera and t.identificacion='"+identifica+"'";   
            
             
           String nombre_deudor,identificacion,carteras,cliente,contenidoTabla = "",valor="";
           int id_cliente,id_identificacion,id_deudor,cont=0;
            try
            {
             pst = conexion.getconexion().prepareStatement(SQL);
             rs = pst.executeQuery();
            while( rs.next() )    //Mientras haya una sig. tupla
                {
                    identificacion=rs.getString("identificacion");
                    nombre_deudor=rs.getString("nombre_completo");
                    carteras=rs.getString("cartera");
                    cliente=rs.getString("cliente");
                    id_cliente=rs.getInt("id_cliente");
                    id_deudor=rs.getInt("id_deudor");
                    id_identificacion=rs.getInt("id_identificacion");
                    contenidoTabla=contenidoTabla+"<tr><td><input style=\"display:none\" id=\"IDentify_"+cont+"\" value=\'"+identificacion+"\'>"+identificacion+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"NomDeudor_"+cont+"\" value=\'"+nombre_deudor+"\'>"+nombre_deudor+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"TipCartera_"+cont+"\" value=\'"+carteras+"\'>"+carteras+"</td>\n" +
                                                    "<td><input style=\"display:none\" id=\"ClientCartera_"+cont+"\" value=\'"+cliente+"\'>"+cliente+"</td>\n" +
                                                    "<td class=\"hidden\"><input id=\"idCliente_"+cont+"\" value=\'"+id_cliente+"\'></td>\n" +
                                                    "<td class=\"hidden\"><input id=\"TipIde_"+cont+"\" value=\'"+id_identificacion+"\'></td>\n" +
                                                    "<td class=\"hidden\"><input id=\"IdeDeudor_"+cont+"\" value=\'"+id_deudor+"\'></td>\n" +
                                                    "<td><button id=\"consultar\" type=\"button\" onclick=\"ConsultaDeudor("+cont+")\" class=\"btn btn-primary btn-sm fa fa-search\">Consultar</button>"+
                                                    "<button id=\"consultar\" type=\"button\" onclick=\"GestionCliente("+id_cliente+","+id_deudor+");\" class=\"btn btn-primary btn-sm fa fa-search\">Gestión</button></td></tr>";
                    cont++;
            }
                rs.close();
                pst.close();
                conexion.cierraConexion();
                valor=contenidoTabla;
                return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
                 }
        return valor;
            
    }   
        
        
  public List<LcRecaudaciones> getDatosRecaudacion(int idRecaudacion){         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcRecaudaciones  E WHERE E.idRecaudacion= :idRecaudacion and  E.estado= :estado");       
        q.setParameter("idRecaudacion",idRecaudacion);
        q.setParameter("estado","A");
        List<LcRecaudaciones> lista=q.list();
         for(LcRecaudaciones datos:lista )
        {            
             System.out.print("ok: "+datos.getIdRecaudacion()+datos.getLcDatosDeudores().getIdentificacion().toString()+datos.getLcDatosDeudores().getNombres()+" "+datos.getLcDatosDeudores().getApellidos());
        }
        tx.commit();
        session.close();
         return lista;
    }
    public List<LcDatosDeudores> getDatosDeudor(String identificacion){         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcDatosDeudores  E WHERE E.identificacion= :identificacion and  E.estado= :estado");       
        q.setParameter("identificacion",identificacion);
        q.setParameter("estado","A");
        List<LcDatosDeudores> lista=q.list();
         for(LcDatosDeudores datos:lista )
        {            
             System.out.print("LcDatosDeudores: "+datos.getIdDatosDeudor());
        }
        tx.commit();
        session.close();
         return lista;
    }
    /*INICIO CAMBIO J.J.G.C*/
    /*METODO QUE OBTIENE LA CANTIDAD TOTAL DE LA RECAUDCION PARA PODER TRANSFORMARLA EN LETRA EN EL REPORTE*/
    public String ObtenerCantidad(int idRecaudacion)throws SQLException{
    
            Conexion conexion=new Conexion();
            PreparedStatement pst;
            ResultSet rs;
            String SQL="select ROUND(sum(r.valor_recaudado),2) as suma from lc_det_recaudaciones r \n" +
                "where r.id_recaudacion= "+idRecaudacion;   
           String suma = null,valor = null;
         
            try
            {
            pst = conexion.getconexion().prepareStatement(SQL);
            rs = pst.executeQuery();
            while( rs.next() )    //Mientras haya una sig. tupla
            { 
            suma=rs.getString("suma");
            }
            rs.close();
            pst.close();
            conexion.cierraConexion();
            valor=suma;
            return valor;
            }catch(Exception ex){
            }finally{
                    if(conexion!=null)
                    conexion.cierraConexion();
            }
        return valor;
    }
    /*OBTENEMOS LOS DATOS DE LA TABLA DE FORMA DE PAGO DATOS COMO: NUMERO CHEQUE,TARJETA Y ID DE BANCO*/   
    public List<LcFormapagoRecaudacion> ObtenerTipPago(int idRecaudacion){         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcFormapagoRecaudacion  E WHERE E.lcRecaudaciones.idRecaudacion= :idRecaudacion and  E.estado= :estado");       
        q.setParameter("idRecaudacion",idRecaudacion);
        q.setParameter("estado","A");
        List<LcFormapagoRecaudacion> lista=q.list();
         for(LcFormapagoRecaudacion datos:lista )
        {            
             System.out.print("LcFormapagoRecaudacion: "+datos.getLcTipoFormaPago().getIdTipoFp());
        }
        tx.commit();
        session.close();
         return lista;
    } 
    /*OBTENEMOS LOS DATOS DE LA TABLA DE INSTITUCION DATO COMO EL NOMBRE DE LA INSTITUCION*/   
    public List<LcInstitucion> ObtenerInstitucion(int id){         
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx= session.beginTransaction();
        Query q = session.createQuery("from LcInstitucion  E WHERE E.idInstitucion= :idInstitucion and  E.estado= :estado");       
        q.setParameter("idInstitucion",id);
        q.setParameter("estado","A");
        List<LcInstitucion> lista=q.list();
         for(LcInstitucion datos:lista )
        {            
             System.out.print("LcFormapagoRecaudacion: "+datos.getNombreInstitucion());
        }
        tx.commit();
        session.close();
         return lista;
    }
    /*FIN CAMBIO J.J.G.C*/
}
