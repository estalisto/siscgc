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
import com.siscogescorp.servicios.ClientesServicios;
import com.siscogescorp.servicios.ConsultaxCarteraServicios;
import com.siscogescorp.servicios.EmpleadosServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.PagosServicios;
import com.siscogescorp.servicios.RecaudacionServicios;
import com.siscogescorp.utils.ArchivoLog;
import java.io.IOException;
import static java.lang.Integer.parseInt;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author CIMA2015
 */
public class RecaudacionController extends HttpServlet {

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
        EmpleadosServicios emp = new EmpleadosServicios();
        ClientesServicios cl = new ClientesServicios();
        RecaudacionServicios rs = new RecaudacionServicios();
        PagosServicios rst = new PagosServicios();
        //PermisosServicios ps = new PermisosServicios();
        ArchivoLog grb = new ArchivoLog();
        ConsultaxCarteraServicios cd = new ConsultaxCarteraServicios();
        String accion;
        accion = request.getParameter("accion");
        HttpSession sesion = request.getSession(true);
        String id_empresas;
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID = Integer.parseInt(id_empresas);
        String id_rol,id_sucursal;
        id_rol = sesion.getAttribute("SstrRolID").toString();
        int RolID = Integer.parseInt(id_rol);
        String id_empleados = sesion.getAttribute("Sstrempleado").toString();
        int EmpleadoID= Integer.parseInt(id_empleados);
        id_sucursal = sesion.getAttribute("Sstrsucursal").toString();
        int SucursalID= Integer.parseInt(id_sucursal);
        
