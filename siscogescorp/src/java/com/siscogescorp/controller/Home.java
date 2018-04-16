/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcParametros;
import com.siscogescorp.servicios.ConsultaxCarteraServicios;
import com.siscogescorp.servicios.DashcboardOk;
import com.siscogescorp.servicios.ParametrosServicios;
import com.siscogescorp.utils.NotificacionesServicios;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CIMA2015
 */
public class Home extends HttpServlet {

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
         HttpSession sesion = request.getSession(true);
        String id_rol_empleado="",id_empleados="";
        
        if (sesion.getAttribute("SstrUsuarioRol")== null && sesion.getAttribute("Sstrempleado")==null){
                  
                  sesion.invalidate();
                  response.sendRedirect("login.jsp");
              return;
        }
        DashcboardOk ds = new DashcboardOk();
        ConsultaxCarteraServicios cli = new ConsultaxCarteraServicios();
        NotificacionesServicios notif = new NotificacionesServicios();
        ParametrosServicios param = new ParametrosServicios();
        
         id_rol_empleado = sesion.getAttribute("SstrUsuarioRol").toString();
            int RolEmpleado= Integer.parseInt(id_rol_empleado);
            id_empleados = sesion.getAttribute("Sstrempleado").toString();
            int EmpleadoID= Integer.parseInt(id_empleados);
            String Grafica1="", str_parametro="LB_PERMISOS_ADMIN";
            String id_empresa = sesion.getAttribute("Sstrempresa").toString();
             String id_agencia = sesion.getAttribute("Sstrsucursal").toString();
            
            int IDEMPRESA = Integer.parseInt(id_empresa);
             int IDAGENCIA = Integer.parseInt(id_agencia);
            
        String accion="", s_cliente="",s_empleado="",fecha_cons="";
        int cliente=0, empleado=0;
        //accion = request.getParameter("accion");
        if (request.getParameter("accion")!= null){
            accion =request.getParameter("accion");
        }
        
        if (accion.equals("")){
             id_rol_empleado = sesion.getAttribute("SstrUsuarioRol").toString();
           /* int RolEmpleado= Integer.parseInt(id_rol_empleado);
            id_empleados = sesion.getAttribute("Sstrempleado").toString();
            int EmpleadoID= Integer.parseInt(id_empleados);
            String Grafica1="", str_parametro="LB_PERMISOS_ADMIN";*/
            if(param.permisos_admin(id_rol_empleado)){
                Grafica1 = ds.fnc_ConsultaGestionesDiarias(0, "", 0);  
                request.setAttribute("Grafica1", Grafica1);           
            }else{
                    cliente = cli.getIdCliente(1, 1, EmpleadoID);              
                    Grafica1 = ds.fnc_ConsultaGestionesDiarias(0, "", cliente);  
                    request.setAttribute("Grafica1", Grafica1);                   
            }
            //request.setAttribute("mensaje", "Respuesta Exitosa"); 
           // request.getRequestDispatcher("sistema/index2.jsp").forward(request, response);
                        request.getRequestDispatcher("sistema/index_final.jsp").forward(request, response);

            
        }
        if (accion.equals("consulta")){
            
            s_empleado=request.getParameter("s_empleado");
            s_cliente=request.getParameter("s_cliente");
            fecha_cons=request.getParameter("fecha_cons");
            if(request.getParameter("s_cliente") != null){
            cliente =Integer.parseInt(s_cliente);
            }
            if(s_empleado != null){
            empleado =Integer.parseInt(s_empleado);
            }
            if(fecha_cons == null){
            fecha_cons ="";
            }
            if(param.permisos_admin(id_rol_empleado)){
                Grafica1 = ds.fnc_ConsultaGestionesDiarias(empleado, fecha_cons, cliente);  
                request.setAttribute("Grafica1", Grafica1);           
            }else{
                   // cliente = cli.getIdCliente(1, 1, EmpleadoID);              
                    Grafica1 = ds.fnc_ConsultaGestionesDiarias(0, fecha_cons, cliente);  
                    request.setAttribute("Grafica1", Grafica1);                   
            }
            request.getRequestDispatcher("sistema/index2.jsp").forward(request, response);
        }
        if (accion.equals("notificaciones")){
            String notificaciones="", query="select *, (select count(*) from lc_notificaciones n where receptor = "+EmpleadoID+"  and fecha_recepcion is null AND tipo_notificacion = ''WEB'') cantidad  from lc_notificaciones where receptor = "+EmpleadoID+" and fecha_recepcion is null AND tipo_notificacion = ''WEB''";
            
            notificaciones="{\"Notificaciones\": "+notif.fnc_ConsultaNotificaciones(query)+"}";
            
           response.getWriter().println(notificaciones);
        }
        if(accion.equals("ConsultaMisNotificaciones")){
         String  resultado = notif.PreparandoNotificaciones(IDEMPRESA,IDAGENCIA,EmpleadoID);
         String  notificaciones="", datos="";
         notificaciones=notif.fnc_ConsultaMisNotificaciones(IDEMPRESA,IDAGENCIA,EmpleadoID);
         if(notificaciones==null){
         datos="{\"Notificaciones\": []}";
         }else{
              datos+="{\"Notificaciones\": "+notificaciones+"}";
         }
             response.getWriter().println(datos);
        }
       //deudor_gestionado_ok
       
        if(accion.equals("deudor_gestionado_ok")){
         int id_recordatorio = Integer.parseInt(request.getParameter("id_recordatorio"));
            String Datos = notif.fnc_elimina_notificacion(id_recordatorio);
            
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
