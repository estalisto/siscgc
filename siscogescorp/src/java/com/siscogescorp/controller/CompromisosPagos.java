/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;


import com.siscogescorp.servicios.CompromisosPagosServicios;
import java.io.IOException;
import java.sql.SQLException;
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
public class CompromisosPagos extends HttpServlet {

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
         CompromisosPagosServicios compp=new CompromisosPagosServicios();
        String accion;
           accion= request.getParameter("accion");
                  String id_empresas, ss_idempleado;    
       HttpSession sesion = request.getSession(true);
       id_empresas = sesion.getAttribute("Sstrempresa").toString();
       int id_empresa= Integer.parseInt(id_empresas);
       ss_idempleado = sesion.getAttribute("SstrIdEmpleado").toString();
       int IdEmpleado =Integer.parseInt(ss_idempleado);

       Date FechaHoy =new Date();
       String Tabla="";
      
       
       if(accion.equals("listar"))
        {
            try {
                Tabla=compp.getCompromisoxdeudores(FechaHoy, IdEmpleado);
            } catch (SQLException ex) {
                Logger.getLogger(CompromisosPagos.class.getName()).log(Level.SEVERE, null, ex);
            }
             request.setAttribute("Tablacompromiso", Tabla);
//        List<LcCompromisosPago> compromisospg = compp.getLcCompromisosPago(FechaHoy,IdEmpleado);
//        request.setAttribute("compromisospg", compromisospg);
         request.getRequestDispatcher("sistema/gestion/frm_compromiso_pago.jsp").forward(request, response);
        
        
        }
         if(accion.equals("consultar"))
        {
            
          //SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
          // String fecha_inicio = dateFormatter.format(new Date());
          // System.out.print("Fecha: "+fecha_inicio);           
           String fechaIni= request.getParameter("fecha_inicio");
           String fechaFin= request.getParameter("fecha_fin");
            try {
                Tabla=compp.getCompromisoxRangos(fechaIni, fechaFin, IdEmpleado);
            } catch (SQLException ex) {
                Logger.getLogger(CompromisosPagos.class.getName()).log(Level.SEVERE, null, ex);
            }
           request.setAttribute("Tablacompromiso", Tabla);
//           List<LcCompromisosPago> compromisospg = compp.getLcCompromisosPagoFechas(fechaIni,fechaFin,IdEmpleado);       
//           request.setAttribute("compromisospg", compromisospg);
           request.getRequestDispatcher("sistema/gestion/frm_compromiso_pago.jsp").forward(request, response);
        
        
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
