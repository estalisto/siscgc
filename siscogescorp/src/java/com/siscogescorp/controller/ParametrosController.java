/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcParametros;
import com.siscogescorp.servicios.ParametrosServicios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ViewSoft
 */
public class ParametrosController extends HttpServlet {

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
        ParametrosServicios ps=new ParametrosServicios();
        
        String accion;
        accion= request.getParameter("accion");


      
        
        if(accion.equals("listar"))
        {
           
        ArrayList<LcParametros> parametros = ps.getLcParametros();
        request.setAttribute("parametros", parametros);
            
      
            request.getRequestDispatcher("sistema/parametros/lista_parametro.jsp").forward(request, response);
        //request.getContextPath()+
        }
        
        if(accion.equals("validar")){
        String ide = request.getParameter("parametross");
        boolean parametro = ps.ValidaLCParametros(ide);
        
        out.println(parametro);
        
        }
        
        if(accion.equals("agregar"))
        {
          
      
            request.getRequestDispatcher("sistema/parametros/frm_parametro.jsp").forward(request, response);
        }
        
        if(accion.equals("registrar"))
        {
                   
        Date fecha_reg = new Date();
        
         int  codigo=Integer.parseInt(request.getParameter("codigo"));
         String  nom_parametros=request.getParameter("nombre");
         System.out.print(nom_parametros);
         String valor=request.getParameter("valor").toString();
         String descripcion=request.getParameter("descripcion");
         int id_parametro;
         ArrayList<LcParametros> parametros = ps.getLcParametros();
         
          if(parametros.isEmpty()){
             id_parametro = 1;
         }else {
                //int secuencia = ps.SecuenciaModulo();
                int secuencia = Integer.parseInt(ps.getNext().toString());
                id_parametro= secuencia;
                }
                ArrayList<LcParametros> encuentra = ps.getDatoEncontrado(codigo);
                if(encuentra.isEmpty()){
                        ps.addLcParametros(new LcParametros
                                          (id_parametro,
                                           codigo,
                                          nom_parametros.toUpperCase(),
                                          valor,
                                          descripcion,
                                          fecha_reg,
                                          "A"));
          
          response.getWriter().println("Parametro registrado correctamente");}
        }
        if(accion.equals("buscaID")){

            int id= Integer.parseInt(request.getParameter("id"));
            
            if(id!=0){
               
                ArrayList<LcParametros> agent = ps.getDatosLCParametrosID(id);
                request.setAttribute("agent", agent);
//                ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
//                 request.setAttribute("empresas", empresas);
                request.getRequestDispatcher("sistema/parametros/frm_parametro_up.jsp").forward(request, response);
                
                
            }else{
                 response.getWriter().println("No existen datos a consultar");
                 
            }
                    
         }
        if(accion.equals("editar")){
            
                int id = Integer.parseInt(request.getParameter("id"));
                int  codigo = Integer.parseInt(request.getParameter("codigo"));
                String  nombre=request.getParameter("nombre");
                String valor=request.getParameter("valor");
                String descripcion=request.getParameter("descripcion");
                ps.updateParametros(id, codigo, nombre, valor,descripcion);
                response.getWriter().println("Registro de Parametros Actualizado");
               
    }
        if(accion.equals("eliminar")){

                int id= Integer.parseInt(request.getParameter("id"));
                ps.deleteParametros(id);
             //response.getWriter().println("Zona Eliminada");
         }
        if(accion.equals("consulta_parametro")){
             String Dato=ps.Consulta_Parametro("LB_CONSULTA_NOTIFICACIONES");

                response.getWriter().println(Dato);
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
