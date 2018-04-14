/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcCiudad;
import com.siscogescorp.modelo.LcClientes;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcPais;
import com.siscogescorp.modelo.LcProvincia;
import com.siscogescorp.modelo.LcRoles;
import com.siscogescorp.modelo.LcTiposIdentificacion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.siscogescorp.servicios.ClientesServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ViewSoft
 */
public class ClientesController extends HttpServlet {

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
        ClientesServicios es = new ClientesServicios();
        EmpresaServicios em = new EmpresaServicios();
        String accion;
        accion = request.getParameter("accion");
        HttpSession sesion = request.getSession(true);
        String id_empresas;

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
                List<LcClientes> clientes = es.getLcClientess();
                request.setAttribute("clientes", clientes);
            }
            if (NivelID == 1) {
                List<LcClientes> clientes = es.getClientesxempresa(EmpresaID);
                request.setAttribute("clientes", clientes);
            }
            if ((NivelID != 0) && (NivelID != 1)) {
                List<LcClientes> clientes = es.getClientesxempresa(EmpresaID);
                request.setAttribute("clientes", clientes);
            }

            request.getRequestDispatcher("sistema/cliente/lista_clientes.jsp").forward(request, response);
        }
        if (accion.equals("agregar")) {
            if (NivelID == 0) {
                ArrayList<LcEmpresa> empresas = em.getLcEmpresa();
                request.setAttribute("empresas", empresas);
            }
            if (NivelID == 1) {
                List<LcRoles> empresaso = em.getLcEmpresaRol(EmpresaID, RolID);
                int Empresa = empresaso.get(0).getLcEmpresa().getIdEmpresa();
                ArrayList<LcEmpresa> empresas = em.getLcEmpresalog(Empresa);
                request.setAttribute("empresas", empresas);
            }
            if ((NivelID != 0) && (NivelID != 1)) {
                List<LcRoles> empresaso = em.getLcEmpresaRol(EmpresaID, RolID);
                int Empresa = empresaso.get(0).getLcEmpresa().getIdEmpresa();
                ArrayList<LcEmpresa> empresas = em.getLcEmpresalog(Empresa);
                request.setAttribute("empresas", empresas);
            }
            
            ArrayList<LcPais> paises = em.getLcEmpPais();
            request.setAttribute("paises", paises);
            ArrayList<LcTiposIdentificacion> tipIDE = em.getLcEmpTipIDE();
            request.setAttribute("tipIDE", tipIDE);
            request.getRequestDispatcher("sistema/cliente/frm_clientes.jsp").forward(request, response);
        }

        if (accion.equals("registrar")) {
            int id_cliente;
            ArrayList<LcClientes> datos = es.getLcClientes();
            if (datos.isEmpty()) {
                id_cliente = 1;
            } else {
                //int secuencia = es.SecuenciaModulo();
                int secuencia = Integer.parseInt(es.getNext().toString());
                id_cliente = secuencia;
            }
           // int empresa2 = Integer.parseInt(request.getParameter("empresa2"));
            int empresa = Integer.parseInt(request.getParameter("empresa"));
            int tipo_identificacion = Integer.parseInt(request.getParameter("Tipo_Identificacion"));
            String identificacion = request.getParameter("identificacion");
            String razon_social = request.getParameter("razon");
            String direccion = request.getParameter("direccion");
            int pais = Integer.parseInt(request.getParameter("pais"));
            int provincia = Integer.parseInt(request.getParameter("provincia"));
            int ciudad = Integer.parseInt(request.getParameter("ciudad"));
            String contacto = request.getParameter("contacto");
            String email = request.getParameter("email");
            String telefono = request.getParameter("fono1");
            String extensioon = request.getParameter("ext");
            String celular = request.getParameter("celular");
            Date fecha_reg = new Date();

            ArrayList<LcClientes> encuentra = es.getDatoEncontrado(identificacion);
            
            if (encuentra.isEmpty()) {
               // if (empresa == 0) {
                    es.addclientes(new LcClientes(id_cliente,
                            (new LcCiudad(ciudad)),
                            (new LcEmpresa(empresa)),
                            (new LcPais(pais)),
                            (new LcProvincia(provincia)),
                            (new LcTiposIdentificacion(tipo_identificacion)),
                            identificacion,
                            razon_social,
                            direccion,
                            contacto,
                            email,
                            telefono,
                            extensioon,
                            celular,
                            //fecha_reg, "A", null, null, null, null,null));
                            fecha_reg, "A",  null, null, null,null,null,null,null));

                response.getWriter().println("Cliente Ingresado Correctamente");
            }

        }
        if (accion.equals("buscaID")) {

            int id = Integer.parseInt(request.getParameter("id"));

            if (id != 0) {

                List<LcClientes> client = es.getDatosLClienteID(id);
                request.setAttribute("client", client);
                if (EmpresaID == 1) {
                    ArrayList<LcEmpresa> empresas = em.getLcEmpresa();
                    request.setAttribute("empresas", empresas);
                } else {

                    ArrayList<LcEmpresa> empresas = em.getLcEmpresalog(EmpresaID);
                    request.setAttribute("empresas", empresas);
                }
                ArrayList<LcTiposIdentificacion> tipIDE = em.getLcEmpTipIDE();
                request.setAttribute("tipIDE", tipIDE);
                ArrayList<LcPais> paises = em.getLcEmpPais();
                request.setAttribute("paises", paises);

                request.getRequestDispatcher("sistema/cliente/frm_clientes_up.jsp").forward(request, response);

            } else {
                response.getWriter().println("No existen datos a consultar");

            }

        }
        if (accion.equals("editar")) {

            int idcliente = Integer.parseInt(request.getParameter("idcliente"));
            int empresa = Integer.parseInt(request.getParameter("empresa"));
            int tipo_identificacion = Integer.parseInt(request.getParameter("Tipo_Identificacion"));
            String identificacion = request.getParameter("identificacion");
            String razon_social = request.getParameter("razon");
            String direccion = request.getParameter("direccion");
            int pais = Integer.parseInt(request.getParameter("pais"));
            int provincia = Integer.parseInt(request.getParameter("provincia"));
            int ciudad = Integer.parseInt(request.getParameter("ciudad"));
            String contacto = request.getParameter("contacto");
            String email = request.getParameter("email");
            String telefono = request.getParameter("fono1");
            String extensioon = request.getParameter("ext");
            String celular = request.getParameter("celular");

            es.updateClientes(idcliente, empresa, tipo_identificacion, identificacion, razon_social, direccion, pais, provincia, ciudad, contacto, email, telefono, extensioon, celular);
            response.getWriter().println("Registro de Clientes Actualizado");

        }
        if (accion.equals("eliminar")) {

            int id = Integer.parseInt(request.getParameter("id"));
            es.deleteClientes(id);
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
