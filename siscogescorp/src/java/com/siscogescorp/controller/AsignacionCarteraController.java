/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcAsignaEmpleadosTmp;
import com.siscogescorp.modelo.LcClientes;
import com.siscogescorp.modelo.LcDatosDeudores;
import com.siscogescorp.modelo.LcEmpleados;
import com.siscogescorp.servicios.AsignacionCarteraServicios;
import com.siscogescorp.servicios.ConsultaxCarteraServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.SucursalServicios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author ViewSoft
 */
public class AsignacionCarteraController extends HttpServlet {

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
       EmpresaServicios es =new EmpresaServicios();
       SucursalServicios su = new SucursalServicios();
       AsignacionCarteraServicios ac= new AsignacionCarteraServicios();
       ConsultaxCarteraServicios cd = new ConsultaxCarteraServicios();
       String accion;
       accion= request.getParameter("accion");
       String id_empresas,id_empleados,id_sucursal;    
       HttpSession sesion = request.getSession(true);
       id_empresas = sesion.getAttribute("Sstrempresa").toString();
       int EmpresaID= Integer.parseInt(id_empresas);
       id_empleados = sesion.getAttribute("Sstrempleado").toString();
       int EmpleadoID= Integer.parseInt(id_empleados);
       id_sucursal = sesion.getAttribute("Sstrsucursal").toString();
       int SucursalID= Integer.parseInt(id_sucursal);
         String SSqlDatosDeudor="";

       
       if(accion.equals("listar"))
        {            

           String Tabla="";
         
           int opcion=0;
           
            
           List<LcClientes> carteras = ac.getLcDatoscliente(EmpresaID);  
           request.setAttribute("carteras", carteras);
           
                    String filtro1 = request.getParameter("orden_dia");
                    String filtro2 = request.getParameter("orden_valor");
                    String filtro3 = request.getParameter("orden_fecha");
                 if((filtro1==null)&&(filtro2==null)&&(filtro3==null)){
                     request.setAttribute("Tablacartera", Tabla);
                     sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                    
                 }

                     request.getRequestDispatcher("sistema/campanias/AsignacionCartera.jsp").forward(request, response);
          }
          
        


        if(accion.equals("listar_datos")){
        int cartera = Integer.parseInt(request.getParameter("cartera"));
            List<LcDatosDeudores> datas = ac.getLcDatos(EmpresaID, SucursalID, cartera,EmpleadoID);
            request.setAttribute("datas", datas);
            request.getRequestDispatcher("sistema/gestion/AsignacionCartera.jsp").forward(request, response);
        }
        
