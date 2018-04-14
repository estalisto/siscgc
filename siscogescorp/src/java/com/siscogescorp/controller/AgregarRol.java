/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.utils.ArchivoLog;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class AgregarRol extends HttpServlet {

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
       EmpresaServicios es =new EmpresaServicios();
       PrintWriter out = response.getWriter();
       ArchivoLog grb = new ArchivoLog();
       HttpSession sesion = request.getSession(true);
       String id_empresas;    
       try{
       id_empresas = sesion.getAttribute("Sstrempresa").toString();
       int EmpresaID= Integer.parseInt(id_empresas);
       int accesoROLES= Integer.parseInt(sesion.getAttribute("NivelAccesoRolID").toString());
       String id_rol;    
       id_rol = sesion.getAttribute("SstrRolID").toString();
       int RolID= Integer.parseInt(id_rol);
     
       List<LcEmpresa> empresas = es.getLcEmpresaRolSuper(EmpresaID,accesoROLES);       
       request.setAttribute("empresas", empresas); 
     
       request.getRequestDispatcher("sistema/roles/rol.jsp").forward(request, response);
       } catch (Exception ex) {
                try{grb.grabaLog("AgregarRol_Agregar roles  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(AgregarRol.class.getName()).log(Level.SEVERE, null, ex);
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
