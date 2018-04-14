/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcAgencia;
import com.siscogescorp.modelo.LcClientes;
import com.siscogescorp.modelo.LcDocumentos;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.servicios.ConsultaxCarteraServicios;
import com.siscogescorp.servicios.DocumentosServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.utils.ArchivoLog;
import java.io.IOException;
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
public class DocumentosController extends HttpServlet {

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
     
        String accion;//declaracion de variable tipo texto
        accion= request.getParameter("accion");//obtenemos el valor de un parametro que se llama accion
        HttpSession session = request.getSession(true);
        DocumentosServicios doc = new DocumentosServicios();
        EmpresaServicios es =new EmpresaServicios();
        ConsultaxCarteraServicios cd = new ConsultaxCarteraServicios();
        
        String id_empleados;
	ArchivoLog grb = new ArchivoLog();
        HttpSession sesion = request.getSession(true);
        id_empleados = sesion.getAttribute("Sstrempleado").toString();
        int EmpleadoID = Integer.parseInt(id_empleados);
            if(accion.equals("listar"))
             {
               try{
               List<LcDocumentos> documentos = doc.getLcDocumentos();//invocamos al metodo
               request.setAttribute("documentos", documentos);  //seteamos los atributos del metodo
               request.getRequestDispatcher("sistema/terrenos/frm_lista_documento.jsp").forward(request, response);//despachamos la informacion en el jsp
               }catch(Exception ex){
                try{grb.grabaLog("DocumentosController_listar Documentos Error Java: "+ex.getMessage());}catch(Exception e){}   
               }
            }
      
