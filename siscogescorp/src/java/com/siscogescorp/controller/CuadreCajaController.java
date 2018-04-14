/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcCuadreCaja;
import com.siscogescorp.servicios.CuadreCajaServicios;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ViewSoft
 */
public class CuadreCajaController extends HttpServlet {

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
        CuadreCajaServicios cc= new CuadreCajaServicios();
        String accion;
        accion = request.getParameter("accion");
        HttpSession sesion = request.getSession(true);
        String id_empresas, id_empleados, id_sucursal;
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID = Integer.parseInt(id_empresas);
        id_empleados = sesion.getAttribute("Sstrempleado").toString();
        int EmpleadoID = Integer.parseInt(id_empleados);
        id_sucursal = sesion.getAttribute("Sstrsucursal").toString();
        int SucursalID = Integer.parseInt(id_sucursal);
        if (accion.equals("listar")) {
            String Tabla="", table_efectivo="", table_cheque="", table_tarjeta="",sumatotal="";
            try {
                Tabla=cc.getRecaudaciones();
                String separo[] = Tabla.split("&");
                if(separo.length==4){
                table_efectivo=separo[0];
                table_cheque=separo[1];
                table_tarjeta=separo[2];
                sumatotal=separo[3];
                request.setAttribute("Tablacaja", table_efectivo);
                request.setAttribute("Tablacheque", table_cheque);
                request.setAttribute("Tablatarjeta", table_tarjeta);
                request.setAttribute("sumatotal", sumatotal);
                }
                request.getRequestDispatcher("sistema/caja/frm_cuadre_caja.jsp").forward(request, response); 
            } catch (SQLException ex) {
                Logger.getLogger(CuadreCajaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if (accion.equals("consultacuadre")) {
            String Tabla="", table_efectivo="", table_cheque="", table_tarjeta="",sumatotal="";
            try {        
            String fecha_desde=request.getParameter("fecha_desde");
            String fecha_hasta=request.getParameter("fecha_hasta");
            Tabla=cc.getRecaudacionesxFecha(fecha_desde, fecha_hasta);
            String separo[] = Tabla.split("&");
                if(separo.length==4){
                table_efectivo=separo[0];
                table_cheque=separo[1];
                table_tarjeta=separo[2];
                sumatotal=separo[3];
                request.setAttribute("Tablacaja", table_efectivo);
                request.setAttribute("Tablacheque", table_cheque);
                request.setAttribute("Tablatarjeta", table_tarjeta);
                request.setAttribute("sumatotal", sumatotal);
                
                }
                if(separo.length==0){
                    response.getWriter().println(0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CuadreCajaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("guardar_cuadre")) {
            try{
                int id_forma_pago=Integer.parseInt(request.getParameter("id_forma_pago"));
                int id_deudor=Integer.parseInt(request.getParameter("id_deudor"));
                int id_recaudacion_caja=Integer.parseInt(request.getParameter("id_recaudacion"));
                String fecha_cobro_caja=request.getParameter("fecha_cobro");
                String nombres_completo_caja=request.getParameter("nombres_completo");
                String total_recaudado_caja=request.getParameter("total_recaudado");
                BigDecimal total = new BigDecimal(total_recaudado_caja);
                int id_cuadre = Integer.parseInt(cc.getNext().toString());
                Date fecha_reg = new Date();
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date fecha_pago = dateFormatter.parse(fecha_cobro_caja);
                 cc.addCuadre(new LcCuadreCaja(id_cuadre,
                            id_forma_pago,
                            EmpleadoID, EmpresaID,SucursalID,
                            id_recaudacion_caja,
                            fecha_pago,
                            id_deudor,
                            total,
                            fecha_reg, fecha_reg, "A"
                            
                    ));
            }catch (ParseException e) {
                e.printStackTrace();
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
