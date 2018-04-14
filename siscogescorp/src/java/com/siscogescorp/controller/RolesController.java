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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ViewSoft
 */
public class RolesController extends HttpServlet {

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
        String accion;
        accion= request.getParameter("accion");
        RolesOperaciones op = new RolesOperaciones();
        EmpresaServicios es =new EmpresaServicios();
        HttpSession sesion = request.getSession(true);
       String id_empresas;    
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID= Integer.parseInt(id_empresas);
       
       
        if(accion.equals("eliminar")){

                int id= Integer.parseInt(request.getParameter("id"));
                op.deleteRol(id);
             response.getWriter().println("Rol Eliminado Exitosamente.");
         }
        
               if(accion.equals("buscaID")){

            int id= Integer.parseInt(request.getParameter("id"));
            
            if(id!=0){
               
                List<LcRoles> roles = op.getDatosLCRolesID(id);
                request.setAttribute("roles", roles);
                if(EmpresaID==1){
                    ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
                    request.setAttribute("empresas", empresas); 
                    }else{

                    ArrayList<LcEmpresa> empresas = es.getLcEmpresalog(EmpresaID);
                    request.setAttribute("empresas", empresas); 
                    }
                
                //se envia los datos al formulario para actualizar
                request.getRequestDispatcher("sistema/roles/rol_up.jsp").forward(request, response);
                
                
            }else{
                 response.getWriter().println("No existen datos a consultar");
                 
            }
         }
       
       if(accion.equals("editar")){
                
                int idrol =Integer.parseInt(request.getParameter("idrol"));
                int empresa=Integer.parseInt(request.getParameter("empresa"));
                String rol= request.getParameter("rol");
                
                op.updateRoles(idrol, empresa, rol);
                response.getWriter().println("Registro de Roles Actualizado");
               
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
