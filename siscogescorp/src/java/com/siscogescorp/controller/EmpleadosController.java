/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.controller;

import com.siscogescorp.modelo.LcAgencia;
import com.siscogescorp.modelo.LcCargos;
import com.siscogescorp.modelo.LcEmpleados;
import com.siscogescorp.modelo.LcEmpresa;
import com.siscogescorp.modelo.LcEstadoCivil;
import com.siscogescorp.modelo.LcGenero;
import com.siscogescorp.modelo.LcTiposIdentificacion;
import com.siscogescorp.servicios.AgenciaServicios;
import com.siscogescorp.servicios.CargosServicios;
import com.siscogescorp.servicios.EmpleadosServicios;
import com.siscogescorp.servicios.EmpresaServicios;
import com.siscogescorp.servicios.ParametrosServicios;
import com.siscogescorp.servicios.SucursalServicios;
import java.io.IOException;
import java.text.ParseException;
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

/**
 *
 * @author CIMA2015
 */
public class EmpleadosController extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession(true);
        EmpleadosServicios emp = new EmpleadosServicios();
        EmpresaServicios es =new EmpresaServicios();
        CargosServicios cs = new CargosServicios();
         AgenciaServicios ag = new AgenciaServicios();
        SucursalServicios su = new SucursalServicios();
        ParametrosServicios param = new ParametrosServicios();
        String accion,id_rol_empleado;
        accion= request.getParameter("accion");
        String id_empresas,id_empleados;    
        String nivel;
        nivel = sesion.getAttribute("SstrNivelUser").toString();
        int NivelID = Integer.parseInt(nivel);        
        id_empresas = sesion.getAttribute("Sstrempresa").toString();
        int EmpresaID = Integer.parseInt(id_empresas);
        String id_rol;
        id_rol = sesion.getAttribute("SstrRolID").toString();
        int RolID = Integer.parseInt(id_rol); 
        int accesoROLES= Integer.parseInt(sesion.getAttribute("NivelAccesoRolID").toString());
        id_rol_empleado = sesion.getAttribute("SstrUsuarioRol").toString();
       int RolEmpleado= Integer.parseInt(id_rol_empleado);
       id_empleados = sesion.getAttribute("Sstrempleado").toString();
       int EmpleadoID= Integer.parseInt(id_empleados);

      
        
        if (accion.equals("listar")) {
            if (accesoROLES == 1) {
                List<LcEmpleados> empleados = emp.getLcEmpleadoss();
                request.setAttribute("empleados", empleados);

                List<LcEmpleados> Activos = emp.getLcEmpleadosActivo();
                request.setAttribute("Activos", Activos);

                List<LcEmpleados> Inactivo = emp.getLcEmplInactivo();
                request.setAttribute("Inactivo", Inactivo);
            }else{

                List<LcEmpleados> empleados = emp.getEmpleadoxempresa(EmpresaID);
                request.setAttribute("empleados", empleados);

                List<LcEmpleados> Activos = emp.getEmpleadoxempresaAct(EmpresaID);
                request.setAttribute("Activos", Activos);

                List<LcEmpleados> Inactivo = emp.getEmpleadoxempresaInact(EmpresaID);
                request.setAttribute("Inactivo", Inactivo);
            }/*
            if ((NivelID != 0) && (NivelID != 1)) {
                List<LcEmpleados> empleados = emp.getEmpleadoxempresa(EmpresaID);
                request.setAttribute("empleados", empleados);

                List<LcEmpleados> Activos = emp.getEmpleadoxempresaAct(EmpresaID);
                request.setAttribute("Activos", Activos);

                List<LcEmpleados> Inactivo = emp.getEmpleadoxempresaInact(EmpresaID);
                request.setAttribute("Inactivo", Inactivo);
            }*/
        request.getRequestDispatcher("sistema/empleados/lista_empleados.jsp").forward(request, response);
        
        }
        if(accion.equals("agregar"))
        {
            
            /*if (NivelID == 0) {
                ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
                request.setAttribute("empresas", empresas);
            }
            if (NivelID == 1) {
                List<LcRoles> empresaso = es.getLcEmpresaRol(EmpresaID, RolID);
                int Empresa = empresaso.get(0).getLcEmpresa().getIdEmpresa();
                ArrayList<LcEmpresa> empresao = es.getLcEmpresalog(Empresa);
                request.setAttribute("empresao", empresao);
            }
            if ((NivelID != 0) && (NivelID != 1)) {
                List<LcRoles> empresaso = es.getLcEmpresaRol(EmpresaID, RolID);
                int Empresa = empresaso.get(0).getLcEmpresa().getIdEmpresa();
                ArrayList<LcEmpresa> empresao = es.getLcEmpresalog(Empresa);
                request.setAttribute("empresao", empresao);
            }
            */
            List<LcEmpresa> empresas = es.getLcEmpresaRolSuper(EmpresaID,accesoROLES);       
            request.setAttribute("empresas", empresas); 
       
           /* if (accesoROLES == 0) {
                ArrayList<LcCargos> cargos = cs.getLcCargos();
                request.setAttribute("cargos", cargos);
            }else{*/
                List<LcCargos> cargos = cs.getLcCargosxEmpresa(EmpresaID);
                request.setAttribute("cargos", cargos);
            //}
            /*if ((NivelID != 0) && (NivelID != 1)) {
                List<LcCargos> cargos = cs.getLcCargosxEmpresa(EmpresaID);
                request.setAttribute("cargos", cargos);
            }
            
            ArrayList<LcSucursal> sucursales = su.getLcSucursal();
            request.setAttribute("sucursales", sucursales);*/
            
            List<LcAgencia> agencias = ag.getLcAgenciasxEmpresa(EmpresaID);
            request.setAttribute("agencias", agencias);

            ArrayList<LcGenero> generos = emp.getLcEmpGenero();
            request.setAttribute("generos", generos);

            ArrayList<LcEstadoCivil> estacivil = emp.getLcEmpcivil();
            request.setAttribute("estacivil", estacivil);

            ArrayList<LcTiposIdentificacion> tipIDE = emp.getLcEmpTipIDE();
            request.setAttribute("tipIDE", tipIDE);

            
            request.getRequestDispatcher("sistema/empleados/frm_empleados.jsp").forward(request, response);
        }
        if(accion.equals("registrar"))
        {
            
        String strDate=request.getParameter("fecha_nac");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_nac = formatter.parse(strDate);
        Date fecha_reg = new Date();
        
         //int empresa2 = Integer.parseInt(request.getParameter("empresa2"));
         int  empresa=Integer.parseInt(request.getParameter("empresa"));
         int  t_identificacion=Integer.parseInt(request.getParameter("t_identificacion"));
         String identificacion=request.getParameter("identificacion");
         String nombres=request.getParameter("nombres");
         String apellidos=request.getParameter("apellidos");
         String lugar_nac=request.getParameter("lugar_nac");
        // Date fecha_nac=  request.getParameter("fecha_nac");
         String email=request.getParameter("email");
         String telefono=request.getParameter("telefono");
         String celular=request.getParameter("celular");        
         int est_civil=Integer.parseInt(request.getParameter("est_civil"));
         int genero=Integer.parseInt(request.getParameter("genero"));
         String profesion=request.getParameter("profesion");
         int cargo=Integer.parseInt(request.getParameter("cargo"));
         int jefe_directo=Integer.parseInt(request.getParameter("jefe_directo"));
         String dir_domicilio=request.getParameter("dir_domicilio");
         String observacion=request.getParameter("observacion");
         int agencias=Integer.parseInt(request.getParameter("sucursal"));
         ArrayList<LcEmpleados> empleados = emp.getLCEmpleados();
         int id_empleado;
            /*if(empleados.isEmpty()){
             id_empleado = 1;
            }else {
                //int secuencia = emp.SecuenciaModulo();
                int secuencia = Integer.parseInt(emp.getNext().toString());
                id_empleado=secuencia;
            }*/

            //if (empresa == 0) {
                ArrayList<LcEmpleados> encuentra = emp.getDatoEncontrado(empresa, identificacion);

                if (encuentra.isEmpty()) {
                
                id_empleado=Integer.parseInt(emp.getNext().toString());
                
                    emp.addEmpleado(new LcEmpleados(id_empleado,
                            (new LcAgencia(agencias)),
                            (new LcCargos(cargo)),
                            (new LcEmpresa(empresa)),
                            (new LcEstadoCivil(est_civil)),
                            (new LcGenero(genero)),
                           // (new LcSucursal(sucursal)),
                            (new LcTiposIdentificacion(t_identificacion)),
                            identificacion,
                            nombres.toUpperCase(),
                            apellidos.toUpperCase(),
                            lugar_nac.toUpperCase(),
                            fecha_nac, email, telefono,
                            celular, dir_domicilio, profesion.toUpperCase(),
                            jefe_directo, observacion.toUpperCase(),
                            fecha_reg, fecha_reg, "A", null, null, null,null,null,null));
                    
                        response.getWriter().println("Nuevo Empleado Registrado");
                    
                    
                    
                    
                }else{
                 response.getWriter().println("Ya se encuentra un Registrado el Empleado");
                }
          
       
        }
        
        if(accion.equals("inactivar"))
        {
            
        //EmpleadosServicios emp = new EmpleadosServicios();
        ArrayList<LcEmpleados> empleados = emp.getLCEmpleados();
        request.setAttribute("empleados", empleados);
            
            request.getRequestDispatcher("sistema/empleados/lista_empleados.jsp").forward(request, response);
        //request.getContextPath()+
        }
        
        if(accion.equals("buscaID")){

            int id= Integer.parseInt(request.getParameter("id"));
            
            if(id!=0){
               
                List<LcEmpleados> emplea = emp.getDatosLCEmpleadosID(id);
                request.setAttribute("emplea", emplea);
                
                if(EmpresaID==1){
                ArrayList<LcEmpresa> empresas = es.getLcEmpresa();
                request.setAttribute("empresas", empresas); 
                }else{

                ArrayList<LcEmpresa> empresas = es.getLcEmpresalog(EmpresaID);
                request.setAttribute("empresas", empresas); 
                }
                //ArrayList<LcSucursal> sucursales = su.getLcSucursal();
                //request.setAttribute("sucursales", sucursales);
                List<LcAgencia> agencias = ag.getLcAgenciasxEmpresa(EmpresaID);
                request.setAttribute("agencias", agencias);
                
                ArrayList<LcCargos> cargos = cs.getLcCargos();
                request.setAttribute("cargos", cargos);   

                ArrayList<LcEmpleados> empleados = emp.getLCEmpleados();
                request.setAttribute("empleados", empleados);
                
                ArrayList<LcGenero> generos = emp.getLcEmpGenero();
                request.setAttribute("generos", generos);

                ArrayList<LcEstadoCivil> estacivil = emp.getLcEmpcivil();
                request.setAttribute("estacivil", estacivil);

                ArrayList<LcTiposIdentificacion> tipIDE = emp.getLcEmpTipIDE();
                request.setAttribute("tipIDE", tipIDE);
                //se envia los datos al formulario para actualizar
                request.getRequestDispatcher("sistema/empleados/frm_empleados_up.jsp").forward(request, response);
                
                
            }else{
                 response.getWriter().println("No existen datos a consultar");
                 
            }
                    
         }
        if(accion.equals("editar")){
            String strDate=request.getParameter("fecha_nac");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha_nac = formatter.parse(strDate);
            
                int idempleado = Integer.parseInt(request.getParameter("idempleado"));
                int  empresa=Integer.parseInt(request.getParameter("empresa"));
                int  t_identificacion=Integer.parseInt(request.getParameter("t_identificacion"));
                String identificacion=request.getParameter("identificacion");
                String nombres=request.getParameter("nombres");
                String apellidos=request.getParameter("apellidos");
                String lugar_nac=request.getParameter("lugar_nac");
                int agencia=Integer.parseInt(request.getParameter("sucursal"));
                String email=request.getParameter("email");
                String telefono=request.getParameter("telefono");
                String celular=request.getParameter("celular");        
                int est_civil=Integer.parseInt(request.getParameter("est_civil"));
                int genero=Integer.parseInt(request.getParameter("genero"));
                String profesion=request.getParameter("profesion");
                int cargo=Integer.parseInt(request.getParameter("cargo"));
                int jefe_directo=Integer.parseInt(request.getParameter("jefe_directo"));
                String dir_domicilio=request.getParameter("dir_domicilio");
                String observacion=request.getParameter("observacion"); 
                
                emp.updateEmpleados(idempleado, empresa, t_identificacion, identificacion, nombres, apellidos, lugar_nac, fecha_nac, email, telefono, celular, dir_domicilio, est_civil, genero, profesion, cargo, jefe_directo, observacion,agencia);
                response.getWriter().println("Registro de Empleados Actualizado");
               
    }
        if(accion.equals("eliminar")){

                int id= Integer.parseInt(request.getParameter("id"));
                emp.deleteEmpleado(id);
             //response.getWriter().println("Zona Eliminada");
         }
         if(accion.equals("MisEmpleados")){
              String empleados="";
              
             if(param.permisos_admin(id_rol_empleado)){
                            empleados = "{\"listaEmpleados\": "+emp.fnc_ConsultaEmpleados("select distinct(e.id_empleado) as id_empleado, e.apellidos||'' ''||e.nombres as empleado  from lc_usuarios u, lc_empleados e\n" +
"where e.id_empleado=u.id_empleado\n" +
"and e.id_empresa=u.id_empresa\n" +
"and u.id_rol in (select r.id_rol from lc_roles r where r.descripcion in (select valor from lc_parametros p where p.id_parametro=2020))\n" +
"and e.id_empresa=1\n" +
"and u.id_empresa=1\n" +
"and u.estado=''A''\n" +
"and e.estado=''A''\n" +
"order by empleado")+"}";
              }else{
                  System.out.println("select id_empleado, nombres||'' ''||apellidos as empleado from lc_empleados where id_empleado="+EmpleadoID+" '");
                             empleados = "{\"listaEmpleados\": "+emp.fnc_ConsultaEmpleados("select distinct(e.id_empleado) as id_empleado, e.nombres||'' ''||e.apellidos as empleado  from lc_usuarios u, lc_empleados e\n" +
"where e.id_empleado=u.id_empleado\n" +
"and e.id_empresa=u.id_empresa\n" +
"and u.id_rol in (select r.id_rol from lc_roles r where r.descripcion in (select valor from lc_parametros p where p.id_parametro=2020))\n" +
"and e.id_empresa=1\n" +
"and u.id_empresa=1\n" +
"and u.estado=''A''\n" +
"and e.estado=''A'' and e.id_empleado="+EmpleadoID)+"}";

              }
         response.getWriter().println(empleados);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
