/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.servicios.AsignacionCarteraServicios;
import com.siscogescorp.servicios.CampaniasServicios;
import com.siscogescorp.servicios.ConsultaxCarteraServicios;
import com.siscogescorp.servicios.DocumentosServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.NotificacionesPagosServicios;
import com.siscogescorp.servicios.SucursalServicios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CIMA2015
 */
public class NotificacionesPagos extends HttpServlet {

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

             PrintWriter out = response.getWriter(); 
       EmpresaServicios es =new EmpresaServicios();
       SucursalServicios su = new SucursalServicios();
       DocumentosServicios doc = new DocumentosServicios();
       AsignacionCarteraServicios ac= new AsignacionCarteraServicios();
       ConsultaxCarteraServicios cd = new ConsultaxCarteraServicios();
       NotificacionesPagosServicios noti = new NotificacionesPagosServicios();
       
       String accion;
       accion= request.getParameter("accion");
       String id_empresas,id_empleados,id_sucursal;    
       HttpSession sesion = request.getSession(true);
       id_empresas = sesion.getAttribute("Sstrempresa").toString();
       int EmpresaID= Integer.parseInt(id_empresas);
       id_empleados = sesion.getAttribute("Sstrempleado").toString();
       int EmpleadoID= Integer.parseInt(id_empleados);
       id_sucursal = sesion.getAttribute("Sstrsucursal").toString();
       int SucursalID= Integer.parseInt(id_sucursal);
         String SSqlDatosDeudor="";
         
        if(accion.equals("listar"))
        {    
           request.getRequestDispatcher("sistema/terrenos/frm_notificaciones_pagos.jsp").forward(request, response);

        
        }  
         if(accion.equals("consultar"))
        {    
           String Campanias="";
           Campanias=noti.getNotificacionesSaldosPendientes(EmpresaID, SucursalID);
           if(Campanias==null){
           Campanias = "{\"data\":[]}";
           }   else{
               Campanias = "{\"data\": "+Campanias+"}";
           }
           
              response.getWriter().println(Campanias);
        
        }
         
         if(accion.equals("nuevas_notificaciones")){
               int  id_notificacionespago=0, id_documento=0;
               
               //descripcion":descripcion, "fecha_desde":fecha_desde, "fecha_hasta
               String descripcion="";
               
               
               if(request.getParameter("descripcion").isEmpty()){
                response.getWriter().println("Debe Ingresar la Descripción");
               }   
               
              if(request.getParameter("id_documento").isEmpty()){
                response.getWriter().println("Debe Ingresar el tipo de Documento");
               } 
              descripcion=request.getParameter("descripcion");
                 id_documento=Integer.parseInt(request.getParameter("id_documento"));

                 
                 id_notificacionespago =noti.AddNotificacionesPago(descripcion, EmpresaID, SucursalID,id_documento);
                 
               
               if(id_notificacionespago!=0){
                    response.getWriter().println("Se ha creado el paquete de Notificación de Saldos pendientes");
               }else{
                    response.getWriter().println("Problemas al crear la Notificación de Saldos, favor comuniquese con Dpto. Sistemas.");
               }
        
        
        
        
        }
         if(accion.equals("mis_documentos")){
             String documentos="";
           documentos=doc.getMisDocumentos(EmpresaID,SucursalID); 
           if(documentos==null){
           documentos = "{\"data\":[]}";
           }   else{
               documentos = "{\"data\": "+documentos+"}";
           }
           
              response.getWriter().println(documentos);  
             
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