            if(accion.equals("crear"))
             {
                try{
                ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
                request.setAttribute("empresas", empresas);
               request.getRequestDispatcher("sistema/terrenos/frm_crear_cartas.jsp").forward(request, response);//despachamos la informacion en el jsp
                }catch(Exception ex){
                try{grb.grabaLog("DocumentosController_crear Documentos Error Java: "+ex.getMessage());}catch(Exception e){}   
               }
            }
            if(accion.equals("GuardaDoc")){
                try{
                int  empresa=Integer.parseInt(request.getParameter("empresa"));
                int  sucursal=Integer.parseInt(request.getParameter("sucursal"));
                int  cartera=Integer.parseInt(request.getParameter("cartera"));
                String nombre_doc=request.getParameter("nombre_doc");
                String saludo=request.getParameter("saludo");
                String cuerpo=request.getParameter("cuerpo");
                String despedida=request.getParameter("despedida");
                String firma=request.getParameter("firma");
                Date fecha_reg = new Date();
                int idDocumento=Integer.parseInt(doc.getNext().toString()); 
                doc.addDocumentos(new LcDocumentos
                                (idDocumento,
                                 empresa,
                                 sucursal,       
                                 cartera,
                                 EmpleadoID,
                                 nombre_doc,
                                 saludo,
                                 cuerpo,
                                 despedida,
                                 firma,       
                                 fecha_reg, 
                                 fecha_reg,"A"));
                response.getWriter().println("Documento Ingresado");
                }catch(Exception ex){
                try{grb.grabaLog("DocumentosController_GuardaDoc Documentos Error Java: "+ex.getMessage());}catch(Exception e){}   
               }
            }
            if(accion.equals("ObtenerData")){
                try{
                int  idDoc=Integer.parseInt(request.getParameter("idDoc"));
                List<LcDocumentos> documento = doc.ConsultaDataDoc(idDoc);
                int idempresa= documento.get(0).getIdEmpresa();
                List<LcClientes> cartera = doc.getClientesxempresa(idempresa);
                session.setAttribute("cartera", cartera);
                List<LcAgencia> agencia = doc.getLcAgenciasxEmpresa(idempresa);
                session.setAttribute("agencia", agencia);
                String resp="";
                resp="<div class=\"row form-group\">\n" +
"                        <div class=\"form-group\">\n" +
"                            <label>Nombre del Documento</label>\n" +
"                           <input type=\"text\" class=\"form-control\" style=\"display:none\" id=\"EmpresaAct\" value='"+documento.get(0).getIdEmpresa()+"'>\n" +
"                           <input type=\"text\" class=\"form-control\" style=\"display:none\" id=\"AgenciaAct\" value='"+documento.get(0).getIdAgencia()+"'>\n" +
"                           <input type=\"text\" class=\"form-control\" style=\"display:none\" id=\"ClienteAct\" value='"+documento.get(0).getIdCliente()+"'>\n" +
"                           <input type=\"text\" class=\"form-control\" style=\"display:none\" id=\"idDocAct\" value='"+documento.get(0).getIdDocumento()+"'>\n" +
"                           <input type=\"text\" class=\"form-control\" id=\"nombre_documentoAct\" value='"+documento.get(0).getNombreDocumento()+"'>\n" +
"                        </div>\n" +
"                        \n" +
"                        <div class=\"form-group\">\n" +
"                            <textarea class=\"form-control\" rows=\"3\" placeholder=\"Saludo\" id=\"saludoAct\" value='"+documento.get(0).getSaludo()+"'>"+documento.get(0).getSaludo()+"</textarea>\n" +
"                            <textarea class=\"form-control\" rows=\"5\" placeholder=\"Cuerpo\" id=\"cuerpoAct\" value='"+documento.get(0).getCuerpo()+"'>"+documento.get(0).getCuerpo()+"</textarea>\n" +
"                            <textarea class=\"form-control\" rows=\"3\" placeholder=\"Despedida\" id=\"despedidaAct\" value='"+documento.get(0).getDespedida()+"'>"+documento.get(0).getDespedida()+"</textarea>\n" +
"                            <textarea class=\"form-control\" rows=\"3\" placeholder=\"Firma\" id=\"firmaAct\" value='"+documento.get(0).getFirma()+"'>"+documento.get(0).getFirma()+"</textarea>\n" +
"                        </div>\n" +
"                        <div class=\" form-group\">\n" +
"                            <button type=\"button\" class=\"btn btn-primary\" onclick=\"ActualizarDoc()\">Actualizar</button>  \n" +
"                        </div>";
            response.getWriter().println(resp);
            }catch(Exception ex){
                try{grb.grabaLog("DocumentosController_ObtenerData Documentos Error Java: "+ex.getMessage());}catch(Exception e){}   
               }
            }
            if(accion.equals("ActualizarDoc")){
                int  idDoc=Integer.parseInt(request.getParameter("idDoc"));
                int  empresa=Integer.parseInt(request.getParameter("empresa"));
                int  sucursal=Integer.parseInt(request.getParameter("sucursal"));
                int  cartera=Integer.parseInt(request.getParameter("cartera"));
                String nombre_doc=request.getParameter("nombre_doc");
                String saludo=request.getParameter("saludo");
                String cuerpo=request.getParameter("cuerpo");
                String despedida=request.getParameter("despedida");
                String firma=request.getParameter("firma");
                doc.updateDocumento(idDoc, empresa, sucursal, cartera, nombre_doc, saludo, cuerpo, despedida, firma);
                response.getWriter().println("Documento Actualizado");
            }
            if(accion.equals("nuevaConsulta")){
                  int cartera = Integer.parseInt(request.getParameter("cartera")); 
                 String QueryConsulta =request.getParameter("sqlQuery");
                 String NuevosDatos="";
                 int secuancia;
                 QueryConsulta = QueryConsulta.replaceAll("IDClienteConsulta", Integer.toString(cartera));
                 QueryConsulta = QueryConsulta.replaceAll("'", "''");
                 //QueryConsulta = QueryConsulta.replaceAll("IDEmpleadoConsulta", Integer.toString(EmpleadoID));
                 
                //try {
                    sesion.setAttribute("SSqlDatosDeudor",QueryConsulta); 
                    NuevosDatos = "{\"data\": "+cd.getConsultaCartera(QueryConsulta)+"}";
                    
                //} catch (SQLException ex) {
                  //  Logger.getLogger(ConsultaxCartera.class.getName()).log(Level.SEVERE, null, ex);

                //}
                 response.getWriter().println(NuevosDatos);
           
            }
            if(accion.equals("new_ticket")){
                int IDTicket = 0;
                IDTicket=Integer.parseInt(doc.getNextTicket().toString());
                
                 response.getWriter().println(IDTicket);
           
            }
            
            if(accion.equals("registra_trama")){
                try{
                String trama =request.getParameter("trama");
                int ticket=Integer.parseInt(request.getParameter("ticket")); 
                ticket=doc.getEjecutaSQL(trama,ticket);
                 response.getWriter().println(ticket);
                }catch(Exception ex){
                try{grb.grabaLog("DocumentosController_registra_trama Documentos Error Java: "+ex.getMessage());}catch(Exception e){}   
               }
            }
             if (accion.equals("eliminar")) {
                try{
            int id = Integer.parseInt(request.getParameter("id"));
            doc.deleteDocumento(id);
            }catch(Exception ex){
                try{grb.grabaLog("DocumentosController_eliminar Documentos Error Java: "+ex.getMessage());}catch(Exception e){}   
               }
            }
            if (accion.equals("VerDoc")) {
            try{    
            int idDoc = Integer.parseInt(request.getParameter("idDoc"));
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            request.setAttribute("idDoc", idDoc); 
            request.setAttribute("idCliente", idCliente);   
            request.getRequestDispatcher("sistema/terrenos/rpt_genera_documento.jsp").forward(request, response);
            }catch(Exception ex){
                try{grb.grabaLog("DocumentosController_VerDoc Documentos Error Java: "+ex.getMessage());}catch(Exception e){}   
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
