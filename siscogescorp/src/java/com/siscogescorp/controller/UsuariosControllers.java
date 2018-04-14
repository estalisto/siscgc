/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcEmpleados;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcRoles;
import com.siscogescorp.modelo.LcUsuarios;
import com.siscogescorp.servicios.CargosServicios;
import com.siscogescorp.servicios.EmpleadosServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.RolesOperaciones;
import com.siscogescorp.servicios.UsuariosServicios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class UsuariosControllers extends HttpServlet {

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
        UsuariosServicios us = new UsuariosServicios();
        EmpleadosServicios emp = new EmpleadosServicios();
        RolesOperaciones ro = new RolesOperaciones();
        EmpresaServicios es = new EmpresaServicios();
        CargosServicios cs = new CargosServicios();
        PrintWriter out = response.getWriter();
        String accion;
        accion = request.getParameter("accion");
        String id_empresas;
        HttpSession sesion = request.getSession(true);
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID = Integer.parseInt(id_empresas);
        String id_rol;
        id_rol = sesion.getAttribute("SstrRolID").toString();
        int RolID = Integer.parseInt(id_rol);
        String nivel;
        nivel = sesion.getAttribute("NivelAccesoRolID").toString();
        int NivelID = Integer.parseInt(nivel);

        if (accion.equals("listar")) {
            if (NivelID == 0) {
                List<LcUsuarios> usuarios = us.getLcUsuarioss();
                request.setAttribute("usuarios", usuarios);
            }
            if (NivelID == 1) {
                List<LcUsuarios> usuarios = us.getUsuarioxempresa(EmpresaID);
                request.setAttribute("usuarios", usuarios);
            }
            if ((NivelID != 0) && (NivelID != 1)) {
                List<LcUsuarios> usuarios = us.getUsuarioxempresa(EmpresaID);
                request.setAttribute("usuarios", usuarios);
            }

            request.getRequestDispatcher("sistema/usuarios/lista_usuarios.jsp").forward(request, response);
            //request.getContextPath()+
        }

        if (accion.equals("agregar")) {

            if (NivelID == 0) {
                ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
                request.setAttribute("empresas", empresas);

                ArrayList<LcRoles> roles = ro.getLCRoles();
                request.setAttribute("roles", roles);
            }
            if (NivelID == 1) {
                List<LcRoles> empresaso = es.getLcEmpresaRol(EmpresaID, RolID);
                int Empresa = empresaso.get(0).getLcEmpresa().getIdEmpresa();
                ArrayList<LcEmpresa> empresao = es.getLcEmpresalog(Empresa);
                request.setAttribute("empresao", empresao);
                List<LcRoles> roles = ro.getLCRolesxEmpresa(EmpresaID);
                request.setAttribute("roles", roles);
            }
            if ((NivelID != 0) && (NivelID != 1)) {
                List<LcRoles> empresaso = es.getLcEmpresaRol(EmpresaID, RolID);
                int Empresa = empresaso.get(0).getLcEmpresa().getIdEmpresa();
                ArrayList<LcEmpresa> empresao = es.getLcEmpresalog(Empresa);
                request.setAttribute("empresao", empresao);
                List<LcRoles> roles = ro.getLCRolesxEmpresa(EmpresaID);
                request.setAttribute("roles", roles);
            }

            ArrayList<LcUsuarios> usuarios = us.getLcUsuarios();
            request.setAttribute("usuarios", usuarios);

            request.getRequestDispatcher("sistema/usuarios/frm_usuarios.jsp").forward(request, response);
            //request.getContextPath()+
        }
        if (accion.equals("cambio_clave")) {
            ArrayList<LcUsuarios> usuarios = us.getLcUsuarios();
            request.setAttribute("usuarios", usuarios);

            request.getRequestDispatcher("sistema/usuarios/frm_cambio_clave.jsp").forward(request, response);
            //request.getContextPath()+
        }
        if (accion.equals("recupera_clave")) {
            ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
            request.setAttribute("empresas", empresas);
            request.getRequestDispatcher("sistema/usuarios/frm_recuperar_clave.jsp").forward(request, response);
            //request.getContextPath()
        }
        if (accion.equals("recuperar")) {
            String nombre = request.getParameter("nombre");

            ArrayList<LcUsuarios> userio = us.getUserempresa(nombre, EmpresaID);
            if (!userio.isEmpty()) {
                LcUsuarios activo = userio.get(0);
                String user = activo.getEstado();
                if (user.equals("A")) {
                    response.getWriter().println("Usuario existente");
                } else if (user.equals("E")) {

                    response.getWriter().println("Usuario con contraseña temporal. Favor de cambiar la contraseña");
                }
            }
        }

        if (accion.equals("validar")) {
            int empresa_nom = 0;
            int existe=0;
            String ide = request.getParameter("identificacion");
            LcEmpleados dato = null;
             if (NivelID==0){
                   List<LcEmpleados> empleado = emp.ValidaLCEmpleado(ide); 
                    existe=empleado.size();
                    if(empleado.size()>0){
                     dato = empleado.get(0);
                    }
                   
             }else{
                 List<LcEmpleados> empleado = emp.ValidaLCEmpleadoss(ide,EmpresaID);  
                 existe=empleado.size();
                  if(empleado.size()>0){
                     dato = empleado.get(0);
                    }
             }          
            
             if(existe == 0){
                  response.getWriter().println("1");
             // out.println("1");
                  /*
                  1 identificacion no se encuentra asociada a ningun empleado
                  2 Existen mas de un empleado con la misma identificación
                  3 Identificacion ya se encuentra asociada a un USUARIO
                  4 Listo para crear el usuario
                  */
              //no existe empleado
              return;
             }else if(existe>1){
                //out.println("2");
                 response.getWriter().println("2");
                // probelmas ya existe más de un empleado para asignar este usuario
              return;  
             }else if(existe==1){
                  empresa_nom = dato.getLcEmpresa().getIdEmpresa();
                     int id_empleado = dato.getIdEmpleado();
                     ArrayList<LcUsuarios> user = us.getDatoFind(id_empleado, empresa_nom);
                     ArrayList<LcUsuarios> userExp = us.getDatoFindExp(id_empleado, empresa_nom);
                    if (!user.isEmpty()) {
                        // out.println("3");
                          response.getWriter().println("3");
                          
                    }else if (!userExp.isEmpty()) {
                            response.getWriter().println("5");
                    }else{
                       // out.println("3");
                         response.getWriter().println("4");
                    }
                    
                        
                  
             }
             
           
        }

        if (accion.equals("cambio")) {

            String nombre = request.getParameter("nombre");
            List<LcUsuarios> usuarios = us.getUsuariocambio(EmpresaID, nombre);
            int id_usuario = usuarios.get(0).getIdUsuario();
            String newclave = request.getParameter("newclave");
            String encriptar = DigestUtils.sha1Hex(newclave);
            us.Cambio_clave(id_usuario, encriptar);
            response.getWriter().println("Contraseña Actualizada");
        }

        if (accion.equals("registrar")) {
            int id_usuario;
            int id_empleados;
            //mpresaOperaciones op =new EmpresaOperaciones();
            
            int empresa2 = Integer.parseInt(request.getParameter("empresa2"));
            int empresa = Integer.parseInt(request.getParameter("empresa"));
            String idEmpleado = request.getParameter("sel_cedula");
            List<LcEmpleados> busca = emp.ValidaLCEmpleado(idEmpleado);
            LcEmpleados busc = busca.get(0);
            int id_empleado = busc.getIdEmpleado();
            int idRol = Integer.parseInt(request.getParameter("rol"));
            String usuario = request.getParameter("nusuario");
            String observacion = request.getParameter("observaciones");
            String ncontrasenia = request.getParameter("ncontrasenia");

            Date fecha_regis = new Date();
            Date nuevaFecha = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha_regis);
            cal.add(Calendar.DATE, 1);
            nuevaFecha = cal.getTime();
            System.out.println(nuevaFecha);

            String mensagemUsuario = null;
            String encript = DigestUtils.sha1Hex(ncontrasenia);
            if (empresa == 0) {
                ArrayList<LcUsuarios> encuentra = us.getDatoEncontrado(EmpresaID, id_empleado, usuario, idRol);
                //int cab_grupo=0;
                if (encuentra.isEmpty()) {

                    ArrayList<LcUsuarios> usernv = us.getUsuarioEncontrado(EmpresaID, usuario);
                    if (usernv.isEmpty()) {
                         int secuencia = Integer.parseInt(us.getNext().toString());
                        id_usuario = secuencia;
                        
                        us.addUsuario(new LcUsuarios(id_usuario,
                                (new LcEmpleados(id_empleado)),
                                (new LcEmpresa(empresa2)),
                                (new LcRoles(idRol)),
                                usuario,
                                encript,
                                observacion,
                                fecha_regis, "E", nuevaFecha));
                        response.getWriter().println("Nuevo Usuario Registrado");
                        
                        /*
                        this.idUsuario = idUsuario;
       this.lcEmpleados = lcEmpleados;
       this.lcEmpresa = lcEmpresa;
       this.lcRoles = lcRoles;
       this.usuario = usuario;
       this.contrasenia = contrasenia;
       this.observacion = observacion;
       this.fechaCreacion = fechaCreacion;
       this.estado = estado;
       this.expiracionTmp = expiracionTmp;
                         */
                        
                    }
                }
            }
            if (empresa2 == 0) {
                ArrayList<LcUsuarios> encuentra = us.getDatoEncontrado(EmpresaID, id_empleado, usuario, idRol);
                if (encuentra.isEmpty()) {

                    ArrayList<LcUsuarios> usernv = us.getUsuarioEncontrado(EmpresaID, usuario);
                    if (usernv.isEmpty()) {
                        int secuencia = Integer.parseInt(us.getNext().toString());
                        id_usuario = secuencia;
                        us.addUsuario(new LcUsuarios(id_usuario,
                                (new LcEmpleados(id_empleado)),
                                (new LcEmpresa(empresa)),
                                (new LcRoles(idRol)),
                                usuario,
                                encript,
                                observacion,
                                fecha_regis, "E", nuevaFecha));
                        response.getWriter().println("Nuevo Usuario Registrado");
                    }
                }
            }


        }

        if (accion.equals("buscaID")) {

            int id = Integer.parseInt(request.getParameter("id"));

            if (id != 0) {

                List<LcUsuarios> user = us.getDatosLCUsuariosID(id);
                request.setAttribute("user", user);
                if (EmpresaID == 1) {
                    ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
                    request.setAttribute("empresas", empresas);

                    ArrayList<LcRoles> roles = ro.getLCRoles();
                    request.setAttribute("roles", roles);
                } else {
                    List<LcEmpresa> empresas = es.getEmpresxempresa(EmpresaID);
                    request.setAttribute("empresas", empresas);

                    List<LcRoles> roles = ro.getLCRolesxEmpresa(EmpresaID);
                    request.setAttribute("roles", roles);
                }

                ArrayList<LcUsuarios> usuarios = us.getLcUsuarios();
                request.setAttribute("usuarios", usuarios);
                //se envia los datos al formulario para actualizar
                request.getRequestDispatcher("sistema/usuarios/frm_usuarios_up.jsp").forward(request, response);

            } else {
                response.getWriter().println("No existen datos a consultar");

            }

        }
        if (accion.equals("editar")) {

            int idusuario = Integer.parseInt(request.getParameter("idusuario"));
            int empresa = Integer.parseInt(request.getParameter("empresa"));
            String idEmpleado = request.getParameter("sel_cedula");
            List<LcEmpleados> busca = emp.ValidaLCEmpleado(idEmpleado);
            LcEmpleados busc = busca.get(0);
            int id_empleado = busc.getIdEmpleado();
            int idRol = Integer.parseInt(request.getParameter("rol"));
            String usuario = request.getParameter("nusuario");
            String observacion = request.getParameter("observaciones");
            String ncontrasenia = request.getParameter("ncontrasenia");

            us.updateUsuario(idusuario, empresa, id_empleado, idRol, usuario, ncontrasenia, observacion);
            response.getWriter().println("Registro de Usuarios Actualizado");

        }
        if (accion.equals("eliminar")) {

            int id = Integer.parseInt(request.getParameter("id"));
            us.deleteUsuario(id);
            //response.getWriter().println("Zona Eliminada");
        }
        if (accion.equals("getEmpresa")) {

            String identificacion = request.getParameter("identificacion");
            //LcEmpleados datosEmpleados=null;
            String comboEmpresa = "";
            String inputHTML="";
            comboEmpresa+="<select class=\"form-control\" name=\"empresa\" id=\"empresa2\" onchange=\"getRoles()\"  required=\"required\">";
            comboEmpresa+="<option value=0>Escoger Una empresa</option>";
           
            if (NivelID==0){
                 List<LcEmpleados> empresa_empleado = emp.ValidaLCEmpleado(identificacion);
                 for(int i=0; i< empresa_empleado.size(); i++) {
                    comboEmpresa+="<option value="+empresa_empleado.get(i).getLcEmpresa().getIdEmpresa()+">"+empresa_empleado.get(i).getLcEmpresa().getRazonSocial()+"</option>";
                    inputHTML="<input type=\"text\" id='IdEmpresaHTML' value="+empresa_empleado.get(i).getLcEmpresa().getIdEmpresa()+" hidden>";
                    }
                 
            }else{
                List<LcEmpleados> empresa_empleado2 = emp.ValidaLCEmpleadoss(identificacion,EmpresaID);
                for(int i=0; i< empresa_empleado2.size(); i++) {
                    comboEmpresa+="<option value="+empresa_empleado2.get(i).getLcEmpresa().getIdEmpresa()+">"+empresa_empleado2.get(i).getLcEmpresa().getRazonSocial()+"</option>";
                    inputHTML="<input type=\"text\" id='IdEmpresaHTML' value="+empresa_empleado2.get(i).getLcEmpresa().getIdEmpresa()+" hidden>";
                    }
            }
            comboEmpresa+="</select>"+inputHTML;            
            response.getWriter().println(comboEmpresa);
        }
        
        if (accion.equals("getRolesEmpresas")) {

            int IdEmpresa = Integer.parseInt(request.getParameter("IdEmpresa"));
            List<LcRoles> roles_empresa = ro.getLCRolesxEmpresa(IdEmpresa);
           String comboRolesEmpresa = "";
           comboRolesEmpresa+="<select id=\"rol\" class=\"form-control\" name=\"rol\" required=\"required\">";
             for(int i=0; i< roles_empresa.size(); i++) {
                 comboRolesEmpresa+="<option value="+roles_empresa.get(i).getIdRol()+">"+roles_empresa.get(i).getDescripcion()+"</option>";
             }
           
            comboRolesEmpresa+="</select>";
            response.getWriter().println(comboRolesEmpresa);
        }
        if (accion.equals("ConsultaALLUsuarios")) {

           String Usuarios="";
           Usuarios="{\"data\": "+ us.getConsultaUsuariosEmpresa(EmpresaID)+"}";
           response.getWriter().println(Usuarios);
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
