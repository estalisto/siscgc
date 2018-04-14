/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcEmpleados;
import com.siscogescorp.modelo.LcUsuarios;
import com.siscogescorp.servicios.EmpleadosServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.ParametrosServicios;
import com.siscogescorp.servicios.UsuariosServicios;
import com.siscogescorp.servicios.ValidaUsuario;
import com.siscogescorp.utils.ArchivoLog;
import java.io.IOException;
import java.net.InetAddress;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author CIMA2015
 */
public class AutorizaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        
        UsuariosServicios us = new UsuariosServicios();
        ValidaUsuario vs=new ValidaUsuario();
        EmpresaServicios es =new EmpresaServicios();
        ParametrosServicios param = new ParametrosServicios();
        EmpleadosServicios empl = new EmpleadosServicios();
        ArchivoLog grb = new ArchivoLog();
        String accion, user, pass;
        int empresas;
        int rol=0;
        int empresa=0;
        
        boolean autorizaOK=false;
               
        accion= request.getParameter("accion");
        
        if(accion.equals("autorizar"))
        {
            try{
            user= request.getParameter("usuario");
        pass= request.getParameter("password");
        if("0".equals(request.getParameter("empresa"))){

           if(vs.IsRoot(user, pass)){
            
             if(!param.getValorParametro("DEFAULTPYMES").isEmpty()){
                empresa=Integer.parseInt(param.getValorParametro("DEFAULTPYMES"));
                autorizaOK=vs.getValidaUsuario(user,pass,empresa); 
             }else{
                  response.getWriter().println("Problemas en la Configuración de Accesos...\ncomunicarse con el Departamento de Sistemas"); 
                  return;
             }
            }
        
        }else{ 
            empresa= Integer.parseInt(request.getParameter("empresa"));
            autorizaOK=vs.getValidaUsuario(user,pass,empresa); 
        }
       
            
        
        
         if (!autorizaOK){  
             response.getWriter().println("Usuario no Autorizado."); 
             // response.sendRedirect("/laticobsa/login");
              
         }else{
                String encript=DigestUtils.sha1Hex(pass);
                List<LcUsuarios> autoriza = vs.getLcValidaUser2(user, encript,empresa);
                if(!autoriza.isEmpty()){
                LcUsuarios dato = autoriza.get(0);
                session.setAttribute("SstrNivelUser",dato.getLcRoles().getNivel());
                session.setAttribute("SstrUsuarioRol",dato.getLcRoles().getIdRol());
                session.setAttribute("SstrIdEmpleado",dato.getLcEmpleados().getIdEmpleado());
                session.setAttribute("SstrNombresEmpleado",dato.getLcEmpleados().getNombres());
                session.setAttribute("SstrApellidosEmpleado",dato.getLcEmpleados().getApellidos());
                session.setAttribute("SstrSexo",dato.getLcEmpleados().getLcGenero().getDescripcion());
                session.setAttribute("SstrRolEmpleado",dato.getLcRoles().getDescripcion());
                session.setAttribute("SstrRolID",dato.getLcRoles().getIdRol());
                session.setAttribute("SstrNomEmpresa", dato.getLcEmpresa().getRazonSocial());
                session.setAttribute("NivelAccesoRolID",dato.getLcRoles().getNivel());
                session.setAttribute("SstrUSER",user);    
                session.setAttribute("Sstrempresa", empresa);
                session.setAttribute("UsuarioID",dato.getIdUsuario());
                session.setAttribute("NombreID",dato.getUsuario());
                int id_usuario = dato.getLcEmpleados().getIdEmpleado();
                int IDusuario = dato.getIdUsuario();
                List<LcEmpleados> consulta = empl.getEmpleadoxconsulta(empresa,id_usuario);
                LcEmpleados dan = consulta.get(0);
                session.setAttribute("Sstrsucursal", dan.getLcAgencia().getIdAgencia());
                session.setAttribute("Sstrempleado", dan.getIdEmpleado());
                //response.sendRedirect("/laticobsa/home");
                //response.getWriter().println("Bienvenido a Laticobsa."); 
                
                //Guardando en la tabla de Auditoria de sesiones
                InetAddress addr = InetAddress.getLocalHost();
                String hostname = addr.getHostName();
                String Ip = addr.getHostAddress();
                System.out.println("Host: " + hostname);
                System.out.println("IP22: " + addr.getAddress());
                Date fecha_reg = new Date();
                //int Id_AudSesion = Integer.parseInt(vs.getNext().toString());
                String descripcion=  "Sesion Iniciada";
                 vs.GuardarAudSesiones(IDusuario,Ip,hostname,descripcion);
                
                }else{

                String encript2=DigestUtils.sha1Hex(pass);
                List<LcUsuarios> autoriza2 = vs.getLcValidaUserEx(user, encript2,empresa);//.getLcAutorizaUser(user,pass);
                LcUsuarios dato2 = autoriza2.get(0);
                Date expiro = dato2.getExpiracionTmp();
                Date hoy = new Date();
                long horaexo = expiro.getTime();
                long horaactual= hoy.getTime();
                System.out.println(horaexo);
                System.out.println(horaactual);
                if(horaactual>horaexo){
                    response.getWriter().println("Cuenta de Usuario Expirada.");
                    //response.sendRedirect("/laticobsa/login");
                }else{
                session.setAttribute("SstrNivelUser",dato2.getLcRoles().getNivel());    
                session.setAttribute("SstrUsuarioRol",dato2.getLcRoles().getIdRol());
                session.setAttribute("SstrIdEmpleado",dato2.getLcEmpleados().getIdEmpleado());
                session.setAttribute("SstrNomEmpresa", dato2.getLcEmpresa().getRazonSocial());
                session.setAttribute("SstrNombresEmpleado",dato2.getLcEmpleados().getNombres());
                session.setAttribute("SstrApellidosEmpleado",dato2.getLcEmpleados().getApellidos());
                session.setAttribute("SstrSexo",dato2.getLcEmpleados().getLcGenero().getDescripcion());
                session.setAttribute("SstrRolEmpleado",dato2.getLcRoles().getDescripcion());
                session.setAttribute("SstrUSER",user);    
                session.setAttribute("Sstrempresa", empresa);
                session.setAttribute("SstrRolID",dato2.getLcRoles().getIdRol());
                session.setAttribute("NivelAccesoRolID",dato2.getLcRoles().getNivel());
                session.setAttribute("UsuarioID",dato2.getIdUsuario());
                int id_usuario = dato2.getLcEmpleados().getIdEmpleado();
                int IDusuario = dato2.getIdUsuario();
                List<LcEmpleados> consulta = empl.getEmpleadoxconsulta(empresa,id_usuario);
                if(consulta.size()>0){
                    LcEmpleados dan = consulta.get(0);                
                session.setAttribute("Sstrsucursal", dan.getLcAgencia().getIdAgencia());
                session.setAttribute("Sstrempleado", dan.getIdEmpleado());
                InetAddress addr = InetAddress.getLocalHost();
                String hostname = addr.getHostName();
                String Ip = addr.getHostAddress();
                System.out.println("Host: " + hostname);
                System.out.println("IP: " + addr.getHostAddress());
                System.out.println("IP22: " + addr.getAddress());
                Date fecha_reg = new Date();
               // int Id_AudSesion = Integer.parseInt(vs.getNext().toString());
                String descripcion=  "Sesion Iniciada.Pero se debe Cambiar Contraseña";
                 vs.GuardarAudSesiones(IDusuario,Ip,hostname,descripcion);
               
                //response.sendRedirect("/laticobsa/home");
                 //response.getWriter().println("Bienvenido a Laticobsa."); 
                }else{
                       response.getWriter().println("Problemas para acceder al sistema.. comunicarse con Depto. Sistemas");
                       
                
                }
                
                
                }
                
                
                }
            }
          } catch (Exception ex) {
                //try{grb.grabaLog("AutorizaController_Autorizar Usuarios  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(AutorizaController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
            if (accion.equals("outlogin")){
               try{
                HttpSession sesion = request.getSession(true);  
               String id_usuario = sesion.getAttribute("UsuarioID").toString();
               int UserID = Integer.parseInt(id_usuario);
              session.invalidate();
              InetAddress addr = InetAddress.getLocalHost();
                String hostname = addr.getHostName();
                String Ip = addr.getHostAddress();
                System.out.println("Host: " + hostname);
                System.out.println("IP: " + addr.getHostAddress());
                System.out.println("IP22: " + addr.isSiteLocalAddress());
                Date fecha_reg = new Date();
               // int Id_AudSesion = Integer.parseInt(vs.getNext().toString());
                String descripcion=  "Sesion Finalizada";
                vs.GuardarAudSesiones(UserID,Ip,hostname,descripcion);
               
             response.sendRedirect("/siscogescorp/login");
             
             } catch (Exception ex) {
                //try{grb.grabaLog("AutorizaController_outlogin Usuarios  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(AutorizaController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        if (accion.equals("recarga")){
            request.getRequestDispatcher("/siscogescorp/login").forward(request, response);
           //response.sendRedirect("/laticobsa/home");
 
        }
        if(accion.equals("registra_gestion")){
            int    IdDeudor=Integer.parseInt(request.getParameter("IdDeudor"));
            int    IdClienteCartera=Integer.parseInt(request.getParameter("IdClienteCartera"));
            
            session.setAttribute("SstrIdDeudor",IdDeudor);    
            session.setAttribute("SstrIdClienteCartera", IdClienteCartera);
             response.getWriter().println("ok");
        }
         if(accion.equals("elimina_registro_gestion")){
                     
            session.setAttribute("SstrIdDeudor","");    
            session.setAttribute("SstrIdClienteCartera","");
            response.getWriter().println("ok");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
            processRequest(request, response);
      
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
            processRequest(request, response);
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