        if(accion.equals("autocomplete")){
        try {    
        String nombrs = request.getParameter("company"); 
        int cartera= Integer.parseInt(request.getParameter("cartera")); 
        //ArrayList<LcDatosDeudores> nombres = rs.getLcNombresDeudor(nombrs);
        
        List<String> nombres = rs.getNombreAutocomplete(nombrs, cartera);
        String resp="";
        for (int i = 0; i < nombres.size(); i++) {
            
            //resp +="<option value='"+nombres.get(i).getIdentificacion()+"|"+nombres.get(i).getNombresCompleto()+"'>"; 
            resp +="<option value='"+nombres.get(i)+"'>"; 
        }
        System.out.println(resp);
        response.getWriter().println(resp);
        } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_autocomplete Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (accion.equals("listar")) {
        try{
        List<LcEmpleados> empleados = emp.getEmpleadoxempresa(EmpresaID);
        request.setAttribute("empleados", empleados);
        List<LcClientes> clientes = cl.getClientesxempresa(EmpresaID);
        request.setAttribute("clientes", clientes);
        ArrayList<LcTiposIdentificacion> tipIDE = es.getLcEmpTipIDE();
        request.setAttribute("tipIDE", tipIDE);
        List<LcInstitucion> instituciones = rs.getLcInstituciones();
        request.setAttribute("instituciones", instituciones);
        //response.getWriter().println(resp);
        request.getRequestDispatcher("sistema/caja/frm_recaudacion.jsp").forward(request, response);
        } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_listar Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("BuscarDeudores")) {
            int cartera = Integer.parseInt(request.getParameter("cartera"));
            String identificacion = request.getParameter("ide");
            String nombres = request.getParameter("nombre_completo");
            String Consulta="";
            try {
                    if(identificacion!=null){
                        Consulta=rs.NuevaBusquedaDeudorIDE(identificacion, cartera);
                        response.getWriter().println(Consulta);
                    }
                    if(nombres!=null){
                        Consulta=rs.NuevaBusquedaDeudor(nombres, cartera);
                        //request.setAttribute("stConsulta", Consulta);
                        response.getWriter().println(Consulta);
                    }
                } catch (SQLException ex) {
                try{grb.grabaLog("RecaudacionController_BuscarDeudores Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                    Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("recaudo")) {
         try{   
        int tipo_identificacion = Integer.parseInt(request.getParameter("tipo"));
        int oficial = Integer.parseInt(request.getParameter("oficial"));
        int cartera = Integer.parseInt(request.getParameter("cartera"));
        String identificacion = request.getParameter("ide");
        String nombres = request.getParameter("nombres");
        if((oficial==0)&&(nombres.equals("N"))){
        List<LcDatosDeudores> lista1 = rs.getLcDatosDeudoIDE(EmpresaID,tipo_identificacion , identificacion);
            if(!lista1.isEmpty()){
            String nombre = lista1.get(0).getNombres();
            String apellido = lista1.get(0).getApellidos();
            String completo = lista1.get(0).getNombresCompleto();
            int id = lista1.get(0).getIdDatosDeudor();
            response.getWriter().println(completo+"|"+id);
            }
           
        }
        if((oficial==0)&&(tipo_identificacion==0)&&(identificacion.equals("N"))){
        List<LcDatosDeudores> lista2 = rs.getLcDatosDeudorNombre(EmpresaID,nombres);
            if(!lista2.isEmpty()){
                String identify = lista2.get(0).getIdentificacion();
                int tip = lista2.get(0).getTipoIdentificacion();
                String nom = lista2.get(0).getNombresCompleto();
                int id = lista2.get(0).getIdDatosDeudor();
                response.getWriter().println(identify +"|"+ tip+"|"+nom+"|"+id);
            }
        }
        if((oficial!=0)&&(nombres.equals("N"))){
        List<LcDatosDeudores> lista = rs.getLcDatosDeudores(EmpresaID, oficial, identificacion,cartera,tipo_identificacion);
         if(!lista.isEmpty()){
            String nombre = lista.get(0).getNombres();
            String apellido = lista.get(0).getApellidos();
            String completo = lista.get(0).getNombresCompleto();
            int id = lista.get(0).getIdDatosDeudor(); 
            response.getWriter().println(completo+"|"+id);
         }
        }
        if((oficial!=0)&&(tipo_identificacion==0)&&(identificacion.equals("N"))){
        List<LcDatosDeudores> lista4 = rs.getLcDatosDeudorNombre(EmpresaID,nombres);
            if(!lista4.isEmpty()){
                String identify = lista4.get(0).getIdentificacion();
                int tip = lista4.get(0).getTipoIdentificacion();
                String nom = lista4.get(0).getNombresCompleto();
                int id = lista4.get(0).getIdDatosDeudor();
                response.getWriter().println(identify +"|"+ tip+"|"+nom+"|"+id);
            }
        }
        } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_recaudo Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("cuota")) {
        try{    
        int tipo_identificacion = Integer.parseInt(request.getParameter("tipo"));
        String verifica = request.getParameter("almacen");
        int cartera = Integer.parseInt(request.getParameter("cartera"));
        String identificacion = request.getParameter("ide");
        String nombres = request.getParameter("nombres");
        List<LcDatosDeudores> lista3 = cd.getLcDatosidentifica(identificacion,EmpresaID,cartera);  
         int deudor = lista3.get(0).getIdDatosDeudor();
         List<LcCuotas> dataCuotas = cd.getCuotas(cartera,deudor);
         //request.setAttribute("dataCuotas", dataCuotas);
         String resp="",cuotaa="";
         //int cont=Integer.parseInt(request.getParameter("cont"));
         for (int i = 0; i < dataCuotas.size(); i++) {
            
            resp +="<tbody><tr id=\"fila2_"+i+"\">";
            resp +="<input id=\"contcuota\" style=\"display:none\" value=\'"+i+"'\" type=\"text\">";
            resp +="<td class=\"hidden\" value='"+dataCuotas.get(i).getIdCuota()+"'>"+dataCuotas.get(i).getIdCuota();
            resp +="<td value='"+dataCuotas.get(i).getLcArticulo().getReferencia()+"'>"+dataCuotas.get(i).getLcArticulo().getReferencia(); 
            resp +="<td value='"+dataCuotas.get(i).getNumCuotas()+"'>"+dataCuotas.get(i).getNumCuotas(); 
            resp +="<td value='"+dataCuotas.get(i).getInteresCuota()+"'>"+dataCuotas.get(i).getInteresCuota(); 
            resp +="<td value='"+dataCuotas.get(i).getTotalCuotas()+"'>"+dataCuotas.get(i).getTotalCuotas(); 
             BigDecimal GstAdic = dataCuotas.get(i).getGastosAdicional();
             BigDecimal Mora= dataCuotas.get(i).getValorMora();
             BigDecimal gcobr = dataCuotas.get(i).getGastosCobranzas();
             BigDecimal other = dataCuotas.get(i).getOtrosValores();
             BigDecimal mora_acum = ((GstAdic.add(Mora)).add(gcobr)).add(other);
            resp +="<td value='"+mora_acum.setScale(2)+"'>"+mora_acum.setScale(2); 
            int idCuota=dataCuotas.get(i).getIdCuota();
            if(verifica!=null){
             String array[] = verifica.split("&");
             
            for(int j=1;j<= array.length;j++){
                
                    cuotaa=array[j];
                if(j>i)
                {
                    if((parseInt(cuotaa)==idCuota)&&(!"undefined".equals(cuotaa))){ 
                        resp +="<td><input id=\"check_active_"+i+"\" class=\"check_"+i+"\" onclick=\"Recaudacion("+dataCuotas.get(i).getIdCuota()+","+i+")\" checked  type=\"checkbox\">";
                    break; 
                    }else{
                        resp +="<td><input id=\"check_active_"+i+"\" class=\"check_"+i+"\" onclick=\"Recaudacion("+dataCuotas.get(i).getIdCuota()+","+i+")\"   type=\"checkbox\">";
                    }
                }  else{
                    if(j<i)
                {
                        resp +="<td><input id=\"check_active_"+i+"\" class=\"check_"+i+"\" onclick=\"Recaudacion("+dataCuotas.get(i).getIdCuota()+","+i+")\"   type=\"checkbox\">";
                }
                }  
               
            }
         }else{
            resp +="<td><input id=\"check_active_"+i+"\" class=\"check_"+i+"\" onclick=\"Recaudacion("+dataCuotas.get(i).getIdCuota()+","+i+")\"   type=\"checkbox\">";
            }
            resp +="</tr></tbody>";
        }
         response.getWriter().println(resp);
           } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_cuota Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("Cambiototal")) {
            try{
            String total_cuotas = request.getParameter("checkactive");
            String resp="";
            resp +="<td id=\"newTotal\" value='"+total_cuotas+"'>"+total_cuotas;
            response.getWriter().println(resp);
            } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_Cambiototal Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("listarCuota")) {
            try{
            int idCuota = Integer.parseInt(request.getParameter("idCuota"));
            int cont = Integer.parseInt(request.getParameter("cont"));
            String resp="";
            List<LcCuotas> dataIDCuotas = rs.getIDCuotas(idCuota);
            
            resp +="<tbody>";
            resp +="<tr id=\"fila_"+cont+"\">";
            resp +="<td class=\"hidden\"><input  id=\"idcuota"+"_"+cont+"\"  type=\"text\"  value='"+dataIDCuotas.get(0).getIdCuota()+"'>"+dataIDCuotas.get(0).getIdCuota(); 
            resp +="<td class=\"hidden\"><input id=\"idarticulo"+"_"+cont+"\"  type=\"text\"  value='"+dataIDCuotas.get(0).getLcArticulo().getIdArticulo()+"'>"+dataIDCuotas.get(0).getLcArticulo().getIdArticulo();
            resp +="<td  value='"+dataIDCuotas.get(0).getLcArticulo().getNombreArticulo()+"'>"+dataIDCuotas.get(0).getLcArticulo().getReferencia()+" "+dataIDCuotas.get(0).getLcArticulo().getNombreArticulo(); 
            resp +="<td value='"+dataIDCuotas.get(0).getNumCuotas()+"'>"+dataIDCuotas.get(0).getNumCuotas();
             BigDecimal valorCuota = dataIDCuotas.get(0).getValorCuota();
            resp +="<td value='"+valorCuota+"'><input id=\"valorcuota"+"_"+cont+"\" class=\"hidden\"  type=\"text\"  value='"+valorCuota+"'>"+valorCuota; 
             BigDecimal interes = dataIDCuotas.get(0).getInteresCuota();
            resp +="<td value='"+interes+"'><input id=\"interes"+"_"+cont+"\" class=\"hidden\"  type=\"text\"  value='"+interes+"'>"+interes; 
             BigDecimal GstAdic = dataIDCuotas.get(0).getGastosAdicional();
             BigDecimal Mora= dataIDCuotas.get(0).getValorMora();
             BigDecimal gcobr = dataIDCuotas.get(0).getGastosCobranzas();
             BigDecimal other = dataIDCuotas.get(0).getOtrosValores();
             BigDecimal mora_acum = ((GstAdic.add(Mora)).add(gcobr)).add(other);
            resp +="<td value='"+mora_acum+"'><input id=\"valormora"+"_"+cont+"\" class=\"hidden\"  type=\"text\"  value='"+mora_acum+"'>"+mora_acum; 
             BigDecimal sum_total = ((valorCuota.add(interes)).add(mora_acum));
             BigDecimal capital = dataIDCuotas.get(0).getTotalCuotas();
             BigDecimal saldototal = (sum_total).subtract(sum_total);
            resp +="<td value='"+sum_total+"'>"+sum_total; 
            
            resp +="<td><b><input id=\"valor_pagar"+"_"+cont+"\"  onkeyup=\"Actualizar_saldo("+sum_total+","+cont+")\" onkeypress=\"return filterFloat(event,this);\" name=\"valor\" style=\"color:#00a65a\" type=\"text\" value=\'"+sum_total+"\'></b>";
            
            resp +="<td> <input style=\"color:#00a65a\" id=\"saldo"+"_"+cont+"\"  type=\"text\"  value='"+saldototal+"' disabled=\"true\">";
            
            resp +="<td><a ><span onclick=\"EliminaFila("+cont+")\" class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>";
            resp +="</tbody>";
            BigDecimal totalpagar = (sum_total).add(sum_total);
            resp +="<input id=\"valorpagar\" style=\"display:none\" value=\'"+totalpagar+"'\" type=\"text\">";
            resp +="<input id=\"contando\" style=\"display:none\" value=\'"+cont+"'\" type=\"text\">";
            response.getWriter().println(resp);
            } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_listarCuota Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("guardaregistros")) {
            try{
            int EmpleadoID2 = Integer.parseInt(request.getParameter("id_empleado"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
            String deu=request.getParameter("idDeudor");
            int idDeudor = Integer.parseInt(deu);
            Date fecha_reg = new Date();
            int id_recaudacion = Integer.parseInt(rs.getNext().toString());
            String valor = request.getParameter("valorecaudacion");
            BigDecimal valor_total = new BigDecimal(valor);
            //int id_empleado=Integer.parseInt(request.getParameter("id_empleado"));
            
//            if(EmpleadoID != id_empleado){
//              EmpleadoID2=Integer.parseInt(request.getParameter("id_empleado"));
//            }else {
//             EmpleadoID2=EmpleadoID;
//            }
            
            rs.addRecaudacion(new LcRecaudaciones
                (id_recaudacion,
                 new LcDatosDeudores(idDeudor),
                 EmpresaID,
                 SucursalID,      
                 cartera,
                 EmpleadoID2,
                 valor_total,       
                 fecha_reg,
                 fecha_reg,"A",null,"WEB",null,null));
                 response.getWriter().println(id_recaudacion);
            } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_guardaregistros Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("detalle")) {
            try{
            int id_Detrecaudacion = Integer.parseInt(rs.getNext2().toString());
            int id_recaudacion = Integer.parseInt(request.getParameter("idRecaudacion"));
            int id_articulo = Integer.parseInt(request.getParameter("idArticulo"));
            int id_cuota = Integer.parseInt(request.getParameter("idCuota"));
            String valorCuota = request.getParameter("valorCuota");
            BigDecimal valor_cuota = new BigDecimal(valorCuota);
            String valorPagar = request.getParameter("valorPagar");
            BigDecimal valor_pagar = new BigDecimal(valorPagar);
            String interes = request.getParameter("interes");
            BigDecimal intereses = new BigDecimal(interes);
            String mora = request.getParameter("mora");
            BigDecimal mora_generada = new BigDecimal(mora);
            String saldo = request.getParameter("saldo");
            BigDecimal saldo_total = new BigDecimal(saldo);
            Date fecha_reg = new Date();
            
            rs.addDetRecaudacion(new LcDetRecaudaciones  
                (id_Detrecaudacion,
                 (new LcRecaudaciones(id_recaudacion)),
                 id_articulo,
                 id_cuota,       
                 valor_cuota,
                 valor_pagar,
                 intereses,
                 mora_generada,
                 saldo_total,
                 fecha_reg,
                 fecha_reg, 
                 fecha_reg,"A"));
             //response.getWriter().println(id_recaudacion);
             } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_detalle Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equals("Formapago")) {
            try{
            int id_FormPagrecaudacion = Integer.parseInt(rs.getNext3().toString());
            int id_recaudacion = Integer.parseInt(request.getParameter("idRecaudacion"));
            int formaPago = Integer.parseInt(request.getParameter("formaPago"));
            int formaPago2 = Integer.parseInt(request.getParameter("formaPago2"));
            int formaPago3 = Integer.parseInt(request.getParameter("formaPago3"));
            Date fecha_reg = new Date();
            String totalpago = request.getParameter("totalpago");
            BigDecimal total_pago = new BigDecimal(totalpago);
            String totalpago2 = request.getParameter("totalpago2");
            BigDecimal total_pago2 = new BigDecimal(totalpago2);
            int institucion = Integer.parseInt(request.getParameter("institucion"));
            String num_cuenta = request.getParameter("num_cuenta");
            String num_cheque = request.getParameter("num_cheque");
            String num_tarjeta = request.getParameter("num_tarjeta");
            String totalpago3 = request.getParameter("totalpago3");
            BigDecimal total_pago3 = new BigDecimal(totalpago3);
            int institucion2 = Integer.parseInt(request.getParameter("institucion2"));
            
            if((formaPago == 1)&&(formaPago2 == 0)&&(formaPago3 == 0)){
                rs.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,(new LcRecaudaciones(id_recaudacion)),
                 (new LcTipoFormaPago(formaPago)),total_pago,
                 0,       
                 "","",
                 "",
                 fecha_reg,
                 fecha_reg, 
                 fecha_reg,"A"));
                
                
              
                response.getWriter().println("Recaudacion Guardada Exitosamente");
            }
            if((formaPago == 0)&&(formaPago2 == 2)&&(formaPago3 == 0)){
                rs.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
                        (new LcRecaudaciones(id_recaudacion)),(new LcTipoFormaPago(formaPago2)),
                        total_pago2,
                        institucion,
                        "","",
                        num_tarjeta,
                        fecha_reg,
                        fecha_reg,
                        fecha_reg, "A"));
                response.getWriter().println("Recaudacion Guardada Exitosamente");
            }
            if ((formaPago == 0) && (formaPago2 == 0) && (formaPago3 == 3)) {
                rs.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
                        (new LcRecaudaciones(id_recaudacion)),(new LcTipoFormaPago(formaPago3)),
                        total_pago3,
                        institucion2,
                        num_cheque,
                        num_cuenta,"",
                        fecha_reg,
                        fecha_reg,
                        fecha_reg, "A"));
                response.getWriter().println("Recaudacion Guardada Exitosamente");
            }
            if((formaPago == 1)&&(formaPago2 == 2)&&(formaPago3 == 0)){
                //if(formaPago == 1){
                    rs.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
                            (new LcRecaudaciones(id_recaudacion)),(new LcTipoFormaPago(formaPago)),
                            total_pago,
                            0,
                            "","",
                            "",
                            fecha_reg,
                            fecha_reg,
                            fecha_reg, "A"));
//                }
//                if(formaPago2 == 2){
                    rs.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion+1,
                            (new LcRecaudaciones(id_recaudacion)),(new LcTipoFormaPago(formaPago2)),
                            total_pago2,
                            institucion,
                            "","",
                            num_tarjeta,
                            fecha_reg,
                            fecha_reg,
                            fecha_reg, "A"));
                    response.getWriter().println("Recaudacion Guardada Exitosamente");
                //}
                
            }/*INICIO CAMBIO J.J.G.C*/
            if((formaPago == 1)&&(formaPago2 == 0)&&(formaPago3 == 3)){
                //if(formaPago == 1){
                    rs.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
                            (new LcRecaudaciones(id_recaudacion)),(new LcTipoFormaPago(formaPago)),
                            total_pago,
                            0,
                            "","",
                            "",
                            fecha_reg,
                            fecha_reg,
                            fecha_reg, "A"));
