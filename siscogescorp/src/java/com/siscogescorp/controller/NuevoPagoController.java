/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcClientes;
import com.siscogescorp.modelo.LcCuotas;
import com.siscogescorp.modelo.LcDatosDeudores;
import com.siscogescorp.modelo.LcDetRecaudaciones;
import com.siscogescorp.modelo.LcEmpleados;
import com.siscogescorp.modelo.LcFormapagoRecaudacion;
import com.siscogescorp.modelo.LcInstitucion;
import com.siscogescorp.modelo.LcRecaudaciones;
import com.siscogescorp.modelo.LcTipoFormaPago;
import com.siscogescorp.modelo.LcTiposIdentificacion;
import com.siscogescorp.servicios.AudTrasaccionesServicios;
import com.siscogescorp.servicios.ClientesServicios;
import com.siscogescorp.servicios.ConsultaxCarteraServicios;
import com.siscogescorp.servicios.EmpleadosServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.NuevoPagoServicios;
import com.siscogescorp.servicios.RecaudacionServicios;
import com.siscogescorp.utils.ArchivoLog;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class NuevoPagoController extends HttpServlet {

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
        HttpSession sesion = request.getSession(true);
        NuevoPagoServicios rst = new NuevoPagoServicios();
        RecaudacionServicios rs = new RecaudacionServicios();
        EmpresaServicios es = new EmpresaServicios();
        EmpleadosServicios emp = new EmpleadosServicios();
        ClientesServicios cl = new ClientesServicios();
        ArchivoLog grb = new ArchivoLog();
        ConsultaxCarteraServicios cd = new ConsultaxCarteraServicios();
        AudTrasaccionesServicios aud = new AudTrasaccionesServicios();
        String id_empresas;
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID = Integer.parseInt(id_empresas);
        String id_empleados,NameUser;
        id_empleados = sesion.getAttribute("Sstrempleado").toString();
        int EmpleadoID = Integer.parseInt(id_empleados);
        NameUser = sesion.getAttribute("NombreID").toString();
        if (accion.equals("agregar")) {
            try{
            request.getRequestDispatcher("sistema/caja/NuevoPago.jsp").forward(request, response);
            } catch (Exception ex) {
                try{grb.grabaLog("NuevoPagoController_agregar Nuevarecaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("eliminar")) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            rst.deleteCabecera(id);
            List<LcDetRecaudaciones> ListRecaudacion = rst.getDEtalle(id);
            if (!ListRecaudacion.isEmpty()) {
                for (int i = 0; i < ListRecaudacion.size(); i++) {
                    int idDEtalle = ListRecaudacion.get(i).getIdDetRecaudacion();
                    rst.deletePago(idDEtalle);
                }
            }
            List<LcFormapagoRecaudacion> ListPago = rst.getFormPago(id);
            int idForma = 0;
            if (!ListPago.isEmpty()) {
                for (int j = 0; j < ListPago.size(); j++) {
                    idForma = ListPago.get(j).getIdFormpago();
                    rst.deleteFormaPago(idForma);
                }
            }
            //Guardo en la Tabla auditora de Transacciones
            Date fecha_reg = new Date();
             int id_AudTransaccion=Integer.parseInt(aud.getNext().toString());
                        InetAddress addr = InetAddress.getLocalHost();
                        String hostname = addr.getHostName();
                        String Ip = addr.getHostAddress();
                        System.out.println("Host: " + hostname);
                        System.out.println("IP: " + addr.getHostAddress());
                        String proceso = "Eliminación lógica de una Recaudacion";
                        String datos = "Transacción Eliminada con el ID Recaudacion"+id+" Id forma de pago:"+idForma;
                        String origen ="WEB";
            try {
                aud.GuardarAudTransacciones(NameUser, Ip, proceso, datos,origen);
            } catch (Exception ex) {
                try{grb.grabaLog("NuevoPagoController_eliminar Nuevarecaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("cuotatable")) {
            try{
            int idRecaudacion = Integer.parseInt(request.getParameter("idRecaudacion"));
            int cont = Integer.parseInt(request.getParameter("cont"));
            String resp = "";
            List<LcDetRecaudaciones> dataIDCuotas = rst.getDEtalleid(idRecaudacion);
            resp += "<tbody>";
            
            for (int i = 0; i < dataIDCuotas.size(); i++) {
                resp += "<tr id=\"fila_" + cont + "\">";
                int idDetalle = dataIDCuotas.get(i).getIdDetRecaudacion() ;
                resp += "<input id=\"idDetalle\" style=\"display:none\" value=\'" +idDetalle+ "'\" type=\"text\">";
                resp += "<td class=\"hidden\"><input id=\"idarticulo" + "_" + cont + "\"  type=\"text\"  value='" + dataIDCuotas.get(i).getIdArticulo() + "'>" + dataIDCuotas.get(i).getIdArticulo();
                resp += "<td  value='" + dataIDCuotas.get(i).getIdArticulo() + "'>" + dataIDCuotas.get(i).getIdArticulo();
                resp += "<td value='" + dataIDCuotas.get(i).getValorCuota() + "'><input id=\"valorcuota" + "_" + cont + "\" class=\"hidden\"  type=\"text\"  value='" + dataIDCuotas.get(i).getValorCuota() + "'>" + dataIDCuotas.get(i).getValorCuota();
                resp += "<td value='" + dataIDCuotas.get(i).getInteres() + "'><input id=\"interes" + "_" + cont + "\" class=\"hidden\"  type=\"text\"  value='" + dataIDCuotas.get(i).getInteres() + "'>" + dataIDCuotas.get(i).getInteres();
                resp += "<td value='" + dataIDCuotas.get(i).getMora() + "'><input id=\"valormora" + "_" + cont + "\" class=\"hidden\"  type=\"text\"  value='" + dataIDCuotas.get(i).getMora() + "'>" + dataIDCuotas.get(i).getMora();
                BigDecimal interes = dataIDCuotas.get(i).getInteres();
                BigDecimal valorCuota = dataIDCuotas.get(i).getValorCuota();
                BigDecimal Mora= dataIDCuotas.get(i).getMora();
                BigDecimal sum_total = ((valorCuota.add(interes)).add(Mora));
                resp += "<td value='" + sum_total + "'>" + sum_total;

                resp += "<td><input id=\"valor_pagar" + "_" + cont + "\" onkeypress=\"ValidaSoloNumeros()\" onkeyup=\"Actualizar_saldo2(" + sum_total + "," + cont + ")\"  name=\"valor\"  type=\"text\" value=\'" + dataIDCuotas.get(i).getValorRecaudado() + "\'>";

                resp += "<td> <input style=\"color:#00a65a\" id=\"saldo" + "_" + cont + "\"  type=\"text\"  value='" + dataIDCuotas.get(i).getSaldo() + "'>";

                resp += "<td><a> <span onclick=\"EliminaFila2(" + cont +","+idDetalle+")\" class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>";
                resp += "</tr>";

                cont++;
                resp += "</tbody>";
                BigDecimal totalpagar = (dataIDCuotas.get(i).getValorRecaudado()).add(dataIDCuotas.get(i).getValorRecaudado());
                resp += "<input id=\"valorpagar\" style=\"display:none\" value=\'" + totalpagar + "'\" type=\"text\">";
                
            }
                resp += "<input id=\"conter\" style=\"display:none\" value=\'" + cont + "'\" type=\"text\">";
            response.getWriter().println(resp);
            } catch (Exception ex) {
                try{grb.grabaLog("NuevoPagoController_cuotatable Nuevarecaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            /* TODO output your page here. You may use following sample code. */
//
//            }
//
//        }
        if (accion.equals("contador")) {
            try{
            int idRecaudacion = Integer.parseInt(request.getParameter("idRecaudacion"));
            List<LcDetRecaudaciones> contador = rst.getDEtalleid(idRecaudacion);
            int tamanio = contador.size();

            response.getWriter().println(tamanio);
            } catch (Exception ex) {
                try{grb.grabaLog("NuevoPagoController_contador Nuevarecaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("tipopago")) {
            try{
            int idRecaudacion = Integer.parseInt(request.getParameter("idRecaudacion"));
            List<LcFormapagoRecaudacion> formapago = rst.getFormPago(idRecaudacion);
            if(!formapago.isEmpty()){
                 
                 for (int i = 0; i < formapago.size(); i++) {
                     int tipopago = formapago.get(i).getLcTipoFormaPago().getIdTipoFp();
                     if(tipopago == 1){
                         String resp="";
                         BigDecimal valor_efectivo = formapago.get(i).getTotalRecaudado();
                         response.getWriter().println(1+"|"+valor_efectivo+"%");
                     }
                     if(tipopago == 2){
                         BigDecimal valor_tarjeta = formapago.get(i).getTotalRecaudado();
                         int entidad = formapago.get(i).getIdEntidadFinanciera();
                         String num_tarj = formapago.get(i).getNumTarjetaCred();
                         response.getWriter().println(2+"|"+valor_tarjeta+"|"+entidad+"|"+num_tarj+"%");
                     }
                     
                     if(tipopago == 3){
                         BigDecimal valor_cheque = formapago.get(i).getTotalRecaudado();
                         int entidad2 = formapago.get(i).getIdEntidadFinanciera();
                         String num_cheque = formapago.get(i).getNumCheque();
                         response.getWriter().println(3+"|"+valor_cheque+"|"+entidad2+"|"+num_cheque+"%");
                     }
                     
                 }
            }
            } catch (Exception ex) {
                try{grb.grabaLog("NuevoPagoController_tipopago Nuevarecaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("Actcabecera")) {
            try{
            int idRecaudacion = Integer.parseInt(request.getParameter("idRecaudacion"));
            Date fecha_reg = new Date();
            String totalpago = request.getParameter("valor_pagar");
            BigDecimal total_pago = new BigDecimal(totalpago);
            
            rst.updateCabecera(idRecaudacion, total_pago,fecha_reg);
            response.getWriter().println(1);
            } catch (Exception ex) {
                try{grb.grabaLog("NuevoPagoController_Actcabecera Nuevarecaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("Actdetalle")) {
            try{
            int idRecaudacion = Integer.parseInt(request.getParameter("idRecaudacion"));
//            int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
//            int numCuota = Integer.parseInt(request.getParameter("idCuota"));
//            String ValorCuota = request.getParameter("valCuota");
//            BigDecimal Valor_cuota = new BigDecimal(ValorCuota);
            String ValorPagar = request.getParameter("valor_pagar");
            BigDecimal Valor_Pagar = new BigDecimal(ValorPagar);
//            String interes = request.getParameter("interes");
//            BigDecimal Interes = new BigDecimal(interes);
//            String mora = request.getParameter("mora");
//            BigDecimal Mora = new BigDecimal(mora);
            String saldo = request.getParameter("saldo");
            BigDecimal Saldo = new BigDecimal(saldo);
            Date fecha_act = new Date();
            List<LcDetRecaudaciones> detRecaudacion = rst.getDEtalle(idRecaudacion);
            for (int i = 0; i < detRecaudacion.size(); i++) {
            int idDetRecaudacion = detRecaudacion.get(i).getIdDetRecaudacion();
            rst.updateDetalle(idDetRecaudacion,Valor_Pagar,Saldo,fecha_act);//idArticulo,numCuota,Valor_cuota,Interes,Mora,
            }
            } catch (Exception ex) {
                try{grb.grabaLog("NuevoPagoController_Actdetalle Nuevarecaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("eliminafila")) {
            try{
            int idDetalle = Integer.parseInt(request.getParameter("idDetalle"));
            //List<LcDetRecaudaciones> dataIDCuotas = rst.getDEtalleide(idDetalle);
            rst.updateDEtallEstado(idDetalle);
            response.getWriter().println("Eliminar");
            } catch (Exception ex) {
                try{grb.grabaLog("NuevoPagoController_eliminafila Nuevarecaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("Actipago")) {
            try{
            int idRecaudacion = Integer.parseInt(request.getParameter("idRecaudacion"));
            List<LcFormapagoRecaudacion> FPRecaudacion = rst.getFormPago(idRecaudacion);
            String valor_efectivo = request.getParameter("valor_efectivo");
            BigDecimal ValorEfectivo = new BigDecimal(valor_efectivo);
            String valor_tarjeta = request.getParameter("valor_tarjeta");
            BigDecimal ValorTarjeta = new BigDecimal(valor_tarjeta);
            int inst_tarjeta = Integer.parseInt(request.getParameter("inst_tarjeta"));
            String num_tarjeta = request.getParameter("num_tarjeta");
            String valor_cheque = request.getParameter("valor_cheque");
            BigDecimal ValorCheque = new BigDecimal(valor_cheque);
            int inst_cheque = Integer.parseInt(request.getParameter("inst_cheque"));
            String num_cheque = request.getParameter("num_cheque");
            String num_cuenta = request.getParameter("num_cuenta");
            int pago_efectivo = 1, pago_tarjeta = 2,pago_cheque = 3;
            
            Date fecha_act = new Date();
            int id_FormPagrecaudacion = Integer.parseInt(rs.getNext3().toString());
            
            //Actualiza datos solo de efectivo
             if ((inst_tarjeta == 0)&&(inst_cheque == 0)) {
                for (int i = 0; i < FPRecaudacion.size(); i++) {
                    int idPago = FPRecaudacion.get(i).getIdFormpago();
                    int TipoPago = FPRecaudacion.get(i).getLcTipoFormaPago().getIdTipoFp();
                    rst.updateTipoPago(idPago, TipoPago, ValorEfectivo, ValorTarjeta, inst_tarjeta, num_tarjeta, ValorCheque, inst_cheque, num_cheque, fecha_act);
                    
                }
             }
            //Actualiza todos
             if ((inst_tarjeta != 0)&&(inst_cheque != 0)&&(!valor_efectivo.equals("0"))) {
                for (int i = 0; i < FPRecaudacion.size(); i++) {
                    int idPago = FPRecaudacion.get(i).getIdFormpago();
                    int TipoPago = FPRecaudacion.get(i).getLcTipoFormaPago().getIdTipoFp();
                    rst.updateTipoPago(idPago, TipoPago, ValorEfectivo, ValorTarjeta, inst_tarjeta, num_tarjeta, ValorCheque, inst_cheque, num_cheque, fecha_act);
                }
             }
            //Actualiza datos solo de tarjeta
             if ((valor_efectivo.equals("0"))&&(inst_cheque == 0)) {
                for (int i = 0; i < FPRecaudacion.size(); i++) {
                    int idPago = FPRecaudacion.get(i).getIdFormpago();
                    int TipoPago = FPRecaudacion.get(i).getLcTipoFormaPago().getIdTipoFp();
                    rst.updateTipoPago(idPago, TipoPago, ValorEfectivo, ValorTarjeta, inst_tarjeta, num_tarjeta, ValorCheque, inst_cheque, num_cheque, fecha_act);
                }
             } 
             
            //Actualiza datos solo de cheque
             if ((valor_efectivo.equals("0"))&&(inst_tarjeta == 0)) {
                for (int i = 0; i < FPRecaudacion.size(); i++) {
                    int idPago = FPRecaudacion.get(i).getIdFormpago();
                    int TipoPago = FPRecaudacion.get(i).getLcTipoFormaPago().getIdTipoFp();
                    
                    if(!valor_cheque.equals("0")){
                         rst.updateTipoPagoEstado(idPago);
                          rst.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
                                (new LcRecaudaciones(idRecaudacion)),
                                (new LcTipoFormaPago(3)),
                                ValorTarjeta,
                                inst_cheque,
                                num_cheque,
                                num_cuenta,
                                null,
                                fecha_act,
                                fecha_act,
                                fecha_act, "A"));
                    
                    }else{
                        rst.updateTipoPago(idPago, TipoPago, ValorEfectivo, ValorTarjeta, inst_tarjeta, num_tarjeta, ValorCheque, inst_cheque, num_cheque, fecha_act);
                    }
                }
             } 
            //Actualiza datos de efectivo y tarjeta
            if ((inst_cheque == 0)) {
                for (int i = 0; i < FPRecaudacion.size(); i++) {
                    int idPago = FPRecaudacion.get(i).getIdFormpago();
                    int TipoPago = FPRecaudacion.get(i).getLcTipoFormaPago().getIdTipoFp();
                    if((!valor_cheque.equals("0"))&&(!valor_tarjeta.equals("0"))){
                    rst.updateTipoPago(idPago, TipoPago, ValorEfectivo, ValorTarjeta, inst_tarjeta, num_tarjeta, ValorCheque, inst_cheque, num_cheque, fecha_act);
                    }
                    if(TipoPago == 1){
                          rst.updateTipoPagoEstado(idPago);
                          rst.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
                                (new LcRecaudaciones(idRecaudacion)),
                                (new LcTipoFormaPago(2)),
                                ValorTarjeta,
                                inst_tarjeta,
                                null,null,
                                num_tarjeta,
                                fecha_act,
                                fecha_act,
                                fecha_act, "A"));
                      }
                      if(TipoPago == 2){
                          rst.updateTipoPago(idPago, TipoPago, ValorEfectivo, ValorTarjeta, inst_tarjeta, num_tarjeta, ValorCheque, inst_cheque, num_cheque, fecha_act);
                      }
                      if(!(valor_efectivo.equals("0"))){
                          rst.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
                                (new LcRecaudaciones(idRecaudacion)),
                                (new LcTipoFormaPago(1)),
                                ValorEfectivo,
                                0,
                                null,null,
                                null,
                                fecha_act,
                                fecha_act,
                                fecha_act, "A"));
                      }
                }
            }
            //Actualiza datos de tarjeta y cheque
            if (valor_efectivo.equals("0")) {
                for (int i = 0; i < FPRecaudacion.size(); i++) {
                    int idPago = FPRecaudacion.get(i).getIdFormpago();
                    int TipoPago = FPRecaudacion.get(i).getLcTipoFormaPago().getIdTipoFp();
                    if((!valor_cheque.equals("0"))&&(!valor_tarjeta.equals("0"))){
                    rst.updateTipoPago(idPago, TipoPago, ValorEfectivo, ValorTarjeta, inst_tarjeta, num_tarjeta, ValorCheque, inst_cheque, num_cheque, fecha_act);
                    }
//                    if(valor_tarjeta.equals("0")){
                      if(TipoPago == 1){
                          rst.updateTipoPagoEstado(idPago);
                          rst.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
                                (new LcRecaudaciones(idRecaudacion)),
                                (new LcTipoFormaPago(3)),
                                ValorCheque,
                                inst_cheque,
                                num_cheque,
                                num_cuenta,
                                null,
                                fecha_act,
                                fecha_act,
                                fecha_act, "A"));
                      }
                      if(TipoPago == 2){
                          rst.updateTipoPago(idPago, TipoPago, ValorEfectivo, ValorTarjeta, inst_tarjeta, num_tarjeta, ValorCheque, inst_cheque, num_cheque, fecha_act);
                      }
                      
                    //}
//                    if(valor_cheque.equals("0")){
//                        rst.updateTipoPagoEstado(idPago);
//                      rst.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
//                                (new LcRecaudaciones(idRecaudacion)),
//                                (new LcTipoFormaPago(1)),
//                                ValorEfectivo,
//                                0,
//                                null,
//                                null,
//                                fecha_act,
//                                fecha_act,
//                                fecha_act, "A"));
//                    }
                    
                    
                }
            }
            //Actualiza datos de efectivo y cheque
            if ((inst_tarjeta == 0)) {
                for (int i = 0; i < FPRecaudacion.size(); i++) {
                    int idPago = FPRecaudacion.get(i).getIdFormpago();
                    int TipoPago = FPRecaudacion.get(i).getLcTipoFormaPago().getIdTipoFp();
                    if((!valor_cheque.equals("0"))&&(!valor_tarjeta.equals("0"))){
                    rst.updateTipoPago(idPago, TipoPago, ValorEfectivo, ValorTarjeta, inst_tarjeta, num_tarjeta, ValorCheque, inst_cheque, num_cheque, fecha_act);
                    }
                    if(valor_tarjeta.equals("0")){
                      if(TipoPago == 2){
                          rst.updateTipoPagoEstado(idPago);
                          rst.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
                                (new LcRecaudaciones(idRecaudacion)),
                                (new LcTipoFormaPago(1)),
                                ValorEfectivo,
                                0,
                                null,null,
                                null,
                                fecha_act,
                                fecha_act,
                                fecha_act, "A"));
                      }
                      if(TipoPago == 3){
                          rst.updateTipoPago(idPago, TipoPago, ValorEfectivo, ValorTarjeta, inst_tarjeta, num_tarjeta, ValorCheque, inst_cheque, num_cheque, fecha_act);
                      }
                      if(!valor_efectivo.equals("0")){
                          rst.updateTipoPago(idPago, TipoPago, ValorEfectivo, ValorTarjeta, inst_tarjeta, num_tarjeta, ValorCheque, inst_cheque, num_cheque, fecha_act);
                          rst.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
                                (new LcRecaudaciones(idRecaudacion)),
                                (new LcTipoFormaPago(3)),
                                ValorCheque,
                                inst_cheque,
                                num_cheque,
                                num_cuenta,
                                null,
                                fecha_act,
                                fecha_act,
                                fecha_act, "A"));
                      }
                      if(!valor_tarjeta.equals("0")){}
                    }
                }
                //Actualiza datos solo de efectivo
               
//               
            }
            } catch (Exception ex) {
                try{grb.grabaLog("NuevoPagoController_Actipago Nuevarecaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
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
