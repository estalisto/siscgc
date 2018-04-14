/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcAgencia;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcRoles;
import com.siscogescorp.servicios.AgenciaServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "AgenciaController", urlPatterns = {"/agencia"})
public class AgenciaController extends HttpServlet {

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
        accion = request.getParameter("accion");
        AgenciaServicios ag = new AgenciaServicios();
        EmpresaServicios es = new EmpresaServicios();
        PrintWriter out = response.getWriter();
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
                List<LcAgencia> agencias = ag.getLcAgencias();
                request.setAttribute("agencias", agencias);
            }
            if (NivelID == 1) {
                List<LcAgencia> agencias = ag.getLcAgenciasxEmpresa(EmpresaID);
                request.setAttribute("agencias", agencias);
            }
            if ((NivelID != 0) && (NivelID != 1)) {
                List<LcAgencia> agencias = ag.getLcAgenciasxEmpresa(EmpresaID);
                request.setAttribute("agencias", agencias);
            }
            request.getRequestDispatcher("sistema/agencia/lista_agencia.jsp").forward(request, response);

        }
        if (accion.equals("agregar")) {

            if (NivelID == 0) {
                ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
                request.setAttribute("empresas", empresas);
            }
            if (NivelID == 1) {

                List<LcRoles> empresaso = es.getLcEmpresaRol(EmpresaID, RolID);
                int Empresa = empresaso.get(0).getLcEmpresa().getIdEmpresa();
                ArrayList<LcEmpresa> empresas = es.getLcEmpresalog(Empresa);
                request.setAttribute("empresas", empresas);
            }
            if ((NivelID != 0) && (NivelID != 1)) {
                List<LcRoles> empresaso = es.getLcEmpresaRol(EmpresaID, RolID);
                int Empresa = empresaso.get(0).getLcEmpresa().getIdEmpresa();
                ArrayList<LcEmpresa> empresas = es.getLcEmpresalog(Empresa);
                request.setAttribute("empresas", empresas);
            }
            request.getRequestDispatcher("sistema/agencia/frm_agencia.jsp").forward(request, response);
        }

        if (accion.equals("registrar")) {
            int id_agencia;
            ArrayList<LcAgencia> datos = ag.getLcAgencia();
            if (datos.isEmpty()) {
                id_agencia = 1;
            } else {
                //int secuencia = ag.SecuenciaModulo();
                int secuencia = Integer.parseInt(ag.getNext().toString());
                id_agencia = secuencia ;
            }
           // int empresa2 = Integer.parseInt(request.getParameter("empresa2"));
            Date fecha_reg = new Date();
            int empresa = Integer.parseInt(request.getParameter("empresa"));
            String nombre = request.getParameter("nombre");
            String opcion = request.getParameter("opcion");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String telefono2 = request.getParameter("telefono2");
            String celular = request.getParameter("celular");
            String mail = request.getParameter("mail");
            //if (empresa == 0) {
                ag.addAgencia(new LcAgencia(id_agencia,
                        (new LcEmpresa(empresa)),
                        nombre,
                        opcion,
                        direccion,
                        telefono,
                        telefono2,
                        celular,
                        mail,
                        fecha_reg, "A", null,
                        null,null));
/*
                  private int idAgencia;
     private LcEmpresa lcEmpresa;
     private String nombre;
     private String opcion;
     private String direccion;
     private String telefono;
     private String telefono2;
     private String celular;
     private String email;
     private Date fechaCreacion;
     private String estado;
                */
                
           /*}
            if (empresa2 == 0) {
                ag.addAgencia(new LcAgencia(id_agencia,
                        (new LcEmpresa(empresa)),
                        nombre,
                        opcion,
                        direccion,
                        telefono,
                        telefono2,
                        celular,
                        mail,
                        fecha_reg, "A", null,
                        null));
            }*/
            response.getWriter().println("Agencia Creado Exitosamente");
        }
        if (accion.equals("eliminar")) {

            int id = Integer.parseInt(request.getParameter("id"));
            ag.deleteAgencia(id);
            //response.getWriter().println("Agencia Eliminada");
        }

        if (accion.equals("buscaID")) {

            int id = Integer.parseInt(request.getParameter("id"));

            if (id != 0) {

                List<LcAgencia> agent = ag.getDatosLCAgenciaID(id);
                request.setAttribute("agent", agent);
                if (NivelID == 0) {
                    ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
                    request.setAttribute("empresas", empresas);
                } if (NivelID == 1) {

                    ArrayList<LcEmpresa> empresas = es.getLcEmpresalog(EmpresaID);
                    request.setAttribute("empresas", empresas);
                }
                if ((NivelID != 0) && (NivelID != 1)) {
                    ArrayList<LcEmpresa> empresas = es.getLcEmpresalog(EmpresaID);
                    request.setAttribute("empresas", empresas);
                }
                request.getRequestDispatcher("sistema/agencia/frm_agencia_up.jsp").forward(request, response);

            } else {
                response.getWriter().println("No existen datos a consultar");

            }

        }
        if (accion.equals("editar")) {

            int idagencia = Integer.parseInt(request.getParameter("idagencia"));
            int empresa = Integer.parseInt(request.getParameter("empresa"));
            String nombre = request.getParameter("nombre");
            String opcion = request.getParameter("opcion");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String telefono2 = request.getParameter("telefono2");
            String celular = request.getParameter("celular");
            String mail = request.getParameter("mail");

            ag.updateAgencia(idagencia, empresa, nombre, opcion, direccion, telefono, telefono2, celular, mail);
            response.getWriter().println("Datos de la Agencia Actualizado exitosamente");
        }
        
        if (accion.equals("ConsultaAgencia")) {
           int empresa = Integer.parseInt(request.getParameter("empresa"));
           List<LcAgencia> agencias = ag.getLcAgenciasxEmpresa(empresa);
           String DatosAgencias="";
            for (LcAgencia agencia : agencias) {
                DatosAgencias += "<option value="+agencia.getIdAgencia() + ">" + agencia.getNombre() + "</options>";
                
            }
            
           if(!DatosAgencias.isEmpty()){
               String caebAgencia="<option value=0> Seleccione una Agencia</option>";
               DatosAgencias=caebAgencia+DatosAgencias;
                response.getWriter().println(DatosAgencias);
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