//                }
//                if(formaPago3 == 3){
                    rs.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion+1,
                            (new LcRecaudaciones(id_recaudacion)),(new LcTipoFormaPago(formaPago3)),/* NOMBRE DE VARIABLE ESTABA CAMBIADA*/
                            total_pago3,
                            institucion2,
                            num_cheque,
                            num_cuenta,
                            "",
                            fecha_reg,
                            fecha_reg,
                            fecha_reg, "A"));
                    response.getWriter().println("Recaudacion Guardada Exitosamente");
               // }
                /*FIN CAMBIO J.J.G.C*/
            }
            if((formaPago == 0)&&(formaPago2 == 2)&&(formaPago3 == 3)){
                //if(formaPago2 == 2){
                    rs.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion,
                            (new LcRecaudaciones(id_recaudacion)),(new LcTipoFormaPago(formaPago2)),
                            total_pago2,
                            institucion,
                            "","",
                            num_tarjeta,
                            fecha_reg,
                            fecha_reg,
                            fecha_reg, "A"));
//                }
//                if(formaPago3 == 3){
                    rs.addFormaPago(new LcFormapagoRecaudacion(id_FormPagrecaudacion+1,
                            (new LcRecaudaciones(id_recaudacion)),(new LcTipoFormaPago(formaPago3)),
                            total_pago3,
                            institucion2,
                            num_cheque,
                            num_cuenta,
                            "",
                            fecha_reg,
                            fecha_reg,
                            fecha_reg, "A"));
                    response.getWriter().println("Recaudacion Guardada Exitosamente");
               // }
                
            }
            } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_Formapago Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        if (accion.equals("listar1")) {
            try{
            request.getRequestDispatcher("sistema/caja/NuevoPago.jsp").forward(request, response);
            } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_listar1 Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         if (accion.equals("ConsultaRecaudaciones")) {
            try{
            String ConsultRecaudaciones="";
             String fechaInicio = request.getParameter("fechaInicial");
             String fechaFin = request.getParameter("fechaFinal");
            
             if (fechaInicio == ""){
                ConsultRecaudaciones = "{\"data\": "+rst.getLcRecaudacionesActual(EmpresaID, SucursalID,null,null,EmpleadoID)+"}";
              
             }else{
               
                ConsultRecaudaciones = "{\"data\": "+rst.getLcRecaudacionesActual(EmpresaID, SucursalID,fechaInicio,fechaFin,EmpleadoID)+"}";
             }
             response.getWriter().println(ConsultRecaudaciones);
            } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_ConsultaRecaudaciones Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
            if (accion.equals("Comprobar")) {
            try{  
              String clave = request.getParameter("clave");
              String encript=DigestUtils.sha1Hex(clave);
              /*if((RolID==7)||(RolID==12)||(RolID==8)){
                List<LcPermisos> permiso = ps.getLcDatosPermisosAdmin(RolID, encript);
                if(!permiso.isEmpty()){
                    response.getWriter().println(1);
                }else{
                  response.getWriter().println(0);
              }
              }else{
                  response.getWriter().println(2);
              }*/
            } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_Comprobar Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }  
         }
         if (accion.equals("ConsultaTotalRecaudaciones")) {
            try{
            BigDecimal sumando = BigDecimal.ZERO; 
            String fechaInicio = request.getParameter("fechaInicial");
            String fechaFin = request.getParameter("fechaFinal");
            
            if (fechaInicio == ""){
                sumando = rst.getSumLcRecaudaciones(EmpresaID, EmpleadoID, null, null);              
            }else{            
                sumando = rst.getSumLcRecaudaciones(EmpresaID, EmpleadoID, fechaInicio, fechaFin);              
            } 
            response.getWriter().println(sumando);
             } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_ConsultaTotalRecaudaciones Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            } 
          }
         if (accion.equals("ConsultaTotales")) {
            try{
            String totales = "";
            String fechaInicio = request.getParameter("fechaInicial");
            String fechaFin = request.getParameter("fechaFinal");
            
            if (fechaInicio == ""){
                totales = rst.getConsultaTotales(EmpresaID,null,null);              
            }else{            
                totales = rst.getConsultaTotales(EmpresaID,fechaInicio,fechaFin);               
            } 
            response.getWriter().println(totales);
             } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_ConsultaTotales Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
         
        
        
        
          if (accion.equals("consultaRecaudaciones")) {
             try{ 
             String FechaDesde=request.getParameter("fechaDesde");
             String FechaHasta=request.getParameter("fechaHasta");
             List<LcRecaudaciones> datos = rst.getConsultaLcDatosRecaudaciones(EmpresaID, EmpleadoID,FechaDesde,FechaHasta );
             SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
             BigDecimal sumando = BigDecimal.ZERO;
             String fechaRegistro="";
             String RecaudacionesConsulta="";
             RecaudacionesConsulta=" <div class=\"box-body\" style=\"overflow-x:scroll;\">\n" +
"                                            <table id=\"example1\" class=\"table table-bordered table-hover\">\n" +
"                                                <thead>\n" +
"                                                    <tr>\n" +
"\n" +
"                                                        <th>N° Comprobante</th>\n" +
"                                                        <th>Nombre Deudor</th>\n" +
"                                                        <th>Valor Total</th>\n" +
"                                                        <th>Fecha Pago </th>\n" +
"                                                        <th>Acción </th>\n" +
"                                                    </tr>\n" +
"                                                </thead>\n" +
"                                                <tbody>";
             
             for(LcRecaudaciones dato:datos){
             fechaRegistro= dateFormatter.format(dato.getFechaRegistro());
               RecaudacionesConsulta+=" <tr>\n" +
                    "\n" +
                    " <td>"+dato.getIdRecaudacion() +"</td>\n" +
                    "<td>"+dato.getLcDatosDeudores().getNombresCompleto() +"</td>\n" +
                    "  <td>"+dato.getValorRecaudado() +"</td>\n" +
                    " <td>"+fechaRegistro+"</td>\n" +
                    " <td>\n" +
                    "  </span></a>\n" +
                    "<a onclick=\"VistaPago("+dato.getIdRecaudacion() +")\"> <span class=\"glyphicon glyphicon-eye-open\" aria-hidden=\"true\">\n" +
                    " <a onclick=\"elminarPago("+dato.getIdRecaudacion() +")\"> <span  class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>\n" +
                    "\n" +
                    " </td>    \n" +
                    " </tr> ";
                 
             }
             RecaudacionesConsulta= RecaudacionesConsulta+" </tbody>\n" +
                                    "\n" +
                                    "   </table>\n" +
                                    "    </div>";
             for(int i=0;i<datos.size();i++){
                sumando=sumando.add(datos.get(i).getValorRecaudado());
            }
                String tran= sumando.toString();
                request.setAttribute("sumando", 0);
                request.setAttribute("sumando", tran);
             
             //request.setAttribute("datos", datos);
             //request.getRequestDispatcher("sistema/caja/NuevoPago.jsp").forward(request, response);
                 response.getWriter().println(RecaudacionesConsulta);
             } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_consultaRecaudaciones Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
          
        if (accion.equals("CuadreCaja")) {
            try{
          //  List<LcRecaudaciones> datos = rst.getLcDatosRecaudoActual(EmpresaID, EmpleadoID);

            //request.setAttribute("datos", datos);
            request.getRequestDispatcher("sistema/caja/frm_cuadre_caja.jsp").forward(request, response);
             } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_CuadreCaja Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // 
        
           
        if (accion.equals("consulta_recaudador")) {
            try{
             String data="{\"datos\": "+ rst.getConsultaRecaudadores(EmpresaID,SucursalID,EmpleadoID)+"}";
             response.getWriter().println(data);
            } catch (Exception ex) {
                try{grb.grabaLog("RecaudacionController_consulta_recaudador Recaudacion  Error Java: "+ex.getMessage());}catch(IOException e){}
                Logger.getLogger(RecaudacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.equals("consulta_historial_recaudaciones")){
            
           int idCliente = Integer.parseInt(request.getParameter("idCliente"));
           String deu=request.getParameter("idDeudor");
           int idDeudor = Integer.parseInt(deu);
           String historial=rst.getHistorialPagos(EmpresaID,idDeudor,idCliente);
           String data;
           if(historial==null){
               data="{\"data\":[]}";
           }else{
            data="{\"data\": "+historial +"}";
           }
             
             response.getWriter().println(data);
        }
        if(accion.equals("consulta_sum_historial_recaudaciones")){
            
           int idCliente = Integer.parseInt(request.getParameter("idCliente"));
           String deu=request.getParameter("idDeudor");
           int idDeudor = Integer.parseInt(deu);
           String sum_historial=rst.getSumHistorialPagos(EmpresaID,idDeudor,idCliente);
           if(sum_historial==null){
                sum_historial="{\"data\":[]}";
           }else{
               sum_historial="{\"data\": "+sum_historial +"}";
           }
             
             response.getWriter().println(sum_historial);
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
