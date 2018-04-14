<%-- 
    Document   : combo_rol
    Created on : 03/04/2017, 10:27:27 PM
    Author     : ViewSoft
--%>

<%@page import="com.siscogescorp.servicios.EmpresaServicios"%>
<%@page import="com.siscogescorp.modelo.LcProvincia"%>
<%@page import="java.util.List"%>
<%@page import="com.siscogescorp.servicios.RolesOperaciones"%>
<%@page import="com.siscogescorp.modelo.LcRoles"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
   <select id="rol" class="form-control" name="rol" disabled="true">
   <option value=''>Seleccionar Rol</option>
<%
  HttpSession sesion = request.getSession(true);
  RolesOperaciones ro = new RolesOperaciones();  
  int empresa = Integer.parseInt(request.getParameter("empresa"));  
String nivel;
        nivel = sesion.getAttribute("SstrNivelUser").toString();
        int NivelID = Integer.parseInt(nivel);
        
  
  
  
  ArrayList<LcRoles> roless = ro.getRolesEmpresa(empresa);
  if(!roless.isEmpty()){
   for(int i=0; i< roless.size(); i++) {
        if(NivelID==1){
        %>
               <option value="<%=roless.get(i).getIdRol() %>"><%=roless.get(i).getDescripcion() %></option>   
              <%
        }else{
          if(roless.get(i).getNivel() == NivelID ){

              %>
               <option value="<%=roless.get(i).getIdRol() %>"><%=roless.get(i).getDescripcion() %></option>   
              <%

              }
        }
      
   }
   }else{ response.getWriter().println("Empresa no tiene Usuarios con Roles asignados");}
%>

</select>

