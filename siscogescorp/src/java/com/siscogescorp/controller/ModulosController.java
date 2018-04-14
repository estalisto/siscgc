/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;


import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcModuloRol;
import com.siscogescorp.modelo.LcModulos;
import com.siscogescorp.modelo.LcRoles;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.ModulosRoles;
import com.siscogescorp.servicios.RolesOperaciones;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ModulosController extends HttpServlet {

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
        ModulosRoles mr = new ModulosRoles();
        RolesOperaciones ro = new RolesOperaciones();
        EmpresaServicios es =new EmpresaServicios();
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession(true);
       String id_empresas;    
        
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID= Integer.parseInt(id_empresas);
        int accesoROLES= Integer.parseInt(sesion.getAttribute("NivelAccesoRolID").toString());

        if(accion.equals("listar"))
        {
              
            List<LcEmpresa> empresas = es.getLcEmpresaRolSuper(EmpresaID,accesoROLES);       
            request.setAttribute("empresas", empresas);  
            ArrayList<LcModulos> modu = mr.getLcModelXEmpresa(EmpresaID);
            request.setAttribute("modu", modu);
            request.getRequestDispatcher("sistema/modulos/frm_asigna_modulos_rol.jsp").forward(request, response);
        }
        if(accion.equals("roles")){
            int empresa = Integer.parseInt(request.getParameter("empresa"));
            ArrayList<LcRoles> roless = ro.getRolesEmpresa(empresa);
            request.setAttribute("roless", roless);
            if(roless.isEmpty()){
                response.getWriter().println("true");
            }else{
                response.getWriter().println("false");
            }
            //request.getRequestDispatcher("sistema/modulos/combo_rol.jsp").forward(request, response);
            
        }
        if(accion.equals("desactivar")){

                int id= Integer.parseInt(request.getParameter("id"));
                int empresa = Integer.parseInt(request.getParameter("empresa"));
                int rol= Integer.parseInt(request.getParameter("rol"));
                ArrayList<LcModuloRol> borra = mr.getLcdeleteModulo(empresa, rol, id);
                LcModuloRol datos = borra.get(0);
                int newmodulo= datos.getIdModuloRol();
                mr.deleteModal(newmodulo);
             //response.getWriter().println("Zona Eliminada");
         }
        if(accion.equals("registrar")){

            int id= Integer.parseInt(request.getParameter("id"));
            Date fecha_reg = new Date();
            int id_empresa=Integer.parseInt(request.getParameter("empresa"));
            int id_rol = Integer.parseInt(request.getParameter("rol"));
            int grupo = Integer.parseInt(request.getParameter("grupo"));
            int nivel = Integer.parseInt(request.getParameter("nivel"));
            int orden = Integer.parseInt(request.getParameter("orden"));
            int id_modulorol, id_modulorol2;

                      ArrayList<LcModuloRol> datos = mr.getLcModulo();
                      // en esta linea solo sirve cuando existen datos en la tabla
                      
                       if(datos.isEmpty()){
                           id_modulorol = 1;
                           id_modulorol2=1;
                       }else {
                            //  LcModuloRol idModuloRol =datos.get(datos.size() -1);
                            int secuencia = mr.SecuenciaModulo();
                              id_modulorol=secuencia+1;
                              id_modulorol2=secuencia+1;
//                              List<LcModuloRol> secuencia = mr.SecuenciaModulo();
//                              LcModuloRol  sec = secuencia.get(0);
//                              
//                             id_modulorol =  sec.getIdModuloRol();
                            
                       }
            if(grupo == 0){
                
                List<LcModuloRol> contenedor = mr.getCabecera(id_empresa, id_rol,nivel);// voy consulta si esta la cabecera
                
                if(contenedor.isEmpty()){
                id_modulorol2++;
                ArrayList<LcModulos> cabecera = mr.getLcModuloCabecera(id_empresa,nivel);//traigo los datos de la cabecera si esta no se esncuentra registrada
                

                 LcModulos content = cabecera.get(0);
                int id_cabecera = id_modulorol;
                int empresa_cab = id_empresa;
                int rol_cab = id_rol;
                int modulo_cab = content.getIdModulo();
                int nivel_cab = content.getNivel();
                int grupo_cab = content.getGrupo();
                
                mr.addModulosCabecera(new LcModuloRol( //añado los datos a mi registro de modulos rol
                            id_cabecera,
                            (new LcEmpresa (empresa_cab)),
                            (new LcModulos (modulo_cab)),
                            (new LcRoles (rol_cab)),
                            nivel_cab,
                            grupo_cab,
                            fecha_reg,
                            "A",orden
                ));
                
                }
                List<LcModuloRol> encuentra = mr.getDatoEncontrado(id_empresa,id_rol,id);
                //int cab_grupo=0;
                if(encuentra.isEmpty()){
                     //id_modulorol2=id_modulorol+1;           
                    mr.addModulosrol(new LcModuloRol
                                     (id_modulorol2,
                                      (new LcEmpresa (id_empresa)),
                                      (new LcModulos (id)),
                                      (new LcRoles (id_rol)),
                                      nivel,
                                      grupo,       
                                      fecha_reg,"A",orden
                                      ));
                    response.getWriter().println("true");
                }
            }           
            else{
                 response.getWriter().println("false");
                 
            }
                    
         }
        if(accion.equals("listar_modulos")){
        int id_modulorol = Integer.parseInt(request.getParameter("rol"));    
        int id_empresa=Integer.parseInt(request.getParameter("empresa"));
            
        List<LcModuloRol> moduloss = mr.getDatosLCModuloRoll(id_modulorol, id_empresa);
                //.SelectModuloRol(id_modulorol, id_empresa);
                //.getDatosLCModuloRoll(id_modulorol, id_empresa);
        request.setAttribute("moduloss", moduloss);
                
                // llamar a la pagina
                request.getRequestDispatcher("sistema/modulos/modulos_asignados.jsp").forward(request, response);
        }
        
        if(accion.equals("existe_modulos")){
           int id_empresa=Integer.parseInt(request.getParameter("empresa"));
            ArrayList<LcModulos> modu = mr.getLcModelXEmpresa(id_empresa);
            if(modu.isEmpty()){
                 response.getWriter().println("false");
                 
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
