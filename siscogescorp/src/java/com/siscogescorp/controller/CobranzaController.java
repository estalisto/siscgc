/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcAgencia;
import com.siscogescorp.modelo.LcCiudad;
import com.siscogescorp.modelo.LcClientes;
import com.siscogescorp.modelo.LcCompromisosPago;
import com.siscogescorp.modelo.LcDatosDeudores;
import com.siscogescorp.modelo.LcDireccion;
import com.siscogescorp.modelo.LcEmpleados;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcGestiones;
import com.siscogescorp.modelo.LcNotas;
import com.siscogescorp.modelo.LcRecordatorios;
import com.siscogescorp.modelo.LcReferencias;
import com.siscogescorp.modelo.LcTelefonos;
import com.siscogescorp.modelo.LcTipoGestion;
import com.siscogescorp.modelo.LcTipoPersona;
import com.siscogescorp.modelo.LcTipoReferencia;
import com.siscogescorp.modelo.LcTipoResultado;
import com.siscogescorp.modelo.LcTiposDireccion;
import com.siscogescorp.modelo.LcTiposTelefono;
import com.siscogescorp.modelo.LcTransacciones;
import com.siscogescorp.servicios.ConsultaxCarteraServicios;
import com.siscogescorp.servicios.DireccionServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.ParametrosServicios;
import com.siscogescorp.servicios.RecaudacionServicios;
import com.siscogescorp.servicios.ReferenciasServicios;
import com.siscogescorp.servicios.SucursalServicios;
import com.siscogescorp.servicios.TelefonoServicios;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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
public class CobranzaController extends HttpServlet {

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
        TelefonoServicios telf = new TelefonoServicios();
        DireccionServicios dir = new DireccionServicios();
        ReferenciasServicios ref = new ReferenciasServicios();
        ParametrosServicios param = new ParametrosServicios();
        ConsultaxCarteraServicios cd = new ConsultaxCarteraServicios();
        RecaudacionServicios rs = new RecaudacionServicios();
        String accion;
        accion = request.getParameter("accion");
        String id_empresas, id_empleados, id_sucursal;
        HttpSession sesion = request.getSession(true);
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID = Integer.parseInt(id_empresas);
        id_empleados = sesion.getAttribute("Sstrempleado").toString();
        int EmpleadoID = Integer.parseInt(id_empleados);
        id_sucursal = sesion.getAttribute("Sstrsucursal").toString();
        int SucursalID = Integer.parseInt(id_sucursal);
        String Identificacion=null;
        //EmpresaID SucursalID 
        if(accion.equals("listar"))
        {
            
            request.getRequestDispatcher("sistema/gestion/frm_gestion_cobranzas.jsp").forward(request, response);
        }
        if(accion.equals("envio"))
        {
         int  idCliente=Integer.parseInt(request.getParameter("idCliente"));
         int  idDeudor=Integer.parseInt(request.getParameter("idDeudor"));  
         
         List<LcDatosDeudores> cobranzas = cd.getLcDeudorId(idCliente,idDeudor);
         request.setAttribute("cobranzas", cobranzas);
         List<LcTransacciones> transaccion = cd.getTransaccionesId(idCliente,idDeudor);
         BigDecimal totalPagar=BigDecimal.ZERO;
         BigDecimal valorPagado =BigDecimal.ZERO;
         BigDecimal ValorSaldo=BigDecimal.ZERO;
         if(!transaccion.isEmpty()){
         
         totalPagar =transaccion.get(0).getTotalVencidos();
             try {
                 valorPagado=rs.getValorRecaudado(EmpresaID, idCliente, idDeudor);
             } catch (SQLException ex) {
                 Logger.getLogger(CobranzaController.class.getName()).log(Level.SEVERE, null, ex);
             }
         ValorSaldo=(totalPagar).subtract(valorPagado);
         
         
         
         }
          // request.setAttribute("totalPagar2", totalPagar);
           request.setAttribute("valorPagado2", valorPagado);
           request.setAttribute("ValorSaldo2", ValorSaldo);
         
         request.setAttribute("transaccion", transaccion);
      //   int idcliente = cobranzas.get(0).getLcClientes().getIdCliente();
         List<LcTipoGestion> gestiones = cd.getLcTipoGestion(idCliente);
         request.setAttribute("gestiones", gestiones);
         String identifica= cd.getIdentificacionDeudor(idDeudor);// aqui
         
         List<LcDireccion> direcciones = cd.getLcDireccion(identifica);
         request.setAttribute("direcciones", direcciones);
         
         List<LcTelefonos> telefonos = cd.getLcTelefono(cd.getIdentificacionDeudor(idDeudor));
         request.setAttribute("telefonos", telefonos);
         
         List<LcCiudad> ciudades = cd.getLcCiudad();
         request.setAttribute("ciudades", ciudades);
         
         List<LcNotas> notas = cd.getLcNotas(idCliente,idDeudor);
         request.setAttribute("notas", notas);
         
         String detArticulos = null,detalleArt = null,footTotal=null;
            try {
               // detArticulos = cd.getArticulos2(idCliente,idDeudor);
                detArticulos = cd.getArticulos2(idCliente,idDeudor);
                String separo[]=detArticulos.split("&");
                 detalleArt=separo[0];
                 footTotal=separo[1];
            } catch (SQLException ex) {
                Logger.getLogger(CobranzaController.class.getName()).log(Level.SEVERE, null, ex);
            }
         request.setAttribute("detArticulos", detalleArt);
         request.setAttribute("detTotal", footTotal);
         String detCuotas = null,detalleCuota = null,footCuota=null;
            try {
                //detCuotas = cd.getCuotas2(idcliente, idDeudor);
                detCuotas = cd.getCuotas2(idCliente, idDeudor);
                String separo[]=detCuotas.split("&");
                detalleCuota=separo[0];
                footCuota=separo[1];
            } catch (SQLException ex) {
                Logger.getLogger(CobranzaController.class.getName()).log(Level.SEVERE, null, ex);
            }
         request.setAttribute("detCuotas", detalleCuota);
         request.setAttribute("detTotalCuota", footCuota);
           String TablaGestiones="";
            try {
                 TablaGestiones=cd.getGestiones(idCliente, idDeudor);
            } catch (SQLException ex) {
                Logger.getLogger(CobranzaController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
         
         //List<LcGestiones> GestionTRX = cd.getGestionesTRX(idCliente,idDeudor);
         request.setAttribute("GestionTRX", TablaGestiones);
         
         
         
         
         
         request.getRequestDispatcher("sistema/gestion/frm_gestion_cobranzas.jsp").forward(request, response);
        }
        
        if(accion.equals("guardar"))
        {
            try{  
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            int  id=Integer.parseInt(request.getParameter("id"));
            String  hora=request.getParameter("hora");
            String  compromiso_pago = request.getParameter("compromiso_pago");
            int  idCliente=Integer.parseInt(request.getParameter("cliente"));
            
            List<LcDatosDeudores> recordatorio = cd.getLcDeudorRecordatorio(id,EmpresaID,SucursalID,EmpleadoID);
         
            Date fecha_reg = new Date();
            String Fecha1=compromiso_pago+" "+hora+":00";
           Date  Fecha_final = formatter.parse(Fecha1);
                System.out.println(Fecha_final);
                LcDatosDeudores data = recordatorio.get(0);
                int id_empresa = data.getLcEmpresa().getIdEmpresa();
                int id_agencia = data.getLcAgencia().getIdAgencia();
                int id_deudor = data.getIdDatosDeudor();
                //int id_cliente = data.getLcClientes().getIdCliente();
               // int id_empleado = data.getIdEmpleado();

                int id_recordatorio = Integer.parseInt(cd.getNext().toString());
                
                cd.addRecordatorio(new LcRecordatorios(
                        id_recordatorio,
                        (new LcAgencia(id_agencia)),
                        (new LcClientes(idCliente)),
                        (new LcDatosDeudores(id_deudor)),
                        (new LcEmpleados(EmpleadoID)),
                        (new LcEmpresa(id_empresa)),
                        Fecha_final,
                        "A",
                        fecha_reg,
                        fecha_reg,
                        "A"
                ));
                int id_transaccion = Integer.parseInt(cd.getNext2().toString());
                List<LcTipoGestion> gestion = cd.getLcTipoGestion(idCliente);
                int id_gestion = gestion.get(0).getIdTipoGestion();
                List<LcTipoResultado> resultado = cd.getLcTipoResultado(id_gestion);
                int id_resultado = resultado.get(0).getIdTipoResultado();
                cd.addGestiones(new LcGestiones(
                        id_transaccion,
                        (new LcClientes(idCliente)),
                        (new LcDatosDeudores(id_deudor)),
                        (new LcEmpleados(EmpleadoID)),
                        (new LcTipoGestion(id_gestion)),
                        (new LcTipoResultado(id_resultado)),
                        "RECORDATORIO DE LLAMADA Fecha:" + compromiso_pago + "Hora:" + hora,
                        fecha_reg,
                        "A"
                ));
    

                response.getWriter().println("Recordatorio y Gestión Guardada.");

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (accion.equals("notas")) {
                int idNota = Integer.parseInt(request.getParameter("idNota"));
                int idDeudor = Integer.parseInt(request.getParameter("idDeudor"));
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                Date fecha_reg = new Date();
                String txtnota = request.getParameter("txtnota");
                
                //List<LcNotas> note = cd.getLcNotasId(idNota, idDeudor,idCliente);
                  if (idNota == 0 ){
                    idNota=Integer.parseInt(cd.getNext3().toString()); 
                    cd.addNota(new LcNotas(
                        idNota,
                        idDeudor,
                        idCliente,
                        txtnota,
                        fecha_reg,
                        "A",
                            0,null
                ));
                }else{
                   
                    cd.updateNota(idNota, txtnota);
                }
               
                    response.getWriter().println(idNota);
        }

        if (accion.equals("ResulParametro")) {
            int id_resultado = Integer.parseInt(request.getParameter("resultado"));
        try {
                    if(!param.getValorParametro("COMPROMISO_PAGO").isEmpty()){
                        String valor_parametro = param.getValorParametro("COMPROMISO_PAGO");
                        String id_resultados = Integer.toString(id_resultado);
                        int resultado = valor_parametro.indexOf(id_resultados);
                        if(resultado != -1){
                            response.getWriter().println(id_resultado+"|"+1);
                        }
                    }
                    if(!param.getValorParametro("VOLVER_A_LLAMAR").isEmpty()){
                        String valor_parametro = param.getValorParametro("VOLVER_A_LLAMAR");
                        String id_resultados = Integer.toString(id_resultado);
                        int resultado = valor_parametro.indexOf(id_resultados);
                        if(resultado != -1){
                            response.getWriter().println(id_resultado+"|"+2);
                        }
                    }
        } catch (SQLException ex) {
                    Logger.getLogger(CobranzaController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        if (accion.equals("transaccion")) {
            try{
                int id = Integer.parseInt(request.getParameter("id"));
                
                int id_gestion = Integer.parseInt(request.getParameter("gestion"));
                int id_resultado = Integer.parseInt(request.getParameter("resultado"));
                int tipo_resultado = Integer.parseInt(request.getParameter("tipo_resultado"));
                int id_cliente = Integer.parseInt(request.getParameter("cliente"));
                int id_Transaccion = Integer.parseInt(request.getParameter("idTransaccion"));
                int id_Gestion = Integer.parseInt(cd.getNext2().toString());
                String descripcion = request.getParameter("descripcion");
                
                Date fecha_reg = new Date();
                
                String estado="";
                try {
                    if(!param.getValorParametro("COMPROMISO_PAGO").isEmpty()){
                        
                        String valor_parametro = param.getValorParametro("COMPROMISO_PAGO");
                        String id_resultados = Integer.toString(tipo_resultado);
                        int resultado = valor_parametro.indexOf(id_resultados);
                        if(resultado != -1){
                            //NUEVO CAMBIO
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            
                            int idComPago=Integer.parseInt(cd.getNext4().toString());
                            String  comp_pago = request.getParameter("comp_pago");
                            Date  Fecha_final = formatter.parse(comp_pago);
                            String fecha_comp=formatter.format(Fecha_final);
                            String monto_compromiso = request.getParameter("monto_compromiso");
                            BigDecimal valor_compromiso = new BigDecimal(monto_compromiso);
                            BigDecimal valorComprometido = valor_compromiso;
                            
                            descripcion=descripcion+"  FECHA COMPROMISO DE PAGO: "+fecha_comp+" VALOR QUE SE COMPROMETE A CANCELAR: $"+valorComprometido;
                            //Guarda Compromiso Pago lc_compromiso_pago
                            cd.addCompPago(new LcCompromisosPago(
                                    idComPago,
                                    (new LcDatosDeudores (id)),
                                    id_cliente,
                                    Fecha_final,
                                    valorComprometido,
                                    null,
                                    fecha_reg,
                                    fecha_reg,
                                    "A"
                            ));
                            //Guarda Gestion lc_gestiones
                            cd.addGestiones(new LcGestiones(
                                    id_Gestion,
                                    (new LcClientes(id_cliente)),
                                    (new LcDatosDeudores(id)),
                                    (new LcEmpleados(EmpleadoID)),
                                    (new LcTipoGestion(id_gestion)),
                                    (new LcTipoResultado(id_resultado)),
                                    descripcion.toUpperCase(),
                                    fecha_reg,
                                    "A"
                            ));
                            //Actualiza estado lc_transaciones
                            estado="C";
                            cd.updateTransactionState(id_Transaccion,estado);
                            //NUEVO CAMBIO
                        }
                    } if(!param.getValorParametro("VOLVER_A_LLAMAR").isEmpty()){
                        
                        String valor_parametro = param.getValorParametro("VOLVER_A_LLAMAR");
                        String id_resultados = Integer.toString(tipo_resultado);
                        int resultado = valor_parametro.indexOf(id_resultados);
                        if(resultado != -1){
                            //NUEVO CAMBIO
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                            
                           
                       
                              
                            int idComPago=Integer.parseInt(cd.getNext4().toString());
                            String  Fechacompromiso_pago = request.getParameter("Fechacompromiso_pago");
                            Date  Fecha_final = formatter.parse(Fechacompromiso_pago);
                            String fecha_comp=formatter.format(Fecha_final);
                            String hora = request.getParameter("hora");
                            String Fecha1=Fechacompromiso_pago+" "+hora+":00";
                            Date  Fecha_recordatorio = formatter2.parse(Fecha1);
                           
                              
                            descripcion=descripcion+" RECORDATORIO PROGRAMADO PARA LA FECHA "+Fechacompromiso_pago+" Y HORA"+hora;
                            int id_recordatorio = Integer.parseInt(cd.getNext().toString());
                            ////EmpresaID SucursalID 
                String dato = cd.fnc_registra_notificacion(EmpresaID,SucursalID,id,id_cliente,EmpleadoID,Fecha1);
                          /*  cd.addRecordatorio(new LcRecordatorios(
                                    id_recordatorio,
                                    (new LcAgencia(SucursalID)),
                                    (new LcClientes(id_cliente)),
                                    (new LcDatosDeudores(id)),
                                    (new LcEmpleados(EmpleadoID)),
                                    (new LcEmpresa(EmpresaID)),
                                    Fecha_final,
                                    "A",
                                    fecha_reg,
                                    fecha_reg,
                                    "A"
                            ));*/
                            //Guarda Gestion lc_gestiones
                            cd.addGestiones(new LcGestiones(
                                    id_Gestion,
                                    (new LcClientes(id_cliente)),
                                    (new LcDatosDeudores(id)),
                                    (new LcEmpleados(EmpleadoID)),
                                    (new LcTipoGestion(id_gestion)),
                                    (new LcTipoResultado(id_resultado)),
                                    descripcion.toUpperCase(),
                                    fecha_reg,
                                    "A"
                            ));
                            //Actualiza estado lc_transaciones
                            estado="C";
                            cd.updateTransactionState(id_Transaccion,estado);
                            //NUEVO CAMBIO
                        }
                    }
                    if(tipo_resultado==0){ //Guarda solo Gestion
                            cd.addGestiones(new LcGestiones(
                                    id_Gestion,
                                    (new LcClientes(id_cliente)),
                                    (new LcDatosDeudores(id)),
                                    (new LcEmpleados(EmpleadoID)),
                                    (new LcTipoGestion(id_gestion)),
                                    (new LcTipoResultado(id_resultado)),
                                    descripcion.toUpperCase(),
                                    fecha_reg,
                                    "A"
                            ));
                            estado="P";
                            cd.updateTransactionState(id_Transaccion,estado);
                    
                        }
                } catch (SQLException ex) {
                    Logger.getLogger(CobranzaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.getWriter().println("Gestión Guardada Exitosamente.");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        
        if (accion.equals("listar_transaccion")) {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            int idDeudor = Integer.parseInt(request.getParameter("idDeudor"));
           // List<LcGestiones> GestionTRX = cd.getGestionesTRX(idCliente, idDeudor);
            
            String GestionesJSON= "{\"data\": "+cd.getGestionesJSON(idCliente, idDeudor)+"}";
            response.getWriter().println(GestionesJSON);
           
        }
        if (accion.equals("compromiso")) {
           try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
            int idComPago=Integer.parseInt(cd.getNext4().toString());
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            int idDeudor = Integer.parseInt(request.getParameter("idDeudor"));   
            String  comp_pago = request.getParameter("comp_pago");
            
            Date  Fecha_final = formatter.parse(comp_pago+" "+"00:00:00");
            Date fecha_reg = new Date();

               cd.addCompPago(new LcCompromisosPago(
                       idComPago,
                       (new LcDatosDeudores (idDeudor)),
                       idCliente,
                       Fecha_final,
                       null,
                       null,
                       fecha_reg,
                       fecha_reg,
                       "A"
               ));

             String fecha_comp=formatter.format(Fecha_final);

               int id_transaccion = Integer.parseInt(cd.getNext2().toString());
                List<LcTipoGestion> gestion = cd.getLcTipoGestion(idCliente);
                int id_gestion = gestion.get(0).getIdTipoGestion();
                List<LcTipoResultado> resultado = cd.getLcTipoResultado(id_gestion);
                int id_resultado = resultado.get(0).getIdTipoResultado();
                cd.addGestiones(new LcGestiones(
                        id_transaccion,
                        (new LcClientes(idCliente)),
                        (new LcDatosDeudores(idDeudor)),
                        (new LcEmpleados(EmpleadoID)),
                        (new LcTipoGestion(id_gestion)),
                        (new LcTipoResultado(id_resultado)),
                        "SE COMPROMETE A REALIZAR UN COMPROMISO DE PAGO LA FECHA: " + fecha_comp,
                        fecha_reg,
                        "A"
                ));

                response.getWriter().println("Compromiso Pago Guardado Exitosamente.");
            
            
           } catch (ParseException e) {
                e.printStackTrace();
            }}
        
        if(accion.equals("agraga_telef")){
            int ID_deudorTel = Integer.parseInt(request.getParameter("idDeudor"));
            int IDClienteTel = Integer.parseInt(request.getParameter("cliente"));
            int IdTipoTelf = Integer.parseInt(request.getParameter("tipoTelefono"));
            String NewTelef = request.getParameter("newTelefono");
            Date FechaHoyTel= new Date();
            int IdTelefonoDeudor =Integer.parseInt(telf.getNext().toString());
            telf.addTelefonos(new LcTelefonos(IdTelefonoDeudor,new LcTipoPersona(1),new LcTiposTelefono(IdTipoTelf),ID_deudorTel,cd.getIdentificacionDeudor(ID_deudorTel),NewTelef,0,FechaHoyTel,"A",null ));
            response.getWriter().println("Teléfono Agregado Exitosamente.");
        }
        
        if(accion.equals("agraga_direccion")){
		int ID_deudorTel = Integer.parseInt(request.getParameter("idDeudor"));
		int IDClienteTel = Integer.parseInt(request.getParameter("cliente"));
		int IdTipoDir = Integer.parseInt(request.getParameter("tDireccion"));
		String NewDireccion = request.getParameter("direccion_new");
		 Date FechaHoyDireccion= new Date();
		 int IdDireccion =Integer.parseInt(dir.getNext().toString());
		 dir.addLcDireccion(new LcDireccion(IdDireccion,new LcTiposDireccion(IdTipoDir),cd.getIdentificacionDeudor(ID_deudorTel),NewDireccion,FechaHoyDireccion,"A",null  ) );
		  response.getWriter().println("Dirección Agregada Exitosamente.");
        }
        if (accion.equals("listar_direccion")) {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            int idDeudor = Integer.parseInt(request.getParameter("idDeudor"));
            String identifica= cd.getIdentificacionDeudor(idDeudor);
            String DirecionesJson= "{\"data\": "+cd.getLcDireccionJSON(identifica)+"}";
            // String DirecionesJson= cd.getLcDireccionJSON(identifica);

            response.getWriter().println(DirecionesJson);

        }
        if (accion.equals("listar_telefono")) {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            int idDeudor = Integer.parseInt(request.getParameter("idDeudor"));
            
            String telefonosJSON = "{\"data\": "+cd.getLcTelefonoJSON(cd.getIdentificacionDeudor(idDeudor))+"}";
            //request.setAttribute("telefonos", telefonos);
                response.getWriter().println(telefonosJSON);
        }
        if (accion.equals("DetalleCompras")) {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            int idDeudor = Integer.parseInt(request.getParameter("idDeudor"));
            //String DetalleComprasJSON = "{\"data\": "+cd.getDetallesComprasJSON(idCliente,idDeudor)+"}";
            String DetalleComprasJSON = cd.getDetallesComprasSTRING(idCliente,idDeudor);
            
           // response.getWriter().println(DetalleComprasJSON);
            response.getWriter().println(DetalleComprasJSON);
        }
        
        if (accion.equals("DetalleCuotas")) {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            int idDeudor = Integer.parseInt(request.getParameter("idDeudor"));
            String DetalleCuotasJSON = "{\"data\": "+cd.getDetallesCuotasComprasJSON(idCliente,idDeudor)+"}";
            response.getWriter().println(DetalleCuotasJSON);
        }
        
        
         if(accion.equals("deudor")){
            try {
                int idDeudor = Integer.parseInt(request.getParameter("idDeudor"));
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                int secuencia_query = Integer.parseInt(request.getParameter("secQuery"));
                int opcion=2; 
                
                if(true){
                int iddeusorSig;
                iddeusorSig=cd.getSiguiente(secuencia_query, idDeudor);                
                response.getWriter().println(iddeusorSig);
                 
                }else{
                
                
                
                 String sqlquery="";   
                  List<String> sgte;
                 sqlquery = sesion.getAttribute("SSqlDatosDeudor").toString();
                 if (sqlquery.isEmpty()){
                      List<String> sgte1= cd.getDatosCarterasSiguienteAnterior(idCliente,EmpleadoID, opcion);
                      sgte=sgte1;
                 }else{
                         List<String> sgte2= cd.getEjecutaQueryDatosDeudor(sqlquery);
                          sgte=sgte2;
                 }
                 
                 
               
// List<LcDatosDeudores> sgte = cd.getLcDatossgte(EmpresaID,idCliente, EmpleadoID);
                if (!sgte.isEmpty()){
                    if(sgte.size()>0){
                        for(int i=0; i< sgte.size(); i++) {
                            String otro= sgte.get(i);
                            int id = Integer.parseInt(otro);
                            if(id == idDeudor){
                                
                                if((i+1) > sgte.size()){
                                    response.getWriter().println(0);
                                    break;
                                }
                                if((i+1) < sgte.size()){
                                    otro=sgte.get(i+1);
                                    id = Integer.parseInt(otro);
                                    response.getWriter().println(id);
                                    break;
                                }
                                if((i+1) == sgte.size()){
//                                    int otros= i+1;
                                    opcion=7;
                                    List<String> sgte3= cd.getDatosCarterasSiguienteAnterior(idCliente,EmpleadoID, opcion);
//                                    if(!sgte3.isEmpty()){
//                                        for(int j=0; j< sgte.size(); j++) {
                                            id = Integer.parseInt(sgte3.get(0));
//                                            if(otros==id){
                                                response.getWriter().println(id);
//                                            }
//                                        }
//                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                }
            } catch (SQLException ex) {
                Logger.getLogger(CobranzaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         }
         if(accion.equals("InactivaTelefono")){
             int idTelefono = Integer.parseInt(request.getParameter("idTelefono"));
             telf.updateTelefono(idTelefono);
             response.getWriter().println("Teléfono Eliminado Exitosamente...");
             
         }
         
         if(accion.equals("inactivarDireccion")){
             int idDireccion = Integer.parseInt(request.getParameter("idDireccion"));
             dir.updateInactDireccion(idDireccion);
             response.getWriter().println("Dirección Eliminada Exitosamente...");
             
         }
         
        if(accion.equals("anterior")){
            try {
                int idDeudor = Integer.parseInt(request.getParameter("idDeudor"));
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                int secuencia_query = Integer.parseInt(request.getParameter("secQuery"));
                int opcion=2;
               // List<String> sgte= cd.getDatosCarterasSiguienteAnterior(idCliente,EmpleadoID, opcion);
                if(true){
                int iddeusorSig;
                iddeusorSig=cd.getAnteiror(secuencia_query, idDeudor);                
                response.getWriter().println(iddeusorSig);
                 
                }else{
                
                
                
                 List<String> sgte;
                  String sqlquery="";   
                 sqlquery = sesion.getAttribute("SSqlDatosDeudor").toString();
                 if (sqlquery.isEmpty()){
                      List<String> sgte1= cd.getDatosCarterasSiguienteAnterior(idCliente,EmpleadoID, opcion);
                      sgte=sgte1;
                 }else{
                         List<String> sgte2= cd.getEjecutaQueryDatosDeudor(sqlquery);
                          sgte=sgte2;
                 }
                //List<LcDatosDeudores> sgte = cd.getLcDatossgte(EmpresaID,idCliente, EmpleadoID);
                
                    if (!sgte.isEmpty()){
                    if (sgte.size() > 0) {
                        for (int i = 0; i < sgte.size(); i++) {
                            String otro = sgte.get(i);
                            int id = Integer.parseInt(otro);
                            if (id == idDeudor) {
                                if((i-1) < 0){
                                    response.getWriter().println(0);
                                    break;
                                }else{
                                    otro=sgte.get(i-1);
                                    id = Integer.parseInt(otro);
                                    response.getWriter().println(id);
                                    break;
                                    
                                    
                                }
                                
                                
                                
                            }
                        }
                    }
                    
                }
              }
            } catch (SQLException ex) {
                Logger.getLogger(CobranzaController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        
        if(accion.equals("addReferncia")){
             int idDeudor = Integer.parseInt(request.getParameter("idDeudor"));
             String nombreReferencia=request.getParameter("nombre_ref");
             int TipoReferencia = Integer.parseInt(request.getParameter("TipoRef"));
             int ciudad= 1;
              int IDReferencia=Integer.parseInt(ref.getNext().toString()); 
              Date FechaHoy=new Date();
             
             ref.addReferencias(new LcReferencias(IDReferencia,
                     (new LcCiudad(ciudad)),(new LcTipoReferencia(TipoReferencia)),
                     idDeudor,
                     nombreReferencia.toUpperCase(),
                     null,
                     null,
                     FechaHoy,
                     "A",null));
             
           
    
             response.getWriter().println(IDReferencia);
            
        }
        
        if(accion.equals("agraga_telef_ref")){
            String Persona=request.getParameter("idReferencia");
            Persona=Persona.replaceAll("\n", "");
            Persona=Persona.replaceAll("\r", "");
            
            String newTelefono=request.getParameter("newTelefono");
            int TipoTelefono = Integer.parseInt(request.getParameter("tipoTelefono"));           
            Date FechaHoy=new Date();
            int IdTelefono =Integer.parseInt(telf.getNext().toString());
            int IdPersona =Integer.parseInt(Persona);

            
            telf.addTelefonos(new LcTelefonos(IdTelefono,
                      new LcTipoPersona(2), //Tipo -> 2 Persona Referencia 
                      new LcTiposTelefono(TipoTelefono),
                      IdPersona,
                      "0000000000",newTelefono,0,FechaHoy,"A" ,null));

             response.getWriter().println("ok");

        }
         if(accion.equals("tablaReferencia")){
            int idDeudor = Integer.parseInt(request.getParameter("idDeudor")); 
           // List<LcReferencias> datosref= ref.getDatosLcReferencias(idDeudor);
            
            String TablaReferencias ="  <table id='detalle_articulos' class='table table-bordered table-striped'>"
                                    + "     <thead>"
                                    + "         <tr>"
                                    + "             <th>Tipo Ref.</th> "
                                    + "             <th>Nombre</th> "
                                    + "             <th>Telefonos</th>   "
                                    + "             <th>Dirección</th> "
                                    + "         </tr> "
                                    + "     </thead> "
                                    + "     <tbody>";


                String detReferencia=null;
                try {
                    detReferencia = ref.getReferencias(idDeudor);
                    TablaReferencias +=detReferencia;

                } catch (SQLException ex) {
                    Logger.getLogger(CobranzaController.class.getName()).log(Level.SEVERE, null, ex);
                }
//            }
            
            
           TablaReferencias +="</tbody></table>";
           
           response.getWriter().println(TablaReferencias);
         }
         
        if(accion.equals("GestionCliente")){
        int  idCliente=Integer.parseInt(request.getParameter("idCliente"));
        int  idDeudor=Integer.parseInt(request.getParameter("idDeudor"));  
         
         String cobranzas = "{\"ClienteDeudor\": "+cd.getGestionCliente(idCliente,idDeudor)+"}";
         
         //request.setAttribute("cobranzas", cobranzas);
          response.getWriter().println(cobranzas);
          
      
        }
        
         
         
         
         
        
    }
    public  String cadenaTabla(int deudor){
    ReferenciasServicios ref2 = new ReferenciasServicios();
         int idDeudor = deudor; 
            
            String TablaReferencias ="  <table id='detalle_articulos' class='table table-bordered table-hover'>"
                                    + "     <thead>"
                                    + "         <tr>"
                                    + "             <th>Tipo Ref.</th> "
                                    + "             <th>Nombre</th> "
                                    + "             <th>Telefonos</th>   "
                                    + "             <th>Dirección</th> "
                                    + "         </tr> "
                                    + "     </thead> "
                                    + "     <tbody>";

          
        
 List<LcReferencias> datosref= ref2.getDatosLcReferencias(idDeudor);
            for (LcReferencias referencias : datosref) {
                TablaReferencias +="<tr>"
                        + "<td>"+referencias.getLcTipoReferencia().getDescripcion()+"</td>"
                        + "<td>"+referencias.getNombreReferencia()+"</td>"
                        + "<td>"
                        + "     <table>"
                        + "         <tr>"
                        + "             <td><a>0922676945</a></td>"
                        + "             </tr><tr><td><a>0922676946</a></td>"
                        + "         </tr>"
                        + "     </table>"
                        + "</td>"
                        + "<td></td>"
                        + "</tr>";
            }
            
            
          // TablaReferencias +="<tr><td></td><td></td><td><table><tr><td><a>0922676945</a></td></tr><tr><td><a>0922676946</a></td></tr><tr><td><a>0922676947</a></td></tr></table></td><td></td></tr>";
           TablaReferencias +="</tbody></table>";
        
        
        return TablaReferencias;
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
