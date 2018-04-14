/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcCargos;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.servicios.CargosServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
public class CargosController extends HttpServlet {

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
        
        response.setContentType("text/html;charset=UTF-8");
        EmpresaServicios es =new EmpresaServicios();
        CargosServicios cs = new CargosServicios();
        String accion;
        accion= request.getParameter("accion");
        String id_empresas;    
       HttpSession sesion = request.getSession(true);
       id_empresas = sesion.getAttribute("Sstrempresa").toString();
       int EmpresaID= Integer.parseInt(id_empresas); 
        String id_rol;    
        id_rol = sesion.getAttribute("SstrRolID").toString();
        int RolID= Integer.parseInt(id_rol);
       int accesoROLES= Integer.parseInt(sesion.getAttribute("NivelAccesoRolID").toString());
        
        if(accion.equals("listar"))
        {
            
        if (accesoROLES == 0){  
       List<LcCargos> cargos = cs.getLcCargoss();
       request.setAttribute("cargos", cargos);
        }else{
        List<LcCargos> cargos = cs.getLcCargoxEmpresa(EmpresaID);
       request.setAttribute("cargos", cargos);
       
        }  
        List<LcEmpresa> empresas = es.getLcEmpresaRolSuper(EmpresaID,accesoROLES);       
            request.setAttribute("empresas", empresas);   
        
        
        
        request.getRequestDispatcher("sistema/cargos/lista_cargos.jsp").forward(request, response);
        //request.getContextPath()+
        }
        
       if(accion.equals("agregar"))
        {
            List<LcEmpresa> empresas = es.getLcEmpresaRolSuper(EmpresaID,accesoROLES);       
            request.setAttribute("empresas", empresas);   
            request.getRequestDispatcher("sistema/cargos/frm_cargos.jsp").forward(request, response);
        }
        
       if(accion.equals("registrar"))
        {
           // int empresa2 = Integer.parseInt(request.getParameter("empresa2"));
            int empresa = Integer.parseInt(request.getParameter("empresa"));
            String cargo = request.getParameter("cargo");
            String observacion = request.getParameter("observacion");
            Date fecha_reg = new Date();
             int id_cargo;
            
            
            
               // if (empresa == 0) {
                ArrayList<LcCargos> encuentra = cs.getDatoEncontrado(empresa, cargo);
                if (encuentra.isEmpty()) {
                    id_cargo= Integer.parseInt(cs.getNextValCargos().toString());
                    cs.addCargos(new LcCargos(id_cargo, (new LcEmpresa(empresa)), cargo, observacion, fecha_reg, "A", null));
                     response.getWriter().println("Cargo Creado Exitosamente");
                }else{
                 response.getWriter().println("Ya existe un Cargo creado con el mismo nombre para esta EMPRESA");
                }
                
               
            
        }
        
       if(accion.equals("buscaID")){

            int id= Integer.parseInt(request.getParameter("id"));
            
            if(id!=0){
               
                List<LcCargos> cargos = cs.getDatosLCargosID(id);
                request.setAttribute("cargos", cargos);
                
                 List<LcEmpresa> empresas = es.getLcEmpresaRolSuper(EmpresaID,accesoROLES);       
                request.setAttribute("empresas", empresas);   
                
                //se envia los datos al formulario para actualizar
                request.getRequestDispatcher("sistema/cargos/frm_cargos_up.jsp").forward(request, response);
                
                
            }else{
                 response.getWriter().println("No existen datos a consultar");
                 
            }
         }
       
       if(accion.equals("editar")){
                
                int id =Integer.parseInt(request.getParameter("idcargo"));
                int empresa = Integer.parseInt(request.getParameter("empresa"));
                String cargo=request.getParameter("cargo");
                String observacion=request.getParameter("observacion");
                
                cs.updateCargo(id, empresa, cargo, observacion);
                response.getWriter().println("Registro de Cargos Actualizado");
               
    }
        if(accion.equals("eliminar")){

                int id= Integer.parseInt(request.getParameter("id"));
                cs.deleteCargos(id);
             //response.getWriter().println("Zona Eliminada");
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
