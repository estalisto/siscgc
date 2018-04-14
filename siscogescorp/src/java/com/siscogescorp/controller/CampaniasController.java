/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.servicios.AsignacionCarteraServicios;
import com.siscogescorp.servicios.CampaniasServicios;
import com.siscogescorp.servicios.ConsultaxCarteraServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.SucursalServicios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CIMA2015
 */
public class CampaniasController extends HttpServlet {

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
              PrintWriter out = response.getWriter(); 
       EmpresaServicios es =new EmpresaServicios();
       SucursalServicios su = new SucursalServicios();
       AsignacionCarteraServicios ac= new AsignacionCarteraServicios();
       ConsultaxCarteraServicios cd = new ConsultaxCarteraServicios();
       CampaniasServicios camp = new CampaniasServicios();
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
           request.getRequestDispatcher("sistema/campanias/CrearCampania.jsp").forward(request, response);

        
        }  
        if(accion.equals("consultar"))
        {    
           String Campanias="";
           if(camp.getCampanias(EmpresaID, SucursalID)==null){
           Campanias = "{\"data\":[]}";
           }   else{
               Campanias = "{\"data\": "+camp.getCampanias(EmpresaID, SucursalID)+"}";
           }
           
              response.getWriter().println(Campanias);
        
        }  
           if(accion.equals("eliminar")){
               int  id_campania=0;
               if(request.getParameter("id_campania").isEmpty()){
                response.getWriter().println("El id_campania esta vacia");
               }               
               id_campania=Integer.parseInt(request.getParameter("id_campania")); 
               
               if(camp.eliminarCampania(id_campania)){
                    response.getWriter().println("La campania fue eliminada exitosamente");
               }else{
                    response.getWriter().println("Problemas al eliminar la campaña, favor comuniquese con Dpto. Sistemas.");
               }
        
        
        
        
        }
           if(accion.equals("nueva_campania")){
               int  id_campania=0;
               //descripcion":descripcion, "fecha_desde":fecha_desde, "fecha_hasta
               String descripcion="",fecha_desde="",fecha_hasta="";
               
               
               if(request.getParameter("descripcion").isEmpty()){
                response.getWriter().println("Debe Ingresar la Descripción");
               }   
                if(request.getParameter("fecha_desde").isEmpty()){
                response.getWriter().println("Debe Ingresar la fecha de inicio de la Campaña");
               } 
                 if(request.getParameter("fecha_hasta").isEmpty()){
                 response.getWriter().println("Debe Ingresar la fecha de fin de la Campaña");
               } 
                 descripcion=request.getParameter("descripcion");
                 fecha_desde=request.getParameter("fecha_desde");
                 fecha_hasta=request.getParameter("fecha_hasta");
                 
                 id_campania =camp.AddCampania(descripcion, fecha_desde, fecha_hasta, EmpresaID, SucursalID);
                 
               
               if(id_campania!=0){
                    response.getWriter().println("La campania fue eliminada exitosamente");
               }else{
                    response.getWriter().println("Problemas al crear la campaña, favor comuniquese con Dpto. Sistemas.");
               }
        
        
        
        
        }
           
           if(accion.equals("consulta_det_campania")){
               int  id_campania=0;
                id_campania=Integer.parseInt(request.getParameter("id_campania")); 
                              
                String Campanias="";
                Campanias=camp.getDetCampanias(id_campania);
                
           if(Campanias==null){
           Campanias = "{\"data\":[]}";
           }   else{
               Campanias = "{\"data\": "+Campanias+"}";
           }
           
              response.getWriter().println(Campanias);
        
        
        
        }
           
           
        if(accion.equals("eliminar_det_campania")){
               int  id_det_campania=0;
               if(request.getParameter("id_det_campania").isEmpty()){
                response.getWriter().println("No existe ID del registro para eliminar");
               }               
               id_det_campania=Integer.parseInt(request.getParameter("id_det_campania")); 
               
               if(camp.eliminarDetCampania(id_det_campania)){
                    response.getWriter().println("El registro fue eliminadio exitosamente");
               }else{
                    response.getWriter().println("Problemas al eliminar el registro, favor comuniquese con Dpto. Sistemas.");
               }
        
        
        
        
        }  
        
        if(accion.equals("agregar_detalle_campania")){
               int  id_campania=0;
               String sqlQuery="";
               if(request.getParameter("id_campania").isEmpty()){
                response.getWriter().println("No existe ID del registro para eliminar");
               }               
               id_campania=Integer.parseInt(request.getParameter("id_campania")); 
               
               sqlQuery=request.getParameter("sqlQuery");
               sqlQuery=sqlQuery.replaceAll(" IDCAMPANIA", Integer.toString(id_campania));
               sqlQuery=sqlQuery.replaceAll("'","''");
               
               
            if(camp.CreaDetCampania(sqlQuery, id_campania)==1){
             response.getWriter().println("Los registros fueron agregados exitosamente");
            }else{
             response.getWriter().println("Problemas al registrar la campaña, favor comuniquese con Dpto. Sistemas.");

            }
     
        
        
        
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
        
        
                if(accion.equals("agregar_empleado_campania")){
                     int  id_campania=0, id_empleado=0,resultado=0;
                     String nombres="";
                     
                     
                     if(request.getParameter("id_campania").isEmpty()){
                        response.getWriter().println("No existe ID campaña");
                       } 
                     if(request.getParameter("id_empleado").isEmpty()){
                        response.getWriter().println("Debe ingresar un empleado existente");
                       } 
                     id_campania=Integer.parseInt(request.getParameter("id_campania")); 
                     id_empleado=Integer.parseInt(request.getParameter("id_empleado")); 
                     nombres=request.getParameter("nombres"); 
                     resultado=camp.AgregarEmpleadoCampania(id_campania, id_empleado, nombres);
                     if(resultado==1){
                        response.getWriter().println("Empleado Agregado exitosamente");
                     }
                     
                     if(resultado==2){
                        response.getWriter().println("El empleado ya esta agregado para esta campaña");
                     }
                     if(resultado!=2 && resultado!=1  ){
                            response.getWriter().println("Problemas al registrar el empleado en esta campaña, favor comuniquese con Dpto. Sistemas.");
                     }
                 
            }
                if(accion.equals("all_empleado_campania")){
                     int  id_campania=0, id_empleado=0,resultado=0;
                    
                     
                     
                     if(request.getParameter("id_campania").isEmpty()){
                        response.getWriter().println("No existe ID campaña");
                       } 
                   
                     id_campania=Integer.parseInt(request.getParameter("id_campania")); 
                     
                      String empleadoCampanias="";
                empleadoCampanias=camp.AllEmpleadoCampania(id_campania);
                
           if(empleadoCampanias==null){
           empleadoCampanias = "{\"data\":[]}";
           }   else{
               empleadoCampanias = "{\"data\": "+empleadoCampanias+"}";
           }
           
              response.getWriter().println(empleadoCampanias);
                 
            }
          if(accion.equals("eliminar_empleado_campania")){
               int  id_empleado_campania=0;
               if(request.getParameter("id_empleado_campania").isEmpty()){
                response.getWriter().println("No existe ID del registro para eliminar");
               }               
               id_empleado_campania=Integer.parseInt(request.getParameter("id_empleado_campania")); 
               
               if(camp.eliminaEmpleadoCampania(id_empleado_campania)){
                    response.getWriter().println("El registro fue eliminadio exitosamente");
               }else{
                    response.getWriter().println("Problemas al eliminar el registro, favor comuniquese con Dpto. Sistemas.");
               }
        
        
        
        
        } 
          //procesar_asignación
          if(accion.equals("procesar_asignación")){
               int  id_campania=0;
               String query="", resultado="";
               if(request.getParameter("id_campania").isEmpty()){
                response.getWriter().println("No existe ID del registro para eliminar");
               }               
               id_campania=Integer.parseInt(request.getParameter("id_campania")); 
               query=request.getParameter("query");
               query=query.replaceAll("'","''");
               resultado=camp.Procesar_Asignación(id_campania,query);
               if(resultado.equals("Proceso Exitoso")){
                    response.getWriter().println("Procesado exitosamente");
               }else{
                    response.getWriter().println("Problemas al procesar, favor comuniquese con Dpto. Sistemas.");
               }
        
        
        
        
        }
          
          if(accion.equals("all_empleado_asig_campania")){
                     int  id_campania=0, id_empleado=0,resultado=0;
                    
                     
                     
                     if(request.getParameter("id_campania").isEmpty()){
                        response.getWriter().println("No existe ID campaña");
                       } 
                   
                     id_campania=Integer.parseInt(request.getParameter("id_campania")); 
                     
                      String empleadoCampanias="";
                empleadoCampanias=camp.AllEmpleadoAsigCampania(id_campania);
                
           if(empleadoCampanias==null){
           empleadoCampanias = "{\"data\":[]}";
           }   else{
               empleadoCampanias = "{\"data\": "+empleadoCampanias+"}";
           }
           
              response.getWriter().println(empleadoCampanias);
                 
            }
          
            if(accion.equals("ConsultaDatosCampania")){
                     int  id_campania=0, id_empleado=0;
                     
                    
                     
                     
                     if(request.getParameter("id_campania").isEmpty()){
                        response.getWriter().println("No existe ID campaña");
                       } 
                   
                     id_campania=Integer.parseInt(request.getParameter("id_campania")); 
                     
                      String datos="";
                datos=camp.DatosCampania(id_campania);
                
           if(datos==null){
           datos = "{\"data\":[]}";
           }   
           
              response.getWriter().println(datos);
                 
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
