/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcCargos;
import com.siscogescorp.modelo.LcCiudad;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcPais;
import com.siscogescorp.modelo.LcProvincia;
import com.siscogescorp.modelo.LcRoles;
import com.siscogescorp.modelo.LcZonas;
import com.siscogescorp.servicios.CargosServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.RolesOperaciones;
import com.siscogescorp.servicios.ZonasServicios;
//import com.laticobsa.utils.DefaultPostgresKeyServer;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ViewSoft
 */
@WebServlet(name = "SectoresController", urlPatterns = {"/sectores"})
public class SectoresController extends HttpServlet {

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
        EmpresaServicios es = new EmpresaServicios();
        CargosServicios cs = new CargosServicios();
        RolesOperaciones ro = new RolesOperaciones();
        ZonasServicios lz = new ZonasServicios();

        PrintWriter out = response.getWriter();
        String accion;
        accion = request.getParameter("accion");
        String id_empresas;
        HttpSession sesion = request.getSession(true);
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID = Integer.parseInt(id_empresas);
        String id_rol;
        id_rol = sesion.getAttribute("SstrRolID").toString();
        int RolID = Integer.parseInt(id_rol);
        String nivel;
        nivel = sesion.getAttribute("SstrNivelUser").toString();
        int NivelID = Integer.parseInt(nivel);
        
        if (accion.equals("listar")) {

            if (NivelID == 0) {
                List<LcZonas> zonas = lz.getLcZonass();
                request.setAttribute("zonas", zonas);
            } 
            if(NivelID == 1) {
                List<LcZonas> zonas = lz.getLcZonasxEmpresa(EmpresaID);
                request.setAttribute("zonas", zonas);
            }
            if ((NivelID != 0) && (NivelID != 1)) {
                List<LcZonas> zonas = lz.getLcZonasxEmpresa(EmpresaID);
                request.setAttribute("zonas", zonas);            
            }
            request.getRequestDispatcher("sistema/sectores/lista_sectores.jsp").forward(request, response);

        }

        if (accion.equals("validar")) {
            String ide = request.getParameter("nombre");
            boolean sector = lz.ValidaLCZonas(ide);

            out.println(sector);

        }

        if (accion.equals("agregar")) {

            if (EmpresaID == 1) {
                ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
                request.setAttribute("empresas", empresas);
            } else {

                List<LcRoles> empresaso = es.getLcEmpresaRol(EmpresaID, RolID);
                int Empresa = empresaso.get(0).getLcEmpresa().getIdEmpresa();
                ArrayList<LcEmpresa> empresao = es.getLcEmpresalog(Empresa);
                request.setAttribute("empresao", empresao);
            }
            ArrayList<LcRoles> roles = ro.getLCRoles();
            request.setAttribute("roles", roles);
            ArrayList<LcPais> paises = es.getLcEmpPais();
            request.setAttribute("paises", paises);

            request.getRequestDispatcher("sistema/sectores/frm_sectores.jsp").forward(request, response);
        }

        if (accion.equals("registrar")) {
            int id_zona = 0;
            ArrayList<LcZonas> zona = lz.getLCZonas();

            if (zona.isEmpty()) {
                id_zona = 1;
            } else {

               // int secuencia = lz.SecuenciaModulo();// traigo el 
                int secuencia = Integer.parseInt(lz.getNext().toString());
                id_zona = secuencia;
            }
            int empresa2 = Integer.parseInt(request.getParameter("empresa2"));
            int empresa = Integer.parseInt(request.getParameter("empresa"));
            String nombre = request.getParameter("nombre");
            int pais = Integer.parseInt(request.getParameter("pais"));
            int provincia = Integer.parseInt(request.getParameter("provincia"));
            int ciudad = Integer.parseInt(request.getParameter("ciudad"));
            String descripcion = request.getParameter("descripcion");
            if (empresa == 0) {
                lz.addZonas(new LcZonas(id_zona,
                        (new LcCiudad(ciudad)),
                        (new LcEmpresa(empresa2)),
                        (new LcPais(pais)),
                        (new LcProvincia(provincia)),
                        nombre,
                        descripcion,
                        null, "A"));

                
            }
            if (empresa2 == 0) {
                lz.addZonas(new LcZonas(id_zona,
                        (new LcCiudad(ciudad)),
                        (new LcEmpresa(empresa)),
                        (new LcPais(pais)),
                        (new LcProvincia(provincia)),
                        nombre,
                        descripcion,
                        null, "A"));

                
            }
            response.getWriter().println("Zona Creada Exitosamente");
        }
        if (accion.equals("buscaID")) {

            int id = Integer.parseInt(request.getParameter("id"));

            if (id != 0) {

                List<LcZonas> zonas = lz.getDatosLCZonasID(id);
                request.setAttribute("zonas", zonas);
                if (EmpresaID == 1) {
                    ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
                    request.setAttribute("empresas", empresas);
                } else {

                    ArrayList<LcEmpresa> empresas = es.getLcEmpresalog(EmpresaID);
                    request.setAttribute("empresas", empresas);
                }

                //se envia los datos al formulario para actualizar
                request.getRequestDispatcher("sistema/sectores/frm_sectores_up.jsp").forward(request, response);

            } else {
                response.getWriter().println("No existen datos a consultar");

            }

        }

        if (accion.equals("editar")) {

            //request.getRequestDispatcher("sistema/sectores/frm_sectores.jsp").forward(request, response);
            int id = Integer.parseInt(request.getParameter("idzona"));
            int empresa = Integer.parseInt(request.getParameter("empresa"));
            String nombre = request.getParameter("nombre");
            int pais = Integer.parseInt(request.getParameter("pais"));
            int provincia = Integer.parseInt(request.getParameter("provincia"));
            int ciudad = Integer.parseInt(request.getParameter("ciudad"));
            String descripcion = request.getParameter("descripcion");
            lz.updateZona(id, empresa, pais, provincia, ciudad, nombre, descripcion);
            response.getWriter().println("Registro de zona actualizado");

        }

        if (accion.equals("eliminar")) {

            int id = Integer.parseInt(request.getParameter("id"));
            lz.deleteZona(id);
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