        if(accion.equals("orden")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_dia"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                Tabla2=ac.getDatosCarteras(cartera, EmpleadoID, opcion);
                 SSqlDatosDeudor=ac.getRetornaQuery(cartera, EmpleadoID, opcion);
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.equals("ordenDesc")){
            String Tabla3="";
            int opcion=Integer.parseInt(request.getParameter("opcion"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                Tabla3=ac.getDatosCarteras(cartera, EmpleadoID, opcion);
                 //request.setAttribute("Tablacartera3", Tabla3);
                 SSqlDatosDeudor=ac.getRetornaQuery(cartera, EmpleadoID, opcion);
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 response.getWriter().println(Tabla3);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         if(accion.equals("filtrosDiasMora")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_dia"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=ac.getRetornaQuery2(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.equals("filtrosTotalVenc")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_total"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=cd.getRetornaQuery5(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }         
        if(accion.equals("filtrosDiasFecha")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_Fecha"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=ac.getRetornaQuery10(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.equals("filtrosIDE")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_IDE"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=ac.getRetornaQuery3(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         if(accion.equals("filtrosNombre")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_Nombre"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=ac.getRetornaQuery4(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.equals("filtrosPagos")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_Pago"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=ac.getRetornaQuery6(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        if(accion.equals("filtrosSaldo")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_Saldo"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=ac.getRetornaQuery7(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        if(accion.equals("filtrosValorComp")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_ValorComp"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=ac.getRetornaQuery8(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
         if(accion.equals("filtrosFechaComp")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_FechaComp"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=ac.getRetornaQuery9(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
         if(accion.equals("filtrosUltima")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_Ultima"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=ac.getRetornaQuery11(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }         
         if(accion.equals("filtrosResultado")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_Resultado"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=ac.getRetornaQuery12(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.equals("filtrosFechaUltPagos")){
            String Tabla2="";
           
            
            int opcion=Integer.parseInt(request.getParameter("orden_FechaUltPagos"));
            int cartera = Integer.parseInt(request.getParameter("cartera"));
         try {
                
                 SSqlDatosDeudor=cd.getRetornaQuery13(cartera, EmpleadoID, opcion);                 
                 sesion.setAttribute("SSqlDatosDeudor",SSqlDatosDeudor); 
                 Tabla2=ac.getDatosCarteras2(SSqlDatosDeudor);
                 response.getWriter().println(Tabla2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

         if(accion.equals("TiposSegmentos")){
          
            int idsubcartera=Integer.parseInt(request.getParameter("idsubcartera"));
            String DatoSegmento="";
            DatoSegmento=ac.getTipoSegmento(idsubcartera);
            if(DatoSegmento.equals("[]")){
                DatoSegmento="{\"tipo_segmento\": []}";
            }else{
                DatoSegmento="{\"tipo_segmento\": "+ DatoSegmento+"}";//  getTiposGestion

            }
               System.out.println("json: "+DatoSegmento);
                response.getWriter().println(DatoSegmento);
        }
        if(accion.equals("TiposGestiones")){
          int idclienteok;
            idclienteok = cd.getIdCliente(EmpresaID, SucursalID, EmpleadoID);
            
            String DatosTiposGestion="{\"tipo_gestion\": ["+ ac.getTiposGestion(idclienteok)+"]}";//  getTiposGestion
            System.out.println("json: "+DatosTiposGestion);
             response.getWriter().println(DatosTiposGestion);
        }
        
        if(accion.equals("TiposResulatdos")){
         int idcliente;
       
         if(!request.getParameter("idcliente").isEmpty()){
             idcliente=Integer.parseInt(request.getParameter("idcliente"));
             
         }else{
             idcliente = ac.getIdCliente(EmpresaID, SucursalID, EmpleadoID);
         }
            
         String DatosTiposResultados="";
         DatosTiposResultados=ac.getTiposResultados(idcliente);
         if(DatosTiposResultados.equals("[]")){
             DatosTiposResultados="{\"tipos_resultado\": []}";
         }else{
             DatosTiposResultados="{\"tipos_resultado\": "+ DatosTiposResultados+"}";//  getTiposGestion
  
         }
            System.out.println("json: "+DatosTiposResultados);
             response.getWriter().println(DatosTiposResultados);
        }
        
        if(accion.equals("TiposCarteras")){
         int idcliente=Integer.parseInt(request.getParameter("idcliente"));
         
         String DatosCarteras="";
         DatosCarteras=ac.getTiposCartera(idcliente);
         if(DatosCarteras.equals("[]")){
             DatosCarteras="{\"tipo_cartera\": []}";
         }else{
             DatosCarteras="{\"tipo_cartera\": "+ DatosCarteras+"}";//  getTiposGestion
  
         }
            System.out.println("json: "+DatosCarteras);
             response.getWriter().println(DatosCarteras);
        }
        
        if(accion.equals("Subcarteras")){
         
            int idcartera=Integer.parseInt(request.getParameter("idcartera"));
            String DatosubCarteras="";
            DatosubCarteras=ac.getTiposubCartera(idcartera);
            if(DatosubCarteras.equals("[]")){
                DatosubCarteras="{\"tipo_subcartera\": []}";
            }else{
                DatosubCarteras="{\"tipo_subcartera\": "+ DatosubCarteras+"}";//  getTiposGestion

            }
               System.out.println("json: "+DatosubCarteras);
                response.getWriter().println(DatosubCarteras);
        }
        if(accion.equals("Subsegmentos")){
         
            int idsegmento=Integer.parseInt(request.getParameter("idsegmento"));
            String DatosubSegmento="";
            DatosubSegmento=ac.getTipoSubSegmento(idsegmento);
            if(DatosubSegmento.equals("[]")){
                DatosubSegmento="{\"tipo_subsegmento\": []}";
            }else{
                DatosubSegmento="{\"tipo_subsegmento\": "+ DatosubSegmento+"}";//  getTiposGestion

            }
               System.out.println("json: "+DatosubSegmento);
                response.getWriter().println(DatosubSegmento);
        }        
         if(accion.equals("nuevaConsulta")){
                 int cartera = Integer.parseInt(request.getParameter("cartera")); 
                 String QueryConsulta =request.getParameter("sqlQuery");
                 String NuevosDatos="";
                 QueryConsulta = QueryConsulta.replaceAll("IDClienteConsulta", Integer.toString(cartera));
                 QueryConsulta = QueryConsulta.replaceAll("IDEmpleadoConsulta", Integer.toString(EmpleadoID));
                 
                 sesion.setAttribute("SSqlDatosDeudor",QueryConsulta); 
                 QueryConsulta = QueryConsulta.replaceAll("'", "''");
                 NuevosDatos = "{\"data\": "+cd.getConsultaCartera(QueryConsulta)+"}";
                     response.getWriter().println(NuevosDatos);
             
                 
                 
         }
        if(accion.equals("ListarTodosEmpleados")){
             //int cartera = Integer.parseInt(request.getParameter("cartera")); 
             String Tabla="";
             try{
                 Tabla=ac.getTodosEmpleados(EmpresaID,SucursalID);
                 //request.setAttribute("Tabla", Tabla);
                 response.getWriter().println(Tabla);
             } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
         if(accion.equals("FiltrarEmpleados")){
             int cartera = Integer.parseInt(request.getParameter("cartera")); 
             String Tabla="";
             try{
                 Tabla=ac.getFiltroEmpleados(cartera,EmpresaID,SucursalID);
                 //request.setAttribute("Tabla", Tabla);
                 response.getWriter().println(Tabla);
             } catch (SQLException ex) {
                Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
         if(accion.equals("GuardarAsignar")){
             int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
             int cartera = Integer.parseInt(request.getParameter("cartera"));
             int id_asigna = Integer.parseInt(ac.getNext5().toString());
             Date fecha_reg = new Date();
             ac.addEmpleadoAsigna(new LcAsignaEmpleadosTmp
                                     (id_asigna,
                                      (new LcEmpleados (idEmpleado)),
                                      EmpleadoID,
                                      fecha_reg,"A",cartera
                                      ));
              response.getWriter().println("Empleado Asignado");
         }//asignado
         if(accion.equals("ActualizarAsignar")){
             
             int id_asigna = Integer.parseInt(request.getParameter("asignado"));
              ac.updateAsignacion(id_asigna);
              response.getWriter().println("Asignación Inactivada");
         }
          if(accion.equals("ConsultaAllEmpleados")){
                 String NuevosDatos="";
                 
                //try {
              
                    NuevosDatos = "{\"data\": "+ac.getEmpledosAsginadosAll(EmpleadoID,SucursalID)+"}";
                    
                //} catch (SQLException ex) {
                  //  Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);

                //}
                 response.getWriter().println(NuevosDatos);
           
            }
          if(accion.equals("ConsultaEmpleadosCartera")){
                   int cartera = Integer.parseInt(request.getParameter("cartera"));
                 String NuevosDatos="";
                 
              
              
                    NuevosDatos = "{\"data\": "+ac.getEmpledosAsginadosCarteras(EmpleadoID,SucursalID,cartera)+"}";
                    
              
                 response.getWriter().println(NuevosDatos);
           
            }
          if(accion.equals("consulta_totales")){
                    int cartera = Integer.parseInt(request.getParameter("cliente"));
                    int secuencia = Integer.parseInt(request.getParameter("secuencia"));
                    String NuevosDatos="";
                    NuevosDatos = "{\"data\": "+ac.getTotalesAsignados(secuencia,cartera)+"}";
                    response.getWriter().println(NuevosDatos);
           
            }
          
          
          if(accion.equals("procesar_asignacion")){
                    int cartera = Integer.parseInt(request.getParameter("cartera"));
                    int secuencia = Integer.parseInt(request.getParameter("secuencia"));
                    double proporcionindv = Double.parseDouble(request.getParameter("proporcionindv"));
                    String registraemp = request.getParameter("registraemp");
                    
                    String ejecutaQuery=ac.getEjecutaQuery(registraemp);
                    if(!ejecutaQuery.equals("1") ){
                        response.getWriter().println("Problemas al registrar los Empleados Seleccionados... \nComuncarse con Dep. SISTEMAS");
                        return;
                    }
                    
                    String registTransTem = ac.getRegistraTransTem(secuencia, cartera);
                    if(!registTransTem.equals("1") ){
                        response.getWriter().println("Problemas al registrar las transacciones... \nComuncarse con Dep. SISTEMAS");
                        return;
                    }
                    String procAsignacion = ac.getProcesaAsignacion(secuencia, proporcionindv);
                    if(!procAsignacion.equals("1") ){
                        response.getWriter().println("Problemas al registrar las Asignaciones... \nComuncarse con Dep. SISTEMAS");
                        return;
                    }
                    if(procAsignacion.equals("1") ){
                        response.getWriter().println("Proceso realizado Con exito");
                        return;
                    }
                    
                    
           
            }
          if(accion.equals("consulta_empleados_asigados")){                    
                    int secuencia = Integer.parseInt(request.getParameter("secuencia"));
                     String NuevosDatos="";
                    NuevosDatos = "{\"data\": "+ac.getEmpleadosAsignados(secuencia)+"}";
                    
              
                 response.getWriter().println(NuevosDatos);
           
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
