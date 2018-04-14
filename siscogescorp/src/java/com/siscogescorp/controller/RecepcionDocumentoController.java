/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.servicios.RecepcionDocumentoServicios;
import com.siscogescorp.utils.ArchivoLog;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CIMA2015
 */
public class RecepcionDocumentoController extends HttpServlet {

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
        RecepcionDocumentoServicios recep = new RecepcionDocumentoServicios();
        ArchivoLog grb = new ArchivoLog();
        String accion;
         HttpSession sesion = request.getSession(true);
        accion = request.getParameter("accion");
        response.setContentType("text/html;charset=UTF-8");
         if (accion.equals("listar")) {
           try{
            request.getRequestDispatcher("sistema/terrenos/frm_recepcion_documento.jsp").forward(request, response);
            } catch (Exception ex) {
                try{grb.grabaLog("RecepcionDocumentoController_listar RecepcionDoc  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecepcionDocumentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(accion.equals("consultadatos")){
            try{
            String idQR=request.getParameter("recepcion");
            
         
           String recepcion;
          //  try {
                recepcion = "{\"RecepcionDoc\": "+recep.getDocRecepcion(idQR)+"}";
                response.getWriter().println(recepcion);
           } catch (Exception ex) {
                try{grb.grabaLog("RecepcionDocumentoController_consultadatos RecepcionDoc  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecepcionDocumentoController.class.getName()).log(Level.SEVERE, null, ex);
            }

           
        }
        if(accion.equals("datosrecibidos")){
            try{
            String idQR=request.getParameter("recepcion");
            
         
           String recibido;
         //   try {
                recibido = "{\"Recepcionrecibido\": "+recep.getDatosRecibidos(idQR)+"}";
                response.getWriter().println(recibido);
           } catch (Exception ex) {
                try{grb.grabaLog("RecepcionDocumentoController_datosrecibidos RecepcionDoc  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecepcionDocumentoController.class.getName()).log(Level.SEVERE, null, ex);
            }

           
        }
        if(accion.equals("guardar_recepcion")){
  
            try{
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
            int recepcion=Integer.parseInt(request.getParameter("recepcion"));
            String name_recepcion=request.getParameter("name_recepcion");
            String Ide_recepcion=request.getParameter("Ide_recepcion");
            String fecha_recepcion=request.getParameter("fecha_recepcion");
            String observacion=request.getParameter("observacion");
            String condicional=request.getParameter("condicional");
            Date  Fecha = dateFormatter.parse(fecha_recepcion);
            recep.updateDocumento(recepcion, Ide_recepcion, name_recepcion, Fecha, observacion,condicional);
            response.getWriter().println(condicional);
             } catch (Exception ex) {
                try{grb.grabaLog("RecepcionDocumentoController_guardar_recepcion RecepcionDoc  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecepcionDocumentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.equals("buscarPDF")){
            try{
            String page=request.getParameter("ruta");
            sesion.setAttribute("strpage", page);
            request.getRequestDispatcher("sistema/terrenos/documentoPDF.jsp").forward(request, response);
             } catch (Exception ex) {
                try{grb.grabaLog("RecepcionDocumentoController_buscarPDF RecepcionDoc  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecepcionDocumentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
