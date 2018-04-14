/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.servicios.ConsultaclienteServicios;
import com.siscogescorp.servicios.ConsultaxCarteraServicios;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CIMA2015
 */
public class ConsultaClienteController extends HttpServlet {

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
        String id_empresas, id_empleados, id_sucursal;
        HttpSession sesion = request.getSession(true);
        ConsultaxCarteraServicios cd = new ConsultaxCarteraServicios();
        ConsultaclienteServicios cc= new ConsultaclienteServicios();
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID= Integer.parseInt(id_empresas);
        id_empleados = sesion.getAttribute("Sstrempleado").toString();
        int EmpleadoID= Integer.parseInt(id_empleados);
        id_sucursal = sesion.getAttribute("Sstrsucursal").toString();
        int SucursalID= Integer.parseInt(id_sucursal);
        String SSqlDatosDeudor="";
        
         if(accion.equals("listar"))
        {   
            
         //  List<LcClientes> carteras = cd.getLcDatoscliente(EmpresaID);  
           request.setAttribute("carteras", 1);
          
           request.getRequestDispatcher("sistema/gestion/consulta_cliente.jsp").forward(request, response);
        }
        if (accion.equals("BuscarDeudores")) {
            int cartera = Integer.parseInt(request.getParameter("cartera"));
            String identificacion = request.getParameter("ide");
            String nombres = request.getParameter("nombre_completo");
            String Consulta="";
            try {
                    if(identificacion!=null){
                        Consulta=cc.NuevaBusquedaDeudorIDE(identificacion, cartera);
                        response.getWriter().println(Consulta);
                    }
                    if(nombres!=null){
                        Consulta=cc.NuevaBusquedaDeudor(nombres, cartera);
                        //request.setAttribute("stConsulta", Consulta);
                        response.getWriter().println(Consulta);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
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
