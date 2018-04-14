/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcCiudad;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcPais;
import com.siscogescorp.modelo.LcProvincia;
import com.siscogescorp.modelo.LcSucursal;
import com.siscogescorp.modelo.LcTiposIdentificacion;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.SucursalServicios;
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
public class Empresa extends HttpServlet {

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
        EmpresaServicios es = new EmpresaServicios();
        SucursalServicios su = new SucursalServicios();
        String accion;
        accion = request.getParameter("accion");
        String id_empresas;
        HttpSession sesion = request.getSession(true);
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID = Integer.parseInt(id_empresas);
        String nivel;
        nivel = sesion.getAttribute("SstrNivelUser").toString();
        int NivelID = Integer.parseInt(nivel);
        
        if (accion.equals("listar")) {
            if (NivelID == 0) {
                ArrayList<LcEmpresa> empresas = es.getLcEmpresa2();
                request.setAttribute("empresas", empresas);
            } 
            if (NivelID == 1) {
                List<LcEmpresa> empresas = es.getEmpresxempresa(EmpresaID);
                request.setAttribute("empresas", empresas);
            }
            if ((NivelID != 0) && (NivelID != 1)) {
                List<LcEmpresa> empresas = es.getEmpresxempresa(EmpresaID);
                request.setAttribute("empresas", empresas);
            }
            request.getRequestDispatcher("sistema/empresa/lista_empresa.jsp").forward(request, response);
        }
        if (accion.equals("agregar")) {
            ArrayList<LcSucursal> sucursal = su.getLcSucursal();
            request.setAttribute("sucursale", sucursal);
            ArrayList<LcPais> paises = es.getLcEmpPais();
            request.setAttribute("paises", paises);

            ArrayList<LcTiposIdentificacion> tipIDE = es.getLcEmpTipIDE();
            request.setAttribute("tipIDE", tipIDE);
            request.getRequestDispatcher("sistema/empresa/frm_empresa.jsp").forward(request, response);
            //request.getContextPath()+
        }

        if (accion.equals("registrar")) {
            int id_empresa;

            //mpresaOperaciones op =new EmpresaOperaciones();
            ArrayList<LcEmpresa> datos = es.getLcEmpresa();
            // en esta linea solo sirve cuando existen datos en la tabla
            if (datos.isEmpty()) {
                id_empresa = 1;
            } else {
                //int secuencia = es.SecuenciaModulo();
                int secuencia = Integer.parseInt(es.getNext().toString());
                id_empresa = secuencia;
            }

            Date fecha_reg = new Date();

            int tipo_identificacion = Integer.parseInt(request.getParameter("t_identificacion"));
            String identificacion = request.getParameter("identificacion");
            String nom_emp = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String mail = request.getParameter("mail");
            int pais = Integer.parseInt(request.getParameter("pais"));
            int provincia = Integer.parseInt(request.getParameter("provincia"));
            int ciudad = Integer.parseInt(request.getParameter("ciudad"));
            //int sucursal=Integer.parseInt(request.getParameter("sucursal"));
            String telefono2 = request.getParameter("telefono2");
            String celular = request.getParameter("celular");
            ArrayList<LcEmpresa> encuentra = es.getDatoEncontrado(identificacion);
            //int cab_grupo=0;
            if (encuentra.isEmpty()) {
                es.addEmpresa(new LcEmpresa(id_empresa,
                        (new LcCiudad(ciudad)),
                        (new LcPais(pais)),
                        (new LcProvincia(provincia)),
                        (new LcTiposIdentificacion(tipo_identificacion)),
                        identificacion,
                        nom_emp,
                        direccion,
                        telefono,
                        mail,
                        fecha_reg, fecha_reg, "A",
                        telefono2, celular, null, null, null, null, null, null, null, null, null, null, null, null
                ));
                //response.getWriter().println("False");           
                response.getWriter().println("Empresa Creado Exitosamente");
            }

            //response.sendRedirect("/laticobsa/empresa?accion=listar");
            // request.getRequestDispatcher("sistema/empresa/frm_empresa.jsp").forward(request, response);
            //request.getContextPath()+
        }
        if (accion.equals("buscaID")) {

            int id = Integer.parseInt(request.getParameter("id"));

            if (id != 0) {

                List<LcEmpresa> enterprise = es.getDatosLCZonasID(id);
                request.setAttribute("enterprise", enterprise);
                ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
                request.setAttribute("empresas", empresas);
                ArrayList<LcPais> paises = es.getLcEmpPais();
                request.setAttribute("paises", paises);
//                ArrayList<LcProvincia> provincias = es.getLcEmpProvincia();
//                request.setAttribute("provincias", provincias);
//                ArrayList<LcCiudad> ciudades = es.getLcEmpCiudad();
//                request.setAttribute("ciudades", ciudades);
                ArrayList<LcTiposIdentificacion> tipIDE = es.getLcEmpTipIDE();
                request.setAttribute("tipIDE", tipIDE);
                //se envia los datos al formulario para actualizar
                request.getRequestDispatcher("sistema/empresa/frm_empresa_up.jsp").forward(request, response);

            } else {
                response.getWriter().println("No existen datos a consultar");

            }

        }
        if (accion.equals("editar")) {

            int id = Integer.parseInt(request.getParameter("idempresa"));
            int tipo_identificacion = Integer.parseInt(request.getParameter("t_identificacion"));
            String identificacion = request.getParameter("identificacion");
            String nom_emp = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String mail = request.getParameter("mail");
            int pais = Integer.parseInt(request.getParameter("pais"));
            int provincia = Integer.parseInt(request.getParameter("provincia"));
            int ciudad = Integer.parseInt(request.getParameter("ciudad"));
            //int sucursal=Integer.parseInt(request.getParameter("sucursal"));
            String telefono2 = request.getParameter("telefono2");
            String celular = request.getParameter("celular");
            es.updateEmpresa(id, pais, ciudad, provincia, tipo_identificacion, identificacion, nom_emp, direccion, telefono, mail, telefono2, celular);
            response.getWriter().println("Registro de Empresa Actualizado");

        }
        if (accion.equals("eliminar")) {

            int id = Integer.parseInt(request.getParameter("id"));
            es.deleteEmpresa(id);
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
