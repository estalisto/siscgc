/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcCargos;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcRoles;
import com.siscogescorp.modelo.LcUsuarios;
import com.siscogescorp.servicios.CargosServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.RolesOperaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ViewSoft
 */
@WebServlet(name = "AccesosController", urlPatterns = {"/accesos"})
public class AccesosController extends HttpServlet {

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
        CargosServicios cs = new CargosServicios();
        RolesOperaciones ro = new  RolesOperaciones();
        String accion;
        accion= request.getParameter("accion");


      
        
        if(accion.equals("listar"))
        {
            
        
       ArrayList<LcCargos> cargos = cs.getLcCargos();
       request.setAttribute("cargos", cargos);
            
              request.getRequestDispatcher("sistema/Acceso/lista_acceso.jsp").forward(request, response);
        //request.getContextPath()+
        }
        if(accion.equals("agregar"))
        {
            
                    
               
       ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
       request.setAttribute("empresas", empresas);
       ArrayList<LcRoles> roles = ro.getLCRoles();
       request.setAttribute("roles", roles);
       
       request.getRequestDispatcher("sistema/Acceso/frm_acceso.jsp").forward(request, response);
        //request.getContextPath()+
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
