/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.utils.Conexion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author CIMA2015
 */
public class MisReportes extends HttpServlet {

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
        HttpSession sesion = request.getSession(true);
        String id_empresas;  
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID = Integer.parseInt(id_empresas);
        String accion="";
        Conexion conexion=new Conexion();
          accion= request.getParameter("accion");
          if(accion.equals("CuadreCaja")){
              
              /*
           double pn_efectivo,pn_banco,pn_tcredito,pn_total;
            pn_efectivo=Double.parseDouble(request.getParameter("valor_efec"));
            pn_banco=Double.parseDouble(request.getParameter("valor_ch"));
            pn_tcredito=Double.parseDouble(request.getParameter("valor_tar"));
            pn_total=Double.parseDouble(request.getParameter("valor_inpt"));
             
            JasperPrint jasperPrint;                
            try
            {
              //se carga el reporte
             // URL  in=this.getClass().getResource( "D:/Proyecto/laticobsa/web/sistema/reportes/cuadredecaja.jasper" );
              //JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("D:\\Proyecto\\laticobsa\\web\\sistema\\reportes\\cuadredecaja.jasper");
        
           //   jasperReport=(JasperReport)JRLoader.loadObject(in);
              //se procesa el archivo jasper
              Map parameters = new HashMap();
              int pn_empresa;
              pn_empresa=1;
                parameters.put("pn_empresa", pn_empresa); 
                parameters.put("pn_efectivo", pn_efectivo); 
                parameters.put("pn_banco", pn_banco); 
                parameters.put("pn_tcredito", pn_tcredito); 
                parameters.put("pn_total", pn_total); 
               

                JasperReport report = JasperCompileManager.compileReport("D:\\Proyecto\\laticobsa\\web\\sistema\\reportes\\cuadredecaja.jrxml");
                JasperPrint print = JasperFillManager.fillReport(report, parameters, conexion.getConexion());
                // Exporta el informe a PDF
                JasperExportManager.exportReportToPdfFile(print,"D:/temporal/reporte.pdf");
                
                /*
                
              //jasperPrint = JasperFillManager.fillReport(reporte, parameters, conexion.getConexion() );
              jasperPrint = JasperFillManager.fillReport(reporte, parameters, conexion.getConexion());
              JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
            exporter.exportReport();
 
              //se crea el archivo PDF
              JasperExportManager.exportReportToPdfFile( jasperPrint, "D:/temporal/reporte.pdf");
            }
            catch (JRException ex)
            {
              System.err.println( "Error iReport: " + ex.getMessage() );
            }
*/
            
              
               request.getRequestDispatcher("sistema/caja/reporte_cuadre_caja.jsp").forward(request, response);
        }
        if(accion.equals("genera_doc_masivos")){
    
             // request.getRequestDispatcher("sistema/terrenos/report_doc_masivos.jsp").forward(request, response);
              request.getRequestDispatcher("sistema/terrenos/report_doc_masivos.jsp").forward(request, response);
              //request.getRequestDispatcher("sistema/terrenos/report_doc_masivos.jsp").forward(request, response);//despachamos la informacion en el jsp
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