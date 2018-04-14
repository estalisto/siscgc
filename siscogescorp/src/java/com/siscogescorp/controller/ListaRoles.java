/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcRoles;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.RolesOperaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ListaRoles extends HttpServlet {

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
        RolesOperaciones op = new RolesOperaciones();
                HttpSession sesion = request.getSession(true);
                EmpresaServicios es =new EmpresaServicios();
        String id_empresas;    
        
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID = Integer.parseInt(id_empresas);
        String nivel;
        nivel = sesion.getAttribute("SstrNivelUser").toString();
        int NivelID = Integer.parseInt(nivel);

        if (NivelID == 0) {
            List<LcRoles> datos = op.getLCRolesoper();
            request.setAttribute("datos", datos);
        }
        if (NivelID == 1) {
            List<LcRoles> datos = op.getLCRolesxEmpresa(EmpresaID);
            
            request.setAttribute("datos", datos);
        }
        if ((NivelID != 0) && (NivelID != 1)) {
           List<LcRoles> datos = op.getLCRolesxEmpresaUsuario(EmpresaID,NivelID); 
            request.setAttribute("datos", datos);
        }
        
       
       int accesoROLES= Integer.parseInt(sesion.getAttribute("NivelAccesoRolID").toString());
       String id_rol;    
       id_rol = sesion.getAttribute("SstrRolID").toString();
       int RolID= Integer.parseInt(id_rol);
     
       List<LcEmpresa> empresas = es.getLcEmpresaRolSuper(EmpresaID,accesoROLES);       
       request.setAttribute("empresas", empresas); 
       
        request.getRequestDispatcher("sistema/roles/roles.jsp").forward(request, response);  
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
